package com.marco.processo_seletivo_logap_2025.model.DTO;

import com.marco.processo_seletivo_logap_2025.model.enums.StatusPedidos;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoPendenteDTO {
    private Long idPedido;
    private String nomeCliente;
    private LocalDateTime dataPedido;
    private BigDecimal valorTotal;
    private StatusPedidos status;


}
