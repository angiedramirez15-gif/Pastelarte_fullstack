package com.pastelarte.pastelarte_api.service;

import com.pastelarte.pastelarte_api.dto.PedidoRequestDTO;
import com.pastelarte.pastelarte_api.dto.PedidoResponseDTO;
import com.pastelarte.pastelarte_api.entities.Pedido;
import com.pastelarte.pastelarte_api.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    private final PedidoRepository repository;

    public PedidoService(PedidoRepository repository) {
        this.repository = repository;
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

        return convertirAResponse(repository.save(pedido));
    }

    public void eliminar(Integer id) {
        repository.deleteById(id);
    }

    private PedidoResponseDTO convertirAResponse(Pedido pedido) {

        PedidoResponseDTO dto = new PedidoResponseDTO();

        dto.setIdPedido(pedido.getIdPedido());
        dto.setIdCliente(pedido.getIdCliente());
        dto.setFecha(pedido.getFecha());
        dto.setEstado(pedido.getEstado());
        dto.setTotal(pedido.getTotal());
        dto.setIdPago(pedido.getIdPago());

        return dto;
    }
}