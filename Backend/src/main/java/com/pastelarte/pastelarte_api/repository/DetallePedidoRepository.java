package com.pastelarte.pastelarte_api.repository;

import com.pastelarte.pastelarte_api.entities.DetallePedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetallePedidoRepository extends JpaRepository<DetallePedido, Integer> {
}