package com.api.algafood.api.model.representation.cozinha;

import com.api.algafood.domain.model.Restaurante;

import java.util.ArrayList;
import java.util.List;

public class CozinhaCompleta {

    private Long id;

    private List<Restaurante> restaurantes = new ArrayList<>();

    private String nome;

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

    public List<Restaurante> getRestaurantes() {
        return restaurantes;
    }

    public void setRestaurantes(List<Restaurante> restaurantes) {
        this.restaurantes = restaurantes;
    }
}
