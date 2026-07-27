package com.pastelarte.pastelarte_api.repository;

import com.pastelarte.pastelarte_api.entities.DetallePedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetallePedidoRepository extends JpaRepository<DetallePedido, Integer> {

    List<DetallePedido> findByPedido_IdPedido(Integer idPedido);

    List<DetallePedido> findByPedido_IdPedidoIn(List<Integer> idsPedidos);
}