package com.marco.processo_seletivo_logap_2025.controller;

import com.marco.processo_seletivo_logap_2025.model.Clientes;
import com.marco.processo_seletivo_logap_2025.model.DTO.ClienteResponseDTO;
import com.marco.processo_seletivo_logap_2025.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/cliente")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>> todosClientesDto() {
        List<ClienteResponseDTO> clientes = clienteService.todosClientesDto();
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> clientePorId(@PathVariable Long id) {
        return clienteService.buscarClientePorId(id)
                .map(clientes -> new ResponseEntity<>(clientes, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @PostMapping
    public ResponseEntity<ClienteResponseDTO> criaCliente(@RequestBody ClienteResponseDTO cliente  ) {
        ClienteResponseDTO NovoCliente =  clienteService.criarCliente(cliente);
        return new ResponseEntity<>(NovoCliente, HttpStatus.CREATED);
    }

   @PutMapping("/{id}")
   public ResponseEntity<ClienteResponseDTO> AtualizarCliente(@PathVariable Long id , @RequestBody ClienteResponseDTO clientes) {
        try {
            ClienteResponseDTO clientesAtt =  clienteService.atualizarClientes(id, clientes);
            return new ResponseEntity<>(clientesAtt, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
   }

   @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletaCliente(@PathVariable Long id) {
        try {
            clienteService.deleteCliente(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
   }
}
