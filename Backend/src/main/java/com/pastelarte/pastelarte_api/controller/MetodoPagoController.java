package com.pastelarte.pastelarte_api.controller;

import com.pastelarte.pastelarte_api.entities.MetodoPago;
import com.pastelarte.pastelarte_api.repository.MetodoPagoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/metodos-pago")
@CrossOrigin
public class MetodoPagoController {

    private final MetodoPagoRepository repository;

    public MetodoPagoController(MetodoPagoRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<MetodoPago> listar() {
        return repository.findAll();
    }

    @PostMapping
    public MetodoPago guardar(@RequestBody MetodoPago metodo) {
        return repository.save(metodo);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        repository.deleteById(id);
    }
}