package com.br.saks.imovel.imovelservice.repository;

import java.util.List;

import com.br.saks.imovel.imovelservice.model.Imovel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImovelRepository extends JpaRepository<Imovel, Long>{
    List<Imovel> findImovelByIdTipoImovel(Long idTipo);
}

