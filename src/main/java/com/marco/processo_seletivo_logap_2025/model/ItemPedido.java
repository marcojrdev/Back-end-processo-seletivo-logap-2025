package com.marco.processo_seletivo_logap_2025.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedidos_id")
    private Pedido pedido;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produtos_id")
    private Produtos produtos;

    private Integer quantidade;



}
