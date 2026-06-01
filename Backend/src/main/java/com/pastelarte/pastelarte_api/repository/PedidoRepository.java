    package com.pastelarte.pastelarte_api.repository;

    import com.pastelarte.pastelarte_api.entities.Pedido;
    import org.springframework.data.jpa.repository.JpaRepository;
    import java.util.List;

    public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

    List<Pedido> findByIdCliente(Integer idCliente);
    }