package com.api.algafood.api.model.representation.restaurante;


import com.api.algafood.api.model.representation.cozinha.CozinhaAssociacao;
import com.api.algafood.api.model.representation.endereco.EnderecoCompleta;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

public class RestauranteCompleta {

    @NotBlank
    private String nome;

    @PositiveOrZero
    @NotNull
    private BigDecimal taxaFrete;

    @Valid
    @NotNull
    private CozinhaAssociacao cozinha;

    @Valid
    @NotNull
    EnderecoCompleta endereco;

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

    public CozinhaAssociacao getCozinha() {
        return cozinha;
    }

    public void setCozinha(CozinhaAssociacao cozinha) {
        this.cozinha = cozinha;
    }

    public EnderecoCompleta getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoCompleta endereco) {
        this.endereco = endereco;
    }
}
