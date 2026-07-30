package com.pastelarte.pastelarte_api.repository;

import com.pastelarte.pastelarte_api.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    // Necesario para el Login
    Optional<Cliente> findByCorreo(String correo);
}