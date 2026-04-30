package com.pastelarte.pastelarte_api.controller;

import com.pastelarte.pastelarte_api.dto.ClienteRequestDTO;
import com.pastelarte.pastelarte_api.dto.ClienteResponseDTO;
import com.pastelarte.pastelarte_api.service.ClienteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
@CrossOrigin
public class ClienteController {

    private final ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }

    @GetMapping
    public List<ClienteResponseDTO> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ClienteResponseDTO buscar(@PathVariable Integer id) {
        return service.buscar(id);
    }

    @PostMapping
    public ClienteResponseDTO guardar(@RequestBody ClienteRequestDTO dto) {
        return service.guardar(dto);
    }

    @PutMapping("/{id}")
    public ClienteResponseDTO actualizar(@PathVariable Integer id,
                                         @RequestBody ClienteRequestDTO dto) {
        return service.actualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        service.eliminar(id);
    }
}