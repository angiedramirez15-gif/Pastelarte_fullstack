package com.pastelarte.pastelarte_api.service;

import com.pastelarte.pastelarte_api.dto.DetallePedidoRequestDTO;
import com.pastelarte.pastelarte_api.dto.PedidoRequestDTO;
import com.pastelarte.pastelarte_api.dto.PedidoResponseDTO;
import com.pastelarte.pastelarte_api.entities.Cliente;
import com.pastelarte.pastelarte_api.entities.DetallePedido;
import com.pastelarte.pastelarte_api.entities.Pedido;
import com.pastelarte.pastelarte_api.repository.ClienteRepository;
import com.pastelarte.pastelarte_api.repository.DetallePedidoRepository;
import com.pastelarte.pastelarte_api.repository.PedidoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    private final PedidoRepository repository;
    private final ClienteRepository clienteRepository;
    private final DetallePedidoRepository detalleRepository;
    private final DetallePedidoService detallePedidoService;

    public PedidoService(PedidoRepository repository,
                         ClienteRepository clienteRepository,
                         DetallePedidoRepository detalleRepository,
                         DetallePedidoService detallePedidoService) {
        this.repository = repository;
        this.clienteRepository = clienteRepository;
        this.detalleRepository = detalleRepository;
        this.detallePedidoService = detallePedidoService;
    }

    @Transactional(readOnly = true)
    public List<PedidoResponseDTO> listar() {
        return repository.findAll()
                .stream()
                .map(this::convertirAResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PedidoResponseDTO buscar(Integer id) {
        Pedido pedido = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("El pedido con ID " + id + " no fue encontrado."));

        return convertirAResponse(pedido);
    }

    @Transactional
    public PedidoResponseDTO guardar(PedidoRequestDTO dto) {
        return guardarConArchivo(dto, null);
    }

    @Transactional
    public PedidoResponseDTO guardarConArchivo(PedidoRequestDTO dto, MultipartFile archivoComprobante) {

        // 1. Buscar y validar el Cliente
        Cliente cliente = clienteRepository.findById(dto.getIdCliente())
                .orElseThrow(() -> new RuntimeException("No se puede crear el pedido. El cliente con ID " + dto.getIdCliente() + " no existe."));

        // 2. Procesar el archivo del comprobante si viene adjunto
        String nombreArchivoComprobante = dto.getComprobante();

        if (archivoComprobante != null && !archivoComprobante.isEmpty()) {
            try {
                // Definimos el directorio usando la API Path para evitar fallos de separador de SO (/ vs \)
                Path directorioComprobantes = Paths.get("uploads", "comprobantes");

                // Garantiza que la carpeta exista en el equipo de quien ejecute el proyecto
                if (!Files.exists(directorioComprobantes)) {
                    Files.createDirectories(directorioComprobantes);
                }

                // Generar un nombre de archivo único utilizando el timestamp
                nombreArchivoComprobante = System.currentTimeMillis() + "_" + archivoComprobante.getOriginalFilename();

                // Construir la ruta final del archivo limpiamente
                Path rutaDestino = directorioComprobantes.resolve(nombreArchivoComprobante);

                Files.copy(archivoComprobante.getInputStream(), rutaDestino, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new RuntimeException("Error al guardar el archivo del comprobante: " + e.getMessage());
            }
        }

        // 3. Creación del encabezado del Pedido
        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setFecha(dto.getFecha());
        pedido.setEstado(dto.getEstado());
        pedido.setTotal(dto.getTotal());
        pedido.setIdPago(dto.getIdPago());
        pedido.setComprobante(nombreArchivoComprobante); // Solo guarda el nombre único del archivo
        pedido.setNumeroNequi(dto.getNumeroNequi());

        Pedido pedidoGuardado = repository.save(pedido);

        // 4. Procesar y guardar cada detalle
        if (dto.getDetalles() != null && !dto.getDetalles().isEmpty()) {
            for (DetallePedidoRequestDTO detalleDTO : dto.getDetalles()) {
                detalleDTO.setIdPedido(pedidoGuardado.getIdPedido());
                detallePedidoService.guardar(detalleDTO);
            }
        }

        return convertirAResponse(pedidoGuardado);
    }

    @Transactional
    public PedidoResponseDTO actualizar(Integer id, PedidoRequestDTO dto) {

        Pedido pedido = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("El pedido con ID " + id + " no existe."));

        Cliente cliente = clienteRepository.findById(dto.getIdCliente())
                .orElseThrow(() -> new RuntimeException("El cliente con ID " + dto.getIdCliente() + " no existe."));

        pedido.setCliente(cliente);
        pedido.setFecha(dto.getFecha());
        pedido.setEstado(dto.getEstado());
        pedido.setTotal(dto.getTotal());
        pedido.setIdPago(dto.getIdPago());
        pedido.setComprobante(dto.getComprobante());
        pedido.setNumeroNequi(dto.getNumeroNequi());

        return convertirAResponse(repository.save(pedido));
    }

    @Transactional
    public PedidoResponseDTO confirmarPago(Integer id) {

        Pedido pedido = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("El pedido con ID " + id + " no existe."));

        pedido.setEstado("pagado");

        return convertirAResponse(repository.save(pedido));
    }

    @Transactional
    public PedidoResponseDTO rechazarPago(Integer id) {

        Pedido pedido = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("El pedido con ID " + id + " no existe."));

        pedido.setEstado("cancelado");

        boolean esNequi = pedido.getIdPago() != null && pedido.getIdPago() == 1;
        pedido.setMotivoCancelacion(esNequi
                ? "Pago rechazado — el comprobante de Nequi no fue válido."
                : "Pago rechazado por el administrador.");

        return convertirAResponse(repository.save(pedido));
    }

    @Transactional(readOnly = true)
    public List<PedidoResponseDTO> listarPorCliente(Integer idCliente) {
        return repository.findByCliente_IdCliente(idCliente)
                .stream()
                .map(this::convertirAResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public void eliminar(Integer id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("No se puede eliminar. El pedido con ID " + id + " no existe.");
        }

        List<DetallePedido> detalles = detalleRepository.findByPedido_IdPedido(id);
        detalleRepository.deleteAll(detalles);

        repository.deleteById(id);
    }

    private PedidoResponseDTO convertirAResponse(Pedido pedido) {

        PedidoResponseDTO dto = new PedidoResponseDTO();

        dto.setIdPedido(pedido.getIdPedido());

        if (pedido.getCliente() != null) {
            dto.setIdCliente(pedido.getCliente().getIdCliente());
            dto.setNombreCliente(pedido.getCliente().getNombre());
        } else {
            dto.setNombreCliente("Cliente eliminado");
        }

        dto.setFecha(pedido.getFecha());
        dto.setEstado(pedido.getEstado());
        dto.setTotal(pedido.getTotal());
        dto.setIdPago(pedido.getIdPago());
        dto.setComprobante(pedido.getComprobante());
        dto.setNumeroNequi(pedido.getNumeroNequi());
        dto.setMotivoCancelacion(pedido.getMotivoCancelacion());

        return dto;
    }
}