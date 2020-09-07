package com.api.algafood.insfrastructure.repository;

import com.api.algafood.domain.model.Restaurante;
import com.api.algafood.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Component
public class RestauranteRepositoryImpl implements RestauranteRepository {

    @Autowired
    private EntityManager manager;

    @Override
    public List<Restaurante> findAll() {
        return manager.createQuery("from Restaurante",Restaurante.class).getResultList();
    }

    @Override
    public Restaurante find(Long id) {
        return manager.find(Restaurante.class,id);
    }

    @Override
    @Transactional
    public Restaurante save(Restaurante restaurante) {
        return manager.merge(restaurante);
    }

    @Override
    @Transactional
    public void remove(Restaurante restaurante) {
        restaurante = find(restaurante.getId());
        manager.remove(restaurante);
    }
}
