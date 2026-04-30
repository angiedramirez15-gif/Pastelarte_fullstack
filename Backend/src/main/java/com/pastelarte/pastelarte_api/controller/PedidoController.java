package com.pastelarte.pastelarte_api.controller;

import com.pastelarte.pastelarte_api.dto.PedidoRequestDTO;
import com.pastelarte.pastelarte_api.dto.PedidoResponseDTO;
import com.pastelarte.pastelarte_api.service.PedidoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
@CrossOrigin
public class PedidoController {

    private final PedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }

    @GetMapping
    public List<PedidoResponseDTO> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public PedidoResponseDTO buscar(@PathVariable Integer id) {
        return service.buscar(id);
    }

    @PostMapping
    public PedidoResponseDTO guardar(@RequestBody PedidoRequestDTO dto) {
        return service.guardar(dto);
    }

    @PutMapping("/{id}")
    public PedidoResponseDTO actualizar(@PathVariable Integer id,
                                        @RequestBody PedidoRequestDTO dto) {
        return service.actualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        service.eliminar(id);
    }
}