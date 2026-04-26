package com.pastelarte.pastelarte_api.repository;

import com.pastelarte.pastelarte_api.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}