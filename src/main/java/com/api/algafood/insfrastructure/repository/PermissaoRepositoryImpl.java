package com.api.algafood.insfrastructure.repository;

import com.api.algafood.domain.model.Permissao;
import com.api.algafood.domain.repository.PermissaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Component
public class PermissaoRepositoryImpl implements PermissaoRepository {

    @Autowired
    private EntityManager manager;

    @Override
    public List<Permissao> listar() {
        return manager.createQuery("from Permissao", Permissao.class).getResultList();
    }

    @Override
    public Permissao find(Long id) {
        return manager.find(Permissao.class,id);
    }

    @Override
    @Transactional
    public Permissao salvar(Permissao permissao) {
        return manager.merge(permissao);
    }

    @Override
    @Transactional
    public void remover(Permissao permissao) {
        permissao = find(permissao.getId());
        manager.remove(permissao);
    }
}
