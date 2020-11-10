package com.api.algafood.domain.service;

import com.api.algafood.domain.Exception.EntidadeNaoEncontradaException;
import com.api.algafood.domain.Exception.NegocioException;
import com.api.algafood.domain.model.Pedido;
import com.api.algafood.domain.repository.PedidoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PedidoService {

    private static final String MSG_PEDIDO_EM_USO
            = "Entity 'Pedido' with identifier %d is in use";

    private static final String MSG_PEDIDO_NAO_ENCONTRADO
            = "Entity 'Pedido' with identifier %d not found";

    private PedidoRepository repository;
    private CidadeService cidadeService;
    private UsuarioService usuarioService;
    private RestauranteService restauranteService;
    private FormaPagamentoService formaPagamentoService;
    private ProdutoService produtoService;

    public PedidoService(PedidoRepository repository,
                         CidadeService cidadeService,
                         UsuarioService usuarioService,
                         RestauranteService restauranteService,
                         FormaPagamentoService formaPagamentoService,
                         ProdutoService produtoService) {

        this.repository = repository;
        this.cidadeService = cidadeService;
        this.usuarioService = usuarioService;
        this.restauranteService = restauranteService;
        this.formaPagamentoService = formaPagamentoService;
        this.produtoService = produtoService;
    }

    public Pedido findById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new EntidadeNaoEncontradaException(String.format(MSG_PEDIDO_NAO_ENCONTRADO, id)));
    }

    @Transactional
    public Pedido emitir(Pedido pedido) {
        validaPedido(pedido);
        validaItens(pedido);

        pedido.setTaxaFrete(pedido.getRestaurante().getTaxaFrete());
        pedido.calcularValorTotal();

        return repository.save(pedido);
    }

    private void validaPedido(Pedido pedido) {
        var restaurante = restauranteService.findById(pedido.getRestaurante().getId());
        var formaPagamento = formaPagamentoService.findById(pedido.getFormaPagamento().getId());

        if (restaurante.naoAceitaFormaPagamento(formaPagamento)) {
            throw new NegocioException(String.format(
                    "Forma de pagamento '%s' não é aceita por esse restaurante", formaPagamento.getDescricao()));
        }

        var cidade = cidadeService.findById(pedido.getEndereco().getCidade().getId());
        var usuario = usuarioService.findById(pedido.getUsuario().getId());

        pedido.getEndereco().setCidade(cidade);
        pedido.setUsuario(usuario);
        pedido.setRestaurante(restaurante);
        pedido.setFormaPagamento(formaPagamento);

    }

    private void validaItens(Pedido pedido){
        pedido.getItens().forEach(item -> {
            var produto = produtoService.findById(pedido.getRestaurante().getId(),item.getProduto().getId());

            item.setPedido(pedido);
            item.setProduto(produto);
            item.setPrecoUnitario(produto.getPreco());

        });
    }
}
