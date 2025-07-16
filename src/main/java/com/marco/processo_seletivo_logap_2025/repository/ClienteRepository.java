package com.marco.processo_seletivo_logap_2025.repository;

import com.marco.processo_seletivo_logap_2025.model.Clientes;
import com.marco.processo_seletivo_logap_2025.model.DTO.ClienteAtivoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ClienteRepository extends JpaRepository<Clientes, Long> {
    @Query("SELECT new com.marco.processo_seletivo_logap_2025.model.DTO.ClienteAtivoDTO(c.id, c.nome, COUNT(p.id))" +
        "FROM Clientes c JOIN c.pedidos p " +
        "GROUP BY c.id, c.nome " +
        "ORDER BY COUNT(p.id) DESC")
    List<ClienteAtivoDTO> findTopActiveClients();
}
