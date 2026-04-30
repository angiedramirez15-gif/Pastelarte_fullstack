package com.pastelarte.pastelarte_api.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MetodoPagoRequestDTO {

    private String tipo;
    private String detalle;
}