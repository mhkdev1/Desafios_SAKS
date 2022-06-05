package com.br.saks.tipoimovelservice.controller;

import com.br.saks.tipoimovelservice.model.Imovel;
import com.br.saks.tipoimovelservice.model.TipoImovel;
import com.br.saks.tipoimovelservice.repository.TipoImovelRepository;
import com.br.saks.tipoimovelservice.service.ImovelService;

import java.util.List;
import java.util.Optional;
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
@RequestMapping("/tipo_imovel")
public class TipoImovelController {
    @Autowired
    TipoImovelRepository tipoImovelRepo;
    
    @Autowired
    private ImovelService imovelService;

    @GetMapping
    public List<TipoImovel> getAll(){
        List<TipoImovel> tipoImoveis = tipoImovelRepo.findAll();
        
        for(TipoImovel tipo : tipoImoveis){
            tipo.setImovel(imovelService.getByIdTipo(tipo.getId()));
        }

        return tipoImoveis;
    }
    
    @GetMapping(value = "/{id}")
    public Optional<TipoImovel> getById(@PathVariable Long id){
        return tipoImovelRepo.findById(id);
    }
    
    @PostMapping
    public TipoImovel adicionar(@RequestBody TipoImovel tipo){
        return tipoImovelRepo.save(tipo);
    }
    
    @PutMapping(value="/{id}")
    public ResponseEntity editar(@PathVariable Long id, @RequestBody TipoImovel tipo) {
        return tipoImovelRepo.findById(id)
                .map(record -> {
                    record.setNome(tipo.getNome());
                    TipoImovel tipoUpdated = tipoImovelRepo.save(record);
                    return ResponseEntity.ok().body(tipoUpdated);
                }).orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping(value="/{id}")
    public ResponseEntity deletar(@PathVariable Long id) {
        return tipoImovelRepo.findById(id)
                .map(record-> {
                    tipoImovelRepo.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }

}
