package com.api.algafood.domain.repository;

import com.api.algafood.domain.model.Restaurante;

import java.util.List;

public interface RestauranteRepository {
    List<Restaurante> findAll();

    Restaurante find(Long id);

    Restaurante save(Restaurante restaurante);

    void remove(Restaurante restaurante);
}
