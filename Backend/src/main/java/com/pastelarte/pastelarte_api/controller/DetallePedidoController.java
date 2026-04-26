package com.pastelarte.pastelarte_api.controller;

import com.pastelarte.pastelarte_api.entities.DetallePedido;
import com.pastelarte.pastelarte_api.repository.DetallePedidoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/detalle-pedidos")
@CrossOrigin
public class DetallePedidoController {

    private final DetallePedidoRepository repository;

    public DetallePedidoController(DetallePedidoRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<DetallePedido> listar() {
        return repository.findAll();
    }

    @PostMapping
    public DetallePedido guardar(@RequestBody DetallePedido detalle) {
        return repository.save(detalle);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        repository.deleteById(id);
    }
}