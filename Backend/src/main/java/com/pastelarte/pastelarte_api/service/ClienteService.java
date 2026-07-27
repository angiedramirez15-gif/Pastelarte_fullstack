// ClienteService.java
package com.pastelarte.pastelarte_api.service;

import com.pastelarte.pastelarte_api.dto.ClienteRequestDTO;
import com.pastelarte.pastelarte_api.dto.ClienteResponseDTO;
import com.pastelarte.pastelarte_api.entities.Cliente;
import com.pastelarte.pastelarte_api.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public List<ClienteResponseDTO> listar() {
        return repository.findAll()
                .stream()
                .map(this::convertirAResponse)
                .collect(Collectors.toList());
    }

    public ClienteResponseDTO buscar(Integer id) {
        Cliente cliente = repository.findById(id).orElse(null);
        return (cliente != null) ? convertirAResponse(cliente) : null;
    }

    public ClienteResponseDTO guardar(ClienteRequestDTO dto) {
        Cliente cliente = new Cliente();
        cliente.setNombre(dto.getNombre());
        cliente.setCorreo(dto.getCorreo());
        cliente.setContrasena(dto.getContrasena());
        cliente.setDireccion(dto.getDireccion());
        cliente.setIdRol(dto.getIdRol());

        return convertirAResponse(repository.save(cliente));
    }

    public ClienteResponseDTO actualizar(Integer id, ClienteRequestDTO dto) {
        Cliente cliente = repository.findById(id).orElse(null);
        if (cliente == null) return null;

        cliente.setNombre(dto.getNombre());
        cliente.setCorreo(dto.getCorreo());
        cliente.setContrasena(dto.getContrasena());
        cliente.setDireccion(dto.getDireccion());
        cliente.setIdRol(dto.getIdRol());

        return convertirAResponse(repository.save(cliente));
    }

    public void eliminar(Integer id) {
        repository.deleteById(id);
    }

    private ClienteResponseDTO convertirAResponse(Cliente cliente) {
        ClienteResponseDTO dto = new ClienteResponseDTO();
        dto.setIdCliente(cliente.getIdCliente());
        dto.setNombre(cliente.getNombre());
        dto.setCorreo(cliente.getCorreo());
        dto.setContrasena(cliente.getContrasena());
        dto.setDireccion(cliente.getDireccion());
        dto.setIdRol(cliente.getIdRol());
        return dto;
    }
}