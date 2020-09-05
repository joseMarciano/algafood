package com.api.algafood.domain.repository;

import com.api.algafood.domain.model.Cidade;
import com.api.algafood.domain.model.Cozinha;

import java.util.List;

public interface CidadeRepository {
    List<Cidade> listar();

    Cidade find(Long id);

    Cidade salvar(Cidade cidade);

    void remover(Cidade cidade);

}
