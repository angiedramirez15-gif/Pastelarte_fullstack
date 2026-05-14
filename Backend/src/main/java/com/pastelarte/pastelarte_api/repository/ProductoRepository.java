package com.pastelarte.pastelarte_api.repository;

import com.pastelarte.pastelarte_api.entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    List<Producto> findByCategoria(String categoria);
    List<Producto> findByDisponibleTrue();
}