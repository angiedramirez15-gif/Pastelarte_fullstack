package com.pastelarte.pastelarte_api.service;

import com.pastelarte.pastelarte_api.dto.MensajeContactoRequestDTO;
import com.pastelarte.pastelarte_api.dto.MensajeContactoResponseDTO;
import com.pastelarte.pastelarte_api.entities.MensajeContacto;
import com.pastelarte.pastelarte_api.repository.MensajeContactoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MensajeContactoService {

    private final MensajeContactoRepository repository;

    public MensajeContactoService(MensajeContactoRepository repository) {
        this.repository = repository;
    }

    public List<MensajeContactoResponseDTO> listar() {
        return repository.findAll()
                .stream()
                .map(this::convertirAResponse)
                // los más recientes primero
                .sorted(Comparator.comparing(MensajeContactoResponseDTO::getFecha).reversed())
                .collect(Collectors.toList());
    }

    public MensajeContactoResponseDTO guardar(MensajeContactoRequestDTO dto) {

        MensajeContacto mensaje = new MensajeContacto();

        mensaje.setNombre(dto.getNombre());
        mensaje.setCorreo(dto.getCorreo());
        mensaje.setTelefono(dto.getTelefono());
        mensaje.setMensaje(dto.getMensaje());
        mensaje.setFecha(LocalDateTime.now());
        mensaje.setLeido(false);

        return convertirAResponse(repository.save(mensaje));
    }

    public MensajeContactoResponseDTO marcarLeido(Integer id) {

        MensajeContacto mensaje = repository.findById(id).orElse(null);

        if (mensaje == null) {
            return null;
        }

        mensaje.setLeido(true);

        return convertirAResponse(repository.save(mensaje));
    }

    public void eliminar(Integer id) {
        repository.deleteById(id);
    }

    private MensajeContactoResponseDTO convertirAResponse(MensajeContacto mensaje) {

        MensajeContactoResponseDTO dto = new MensajeContactoResponseDTO();

        dto.setIdMensaje(mensaje.getIdMensaje());
        dto.setNombre(mensaje.getNombre());
        dto.setCorreo(mensaje.getCorreo());
        dto.setTelefono(mensaje.getTelefono());
        dto.setMensaje(mensaje.getMensaje());
        dto.setFecha(mensaje.getFecha());
        dto.setLeido(mensaje.getLeido());

        return dto;
    }
}
