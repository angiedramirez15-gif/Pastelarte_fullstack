package com.pastelarte.pastelarte_api.service;

import com.pastelarte.pastelarte_api.dto.DetallePedidoRequestDTO;
import com.pastelarte.pastelarte_api.dto.DetallePedidoResponseDTO;
import com.pastelarte.pastelarte_api.dto.PersonalizacionResponseDTO;
import com.pastelarte.pastelarte_api.entities.DetallePedido;
import com.pastelarte.pastelarte_api.entities.Pedido;
import com.pastelarte.pastelarte_api.entities.Personalizacion;
import com.pastelarte.pastelarte_api.entities.Producto;
import com.pastelarte.pastelarte_api.repository.DetallePedidoRepository;
import com.pastelarte.pastelarte_api.repository.PedidoRepository;
import com.pastelarte.pastelarte_api.repository.PersonalizacionRepository;
import com.pastelarte.pastelarte_api.repository.ProductoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DetallePedidoService {

    private final DetallePedidoRepository repository;
    private final PedidoRepository pedidoRepository;
    private final ProductoRepository productoRepository;
    private final PersonalizacionRepository personalizacionRepository;

    public DetallePedidoService(DetallePedidoRepository repository,
                                PedidoRepository pedidoRepository,
                                ProductoRepository productoRepository,
                                PersonalizacionRepository personalizacionRepository) {
        this.repository = repository;
        this.pedidoRepository = pedidoRepository;
        this.productoRepository = productoRepository;
        this.personalizacionRepository = personalizacionRepository;
    }

    @Transactional(readOnly = true)
    public List<DetallePedidoResponseDTO> listar() {
        return repository.findAll()
                .stream()
                .map(this::convertirAResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public DetallePedidoResponseDTO buscar(Integer id) {
        DetallePedido detalle = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("El detalle de pedido con ID " + id + " no fue encontrado."));

        return convertirAResponse(detalle);
    }

    @Transactional(readOnly = true)
    public List<DetallePedidoResponseDTO> listarPorPedido(Integer idPedido) {
        return repository.findByPedido_IdPedido(idPedido)
                .stream()
                .map(this::convertirAResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public DetallePedidoResponseDTO guardar(DetallePedidoRequestDTO dto) {

        Pedido pedido = pedidoRepository.findById(dto.getIdPedido())
                .orElseThrow(() -> new RuntimeException("No se puede crear el detalle. El pedido con ID " + dto.getIdPedido() + " no existe."));

        Producto producto = productoRepository.findById(dto.getIdProducto())
                .orElseThrow(() -> new RuntimeException("No se puede crear el detalle. El producto con ID " + dto.getIdProducto() + " no existe."));

        Personalizacion personalizacion = null;
        if (dto.getIdPersonalizacion() != null) {
            personalizacion = personalizacionRepository.findById(dto.getIdPersonalizacion())
                    .orElseThrow(() -> new RuntimeException("La personalización con ID " + dto.getIdPersonalizacion() + " no existe."));
        }

        DetallePedido detalle = new DetallePedido();
        detalle.setPedido(pedido);
        detalle.setProducto(producto);
        detalle.setPersonalizacion(personalizacion);
        detalle.setCantidad(dto.getCantidad());
        detalle.setSubtotal(dto.getSubtotal());

        return convertirAResponse(repository.save(detalle));
    }

    @Transactional
    public DetallePedidoResponseDTO actualizar(Integer id, DetallePedidoRequestDTO dto) {

        DetallePedido detalle = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("El detalle con ID " + id + " no existe."));

        Pedido pedido = pedidoRepository.findById(dto.getIdPedido())
                .orElseThrow(() -> new RuntimeException("El pedido con ID " + dto.getIdPedido() + " no existe."));

        Producto producto = productoRepository.findById(dto.getIdProducto())
                .orElseThrow(() -> new RuntimeException("El producto con ID " + dto.getIdProducto() + " no existe."));

        Personalizacion personalizacion = null;
        if (dto.getIdPersonalizacion() != null) {
            personalizacion = personalizacionRepository.findById(dto.getIdPersonalizacion())
                    .orElseThrow(() -> new RuntimeException("La personalización con ID " + dto.getIdPersonalizacion() + " no existe."));
        }

        detalle.setPedido(pedido);
        detalle.setProducto(producto);
        detalle.setPersonalizacion(personalizacion);
        detalle.setCantidad(dto.getCantidad());
        detalle.setSubtotal(dto.getSubtotal());

        return convertirAResponse(repository.save(detalle));
    }

    @Transactional
    public void eliminar(Integer id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("No se puede eliminar. El detalle con ID " + id + " no existe.");
        }
        repository.deleteById(id);
    }

    private DetallePedidoResponseDTO convertirAResponse(DetallePedido detalle) {

        DetallePedidoResponseDTO dto = new DetallePedidoResponseDTO();

        dto.setIdDetalle(detalle.getIdDetalle());

        if (detalle.getPedido() != null) {
            dto.setIdPedido(detalle.getPedido().getIdPedido());
        }

        if (detalle.getProducto() != null) {
            dto.setIdProducto(detalle.getProducto().getIdProducto());
            dto.setNombreProducto(detalle.getProducto().getNombre());
        }

        if (detalle.getPersonalizacion() != null) {
            Personalizacion p = detalle.getPersonalizacion();

            dto.setIdPersonalizacion(p.getIdPersonalizacion());

            PersonalizacionResponseDTO personalizacionDTO = new PersonalizacionResponseDTO();
            personalizacionDTO.setIdPersonalizacion(p.getIdPersonalizacion());
            personalizacionDTO.setTamano(p.getTamano());
            personalizacionDTO.setSabor(p.getSabor());
            personalizacionDTO.setDecoraciones(p.getDecoraciones());
            personalizacionDTO.setDescripcion(p.getDescripcion());
            personalizacionDTO.setCostoExtra(p.getCostoExtra());
            personalizacionDTO.setImagen(p.getImagen());

            dto.setPersonalizacion(personalizacionDTO);
        }

        dto.setCantidad(detalle.getCantidad());
        dto.setSubtotal(detalle.getSubtotal());

        return dto;
    }
}