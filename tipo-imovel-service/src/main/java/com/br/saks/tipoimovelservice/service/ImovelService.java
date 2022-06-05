package com.br.saks.tipoimovelservice.service;

import java.util.List;
import com.br.saks.tipoimovelservice.model.Imovel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "imovel-service")
public interface ImovelService {
    @GetMapping(value = "/getByTipo/{idTipo}")
    List<Imovel> getByIdTipo(@PathVariable("idTipo") Long idTipo);
}
