package com.api.algafood.api.model.representation.endereco;

import com.api.algafood.api.model.representation.cidade.CidadeListagemSimples;

public class EnderecoCompletaListagem {

    private String cep;

    private String logradouro;

    private String numero;

    private String complemento;

    private String bairro;

    private CidadeListagemSimples cidade;


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

    public CidadeListagemSimples getCidade() {
        return cidade;
    }

    public void setCidade(CidadeListagemSimples cidade) {
        this.cidade = cidade;
    }
}

