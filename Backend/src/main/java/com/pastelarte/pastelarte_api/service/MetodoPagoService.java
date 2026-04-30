package com.pastelarte.pastelarte_api.service;

import com.pastelarte.pastelarte_api.dto.MetodoPagoRequestDTO;
import com.pastelarte.pastelarte_api.dto.MetodoPagoResponseDTO;
import com.pastelarte.pastelarte_api.entities.MetodoPago;
import com.pastelarte.pastelarte_api.repository.MetodoPagoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MetodoPagoService {

    private final MetodoPagoRepository repository;

    public MetodoPagoService(MetodoPagoRepository repository) {
        this.repository = repository;
    }

    public List<MetodoPagoResponseDTO> listar() {
        return repository.findAll()
                .stream()
                .map(this::convertirAResponse)
                .collect(Collectors.toList());
    }

    public MetodoPagoResponseDTO buscar(Integer id) {
        MetodoPago metodo = repository.findById(id).orElse(null);

        if (metodo == null) {
            return null;
        }

        return convertirAResponse(metodo);
    }

    public MetodoPagoResponseDTO guardar(MetodoPagoRequestDTO dto) {

        MetodoPago metodo = new MetodoPago();

        metodo.setTipo(dto.getTipo());
        metodo.setDetalle(dto.getDetalle());

        return convertirAResponse(repository.save(metodo));
    }

    public MetodoPagoResponseDTO actualizar(Integer id, MetodoPagoRequestDTO dto) {

        MetodoPago metodo = repository.findById(id).orElse(null);

        if (metodo == null) {
            return null;
        }

        metodo.setTipo(dto.getTipo());
        metodo.setDetalle(dto.getDetalle());

        return convertirAResponse(repository.save(metodo));
    }

    public void eliminar(Integer id) {
        repository.deleteById(id);
    }

    private MetodoPagoResponseDTO convertirAResponse(MetodoPago metodo) {

        MetodoPagoResponseDTO dto = new MetodoPagoResponseDTO();

        dto.setIdPago(metodo.getIdPago());
        dto.setTipo(metodo.getTipo());
        dto.setDetalle(metodo.getDetalle());

        return dto;
    }
}