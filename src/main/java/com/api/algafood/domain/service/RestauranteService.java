package com.api.algafood.domain.service;


import com.api.algafood.domain.Exception.EntidadeNaoEncontradaException;
import com.api.algafood.domain.model.FormaPagamento;
import com.api.algafood.domain.model.Restaurante;
import com.api.algafood.domain.model.Usuario;
import com.api.algafood.domain.repository.restaurante.RestauranteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
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
    private UsuarioService usuarioService;
    private FormaPagamentoService formaPagamentoService;

    public RestauranteService(RestauranteRepository repository,
                              CozinhaService cozinhaService,
                              CidadeService cidadeService,
                              UsuarioService usuarioService,
                              FormaPagamentoService formaPagamentoService) {
        this.repository = repository;
        this.cozinhaService = cozinhaService;
        this.cidadeService = cidadeService;
        this.usuarioService = usuarioService;
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
    public void ativarEmMassa(List<Long> restaurantesId) {
        restaurantesId.forEach(this::ativar);
    }


    @Transactional
    public void inativar(Long id){
        var restaurante = findById(id);
        restaurante.inativar();
    }


    @Transactional
    public void inativarEmMassa(List<Long> restaurantesId) {
        restaurantesId.forEach(this::inativar);
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

    @Transactional
    public void abrir(Long id){
        /*Lembrando que não é preciso o uso do save visto que a variável
         *restaurante está no contexto do jpa e a com o a anotação @transactional, é feito um update
         * automaticamente
         */
        var restaurante = findById(id);

        restaurante.abrir();
    }
    @Transactional
    public void fechar(Long id){
        var restaurante = findById(id);

        restaurante.fechar();
    }

    public Collection<Usuario> listAllUsuarios(Long restauranteId) {
        var restaurante = findById(restauranteId);
        return restaurante.getUsuarios();
    }

    @Transactional
    public void associarUsuario(Long restauranteId, Long usuarioId) {
        var restaurante = findById(restauranteId);
        var usuario = usuarioService.findById(usuarioId);
        restaurante.associarUsuario(usuario);
    }

    @Transactional
    public void desassociarUsuario(Long restauranteId, Long usuarioId) {
        var restaurante = findById(restauranteId);
        var usuario = usuarioService.findById(usuarioId);
        restaurante.desassociarUsuario(usuario);
    }

}
