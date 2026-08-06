package com.pastelarte.pastelarte_api.controller;

import com.pastelarte.pastelarte_api.dto.PersonalizacionRequestDTO;
import com.pastelarte.pastelarte_api.dto.PersonalizacionResponseDTO;
import com.pastelarte.pastelarte_api.service.PersonalizacionService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/cliente/{idCliente}")
    public List<PersonalizacionResponseDTO> listarPorCliente(@PathVariable Integer idCliente) {
        return service.listarPorCliente(idCliente);
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

    // Gestión pone el precio: body { "costoExtra": 65000 }
    @PutMapping("/{id}/cotizar")
    public PersonalizacionResponseDTO cotizar(@PathVariable Integer id, @RequestBody Map<String, BigDecimal> body) {
        return service.cotizar(id, body.get("costoExtra"));
    }

    // El cliente acepta la cotización
    @PutMapping("/{id}/aceptar")
    public PersonalizacionResponseDTO aceptar(@PathVariable Integer id) {
        return service.aceptar(id);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        service.eliminar(id);
    }
}