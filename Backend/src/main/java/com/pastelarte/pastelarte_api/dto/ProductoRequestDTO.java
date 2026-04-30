package com.pastelarte.pastelarte_api.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductoRequestDTO {

    private String nombre;
    private String descripcion;
    private Float precio;
    private String categoria;
}