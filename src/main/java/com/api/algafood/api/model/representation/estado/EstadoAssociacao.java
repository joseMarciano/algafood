package com.api.algafood.api.model.representation.estado;

import javax.validation.constraints.NotNull;

public class EstadoAssociacao {

    @NotNull
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
