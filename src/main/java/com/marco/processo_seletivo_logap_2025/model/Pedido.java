package com.marco.processo_seletivo_logap_2025.model;

import com.marco.processo_seletivo_logap_2025.model.enums.StatusPedidos;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente")
    private Clientes cliente;

    @Column(name = "data_pedido")
    private LocalDateTime dataPedido;

    @Enumerated(EnumType.STRING)
    private StatusPedidos status;

    @Column(name = "valor_total")
    private BigDecimal valorTotal;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemPedido> itens = new ArrayList<>();

    public void adicionarItem(ItemPedido item) {
        itens.add(item);
        item.setPedido(this);
        calcularValorTotal();
    }

    public void removerItem(ItemPedido item) {
        itens.remove(item);
        item.setPedido(null);
        calcularValorTotal();
    }

    public void calcularValorTotal() {
        this.valorTotal = itens.stream()
                .map(item ->
                        item.getProdutos().getPreco().multiply(BigDecimal.valueOf(item.getQuantidade())))
        .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
