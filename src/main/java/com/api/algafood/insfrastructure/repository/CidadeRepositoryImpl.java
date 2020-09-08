package com.api.algafood.insfrastructure.repository;

import com.api.algafood.domain.Exception.EntidadeNaoEncontradaException;
import com.api.algafood.domain.model.Cidade;
import com.api.algafood.domain.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Component
public class CidadeRepositoryImpl implements CidadeRepository {

    @Autowired
    private EntityManager manager;

    @Override
    public List<Cidade> findAll() {
        return manager.createQuery("from Cidade", Cidade.class).getResultList();
    }

    @Override
    public Cidade find(Long id) {
        var cidade = manager.find(Cidade.class, id);

        if (cidade == null) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Entity with identifier %d not found", id));
        }
        return cidade;
    }

    @Override
    @Transactional
    public Cidade save(Cidade cidade) {
        return manager.merge(cidade);
    }

    @Override
    @Transactional
    public void remove(Long id) {
        var cidade = find(id);

        if(cidade == null){
            throw new EmptyResultDataAccessException(1);
        }
        manager.remove(cidade);
    }
}
