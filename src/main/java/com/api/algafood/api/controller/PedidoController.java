package com.api.algafood.api.controller;

import com.api.algafood.api.assembler.Converter;
import com.api.algafood.api.model.representation.pedido.PedidoCompleta;
import com.api.algafood.api.model.representation.pedido.PedidoListagem;
import com.api.algafood.api.model.representation.pedido.PedidoListagemSimples;
import com.api.algafood.domain.Exception.EntidadeNaoEncontradaException;
import com.api.algafood.domain.Exception.NegocioException;
import com.api.algafood.domain.model.Pedido;
import com.api.algafood.domain.model.Usuario;
import com.api.algafood.domain.repository.PedidoRepository;
import com.api.algafood.domain.service.PedidoService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private PedidoService service;
    private PedidoRepository repository;
    private Converter<Pedido, PedidoListagem, PedidoCompleta> assemblerListagem;
    private Converter<Pedido, PedidoListagemSimples, PedidoCompleta> assemblerListagemSimples;

    public PedidoController(PedidoService service,
                            PedidoRepository repository,
                            Converter<Pedido, PedidoListagem, PedidoCompleta> assemblerListagem,
                            Converter<Pedido, PedidoListagemSimples, PedidoCompleta> assemblerListagemSimples) {
        this.service = service;
        this.repository = repository;
        this.assemblerListagem = assemblerListagem;
        this.assemblerListagemSimples = assemblerListagemSimples;
    }

    @GetMapping("/{id}")
    public PedidoListagem findById(@PathVariable Long id) {
        try {
            return assemblerListagem.toDTO(service.findById(id), PedidoListagem.class);
        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @GetMapping
    public List<PedidoListagemSimples> findAll() {
        return assemblerListagemSimples.toCollectionDTO(repository.findAll(), PedidoListagemSimples.class);
    }

    @PostMapping
    public PedidoListagem create(@Valid @RequestBody PedidoCompleta pedidoCompleta) {
        try {
            Pedido novoPedido = assemblerListagem.toDomainObject(pedidoCompleta, Pedido.class);
            // TODO pegar usuário autenticado
            novoPedido.setUsuario(new Usuario());
            novoPedido.getUsuario().setId(1L);
            novoPedido = service.emitir(novoPedido);
            return assemblerListagem.toDTO(novoPedido, PedidoListagem.class);
        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

}
