package com.br.saks.imovelservice.controller;

import com.br.saks.imovelservice.model.Imovel;
import com.br.saks.imovelservice.repository.ImovelRepository;
import com.br.saks.imovelservice.service.TipoImovelService;
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
    ImovelRepository imovelRepository;
    
    @Autowired
    private TipoImovelService tipoService;
    
    @GetMapping
    public List<Imovel> getAll(){ 
        List<Imovel> imoveis = imovelRepository.findAll();
        
        for(Imovel imovel: imoveis){
            imovel.setTipoImovel(tipoService.getById(imovel.getId_Tipo_Imovel()));
        }
        
        return imoveis;
    }
    
    @GetMapping(value = "/{id}")
    public Imovel getById(@PathVariable Long id){
        Optional<Imovel> imovelResponse = imovelRepository.findById(id);
        Imovel imovel = imovelResponse.get();
        imovel.setTipoImovel(tipoService.getById(imovel.getId_Tipo_Imovel()));
        return imovel;
    }
    
    
    
    

    @GetMapping(value = "/tipo/{idTipo}")
    public List<Imovel> tipo(@PathVariable Long idTipo){
        return imovelRepository.findImovelByIdTipoImovel(idTipo);
    }
    
    
    
    @PostMapping
    public Imovel adicionar(@RequestBody Imovel cliente){
        return imovelRepository.save(cliente);
    }
    
    
    
    
    
    @PutMapping(value="/{id}")
    public ResponseEntity editar(@PathVariable Long id, @RequestBody Imovel cliente) {
        return imovelRepository.findById(id)
                .map(record -> {
                    record.setId_Tipo_Imovel(cliente.getId_Tipo_Imovel());
                    record.setTitulo(cliente.getTitulo());
                    record.setDescricao(cliente.getDescricao());
                    record.setDataCriacao(cliente.getDataCriacao());
                    record.setValor(cliente.getValor());
                    record.setStatus(cliente.getStatus());
                    Imovel clienteUpdated = imovelRepository.save(record);
                    return ResponseEntity.ok().body(clienteUpdated);
                }).orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping(value="/{id}")
    public ResponseEntity deletar(@PathVariable Long id) {
        return imovelRepository.findById(id)
                .map(record-> {
                    imovelRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
