package com.api.algafood.insfrastructure.repository;

import com.api.algafood.domain.model.Permissao;
import com.api.algafood.domain.repository.PermissaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class PermissaoRepositoryImpl implements PermissaoRepository {

    @Autowired
    private EntityManager manager;

    @Override
    public List<Permissao> findAll() {
        return manager.createQuery("from Permissao", Permissao.class).getResultList();
    }

    @Override
    public Permissao find(Long id) {
        return manager.find(Permissao.class,id);
    }

    @Override
    @Transactional
    public Permissao save(Permissao permissao) {
        return manager.merge(permissao);
    }

    @Override
    @Transactional
    public void remove(Permissao permissao) {
        permissao = find(permissao.getId());
        manager.remove(permissao);
    }
}
