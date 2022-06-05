package com.br.saks.administradorservice.controller;

import com.br.saks.administradorservice.criptografia.Criptografia;
import com.br.saks.administradorservice.model.Administrador;
import com.br.saks.administradorservice.repository.AdministradorRepository;
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
@RequestMapping("/administrador")
public class AdministradorController {
    @Autowired
    AdministradorRepository admRepo;
    
    @GetMapping
    public List<Administrador> getAll(){
        return admRepo.findAll();
    }
    
    @GetMapping(value = "/{id}")
    public Optional<Administrador> getById(@PathVariable Long id){
        return admRepo.findById(id);
    }
    
    @PostMapping
    public Administrador adicionar(@RequestBody Administrador adm) throws NoSuchAlgorithmException, UnsupportedEncodingException{
        adm.setSenha(Criptografia.criptografar(adm.getSenha()));
        return admRepo.save(adm);
    }
    
    @PutMapping(value="/{id}")
    public ResponseEntity editar(@PathVariable Long id, @RequestBody Administrador adm) {
        return admRepo.findById(id)
                .map(record -> {
                    record.setNome(adm.getNome());
                    record.setEmail(adm.getEmail());
            try {
                record.setSenha(Criptografia.criptografar(adm.getSenha()));
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(AdministradorController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(AdministradorController.class.getName()).log(Level.SEVERE, null, ex);
            }
                    record.setStatus(adm.getStatus());
                    Administrador admUpdated = admRepo.save(record);
                    return ResponseEntity.ok().body(admUpdated);
                }).orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping(value="/{id}")
    public ResponseEntity deletar(@PathVariable Long id) {
        return admRepo.findById(id)
                .map(record-> {
                    record.setStatus(0);
                    Administrador admDeleted = admRepo.save(record);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
