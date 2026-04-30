package com.pastelarte.pastelarte_api.service;

import com.pastelarte.pastelarte_api.dto.RolRequestDTO;
import com.pastelarte.pastelarte_api.dto.RolResponseDTO;
import com.pastelarte.pastelarte_api.entities.Rol;
import com.pastelarte.pastelarte_api.repository.RolRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RolService {

    private final RolRepository repository;

    public RolService(RolRepository repository) {
        this.repository = repository;
    }

    public List<RolResponseDTO> listar() {
        return repository.findAll()
                .stream()
                .map(this::convertirAResponse)
                .collect(Collectors.toList());
    }

    public RolResponseDTO buscar(Integer id) {
        Rol rol = repository.findById(id).orElse(null);

        if (rol == null) {
            return null;
        }

        return convertirAResponse(rol);
    }

    public RolResponseDTO guardar(RolRequestDTO dto) {

        Rol rol = new Rol();

        rol.setTitulo(dto.getTitulo());
        rol.setDescripcion(dto.getDescripcion());
        rol.setEstado(dto.getEstado());

        return convertirAResponse(repository.save(rol));
    }

    public RolResponseDTO actualizar(Integer id, RolRequestDTO dto) {

        Rol rol = repository.findById(id).orElse(null);

        if (rol == null) {
            return null;
        }

        rol.setTitulo(dto.getTitulo());
        rol.setDescripcion(dto.getDescripcion());
        rol.setEstado(dto.getEstado());

        return convertirAResponse(repository.save(rol));
    }

    public void eliminar(Integer id) {
        repository.deleteById(id);
    }

    private RolResponseDTO convertirAResponse(Rol rol) {

        RolResponseDTO dto = new RolResponseDTO();

        dto.setIdRol(rol.getIdRol());
        dto.setTitulo(rol.getTitulo());
        dto.setDescripcion(rol.getDescripcion());
        dto.setEstado(rol.getEstado());

        return dto;
    }
}