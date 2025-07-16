package com.marco.processo_seletivo_logap_2025.model.DTO;

import com.marco.processo_seletivo_logap_2025.service.PedidoService;
import lombok.Data;

import java.util.List;

@Data
public class CriarPedidoRequest {
    private Long clienteId;
    private List<ItemPedidoRequest> itens;
}
