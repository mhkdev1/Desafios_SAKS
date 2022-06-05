package com.br.saks.interesseservice.repository;

import java.util.List;
import java.util.Optional;
import com.br.saks.interesseservice.model.Interesse;
import com.br.saks.interesseservice.model.InteresseIdentity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InteresseRepository extends JpaRepository<Interesse, InteresseIdentity>{
    List<Interesse> findByInteresseIdentityIdCliente(Long idCliente);
    
}