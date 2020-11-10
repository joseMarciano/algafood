package com.api.algafood.api.model.representation.formapagamento;

import javax.validation.constraints.NotNull;

public class FormaPagamentoAssociacao {

    @NotNull
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
