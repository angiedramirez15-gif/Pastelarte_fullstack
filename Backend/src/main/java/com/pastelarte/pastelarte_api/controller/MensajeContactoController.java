package com.pastelarte.pastelarte_api.controller;

import com.pastelarte.pastelarte_api.dto.MensajeContactoRequestDTO;
import com.pastelarte.pastelarte_api.dto.MensajeContactoResponseDTO;
import com.pastelarte.pastelarte_api.service.MensajeContactoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mensajes-contacto")
@CrossOrigin
public class MensajeContactoController {

    private final MensajeContactoService service;

    public MensajeContactoController(MensajeContactoService service) {
        this.service = service;
    }

    @GetMapping
    public List<MensajeContactoResponseDTO> listar() {
        return service.listar();
    }

    @PostMapping
    public MensajeContactoResponseDTO guardar(@RequestBody MensajeContactoRequestDTO dto) {
        return service.guardar(dto);
    }

    @PutMapping("/{id}/leido")
    public MensajeContactoResponseDTO marcarLeido(@PathVariable Integer id) {
        return service.marcarLeido(id);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        service.eliminar(id);
    }
}
