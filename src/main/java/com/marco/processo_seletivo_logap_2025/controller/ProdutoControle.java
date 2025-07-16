package com.marco.processo_seletivo_logap_2025.controller;

import com.marco.processo_seletivo_logap_2025.model.Produtos;
import com.marco.processo_seletivo_logap_2025.repository.ProdutoRepository;
import com.marco.processo_seletivo_logap_2025.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/produto")
@RequiredArgsConstructor
public class ProdutoControle {

    @Autowired
    private final ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<Produtos> NovoProduto(@RequestBody Produtos produto){
        Produtos novoProduto = produtoService.criarProduto(produto);
        return new ResponseEntity<>(novoProduto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Produtos>> todosProdutos(){
        List<Produtos> produtos =  produtoService.todosOsProdutos();
        return new ResponseEntity<>(produtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produtos> buscaProduto(@PathVariable Long id){
        return produtoService.buscarProdutoPorId(id).
                map((produto) -> new ResponseEntity<>(produto, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produtos> AtualizarProduto(@PathVariable Long id ,@RequestBody Produtos produto){
        try {
            Produtos produtoAtt = produtoService.atualizarProduto(id, produto);
            return new ResponseEntity<>(produtoAtt, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Produtos> ExcluirProduto(@PathVariable Long id){
        try {
            produtoService.deletarProduto(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
