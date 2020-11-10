package com.api.algafood.api.model.representation.restaurante;

import javax.validation.constraints.NotNull;

public class RestauranteAssociacao {

    @NotNull
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
