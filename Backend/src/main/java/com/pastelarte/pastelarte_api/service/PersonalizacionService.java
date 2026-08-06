// PersonalizacionService.java
package com.pastelarte.pastelarte_api.service;

import com.pastelarte.pastelarte_api.dto.PersonalizacionRequestDTO;
import com.pastelarte.pastelarte_api.dto.PersonalizacionResponseDTO;
import com.pastelarte.pastelarte_api.entities.Personalizacion;
import com.pastelarte.pastelarte_api.repository.PersonalizacionRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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

    public List<PersonalizacionResponseDTO> listarPorCliente(Integer idCliente) {
        return repository.findByIdCliente(idCliente)
                .stream()
                .map(this::convertirAResponse)
                .collect(Collectors.toList());
    }

    public PersonalizacionResponseDTO buscar(Integer id) {
        Personalizacion p = repository.findById(id).orElse(null);
        return (p != null) ? convertirAResponse(p) : null;
    }

    // El cliente crea la solicitud: queda pendiente de que Gestión le ponga precio
    public PersonalizacionResponseDTO guardar(PersonalizacionRequestDTO dto) {
        Personalizacion p = new Personalizacion();
        p.setTamano(dto.getTamano());
        p.setSabor(dto.getSabor());
        p.setDecoraciones(dto.getDecoraciones());
        p.setDescripcion(dto.getDescripcion());
        p.setImagen(dto.getImagen());
        p.setIdCliente(dto.getIdCliente());
        p.setEstado("pendiente_cotizacion");
        // El costoExtra lo pone Gestión después, al cotizar. Ignoramos lo que venga aquí.

        return convertirAResponse(repository.save(p));
    }

    public PersonalizacionResponseDTO actualizar(Integer id, PersonalizacionRequestDTO dto) {
        Personalizacion p = repository.findById(id).orElse(null);
        if (p == null) return null;

        p.setTamano(dto.getTamano());
        p.setSabor(dto.getSabor());
        p.setDecoraciones(dto.getDecoraciones());
        p.setDescripcion(dto.getDescripcion());
        p.setCostoExtra(dto.getCostoExtra());
        p.setImagen(dto.getImagen());

        return convertirAResponse(repository.save(p));
    }

    // Gestión le pone precio a una solicitud pendiente
    public PersonalizacionResponseDTO cotizar(Integer id, BigDecimal costoExtra) {
        Personalizacion p = repository.findById(id).orElse(null);
        if (p == null) return null;

        p.setCostoExtra(costoExtra);
        p.setEstado("cotizado");

        return convertirAResponse(repository.save(p));
    }

    // El cliente acepta la cotización (antes de pasar al carrito)
    public PersonalizacionResponseDTO aceptar(Integer id) {
        Personalizacion p = repository.findById(id).orElse(null);
        if (p == null) return null;

        p.setEstado("aceptado");

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
        dto.setImagen(p.getImagen());
        dto.setIdCliente(p.getIdCliente());
        dto.setEstado(p.getEstado());
        return dto;
    }
}