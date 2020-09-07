package com.api.algafood.domain.repository;

import com.api.algafood.domain.model.Cidade;
import com.api.algafood.domain.model.Cozinha;

import java.util.List;

public interface CidadeRepository {
    List<Cidade> findAll();

    Cidade find(Long id);

    Cidade save(Cidade cidade);

    void remove(Cidade cidade);

}
