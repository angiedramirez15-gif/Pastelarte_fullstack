package com.pastelarte.pastelarte_api.service;

import com.pastelarte.pastelarte_api.dto.PersonalizacionRequestDTO;
import com.pastelarte.pastelarte_api.dto.PersonalizacionResponseDTO;
import com.pastelarte.pastelarte_api.entities.Personalizacion;
import com.pastelarte.pastelarte_api.repository.PersonalizacionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonalizacionService {

    private final PersonalizacionRepository repository;

    public PersonalizacionService(PersonalizacionRepository repository) {
        this.repository = repository;
    }

    public List<PersonalizacionResponseDTO> listar() {
        return repository.findAll()
                .stream()
                .map(this::convertirAResponse)
                .collect(Collectors.toList());
    }

    public PersonalizacionResponseDTO buscar(Integer id) {
        Personalizacion p = repository.findById(id).orElse(null);

        if (p == null) {
            return null;
        }

        return convertirAResponse(p);
    }

    public PersonalizacionResponseDTO guardar(PersonalizacionRequestDTO dto) {

        Personalizacion p = new Personalizacion();

        p.setTamano(dto.getTamano());
        p.setSabor(dto.getSabor());
        p.setDecoraciones(dto.getDecoraciones());
        p.setDescripcion(dto.getDescripcion());
        p.setCostoExtra(dto.getCostoExtra());

        return convertirAResponse(repository.save(p));
    }

    public PersonalizacionResponseDTO actualizar(Integer id, PersonalizacionRequestDTO dto) {

        Personalizacion p = repository.findById(id).orElse(null);

        if (p == null) {
            return null;
        }

        p.setTamano(dto.getTamano());
        p.setSabor(dto.getSabor());
        p.setDecoraciones(dto.getDecoraciones());
        p.setDescripcion(dto.getDescripcion());
        p.setCostoExtra(dto.getCostoExtra());

        return convertirAResponse(repository.save(p));
    }

    public void eliminar(Integer id) {
        repository.deleteById(id);
    }

    private PersonalizacionResponseDTO convertirAResponse(Personalizacion p) {

        PersonalizacionResponseDTO dto = new PersonalizacionResponseDTO();

        dto.setIdPersonalizacion(p.getIdPersonalizacion());
        dto.setTamano(p.getTamano());
        dto.setSabor(p.getSabor());
        dto.setDecoraciones(p.getDecoraciones());
        dto.setDescripcion(p.getDescripcion());
        dto.setCostoExtra(p.getCostoExtra());

        return dto;
    }
}