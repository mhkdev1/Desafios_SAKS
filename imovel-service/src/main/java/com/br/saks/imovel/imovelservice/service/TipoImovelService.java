package com.br.saks.imovel.imovelservice.service;

import com.br.saks.imovel.imovelservice.model.TipoImovel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "tipo-imovel-service")
public interface TipoImovelService {
    @GetMapping(value = "/tipo_imovel/{idTipoImovel}")
    TipoImovel getById(@PathVariable("idTipoImovel") long idTIpoImovel);
}
