package com.marco.processo_seletivo_logap_2025.service;

import com.marco.processo_seletivo_logap_2025.model.DTO.ClienteAtivoDTO;
import com.marco.processo_seletivo_logap_2025.model.DTO.PedidoPendenteDTO;
import com.marco.processo_seletivo_logap_2025.model.DTO.ResumoVendasDTO;
import com.marco.processo_seletivo_logap_2025.model.Pedido;
import com.marco.processo_seletivo_logap_2025.model.enums.StatusPedidos;
import com.marco.processo_seletivo_logap_2025.repository.ClienteRepository;
import com.marco.processo_seletivo_logap_2025.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RelatorioService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public ResumoVendasDTO gerarResumoVendas() {
        List<Pedido> todosPedidos = pedidoRepository.findAll();

        long totalPedidosRealizados =  todosPedidos.size();


        BigDecimal valorTotalFaturado = todosPedidos.stream()
                .filter(p -> p.getStatus() == StatusPedidos.FINALIZADO)
                .map(Pedido::getValorTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        long quantidadeTotalProdutosVendidos = todosPedidos.stream()
                .filter(p -> p.getStatus() == StatusPedidos.FINALIZADO)
                .flatMap(p -> p.getItens().stream())
                .mapToLong(item -> item.getQuantidade().longValue())
                .sum();
        return new ResumoVendasDTO(totalPedidosRealizados, valorTotalFaturado, quantidadeTotalProdutosVendidos);
    }

    public List<PedidoPendenteDTO> listarPedidosPendentes() {
        return pedidoRepository.findByStatus(StatusPedidos.EM_ANDAMENTO).stream()
                .map(pedido -> new PedidoPendenteDTO(
                        pedido.getId(),
                        pedido.getCliente().getNome(),
                        pedido.getDataPedido(),
                        pedido.getValorTotal(),
                        pedido.getStatus()

                )).collect(Collectors.toList());
    }

    public List<ClienteAtivoDTO> listarClientesAtivos() {
        return clienteRepository.findTopActiveClients();
    }
}
