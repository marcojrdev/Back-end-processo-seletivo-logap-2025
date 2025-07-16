package com.marco.processo_seletivo_logap_2025.repository;

import com.marco.processo_seletivo_logap_2025.model.Pedido;
import com.marco.processo_seletivo_logap_2025.model.enums.StatusPedidos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido,Long> {
    List<Pedido> findByStatus(StatusPedidos status);


}
