package com.pastelarte.pastelarte_api.controller;

import com.pastelarte.pastelarte_api.dto.ClienteRequestDTO;
import com.pastelarte.pastelarte_api.dto.ClienteResponseDTO;
import com.pastelarte.pastelarte_api.dto.LoginRequestDTO;
import com.pastelarte.pastelarte_api.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteResponseDTO guardar(@Valid @RequestBody ClienteRequestDTO dto) {
        return service.guardar(dto);
    }

    @PutMapping("/{id}")
    public ClienteResponseDTO actualizar(@PathVariable Integer id,
                                         @Valid @RequestBody ClienteRequestDTO dto) {
        return service.actualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Integer id) {
        service.eliminar(id);
    }

    // ENDPOINT DE LOGIN
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequestDTO dto) {
        try {
            ClienteResponseDTO cliente = service.autenticar(dto.getCorreo(), dto.getContrasena());
            return ResponseEntity.ok(cliente);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Correo o contraseña incorrectos");
        }
    }
}