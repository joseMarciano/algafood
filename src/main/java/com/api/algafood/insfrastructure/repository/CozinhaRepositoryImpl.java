package com.api.algafood.insfrastructure.repository;

import com.api.algafood.domain.model.Cozinha;
import com.api.algafood.domain.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Component
public class CozinhaRepositoryImpl implements CozinhaRepository {

    @Autowired
    private EntityManager manager;

    @Override
    public List<Cozinha> listar(){
        return manager.createQuery("from Cozinha",Cozinha.class).getResultList();
    }

    @Override
    public Cozinha find(Long id){
        return manager.find(Cozinha.class,id);
    }

    @Override
    @Transactional
    public Cozinha salvar(Cozinha cozinha){
        return manager.merge(cozinha);
    }

    @Override
    @Transactional
    public void remover(Cozinha cozinha){
        cozinha = find(cozinha.getId());
        manager.remove(cozinha);
    }
}
