package com.br.saks.interesseservice.controller;

import java.util.List;
import java.util.Optional;

import com.br.saks.interesseservice.model.Interesse;
import com.br.saks.interesseservice.model.InteresseIdentity;
import com.br.saks.interesseservice.repository.InteresseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/interesse")
public class InteresseController {
    @Autowired
    InteresseRepository interesseRepo;

    @GetMapping
    public List<Interesse> getAll(){ 
        return interesseRepo.findAll();
    }
    
    @GetMapping(value = "/{idCliente}")
    public List<Interesse> getByIdCliente(@PathVariable Long idCliente){
        return interesseRepo.findByInteresseIdentityIdCliente(idCliente);
    }

    @GetMapping(value = "/{idCliente}/{idImovel}")
    public Optional<Interesse> getByIdClienteImovel(@PathVariable Long idCliente, @PathVariable Long idImovel){
        final InteresseIdentity identity = new InteresseIdentity(idCliente, idImovel);
        return interesseRepo.findById(identity);
    }
    
    @PostMapping
    public Interesse adicionar(@RequestBody Interesse interesse){
        return interesseRepo.save(interesse);
    }

    @DeleteMapping(value="/{idCliente}/{idImovel}")
    public ResponseEntity deletar(@PathVariable Long idCliente, @PathVariable Long idImovel) {
        final InteresseIdentity identity = new InteresseIdentity(idCliente, idImovel);
        return interesseRepo.findById(identity)
            .map(record-> {
                interesseRepo.deleteById(identity);
                return ResponseEntity.ok().build();
            }).orElse(ResponseEntity.notFound().build());
    }
}
