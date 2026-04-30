package com.pastelarte.pastelarte_api.controller;

import com.pastelarte.pastelarte_api.dto.DetallePedidoRequestDTO;
import com.pastelarte.pastelarte_api.dto.DetallePedidoResponseDTO;
import com.pastelarte.pastelarte_api.service.DetallePedidoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/detalle-pedidos")
@CrossOrigin
public class DetallePedidoController {

    private final DetallePedidoService service;

    public DetallePedidoController(DetallePedidoService service) {
        this.service = service;
    }

    @GetMapping
    public List<DetallePedidoResponseDTO> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public DetallePedidoResponseDTO buscar(@PathVariable Integer id) {
        return service.buscar(id);
    }

    @PostMapping
    public DetallePedidoResponseDTO guardar(@RequestBody DetallePedidoRequestDTO dto) {
        return service.guardar(dto);
    }

    @PutMapping("/{id}")
    public DetallePedidoResponseDTO actualizar(@PathVariable Integer id,
                                               @RequestBody DetallePedidoRequestDTO dto) {
        return service.actualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        service.eliminar(id);
    }
}