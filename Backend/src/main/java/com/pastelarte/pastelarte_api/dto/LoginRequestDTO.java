package com.pastelarte.pastelarte_api.dto;

public class LoginRequestDTO {
    private String correo;
    private String contrasena;

    // Getter y Setters
    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }
}