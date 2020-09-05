package com.api.algafood.domain.repository;

import com.api.algafood.domain.model.Estado;

import java.util.List;

public interface EstadoRepository {
    List<Estado> listar();

    Estado find(Long id);


    Estado salvar(Estado estado);

    void remover(Estado estado);
}
