package com.api.algafood.domain.repository;

import com.api.algafood.domain.model.Permissao;

import java.util.List;

public interface PermissaoRepository {

    List<Permissao> findAll();

    Permissao find(Long id);

    Permissao save(Permissao permissao);

    void remove(Permissao permissao);
}
