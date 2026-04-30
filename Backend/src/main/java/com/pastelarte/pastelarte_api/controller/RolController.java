package com.pastelarte.pastelarte_api.controller;

import com.pastelarte.pastelarte_api.dto.RolRequestDTO;
import com.pastelarte.pastelarte_api.dto.RolResponseDTO;
import com.pastelarte.pastelarte_api.service.RolService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
@CrossOrigin
public class RolController {

    private final RolService service;

    public RolController(RolService service) {
        this.service = service;
    }

    @GetMapping
    public List<RolResponseDTO> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public RolResponseDTO buscar(@PathVariable Integer id) {
        return service.buscar(id);
    }

    @PostMapping
    public RolResponseDTO guardar(@RequestBody RolRequestDTO dto) {
        return service.guardar(dto);
    }

    @PutMapping("/{id}")
    public RolResponseDTO actualizar(@PathVariable Integer id,
                                     @RequestBody RolRequestDTO dto) {
        return service.actualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        service.eliminar(id);
    }
}