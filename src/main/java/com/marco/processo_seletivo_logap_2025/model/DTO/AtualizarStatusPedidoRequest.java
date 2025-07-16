package com.marco.processo_seletivo_logap_2025.model.DTO;

import com.marco.processo_seletivo_logap_2025.model.enums.StatusPedidos;
import lombok.Data;

@Data
public class AtualizarStatusPedidoRequest {
    private StatusPedidos novoStatus;
}
