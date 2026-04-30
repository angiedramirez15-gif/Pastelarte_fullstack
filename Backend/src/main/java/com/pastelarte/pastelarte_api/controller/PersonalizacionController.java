package com.pastelarte.pastelarte_api.controller;

import com.pastelarte.pastelarte_api.dto.PersonalizacionRequestDTO;
import com.pastelarte.pastelarte_api.dto.PersonalizacionResponseDTO;
import com.pastelarte.pastelarte_api.service.PersonalizacionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personalizaciones")
@CrossOrigin
public class PersonalizacionController {

    private final PersonalizacionService service;

    public PersonalizacionController(PersonalizacionService service) {
        this.service = service;
    }

    @GetMapping
    public List<PersonalizacionResponseDTO> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public PersonalizacionResponseDTO buscar(@PathVariable Integer id) {
        return service.buscar(id);
    }

    @PostMapping
    public PersonalizacionResponseDTO guardar(@RequestBody PersonalizacionRequestDTO dto) {
        return service.guardar(dto);
    }

    @PutMapping("/{id}")
    public PersonalizacionResponseDTO actualizar(@PathVariable Integer id,
                                                 @RequestBody PersonalizacionRequestDTO dto) {
        return service.actualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        service.eliminar(id);
    }
}