package com.api.algafood.api.model.representation.cidade;

import com.api.algafood.api.model.representation.estado.EstadoAssociacao;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CidadeCompleta {


    private Long id;
    @NotBlank
    private String nome;

    @Valid
    @NotNull
    private EstadoAssociacao estado;

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

    public EstadoAssociacao getEstado() {
        return estado;
    }

    public void setEstado(EstadoAssociacao estado) {
        this.estado = estado;
    }
}
