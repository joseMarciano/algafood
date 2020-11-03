package com.api.algafood.api.model.representation.permissao;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PermissaoCompleta {

    @NotNull
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    private String descricao;

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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
