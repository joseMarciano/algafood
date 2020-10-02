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

    private static final String MSG_COZINHA_EM_USO =
            "Entity 'Cozinha' with identifier %s is in use";

    private static final String MSG_COZINHA_NAO_ENCONTRADO =
            "Entity 'Cozinha' with identifier %d not found";

    private CozinhaRepository repository;

    public CozinhaService(CozinhaRepository repository) {
        this.repository = repository;
    }

    public Cozinha save(Cozinha cozinha) {
        return repository.save(cozinha);
    }

    public void remove(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(String.format(MSG_COZINHA_NAO_ENCONTRADO, id));
        } catch (DataIntegrityViolationException e) { // se tem conflito de constrait(FK em outra table)
            throw new EntidadeEmUsoException(
                    String.format(MSG_COZINHA_EM_USO, repository.findById(id).get().getNome(), id));  //lança a exception que criei
        }
    }

    public Cozinha findById(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new EntidadeNaoEncontradaException(String.format(MSG_COZINHA_NAO_ENCONTRADO, id)));
    }
}
