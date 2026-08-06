package com.pastelarte.pastelarte_api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pastelarte.pastelarte_api.dto.PedidoRequestDTO;
import com.pastelarte.pastelarte_api.dto.PedidoResponseDTO;
import com.pastelarte.pastelarte_api.service.PedidoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
@CrossOrigin
public class PedidoController {

    private final PedidoService service;
    private final ObjectMapper objectMapper;

    public PedidoController(PedidoService service, ObjectMapper objectMapper) {
        this.service = service;
        this.objectMapper = objectMapper;
    }

    @GetMapping
    public List<PedidoResponseDTO> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public PedidoResponseDTO buscar(@PathVariable Integer id) {
        return service.buscar(id);
    }

    @GetMapping("/cliente/{idCliente}")
    public List<PedidoResponseDTO> listarPorCliente(@PathVariable Integer idCliente) {
        return service.listarPorCliente(idCliente);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PedidoResponseDTO guardar(@Valid @RequestBody PedidoRequestDTO dto) {
        return service.guardar(dto);
    }

    @PostMapping(value = "/con-comprobante", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public PedidoResponseDTO crearConComprobante(
            @RequestPart("pedido") String pedidoJson,
            @RequestPart(value = "comprobante", required = false) MultipartFile comprobante) {
        try {
            PedidoRequestDTO dto = objectMapper.readValue(pedidoJson, PedidoRequestDTO.class);
            return service.guardarConArchivo(dto, comprobante);
        } catch (Exception e) {
            throw new RuntimeException("Error al procesar la solicitud del pedido: " + e.getMessage(), e);
        }
    }

    @PutMapping("/{id}")
    public PedidoResponseDTO actualizar(@PathVariable Integer id,
                                        @Valid @RequestBody PedidoRequestDTO dto) {
        return service.actualizar(id, dto);
    }

    @PutMapping("/{id}/confirmar-pago")
    public PedidoResponseDTO confirmarPago(@PathVariable Integer id) {
        return service.confirmarPago(id);
    }

    @PutMapping("/{id}/rechazar-pago")
    public PedidoResponseDTO rechazarPago(@PathVariable Integer id) {
        return service.rechazarPago(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Integer id) {
        service.eliminar(id);
    }
}