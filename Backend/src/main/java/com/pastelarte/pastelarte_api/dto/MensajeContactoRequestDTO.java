package com.pastelarte.pastelarte_api.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MensajeContactoRequestDTO {

    private String nombre;
    private String correo;
    private String telefono;
    private String mensaje;
}
