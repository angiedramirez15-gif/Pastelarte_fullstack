package com.pastelarte.pastelarte_api.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonalizacionRequestDTO {

    private String tamano;
    private String sabor;
    private String decoraciones;
    private String descripcion;
    private Float costoExtra;
}