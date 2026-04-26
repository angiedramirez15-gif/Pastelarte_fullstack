package com.pastelarte.pastelarte_api.repository;

import com.pastelarte.pastelarte_api.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
}