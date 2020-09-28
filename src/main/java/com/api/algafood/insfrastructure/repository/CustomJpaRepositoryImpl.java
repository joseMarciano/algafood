package com.api.algafood.insfrastructure.repository;

import com.api.algafood.domain.repository.CustomJpaRepository;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;


public class CustomJpaRepositoryImpl<T, ID> extends SimpleJpaRepository<T, ID> implements CustomJpaRepository<T, ID> {

    private EntityManager manager;


    public CustomJpaRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);

        this.manager = entityManager;

    }

//    @Override
//    public Optional<T> buscarPrimeiro() {
//        var jpql = "from Restaurante" ;
//
//        T entity = manager.createQuery(jpql, getDomainClass()).setMaxResults(1).getSingleResult();
//
//        return Optional.ofNullable(entity);
//    }

//    @Override
//    public Optional<T> findEntityById(Long id) {
//        var jpql = "from " + ;
//        T entity = manager.createQuery()
//    }


//    @Override
//    public T findEntityById(Long id) {
//        return takeResultFilter("where id=" + id)
//                .orElseThrow(() -> new EntidadeNaoEncontradaException(String.format("Entity %d not found",id)));
//    }
//
//    Optional<T> takeResultFilter(String filter){
//        var jpql = "from " + getDomainClass().getSimpleName() + " " + filter;
//        T entity = manager.createQuery(jpql, getDomainClass()).getSingleResult();
//        return Optional.ofNullable(entity);
//    }
}
