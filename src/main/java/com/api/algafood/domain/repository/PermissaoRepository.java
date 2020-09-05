package com.api.algafood.domain.repository;

import com.api.algafood.domain.model.Permissao;

import java.util.List;

public interface PermissaoRepository {

    List<Permissao> listar();

    Permissao find(Long id);

    Permissao salvar(Permissao permissao);

    void remover(Permissao permissao);
}
