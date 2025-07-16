package com.marco.processo_seletivo_logap_2025.model.DTO;

import com.marco.processo_seletivo_logap_2025.model.Pedido;
import com.marco.processo_seletivo_logap_2025.model.enums.StatusPedidos;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoResponseDTO {
    private Long id;
    private Long clienteId;
    private String nomeCliente;
    private LocalDateTime dataPedido;
    private StatusPedidos status;
    private BigDecimal valorTotal;
    private List<ItemPedidoResponseDTO> itens;
}
