package com.br.saks.tipo_imovel.tipoimovelservice.repository;

import com.br.saks.tipo_imovel.tipoimovelservice.model.TipoImovel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoImovelRepository extends JpaRepository<TipoImovel, Long>{
    
}
