package com.pastelarte.pastelarte_api.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RolResponseDTO {

    private Integer idRol;
    private String titulo;
    private String descripcion;
    private String estado;
}