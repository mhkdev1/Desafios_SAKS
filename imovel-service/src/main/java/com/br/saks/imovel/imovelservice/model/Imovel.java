package com.br.saks.imovel.imovelservice.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import lombok.Data;

@Data
@Entity
public class Imovel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, name = "id_tipo_imovel")
    private Long idTipoImovel;
    
    @Column(nullable = false, length = 100)
    private String titulo;
    
    @Column(length = 500)
    private String descricao;
    
    @Column(name = "data_criacao", nullable = false)
    private Date dataCriacao;
    
    @Column(precision = 8, scale = 2)
    private float valor;
    
    @Column(nullable = false)
    private int status;
    
    @Transient
    TipoImovel tipoImovel;

}
