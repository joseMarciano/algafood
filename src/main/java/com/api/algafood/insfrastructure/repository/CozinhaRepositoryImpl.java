package com.api.algafood.insfrastructure.repository;

import com.api.algafood.domain.model.Cozinha;
import com.api.algafood.domain.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class CozinhaRepositoryImpl implements CozinhaRepository {

    @Autowired
    private EntityManager manager;

    @Override
    public List<Cozinha> findAll(){
        return manager.createQuery("from Cozinha",Cozinha.class).getResultList();
    }

    @Override
    public Cozinha find(Long id){
        return manager.find(Cozinha.class,id);
    }

    @Override
    @Transactional
    public Cozinha save(Cozinha cozinha){
        return manager.merge(cozinha);
    }

    @Override
    @Transactional
    public void remove(Long id){
        var cozinha = find(id);

        if (cozinha == null) {
            throw new EmptyResultDataAccessException(1);
        }
        manager.remove(cozinha);
    }
}
