package com.api.algafood.insfrastructure.repository;

import com.api.algafood.domain.model.Estado;
import com.api.algafood.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
        return manager.createQuery("from Estado",Estado.class).getResultList();
    }

    @Override
    public Estado find(Long id) {
        return manager.find(Estado.class,id);
    }

    @Override
    @Transactional
    public Estado save(Estado estado) {
        return manager.merge(estado);
    }

    @Override
    @Transactional
    public void remove(Estado estado) {
        estado = find(estado.getId());
        manager.remove(estado);
    }
}
