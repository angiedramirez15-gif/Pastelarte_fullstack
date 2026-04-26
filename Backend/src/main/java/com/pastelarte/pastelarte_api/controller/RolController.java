package com.pastelarte.pastelarte_api.controller;

import com.pastelarte.pastelarte_api.entities.Rol;
import com.pastelarte.pastelarte_api.repository.RolRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
@CrossOrigin
public class RolController {

    private final RolRepository repository;

    public RolController(RolRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Rol> listar() {
        return repository.findAll();
    }

    @PostMapping
    public Rol guardar(@RequestBody Rol rol) {
        return repository.save(rol);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        repository.deleteById(id);
    }
}