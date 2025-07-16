package com.marco.processo_seletivo_logap_2025.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteAtivoDTO {
    private Long idCliente;
    private String nomeCliente;
    private Long totalPedidos;
}
