package com.pastelarte.pastelarte_api.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteResponseDTO {

    private Integer idCliente;
    private String nombre;
    private String correo;
    private String direccion;
    private Integer idRol;
}