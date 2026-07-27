package com.pastelarte.pastelarte_api.service;

import com.pastelarte.pastelarte_api.dto.PedidoRequestDTO;
import com.pastelarte.pastelarte_api.dto.PedidoResponseDTO;
import com.pastelarte.pastelarte_api.entities.Cliente;
import com.pastelarte.pastelarte_api.entities.Pedido;
import com.pastelarte.pastelarte_api.repository.ClienteRepository;
import com.pastelarte.pastelarte_api.repository.PedidoRepository;
import org.springframework.stereotype.Service;

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

        if (pedido == null) {
            return null;
        }

        return convertirAResponse(pedido);
    }

    public PedidoResponseDTO guardar(PedidoRequestDTO dto) {

        Pedido pedido = new Pedido();

        pedido.setIdCliente(dto.getIdCliente());
        pedido.setFecha(dto.getFecha());
        pedido.setEstado(dto.getEstado());
        pedido.setTotal(dto.getTotal());
        pedido.setIdPago(dto.getIdPago());
        pedido.setComprobante(dto.getComprobante());
        pedido.setNumeroNequi(dto.getNumeroNequi());

        return convertirAResponse(repository.save(pedido));
    }

    public PedidoResponseDTO actualizar(Integer id, PedidoRequestDTO dto) {

        Pedido pedido = repository.findById(id).orElse(null);

        if (pedido == null) {
            return null;
        }

        pedido.setIdCliente(dto.getIdCliente());
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

        if (pedido == null) {
            return null;
        }

        pedido.setEstado("pagado");

        return convertirAResponse(repository.save(pedido));
    }
    public List<PedidoResponseDTO> listarPorCliente(Integer idCliente) {
        return repository.findByIdCliente(idCliente)
                .stream()
                .map(this::convertirAResponse)
                .toList();
    }
    public void eliminar(Integer id) {
        repository.deleteById(id);
    }

    private PedidoResponseDTO convertirAResponse(Pedido pedido) {

        PedidoResponseDTO dto = new PedidoResponseDTO();

        dto.setIdPedido(pedido.getIdPedido());
        dto.setIdCliente(pedido.getIdCliente());

        if (pedido.getIdCliente() != null) {
            Cliente cliente = clienteRepository.findById(pedido.getIdCliente()).orElse(null);
            dto.setNombreCliente(cliente != null ? cliente.getNombre() : "Cliente eliminado");
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