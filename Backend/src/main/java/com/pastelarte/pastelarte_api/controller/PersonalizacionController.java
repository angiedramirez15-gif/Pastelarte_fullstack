package com.pastelarte.pastelarte_api.controller;

import com.pastelarte.pastelarte_api.entities.Personalizacion;
import com.pastelarte.pastelarte_api.repository.PersonalizacionRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personalizaciones")
@CrossOrigin
public class PersonalizacionController {

    private final PersonalizacionRepository repository;

    public PersonalizacionController(PersonalizacionRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Personalizacion> listar() {
        return repository.findAll();
    }

    @PostMapping
    public Personalizacion guardar(@RequestBody Personalizacion personalizacion) {
        return repository.save(personalizacion);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        repository.deleteById(id);
    }
}