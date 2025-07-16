package com.marco.processo_seletivo_logap_2025.service;

import com.marco.processo_seletivo_logap_2025.model.Clientes;
import com.marco.processo_seletivo_logap_2025.model.DTO.CriarPedidoRequest;
import com.marco.processo_seletivo_logap_2025.model.DTO.ItemPedidoResponseDTO;
import com.marco.processo_seletivo_logap_2025.model.DTO.PedidoResponseDTO;
import com.marco.processo_seletivo_logap_2025.model.ItemPedido;
import com.marco.processo_seletivo_logap_2025.model.Pedido;
import com.marco.processo_seletivo_logap_2025.model.Produtos;
import com.marco.processo_seletivo_logap_2025.model.enums.StatusPedidos;
import com.marco.processo_seletivo_logap_2025.repository.ClienteRepository;
import com.marco.processo_seletivo_logap_2025.repository.PedidoRepository;
import com.marco.processo_seletivo_logap_2025.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    private PedidoResponseDTO convertToPedidoResponseDTO(Pedido pedido) {
        List<ItemPedidoResponseDTO> itemDTOs = pedido.getItens().stream()
                .map(this::convertToItemPedidoResponseDTO)
                .collect(Collectors.toList());
        return new PedidoResponseDTO(
                pedido.getId(),
                pedido.getCliente().getId(),
                pedido.getCliente().getNome(),
                pedido.getDataPedido(),
                pedido.getStatus(),
                pedido.getValorTotal(),
                itemDTOs
        );
    }

    private ItemPedidoResponseDTO convertToItemPedidoResponseDTO(ItemPedido itemPedido) {
        return new ItemPedidoResponseDTO(
                itemPedido.getId(),
                itemPedido.getProdutos().getId(),
                itemPedido.getProdutos().getNome(),
                itemPedido.getProdutos().getPreco(),
                itemPedido.getQuantidade(),
                itemPedido.getProdutos().getPreco().multiply(BigDecimal.valueOf(itemPedido.getQuantidade()))
        );
    }

    @Transactional
    public PedidoResponseDTO criarPedido(CriarPedidoRequest request) {
        Clientes cliente = clienteRepository.findById(request.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente nao encontrado" + request.getClienteId()));

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setDataPedido(LocalDateTime.now());
        pedido.setStatus(StatusPedidos.EM_ANDAMENTO);
        pedido.setValorTotal(BigDecimal.ZERO);

        List<ItemPedido> itens = request.getItens().stream().map(itemRequest -> {
            Produtos produto = produtoRepository.findById(itemRequest.getProdutoId())
                    .orElseThrow(() -> new RuntimeException("Produti não encontrado com ID:" + itemRequest.getProdutoId()));

            ItemPedido itemPedido = new ItemPedido();
            itemPedido.setProdutos(produto);
            itemPedido.setQuantidade(itemRequest.getQuantidade());
            itemPedido.setPedido(pedido);
            return itemPedido;
        }).collect(Collectors.toList());

        pedido.setItens(itens);
        pedido.calcularValorTotal();

        Pedido savedPedido = pedidoRepository.save(pedido);

        return convertToPedidoResponseDTO(savedPedido);
    }

    public List<PedidoResponseDTO> buscarTodosPedidos() {
        return pedidoRepository.findAll().stream()
                .map(this::convertToPedidoResponseDTO)
                .collect(Collectors.toList());
    }

    public Optional<PedidoResponseDTO> buscarPedidoPorId(Long id) {
        return pedidoRepository.findById(id)
                .map(this::convertToPedidoResponseDTO);
    }

    @Transactional
    public PedidoResponseDTO atualizarPedido(Long id, StatusPedidos novoStatus) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado" + id));
        pedido.setStatus(novoStatus);
        Pedido updatedPedido = pedidoRepository.save(pedido);
        return convertToPedidoResponseDTO(updatedPedido);
    }

    @Transactional
    public void deletarPedido(Long id) {
        pedidoRepository.deleteById(id);
    }


}
