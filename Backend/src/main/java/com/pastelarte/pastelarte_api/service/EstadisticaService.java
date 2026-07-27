package com.pastelarte.pastelarte_api.service;

import com.pastelarte.pastelarte_api.entities.DetallePedido;
import com.pastelarte.pastelarte_api.entities.Pedido;
import com.pastelarte.pastelarte_api.entities.Producto;
import com.pastelarte.pastelarte_api.repository.DetallePedidoRepository;
import com.pastelarte.pastelarte_api.repository.PedidoRepository;
import com.pastelarte.pastelarte_api.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EstadisticaService {

    private final PedidoRepository pedidoRepository;
    private final DetallePedidoRepository detalleRepository;
    private final ProductoRepository productoRepository;

    public EstadisticaService(PedidoRepository pedidoRepository,
                              DetallePedidoRepository detalleRepository,
                              ProductoRepository productoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.detalleRepository = detalleRepository;
        this.productoRepository = productoRepository;
    }

    public Map<String, Object> dashboard() {

        Map<String, Object> resultado = new HashMap<>();

        // --- Rango del mes actual ---
        YearMonth mesActual = YearMonth.now();
        LocalDate inicioMes = mesActual.atDay(1);
        LocalDate finMes = mesActual.atEndOfMonth();

        List<Pedido> pedidosDelMes = pedidoRepository.findByFechaBetween(inicioMes, finMes);

        List<Integer> idsPedidosDelMes = pedidosDelMes.stream()
                .map(Pedido::getIdPedido)
                .toList();

        List<DetallePedido> detallesDelMes = idsPedidosDelMes.isEmpty()
                ? List.of()
                : detalleRepository.findByIdPedidoIn(idsPedidosDelMes);

        // --- Sumar cantidades por producto ---
        Map<Integer, Integer> cantidadPorProducto = new HashMap<>();

        for (DetallePedido detalle : detallesDelMes) {
            cantidadPorProducto.merge(
                    detalle.getIdProducto(),
                    detalle.getCantidad() == null ? 0 : detalle.getCantidad(),
                    Integer::sum
            );
        }

        Map<String, Object> productoTop = new HashMap<>();
        productoTop.put("nombre", "Sin pedidos este mes");
        productoTop.put("cantidad", 0);

        Integer idProductoTop = null;
        int maxCantidad = 0;

        for (Map.Entry<Integer, Integer> entry : cantidadPorProducto.entrySet()) {
            if (entry.getValue() > maxCantidad) {
                maxCantidad = entry.getValue();
                idProductoTop = entry.getKey();
            }
        }

        if (idProductoTop != null) {
            Producto producto = productoRepository.findById(idProductoTop).orElse(null);
            productoTop.put("nombre", producto != null ? producto.getNombre() : "Producto eliminado");
            productoTop.put("cantidad", maxCantidad);
        }

        resultado.put("productoMasVendidoMes", productoTop);

        // --- Contadores de estado (para toda la base de pedidos) ---
        resultado.put("pendientesEfectivo", pedidoRepository.countByEstado("pendiente_efectivo"));
        resultado.put("pendientesRevisionNequi", pedidoRepository.countByEstado("pendiente_revision"));
        resultado.put("pedidosPagados", pedidoRepository.countByEstado("pagado"));

        return resultado;
    }
}