package com.pastelarte.pastelarte_api.repository;

import com.pastelarte.pastelarte_api.entities.DetallePedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DetallePedidoRepository extends JpaRepository<DetallePedido, Integer> {

    List<DetallePedido> findByIdPedidoIn(List<Integer> idsPedido);

    List<DetallePedido> findByIdPedido(Integer idPedido);
}