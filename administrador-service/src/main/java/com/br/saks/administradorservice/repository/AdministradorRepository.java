package com.br.saks.administradorservice.repository;

import com.br.saks.administradorservice.model.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministradorRepository extends JpaRepository<Administrador, Long>{
    
}
