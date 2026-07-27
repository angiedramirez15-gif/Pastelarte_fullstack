// DetallePedidoService.java
package com.pastelarte.pastelarte_api.service;

import com.pastelarte.pastelarte_api.dto.DetallePedidoRequestDTO;
import com.pastelarte.pastelarte_api.dto.DetallePedidoResponseDTO;
import com.pastelarte.pastelarte_api.entities.DetallePedido;
import com.pastelarte.pastelarte_api.entities.Pedido;
import com.pastelarte.pastelarte_api.entities.Personalizacion;
import com.pastelarte.pastelarte_api.entities.Producto;
import com.pastelarte.pastelarte_api.repository.DetallePedidoRepository;
import com.pastelarte.pastelarte_api.repository.PedidoRepository;
import com.pastelarte.pastelarte_api.repository.PersonalizacionRepository;
import com.pastelarte.pastelarte_api.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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

    public List<DetallePedidoResponseDTO> listarPorPedido(Integer idPedido) {
        return repository.findByPedido_IdPedido(idPedido)
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
        return (detalle != null) ? convertirAResponse(detalle) : null;
    }

    public DetallePedidoResponseDTO guardar(DetallePedidoRequestDTO dto) {
        DetallePedido detalle = new DetallePedido();

        // Mapeo de Pedido
        Pedido pedido = pedidoRepository.findById(dto.getIdPedido())
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado con ID: " + dto.getIdPedido()));
        detalle.setPedido(pedido);

        // Mapeo de Producto
        Producto producto = productoRepository.findById(dto.getIdProducto())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + dto.getIdProducto()));
        detalle.setProducto(producto);

        // Mapeo de Personalización (Opcional)
        Personalizacion personalizacion = null;
        BigDecimal costoExtra = BigDecimal.ZERO;
        if (dto.getIdPersonalizacion() != null) {
            personalizacion = personalizacionRepository.findById(dto.getIdPersonalizacion()).orElse(null);
            if (personalizacion != null && personalizacion.getCostoExtra() != null) {
                costoExtra = personalizacion.getCostoExtra();
            }
        }
        detalle.setPersonalizacion(personalizacion);

        // CÁLCULO SEGURO DEL SUBTOTAL EN EL BACKEND
        // Subtotal = (Precio Producto + Costo Extra Personalización) * Cantidad
        BigDecimal precioBase = producto.getPrecio() != null ? producto.getPrecio() : BigDecimal.ZERO;
        BigDecimal precioUnitarioTotal = precioBase.add(costoExtra);
        BigDecimal subtotalCalculado = precioUnitarioTotal.multiply(BigDecimal.valueOf(dto.getCantidad()));

        detalle.setCantidad(dto.getCantidad());
        detalle.setSubtotal(subtotalCalculado);

        DetallePedido guardado = repository.save(detalle);

        // Opcional: recalcular el total del pedido principal
        actualizarTotalPedido(pedido);

        return convertirAResponse(guardado);
    }

    public DetallePedidoResponseDTO actualizar(Integer id, DetallePedidoRequestDTO dto) {
        DetallePedido detalle = repository.findById(id).orElse(null);
        if (detalle == null) return null;

        Producto producto = productoRepository.findById(dto.getIdProducto())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        detalle.setProducto(producto);

        Personalizacion personalizacion = null;
        BigDecimal costoExtra = BigDecimal.ZERO;
        if (dto.getIdPersonalizacion() != null) {
            personalizacion = personalizacionRepository.findById(dto.getIdPersonalizacion()).orElse(null);
            if (personalizacion != null && personalizacion.getCostoExtra() != null) {
                costoExtra = personalizacion.getCostoExtra();
            }
        }
        detalle.setPersonalizacion(personalizacion);

        BigDecimal precioBase = producto.getPrecio() != null ? producto.getPrecio() : BigDecimal.ZERO;
        BigDecimal precioUnitarioTotal = precioBase.add(costoExtra);
        BigDecimal subtotalCalculado = precioUnitarioTotal.multiply(BigDecimal.valueOf(dto.getCantidad()));

        detalle.setCantidad(dto.getCantidad());
        detalle.setSubtotal(subtotalCalculado);

        DetallePedido guardado = repository.save(detalle);
        actualizarTotalPedido(detalle.getPedido());

        return convertirAResponse(guardado);
    }

    public void eliminar(Integer id) {
        DetallePedido detalle = repository.findById(id).orElse(null);
        if (detalle != null) {
            Pedido pedido = detalle.getPedido();
            repository.deleteById(id);
            actualizarTotalPedido(pedido);
        }
    }

    private void actualizarTotalPedido(Pedido pedido) {
        List<DetallePedido> detalles = repository.findByPedido_IdPedido(pedido.getIdPedido());
        BigDecimal totalCalculado = detalles.stream()
                .map(d -> d.getSubtotal() != null ? d.getSubtotal() : BigDecimal.ZERO)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        pedido.setTotal(totalCalculado);
        pedidoRepository.save(pedido);
    }

    private DetallePedidoResponseDTO convertirAResponse(DetallePedido detalle) {
        DetallePedidoResponseDTO dto = new DetallePedidoResponseDTO();
        dto.setIdDetalle(detalle.getIdDetalle());
        dto.setIdPedido(detalle.getPedido() != null ? detalle.getPedido().getIdPedido() : null);
        dto.setIdProducto(detalle.getProducto() != null ? detalle.getProducto().getIdProducto() : null);
        dto.setNombreProducto(detalle.getProducto() != null ? detalle.getProducto().getNombre() : "Producto eliminado");
        dto.setIdPersonalizacion(detalle.getPersonalizacion() != null ? detalle.getPersonalizacion().getIdPersonalizacion() : null);
        dto.setCantidad(detalle.getCantidad());
        dto.setSubtotal(detalle.getSubtotal());
        return dto;
    }
}