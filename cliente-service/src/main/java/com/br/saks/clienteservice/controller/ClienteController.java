package com.br.saks.clienteservice.controller;

import com.br.saks.clienteservice.criptografia.Criptografia;
import com.br.saks.clienteservice.model.Cliente;
import com.br.saks.clienteservice.repository.ClienteRepository;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    ClienteRepository clienteRepo;
    
    @GetMapping
    public List<Cliente> getAll(){
        return clienteRepo.findAll();
    }
    
    @GetMapping(value = "/{id}")
    public Optional<Cliente> getById(@PathVariable Long id){
        return clienteRepo.findById(id);
    }
    
    @PostMapping
    public Cliente adicionar(@RequestBody Cliente cliente) throws NoSuchAlgorithmException, UnsupportedEncodingException{
        cliente.setSenha(Criptografia.criptografar(cliente.getSenha()));
        return clienteRepo.save(cliente);
    }
    
    @PutMapping(value="/{id}")
    public ResponseEntity editar(@PathVariable Long id, @RequestBody Cliente cliente) {
        return clienteRepo.findById(id)
                .map(record -> {
                    record.setNome(cliente.getNome());
                    record.setEmail(cliente.getEmail());
                    record.setTelefone(cliente.getTelefone());
            try {
                record.setSenha(Criptografia.criptografar(cliente.getSenha()));
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
            }
                    Cliente clienteUpdated = clienteRepo.save(record);
                    return ResponseEntity.ok().body(clienteUpdated);
                }).orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping(value="/{id}")
    public ResponseEntity deletar(@PathVariable Long id) {
        return clienteRepo.findById(id)
                .map(record-> {
                    clienteRepo.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
