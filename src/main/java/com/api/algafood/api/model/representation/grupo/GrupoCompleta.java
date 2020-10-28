package com.api.algafood.api.model.representation.grupo;

import javax.validation.constraints.NotBlank;

public class GrupoCompleta {

    @NotBlank
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
