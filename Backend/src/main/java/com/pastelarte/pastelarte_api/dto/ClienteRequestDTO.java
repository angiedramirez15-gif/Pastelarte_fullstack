package com.pastelarte.pastelarte_api.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteRequestDTO {

    private String nombre;
    private String correo;
    private String contrasena;
    private String direccion;
    private Integer idRol;
}