package com.marco.processo_seletivo_logap_2025.controller;

import com.marco.processo_seletivo_logap_2025.model.DTO.ClienteAtivoDTO;
import com.marco.processo_seletivo_logap_2025.model.DTO.PedidoPendenteDTO;
import com.marco.processo_seletivo_logap_2025.model.DTO.ResumoVendasDTO;
import com.marco.processo_seletivo_logap_2025.model.Pedido;
import com.marco.processo_seletivo_logap_2025.service.RelatorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/relatorios")
public class RelatorioController {

    @Autowired
    private RelatorioService relatorioService;

    @GetMapping("/resumo-vendas")
    public ResponseEntity<ResumoVendasDTO> resumoVendas() {
        ResumoVendasDTO resumo = relatorioService.gerarResumoVendas();
        return new ResponseEntity<>(resumo, HttpStatus.OK);
    }

    @GetMapping("/pedidos-pendentes")
    public ResponseEntity<List<PedidoPendenteDTO>> pedidoPendente() {
        List<PedidoPendenteDTO> pedidosPendente = relatorioService.listarPedidosPendentes();
        return new ResponseEntity<>(pedidosPendente, HttpStatus.OK);
    }

    @GetMapping("/clientes-ativos")
    public ResponseEntity<List<ClienteAtivoDTO>> ClientesMaisAtivos() {
        List<ClienteAtivoDTO> clienteAtivos = relatorioService.listarClientesAtivos();
        return new ResponseEntity<>(clienteAtivos, HttpStatus.OK);
    }
}
