package com.api.algafood.insfrastructure.repository;

import com.api.algafood.domain.model.FormaPagamento;
import com.api.algafood.domain.repository.FormaPagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class FormaPagamentoImpl implements FormaPagamentoRepository {

    @Autowired
    private EntityManager manager;

    @Override
    public List<FormaPagamento> findAll() {
        return manager.createQuery("from FormaPagamento", FormaPagamento.class).getResultList();
    }

    @Override
    public FormaPagamento find(Long id) {
        return manager.find(FormaPagamento.class, id);
    }

    @Override
    @Transactional
    public FormaPagamento save(FormaPagamento formaPagamento) {
        return manager.merge(formaPagamento);
    }

    @Override
    @Transactional
    public void remove(FormaPagamento formaPagamento) {
        formaPagamento = find(formaPagamento.getId());
        manager.remove(formaPagamento);
    }
}
