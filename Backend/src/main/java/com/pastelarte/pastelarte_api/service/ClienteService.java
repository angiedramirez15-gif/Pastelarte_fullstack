package com.pastelarte.pastelarte_api.service;

import com.pastelarte.pastelarte_api.dto.ClienteRequestDTO;
import com.pastelarte.pastelarte_api.dto.ClienteResponseDTO;
import com.pastelarte.pastelarte_api.entities.Cliente;
import com.pastelarte.pastelarte_api.repository.ClienteRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    private final ClienteRepository repository;
    private final PasswordEncoder passwordEncoder;

    public ClienteService(ClienteRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional(readOnly = true)
    public List<ClienteResponseDTO> listar() {
        return repository.findAll()
                .stream()
                .map(this::convertirAResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ClienteResponseDTO buscar(Integer id) {
        Cliente cliente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente con ID " + id + " no encontrado."));
        return convertirAResponse(cliente);
    }

    @Transactional
    public ClienteResponseDTO guardar(ClienteRequestDTO dto) {
        Cliente cliente = new Cliente();
        cliente.setNombre(dto.getNombre());
        cliente.setCorreo(dto.getCorreo());

        // ENCRIPTACIÓN DE CONTRASEÑA
        cliente.setContrasena(passwordEncoder.encode(dto.getContrasena()));

        cliente.setDireccion(dto.getDireccion());
        cliente.setIdRol(dto.getIdRol());

        return convertirAResponse(repository.save(cliente));
    }

    @Transactional
    public ClienteResponseDTO actualizar(Integer id, ClienteRequestDTO dto) {
        Cliente cliente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente con ID " + id + " no existe."));

        cliente.setNombre(dto.getNombre());
        cliente.setCorreo(dto.getCorreo());

        // Si se envía una nueva contraseña, se encripta antes de actualizar
        if (dto.getContrasena() != null && !dto.getContrasena().isBlank()) {
            cliente.setContrasena(passwordEncoder.encode(dto.getContrasena()));
        }

        cliente.setDireccion(dto.getDireccion());
        cliente.setIdRol(dto.getIdRol());

        return convertirAResponse(repository.save(cliente));
    }

    @Transactional
    public void eliminar(Integer id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("No se puede eliminar. El cliente con ID " + id + " no existe.");
        }
        repository.deleteById(id);
    }

    // MÉTODO DE LOGIN
    @Transactional(readOnly = true)
    public ClienteResponseDTO autenticar(String correo, String contrasena) {
        Cliente cliente = repository.findByCorreo(correo)
                .orElseThrow(() -> new RuntimeException("Credenciales inválidas"));

        if (!passwordEncoder.matches(contrasena, cliente.getContrasena())) {
            throw new RuntimeException("Credenciales inválidas");
        }

        return convertirAResponse(cliente);
    }

    private ClienteResponseDTO convertirAResponse(Cliente cliente) {
        ClienteResponseDTO dto = new ClienteResponseDTO();
        dto.setIdCliente(cliente.getIdCliente());
        dto.setNombre(cliente.getNombre());
        dto.setCorreo(cliente.getCorreo());
        // NUNCA asignar contrasena aquí
        dto.setDireccion(cliente.getDireccion());
        dto.setIdRol(cliente.getIdRol());
        return dto;
    }
}