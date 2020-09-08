package com.api.algafood.domain.service;

import com.api.algafood.domain.Exception.EntidadeEmUsoException;
import com.api.algafood.domain.Exception.EntidadeNaoEncontradaException;
import com.api.algafood.domain.model.Cozinha;
import com.api.algafood.domain.repository.CozinhaRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CozinhaService {

    private CozinhaRepository repository;

    public CozinhaService(CozinhaRepository repository) {
        this.repository = repository;
    }

    public Cozinha save(Cozinha cozinha){
        return repository.save(cozinha);
    }

    public void remove(Long id){
        try {
            repository.remove(id);
        } catch (EmptyResultDataAccessException e){
            throw new EntidadeNaoEncontradaException(String.format("Entity %d not found",id));
        }catch (DataIntegrityViolationException e) { // se tem conflito de constrait(FK em outra table)
            throw new EntidadeEmUsoException(
                    String.format("Entity %s with identifier %d is in use",repository.find(id).getNome(),id));  //lança a exception que criei
        }
    }

}
