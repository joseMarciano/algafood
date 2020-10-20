package com.api.algafood.api.model.representation.cozinha;

import javax.validation.constraints.NotNull;

public class CozinhaAssociacao {

    @NotNull
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
