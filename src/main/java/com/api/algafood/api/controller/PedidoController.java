package com.api.algafood.api.controller;

import com.api.algafood.api.assembler.Converter;
import com.api.algafood.api.model.representation.pedido.PedidoCompleta;
import com.api.algafood.api.model.representation.pedido.PedidoListagem;
import com.api.algafood.domain.Exception.EntidadeNaoEncontradaException;
import com.api.algafood.domain.Exception.NegocioException;
import com.api.algafood.domain.model.Pedido;
import com.api.algafood.domain.repository.PedidoRepository;
import com.api.algafood.domain.service.PedidoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private PedidoService service;
    private PedidoRepository repository;
    private Converter<Pedido, PedidoListagem, PedidoCompleta> assemblers;

    public PedidoController(PedidoService service,
                            PedidoRepository repository,
                            Converter<Pedido, PedidoListagem, PedidoCompleta> assemblers) {
        this.service = service;
        this.repository = repository;
        this.assemblers = assemblers;
    }

    @GetMapping("/{id}")
    public PedidoListagem findById(@PathVariable Long id) {
        try {
            return assemblers.toDTO(service.findById(id), PedidoListagem.class);
        }catch (EntidadeNaoEncontradaException e){
            throw new NegocioException(e.getMessage(),e);
        }
    }

    @GetMapping
    public List<PedidoListagem> findAll() {
        return assemblers.toCollectionDTO(repository.findAll(), PedidoListagem.class);
    }

}
