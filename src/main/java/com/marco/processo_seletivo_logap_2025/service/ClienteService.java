package com.marco.processo_seletivo_logap_2025.service;

import com.marco.processo_seletivo_logap_2025.model.Clientes;
import com.marco.processo_seletivo_logap_2025.model.DTO.ClienteResponseDTO;
import com.marco.processo_seletivo_logap_2025.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    private ClienteResponseDTO clienteResponseDTO(Clientes cliente){
        return new ClienteResponseDTO(
                cliente.getId(),
                cliente.getNome(),
                cliente.getTelefone(),
                cliente.getTelefone()
        );
    }

    @Autowired
    private ClienteRepository  clienteRepository;

    public  List<ClienteResponseDTO> todosClientesDto (){
        return clienteRepository.findAll().stream()
                .map(this::clienteResponseDTO).collect(Collectors.toList());
    }

    public ClienteResponseDTO criarCliente(ClienteResponseDTO clientedto){
        Clientes cliente = new Clientes();
        cliente.setNome(clientedto.getNome());
        cliente.setTelefone(clientedto.getTelefone());
        cliente.setEmail(clientedto.getEmail());

        Clientes salvo = clienteRepository.save(cliente);
        return clienteResponseDTO(salvo);

    }
    public Optional<ClienteResponseDTO> buscarClientePorId (Long id){
        return clienteRepository.findById(id)
                .map(this::clienteResponseDTO);
    }

    public ClienteResponseDTO atualizarClientes (Long id, ClienteResponseDTO dto){
        Clientes clientes = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        clientes.setTelefone(dto.getTelefone());
        clientes.setEmail(dto.getEmail());
        clientes.setNome(dto.getNome());

        Clientes  updatedCliente = clienteRepository.save(clientes);

        return clienteResponseDTO(updatedCliente);
    }



    public void deleteCliente(Long id) {
        clienteRepository.deleteById(id);
    }

}
