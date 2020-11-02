package com.api.algafood.domain.service;


import com.api.algafood.domain.Exception.EntidadeNaoEncontradaException;
import com.api.algafood.domain.model.FormaPagamento;
import com.api.algafood.domain.model.Restaurante;
import com.api.algafood.domain.repository.restaurante.RestauranteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class RestauranteService {

    private static final String MSG_RESTAURANTE_EM_USO =
            "Entity 'Restaurante' with identifier %d is in use";

    private static final String MSG_RESTAURANTE_NAO_ENCONTRADO =
            "Entity 'Restaurante' with identifier %d not found";

    private RestauranteRepository repository;
    private CozinhaService cozinhaService;
    private CidadeService cidadeService;
    private FormaPagamentoService formaPagamentoService;

    public RestauranteService(RestauranteRepository repository,
                              CozinhaService cozinhaService,
                              CidadeService cidadeService,
                              FormaPagamentoService formaPagamentoService) {
        this.repository = repository;
        this.cozinhaService = cozinhaService;
        this.cidadeService = cidadeService;
        this.formaPagamentoService = formaPagamentoService;
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

    @Transactional
    public void desassociarFormaPagamento(Long restauranteId, Long formaPagamentoId){
        var restaurante = findById(restauranteId);
        var formaPagamento = formaPagamentoService.findById(formaPagamentoId);
        restaurante.removerFormaPagamento(formaPagamento);
        /*Lembrando que não preciso usar o repository.save(restaurante) pois como estou dentro de
         * @Transactional e a entidade restaurante está no contexto do JPA (findById), no final do método
         * será feito um update em restaurante removendo a forma de pagamento do DB
         */
    }

    @Transactional
    public Set<FormaPagamento> associarFormaPagamento(Long restauranteId, Long formaPagamentoId) {
        var restaurante = findById(restauranteId);
        var formaPagamento = formaPagamentoService.findById(formaPagamentoId);
        restaurante.adicionarFormaPagamento(formaPagamento);
        return restaurante.getFormasPagamento();
    }
}
