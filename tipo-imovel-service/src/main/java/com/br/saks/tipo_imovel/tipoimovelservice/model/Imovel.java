package com.br.saks.tipo_imovel.tipoimovelservice.model;

import java.util.Date;

public class Imovel {
    private Long id;
    private Long idTipoImovel;
    private String titulo;
    private String descricao;
    private Date dataCriacao;
    private float valor;
    private int status;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdTipoImovel() {
        return this.idTipoImovel;
    }

    public void setIdTipoImovel(Long idTipoImovel) {
        this.idTipoImovel = idTipoImovel;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataCriacao() {
        return this.dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public float getValor() {
        return this.valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    
}
