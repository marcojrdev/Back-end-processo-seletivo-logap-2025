package com.marco.processo_seletivo_logap_2025.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResumoVendasDTO {
    private Long totalPedidosRealizados;
    private BigDecimal valorTotalFaturado;
    private Long quantidadeTotalProdutosVendido;
}
