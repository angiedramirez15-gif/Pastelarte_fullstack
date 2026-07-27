// PedidoService.java
package com.pastelarte.pastelarte_api.service;

import com.pastelarte.pastelarte_api.dto.PedidoRequestDTO;
import com.pastelarte.pastelarte_api.dto.PedidoResponseDTO;
import com.pastelarte.pastelarte_api.entities.Cliente;
import com.pastelarte.pastelarte_api.entities.Pedido;
import com.pastelarte.pastelarte_api.repository.ClienteRepository;
import com.pastelarte.pastelarte_api.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    private final PedidoRepository repository;
    private final ClienteRepository clienteRepository;

    public PedidoService(PedidoRepository repository, ClienteRepository clienteRepository) {
        this.repository = repository;
        this.clienteRepository = clienteRepository;
    }

    public List<PedidoResponseDTO> listar() {
        return repository.findAll()
                .stream()
                .map(this::convertirAResponse)
                .collect(Collectors.toList());
    }

    public PedidoResponseDTO buscar(Integer id) {
        Pedido pedido = repository.findById(id).orElse(null);
        return (pedido != null) ? convertirAResponse(pedido) : null;
    }

    public PedidoResponseDTO guardar(PedidoRequestDTO dto) {
        Pedido pedido = new Pedido();

        Cliente cliente = clienteRepository.findById(dto.getIdCliente())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + dto.getIdCliente()));

        pedido.setCliente(cliente);
        pedido.setFecha(dto.getFecha());
        pedido.setEstado(dto.getEstado());
        pedido.setTotal(dto.getTotal() != null ? dto.getTotal() : BigDecimal.ZERO);
        pedido.setIdPago(dto.getIdPago());
        pedido.setComprobante(dto.getComprobante());
        pedido.setNumeroNequi(dto.getNumeroNequi());

        return convertirAResponse(repository.save(pedido));
    }

    public PedidoResponseDTO actualizar(Integer id, PedidoRequestDTO dto) {
        Pedido pedido = repository.findById(id).orElse(null);
        if (pedido == null) return null;

        Cliente cliente = clienteRepository.findById(dto.getIdCliente())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + dto.getIdCliente()));

        pedido.setCliente(cliente);
        pedido.setFecha(dto.getFecha());
        pedido.setEstado(dto.getEstado());
        pedido.setTotal(dto.getTotal());
        pedido.setIdPago(dto.getIdPago());
        pedido.setComprobante(dto.getComprobante());
        pedido.setNumeroNequi(dto.getNumeroNequi());

        return convertirAResponse(repository.save(pedido));
    }

    public PedidoResponseDTO confirmarPago(Integer id) {
        Pedido pedido = repository.findById(id).orElse(null);
        if (pedido == null) return null;

        pedido.setEstado("pagado");
        return convertirAResponse(repository.save(pedido));
    }

    public List<PedidoResponseDTO> listarPorCliente(Integer idCliente) {
        return repository.findByCliente_IdCliente(idCliente)
                .stream()
                .map(this::convertirAResponse)
                .collect(Collectors.toList());
    }

    public void eliminar(Integer id) {
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