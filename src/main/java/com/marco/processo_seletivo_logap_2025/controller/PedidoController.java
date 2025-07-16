package com.marco.processo_seletivo_logap_2025.controller;

import com.marco.processo_seletivo_logap_2025.model.DTO.AtualizarStatusPedidoRequest;
import com.marco.processo_seletivo_logap_2025.model.DTO.CriarPedidoRequest;
import com.marco.processo_seletivo_logap_2025.model.DTO.PedidoResponseDTO;
import com.marco.processo_seletivo_logap_2025.model.Pedido;
import com.marco.processo_seletivo_logap_2025.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<PedidoResponseDTO> criarPedido(@RequestBody CriarPedidoRequest request) {
        try {
            PedidoResponseDTO novoPedido = pedidoService.criarPedido(request);
            return new ResponseEntity<>(novoPedido, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<PedidoResponseDTO>> buscarTodosPedidos() {
        List<PedidoResponseDTO> pedidos = pedidoService.buscarTodosPedidos();
        return new ResponseEntity<>(pedidos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponseDTO> buscarPedidoPorId(@PathVariable Long id) {
        return pedidoService.buscarPedidoPorId(id)
                .map( (pedido) -> new ResponseEntity<>(pedido, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<PedidoResponseDTO> atualizarStatusPedido(@PathVariable Long id, @RequestBody AtualizarStatusPedidoRequest request) {
        try {
            PedidoResponseDTO pedidoAtualizado = pedidoService.atualizarPedido(id, request.getNovoStatus());
            return new ResponseEntity<>(pedidoAtualizado, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPedido(@PathVariable Long id) {
        try {
            pedidoService.deletarPedido(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
