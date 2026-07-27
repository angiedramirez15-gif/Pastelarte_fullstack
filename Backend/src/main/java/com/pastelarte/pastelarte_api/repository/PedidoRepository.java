package com.pastelarte.pastelarte_api.repository;

import com.pastelarte.pastelarte_api.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

    List<Pedido> findByCliente_IdCliente(Integer idCliente);

    List<Pedido> findByFechaBetween(LocalDate inicio, LocalDate fin);

    long countByEstado(String estado);
}