package com.api.algafood.insfrastructure.repository;

import com.api.algafood.domain.model.FormaPagamento;
import com.api.algafood.domain.repository.FormaPagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.swing.*;
import java.util.List;

@Component
public class FormaPagamentoImpl implements FormaPagamentoRepository {

    @Autowired
    private EntityManager manager;

    @Override
    public List<FormaPagamento> listar() {
        return manager.createQuery("from FormaPagamento",FormaPagamento.class).getResultList();
    }

    @Override
    public FormaPagamento find(Long id) {
        return manager.find(FormaPagamento.class,id);
    }

    @Override
    @Transactional
    public FormaPagamento salvar(FormaPagamento formaPagamento) {
        return manager.merge(formaPagamento);
    }

    @Override
    @Transactional
    public void remover(FormaPagamento formaPagamento) {
        formaPagamento = find(formaPagamento.getId());
        manager.remove(formaPagamento);
    }
}
