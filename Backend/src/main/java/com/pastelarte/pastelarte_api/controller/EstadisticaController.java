package com.pastelarte.pastelarte_api.controller;

import com.pastelarte.pastelarte_api.service.EstadisticaService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/estadisticas")
@CrossOrigin
public class EstadisticaController {

    private final EstadisticaService service;

    public EstadisticaController(EstadisticaService service) {
        this.service = service;
    }

    @GetMapping("/dashboard")
    public Map<String, Object> dashboard() {
        return service.dashboard();
    }
}