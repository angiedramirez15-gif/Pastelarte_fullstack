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

        // 1. Buscar y validar la entidad Cliente completa
        Cliente cliente = clienteRepository.findById(dto.getIdCliente())
                .orElseThrow(() -> new RuntimeException("No se puede crear el pedido. El cliente con ID " + dto.getIdCliente() + " no existe."));

        // 2. Creación del encabezado del Pedido
        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setFecha(dto.getFecha());
        pedido.setEstado(dto.getEstado());
        pedido.setTotal(dto.getTotal());
        pedido.setIdPago(dto.getIdPago());
        pedido.setComprobante(dto.getComprobante());
        pedido.setNumeroNequi(dto.getNumeroNequi());

        Pedido pedidoGuardado = repository.save(pedido);

        // 3. Procesar y guardar cada detalle (los productos del pedido)
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

        // Eliminar primero los detalles para mantener la integridad referencial
        List<DetallePedido> detalles = detalleRepository.findByPedido_IdPedido(id);
        detalleRepository.deleteAll(detalles);

        // Eliminar la orden principal
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

        return dto;
    }
}