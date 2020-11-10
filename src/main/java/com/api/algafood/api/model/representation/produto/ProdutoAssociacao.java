package com.api.algafood.api.model.representation.produto;

import javax.validation.constraints.NotNull;

public class ProdutoAssociacao {

    @NotNull
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
