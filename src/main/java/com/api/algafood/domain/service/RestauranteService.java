package com.api.algafood.domain.service;


import com.api.algafood.domain.Exception.EntidadeNaoEncontradaException;
import com.api.algafood.domain.model.Restaurante;
import com.api.algafood.domain.repository.restaurante.RestauranteRepository;
import org.springframework.stereotype.Service;

@Service
public class RestauranteService {

    private static final String MSG_RESTAURANTE_EM_USO =
            "Entity 'Restaurante' with identifier %d is in use";

    private static final String MSG_RESTAURANTE_NAO_ENCONTRADO =
            "Entity 'Restaurante' with identifier %d not found";

    private RestauranteRepository repository;
    private CozinhaService cozinhaService;

    public RestauranteService(RestauranteRepository repository,
                              CozinhaService cozinhaService) {
        this.repository = repository;
        this.cozinhaService = cozinhaService;
    }

    public Restaurante save(Restaurante restaurante) {
        cozinhaService.findById(restaurante.getCozinha().getId());
        return repository.save(restaurante);
    }

    public Restaurante findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        String.format(MSG_RESTAURANTE_NAO_ENCONTRADO, id)));
    }
}
