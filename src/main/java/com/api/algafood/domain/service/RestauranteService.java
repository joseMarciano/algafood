package com.api.algafood.domain.service;


import com.api.algafood.domain.Exception.EntidadeNaoEncontradaException;
import com.api.algafood.domain.model.Restaurante;
import com.api.algafood.domain.repository.restaurante.RestauranteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RestauranteService {

    private static final String MSG_RESTAURANTE_EM_USO =
            "Entity 'Restaurante' with identifier %d is in use";

    private static final String MSG_RESTAURANTE_NAO_ENCONTRADO =
            "Entity 'Restaurante' with identifier %d not found";

    private RestauranteRepository repository;
    private CozinhaService cozinhaService;
    private CidadeService cidadeService;

    public RestauranteService(RestauranteRepository repository,
                              CozinhaService cozinhaService,
                              CidadeService cidadeService) {
        this.repository = repository;
        this.cozinhaService = cozinhaService;
        this.cidadeService = cidadeService;
    }

    @Transactional
    public Restaurante save(Restaurante restaurante) {
        //Resolvendo o problema de retornar a prop cozinha e cidade(Dentro de endereco) NULL  na hora de salvar ou update
        var cozinha = cozinhaService.findById(restaurante.getCozinha().getId());
        var enderecoCidade = cidadeService.findById(restaurante.getEndereco().getCidade().getId());
        restaurante.setCozinha(cozinha);
        restaurante.getEndereco().setCidade(enderecoCidade);

        return repository.save(restaurante);
    }

    @Transactional
    public void ativar(Long id){
        var restaurante = findById(id);
        restaurante.ativar();
    }

    @Transactional
    public void inativar(Long id){
        var restaurante = findById(id);
        restaurante.inativar();
    }

    public Restaurante findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        String.format(MSG_RESTAURANTE_NAO_ENCONTRADO, id)));
    }


}
