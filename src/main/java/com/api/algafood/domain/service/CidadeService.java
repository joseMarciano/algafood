package com.api.algafood.domain.service;


import com.api.algafood.domain.Exception.EntidadeEmUsoException;
import com.api.algafood.domain.Exception.EntidadeNaoEncontradaException;
import com.api.algafood.domain.model.Cidade;
import com.api.algafood.domain.repository.CidadeRepository;
import com.api.algafood.domain.repository.EstadoRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CidadeService {

    private CidadeRepository repository;

    public CidadeService(CidadeRepository repository, EstadoRepository estadoRepository) {
        this.repository = repository;
    }

    public Cidade save(Cidade cidade) {
        var estado = repository.find(cidade.getEstado().getId());

        if (estado == null) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Entity with identifier %d not found", cidade.getEstado().getId()));
        }

        return repository.save(cidade);
    }

    public void remove(Long id) {
        try {
            repository.remove(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Entity with identifier %d not found", id));

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format("Entity with identifier %d is in use", id));
        }
    }
}

