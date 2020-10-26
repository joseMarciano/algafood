package com.api.algafood.api.model.representation.formapagamento;

import javax.validation.constraints.NotBlank;

public class FormaPagamentoCompleta {

    @NotBlank
    private String descricao;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
