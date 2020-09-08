package com.api.algafood.insfrastructure.repository;

import com.api.algafood.domain.Exception.EntidadeNaoEncontradaException;
import com.api.algafood.domain.model.Estado;
import com.api.algafood.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Component
public class EstadoRepositoryImpl implements EstadoRepository {

    @Autowired
    private EntityManager manager;

    @Override
    public List<Estado> findAll() {
        return manager.createQuery("from Estado", Estado.class).getResultList();
    }

    @Override
    public Estado find(Long id) {
        var estado = manager.find(Estado.class, id);

        if (estado == null) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Entity with identifier %d not found", id));
        }
        return estado;
    }

    @Override
    @Transactional
    public Estado save(Estado estado) {
        return manager.merge(estado);
    }

    @Override
    @Transactional
    public void remove(Long id) {
      var estado = find(id);

      if(estado == null) throw new EmptyResultDataAccessException(1);

      manager.remove(estado);

    }
}
