package com.api.algafood.domain.repository;

import com.api.algafood.domain.model.Cozinha;

import java.util.List;

public interface CozinhaRepository {
    List<Cozinha> findAll();

    Cozinha find(Long id);

    Cozinha save(Cozinha cozinha);

    void remove(Long id);
}
