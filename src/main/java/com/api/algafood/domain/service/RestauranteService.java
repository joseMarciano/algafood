package com.api.algafood.domain.service;


import com.api.algafood.domain.Exception.EntidadeNaoEncontradaException;
import com.api.algafood.domain.model.Restaurante;
import com.api.algafood.domain.repository.CozinhaRepository;
import com.api.algafood.domain.repository.RestauranteRepository;
import org.springframework.stereotype.Service;

@Service
public class RestauranteService {

    private RestauranteRepository repository;
    private CozinhaRepository cozinhaRepository;

    public RestauranteService(RestauranteRepository repository,
                              CozinhaRepository cozinhaRepository) {
        this.repository = repository;
        this.cozinhaRepository = cozinhaRepository;
    }

    public Restaurante save(Restaurante restaurante){
        var cozinha = cozinhaRepository.find(restaurante.getCozinha().getId());

        if(cozinha == null) {
            throw new EntidadeNaoEncontradaException(
                    String.format(
                            "Entity with identifier %d not found",restaurante.getCozinha().getId()));
        }

        return repository.save(restaurante);
    }
}
