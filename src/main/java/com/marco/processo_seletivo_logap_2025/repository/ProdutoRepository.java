package com.marco.processo_seletivo_logap_2025.repository;

import com.marco.processo_seletivo_logap_2025.model.Produtos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produtos,Long> {
}
