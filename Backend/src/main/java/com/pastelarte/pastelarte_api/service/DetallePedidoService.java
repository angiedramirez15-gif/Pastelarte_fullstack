package com.pastelarte.pastelarte_api.service;

import com.pastelarte.pastelarte_api.dto.DetallePedidoRequestDTO;
import com.pastelarte.pastelarte_api.dto.DetallePedidoResponseDTO;
import com.pastelarte.pastelarte_api.entities.DetallePedido;
import com.pastelarte.pastelarte_api.entities.Producto;
import com.pastelarte.pastelarte_api.repository.DetallePedidoRepository;
import com.pastelarte.pastelarte_api.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DetallePedidoService {

    private final DetallePedidoRepository repository;
    private final ProductoRepository productoRepository;

    public DetallePedidoService(DetallePedidoRepository repository, ProductoRepository productoRepository) {
        this.repository = repository;
        this.productoRepository = productoRepository;
    }

    public List<DetallePedidoResponseDTO> listarPorPedido(Integer idPedido) {
        return repository.findByIdPedido(idPedido)
                .stream()
                .map(this::convertirAResponse)
                .collect(Collectors.toList());
    }

    public List<DetallePedidoResponseDTO> listar() {
        return repository.findAll()
                .stream()
                .map(this::convertirAResponse)
                .collect(Collectors.toList());
    }

    public DetallePedidoResponseDTO buscar(Integer id) {
        DetallePedido detalle = repository.findById(id).orElse(null);

        if (detalle == null) {
            return null;
        }

        return convertirAResponse(detalle);
    }

    public DetallePedidoResponseDTO guardar(DetallePedidoRequestDTO dto) {

        DetallePedido detalle = new DetallePedido();

        detalle.setIdPedido(dto.getIdPedido());
        detalle.setIdProducto(dto.getIdProducto());
        detalle.setIdPersonalizacion(dto.getIdPersonalizacion());
        detalle.setCantidad(dto.getCantidad());
        detalle.setSubtotal(dto.getSubtotal());

        return convertirAResponse(repository.save(detalle));
    }

    public DetallePedidoResponseDTO actualizar(Integer id, DetallePedidoRequestDTO dto) {

        DetallePedido detalle = repository.findById(id).orElse(null);

        if (detalle == null) {
            return null;
        }

        detalle.setIdPedido(dto.getIdPedido());
        detalle.setIdProducto(dto.getIdProducto());
        detalle.setIdPersonalizacion(dto.getIdPersonalizacion());
        detalle.setCantidad(dto.getCantidad());
        detalle.setSubtotal(dto.getSubtotal());

        return convertirAResponse(repository.save(detalle));
    }

    public void eliminar(Integer id) {
        repository.deleteById(id);
    }

    private DetallePedidoResponseDTO convertirAResponse(DetallePedido detalle) {

        DetallePedidoResponseDTO dto = new DetallePedidoResponseDTO();

        dto.setIdDetalle(detalle.getIdDetalle());
        dto.setIdPedido(detalle.getIdPedido());
        dto.setIdProducto(detalle.getIdProducto());

        if (detalle.getIdProducto() != null) {
            Producto producto = productoRepository.findById(detalle.getIdProducto()).orElse(null);
            dto.setNombreProducto(producto != null ? producto.getNombre() : "Producto eliminado");
        }

        dto.setIdPersonalizacion(detalle.getIdPersonalizacion());
        dto.setCantidad(detalle.getCantidad());
        dto.setSubtotal(detalle.getSubtotal());

        return dto;
    }
}