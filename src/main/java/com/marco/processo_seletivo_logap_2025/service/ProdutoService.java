package com.marco.processo_seletivo_logap_2025.service;

import com.marco.processo_seletivo_logap_2025.model.Produtos;
import com.marco.processo_seletivo_logap_2025.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public Produtos criarProduto(Produtos produto){
        return produtoRepository.save(produto);
    }

    public List<Produtos> todosOsProdutos() {
        return produtoRepository.findAll();
    }

    public Optional<Produtos> buscarProdutoPorId(Long id) {
        return produtoRepository.findById(id);
    }

    public Produtos atualizarProduto(Long id, Produtos produtoAtualizado) {
        return produtoRepository.findById(id)
                .map(produtos -> {
                    produtos.setNome(produtoAtualizado.getNome());
                    produtos.setPreco(produtoAtualizado.getPreco());
                    produtos.setQuantidade(produtoAtualizado.getQuantidade());
                    return  produtoRepository.save(produtos);
                }).orElseThrow(() -> new RuntimeException("Produto Não Encontrado"));
    }

    public void deletarProduto(Long id) {
        produtoRepository.deleteById(id);

    }


}
