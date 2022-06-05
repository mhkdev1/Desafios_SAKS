package com.br.saks.imovelservice.service;

import com.br.saks.imovelservice.model.TipoImovel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "tipo-imovel-service")
public interface TipoImovelService {
    @GetMapping(value = "/tipo_imovel/{idTipoImovel}")
    TipoImovel getById(@PathVariable("idTipoImovel") long idTIpoImovel);
}
