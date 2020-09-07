package com.api.algafood.domain.repository;

import com.api.algafood.domain.model.Estado;

import java.util.List;

public interface EstadoRepository {
    List<Estado> findAll();

    Estado find(Long id);


    Estado save(Estado estado);

    void remove(Estado estado);
}
