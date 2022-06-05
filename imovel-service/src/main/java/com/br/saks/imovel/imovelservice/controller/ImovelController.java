package com.br.saks.imovel.imovelservice.controller;

import com.br.saks.imovel.imovelservice.model.Imovel;
import com.br.saks.imovel.imovelservice.repository.ImovelRepository;
import com.br.saks.imovel.imovelservice.service.TipoImovelService;
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
@RequestMapping("/imovel")
public class ImovelController {
    @Autowired
    ImovelRepository imovelRepo;
    
    @Autowired
    private TipoImovelService tipoService;
    
    @GetMapping
    public List<Imovel> getAll(){ 
        List<Imovel> imoveis = imovelRepo.findAll();
        
        for(Imovel imovel: imoveis){
            imovel.setTipoImovel(tipoService.getById(imovel.getIdTipoImovel()));
        }
        
        return imoveis;
    }
    
    @GetMapping(value = "/{id}")
    public Imovel getById(@PathVariable Long id){
        Optional<Imovel> imovelResponse = imovelRepo.findById(id);
        Imovel imovel = imovelResponse.get();
        imovel.setTipoImovel(tipoService.getById(imovel.getIdTipoImovel()));
        return imovel;
    }

    @GetMapping(value = "/getByTipo/{idTipo}")
    public List<Imovel> getByIdTipo(@PathVariable Long idTipo){
        return imovelRepo.findImovelByIdTipoImovel(idTipo);
    }
    
    @PostMapping
    public Imovel adicionar(@RequestBody Imovel cliente){
        return imovelRepo.save(cliente);
    }
    
    @PutMapping(value="/{id}")
    public ResponseEntity editar(@PathVariable Long id, @RequestBody Imovel cliente) {
        return imovelRepo.findById(id)
                .map(record -> {
                    record.setIdTipoImovel(cliente.getIdTipoImovel());
                    record.setTitulo(cliente.getTitulo());
                    record.setDescricao(cliente.getDescricao());
                    record.setDataCriacao(cliente.getDataCriacao());
                    record.setValor(cliente.getValor());
                    record.setStatus(cliente.getStatus());
                    Imovel clienteUpdated = imovelRepo.save(record);
                    return ResponseEntity.ok().body(clienteUpdated);
                }).orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping(value="/{id}")
    public ResponseEntity deletar(@PathVariable Long id) {
        return imovelRepo.findById(id)
                .map(record-> {
                    imovelRepo.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
