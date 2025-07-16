package com.marco.processo_seletivo_logap_2025.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemPedidoResponseDTO {
    private Long id;
    private Long produtoId;
    private String nomeProduto;
    private BigDecimal valorUnitarioProduto;
    private Integer quantidade;
    private BigDecimal subtotal;
}
