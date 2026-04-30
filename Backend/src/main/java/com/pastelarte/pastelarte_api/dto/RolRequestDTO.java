package com.pastelarte.pastelarte_api.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RolRequestDTO {

    private String titulo;
    private String descripcion;
    private String estado;
}