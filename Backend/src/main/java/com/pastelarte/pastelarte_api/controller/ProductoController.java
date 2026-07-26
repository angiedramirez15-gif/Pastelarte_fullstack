package com.pastelarte.pastelarte_api.controller;

import com.pastelarte.pastelarte_api.dto.ProductoRequestDTO;
import com.pastelarte.pastelarte_api.dto.ProductoResponseDTO;
import com.pastelarte.pastelarte_api.service.ProductoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
@CrossOrigin
public class ProductoController {

    private final ProductoService service;

    public ProductoController(ProductoService service) {
        this.service = service;
    }

    @GetMapping
    public List<ProductoResponseDTO> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ProductoResponseDTO buscar(@PathVariable Integer id) {
        return service.buscar(id);
    }

    @GetMapping("/categoria/{categoria}")
    public List<ProductoResponseDTO> listarPorCategoria(@PathVariable String categoria) {
        return service.listarPorCategoria(categoria);
    }

    @PostMapping
    public ProductoResponseDTO guardar(@RequestBody ProductoRequestDTO dto) {
        return service.guardar(dto);
    }

    @PutMapping("/{id}")
    public ProductoResponseDTO actualizar(@PathVariable Integer id,
                                          @RequestBody ProductoRequestDTO dto) {
        return service.actualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        service.eliminar(id);
    }
}