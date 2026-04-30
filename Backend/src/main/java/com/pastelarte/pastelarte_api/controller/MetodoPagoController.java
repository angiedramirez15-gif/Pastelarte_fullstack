package com.pastelarte.pastelarte_api.controller;

import com.pastelarte.pastelarte_api.dto.MetodoPagoRequestDTO;
import com.pastelarte.pastelarte_api.dto.MetodoPagoResponseDTO;
import com.pastelarte.pastelarte_api.service.MetodoPagoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/metodos-pago")
@CrossOrigin
public class MetodoPagoController {

    private final MetodoPagoService service;

    public MetodoPagoController(MetodoPagoService service) {
        this.service = service;
    }

    @GetMapping
    public List<MetodoPagoResponseDTO> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public MetodoPagoResponseDTO buscar(@PathVariable Integer id) {
        return service.buscar(id);
    }

    @PostMapping
    public MetodoPagoResponseDTO guardar(@RequestBody MetodoPagoRequestDTO dto) {
        return service.guardar(dto);
    }

    @PutMapping("/{id}")
    public MetodoPagoResponseDTO actualizar(@PathVariable Integer id,
                                            @RequestBody MetodoPagoRequestDTO dto) {
        return service.actualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        service.eliminar(id);
    }
}