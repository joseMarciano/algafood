package com.api.algafood.domain.service;


import com.api.algafood.domain.Exception.EntidadeEmUsoException;
import com.api.algafood.domain.Exception.EntidadeNaoEncontradaException;
import com.api.algafood.domain.model.Cidade;
import com.api.algafood.domain.model.Estado;
import com.api.algafood.domain.repository.CidadeRepository;
import com.api.algafood.domain.repository.EstadoRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CidadeService {

    private CidadeRepository repository;
    private EstadoRepository estadoRepository;

    public CidadeService(CidadeRepository repository,
                         EstadoRepository estadoRepository) {
        this.repository = repository;
        this.estadoRepository = estadoRepository;
    }

    public Cidade save(Cidade cidade) {
        Optional<Estado> estado = estadoRepository.findById(cidade.getEstado().getId());

        estado.orElseThrow(() ->
                new EntidadeNaoEncontradaException(String.format("Entity %d not found",cidade.getEstado().getId())));

        return repository.save(cidade);
    }

    public void remove(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Entity with identifier %d not found", id));

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format("Entity with identifier %d is in use", id));
        }
    }
}

