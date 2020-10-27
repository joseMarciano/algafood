package com.api.algafood.api.model.representation.cidade;

import javax.validation.constraints.NotNull;

public class CidadeAssociacao {

    @NotNull
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
