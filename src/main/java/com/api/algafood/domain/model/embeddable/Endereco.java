package com.api.algafood.domain.model.embeddable;

import com.api.algafood.domain.model.Cidade;

import javax.persistence.*;

@Embeddable
public class Endereco {

    @Column(name = "ENDERECO_CEP")
    private String cep;

    @Column(name = "ENDERECO_LOGRADOURO")
    private String logradouro;

    @Column(name = "ENDERECO_NUMERO")
    private String numero;

    @Column(name = "ENDERECO_COMPLEMENTO")
    private String complemento;

    @Column(name = "ENDERECO_BAIRRO")
    private String bairro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ENDERECO_CIDADE")
    private Cidade cidade;

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }
}
