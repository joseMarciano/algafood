package com.api.algafood.api.model.representation.restaurante;

import com.api.algafood.api.model.representation.endereco.EnderecoCompletaListagem;
import com.api.algafood.api.model.representation.cozinha.CozinhaCompletaListagem;

import java.math.BigDecimal;

public class RestauranteCompletaListagem {
    private Long id;
    private String nome;
    private BigDecimal taxaFrete;
    private CozinhaCompletaListagem cozinha;
    private Boolean flAtivo;
    private EnderecoCompletaListagem endereco;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getTaxaFrete() {
        return taxaFrete;
    }

    public void setTaxaFrete(BigDecimal taxaFrete) {
        this.taxaFrete = taxaFrete;
    }

    public CozinhaCompletaListagem getCozinha() {
        return cozinha;
    }

    public void setCozinha(CozinhaCompletaListagem cozinha) {
        this.cozinha = cozinha;
    }

    public Boolean getFlAtivo() {
        return flAtivo;
    }

    public void setFlAtivo(Boolean flAtivo) {
        this.flAtivo = flAtivo;
    }

    public EnderecoCompletaListagem getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoCompletaListagem endereco) {
        this.endereco = endereco;
    }
}
