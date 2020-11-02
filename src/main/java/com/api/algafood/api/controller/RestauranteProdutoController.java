package com.api.algafood.api.controller;


import com.api.algafood.api.assembler.Converter;
import com.api.algafood.api.model.representation.produto.ProdutoCompleta;
import com.api.algafood.api.model.representation.produto.ProdutoListagem;
import com.api.algafood.domain.model.Produto;
import com.api.algafood.domain.repository.ProdutoRepository;
import com.api.algafood.domain.service.ProdutoService;
import com.api.algafood.domain.service.RestauranteService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos")
public class RestauranteProdutoController {

    private Converter<Produto, ProdutoListagem, ProdutoCompleta> assemblers;
    private RestauranteService restauranteService;
    private ProdutoService produtoService;
    private ProdutoRepository repository;
    private ModelMapper modelMapper;

    public RestauranteProdutoController(Converter<Produto, ProdutoListagem, ProdutoCompleta> assemblers,
                                        RestauranteService restauranteService,
                                        ProdutoService produtoService,
                                        ProdutoRepository repository,
                                        ModelMapper modelMapper) {
        this.assemblers = assemblers;
        this.restauranteService = restauranteService;
        this.produtoService = produtoService;
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<ProdutoListagem> listAllProdutos(@PathVariable Long restauranteId) {
        var restaurante = restauranteService.findById(restauranteId);
        return assemblers.toCollectionDTO(repository.findByRestaurante(restaurante), ProdutoListagem.class);
    }

    @GetMapping("/{produtoId}")
    public ProdutoListagem listProduto(@PathVariable Long restauranteId,
                                       @PathVariable Long produtoId) {
        var produto = produtoService.findById(restauranteId,produtoId);
        return assemblers.toDTO(produto, ProdutoListagem.class);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoListagem create(@PathVariable Long restauranteId,
                                  @RequestBody @Valid ProdutoCompleta produtoCompleta){
        var restaurante = restauranteService.findById(restauranteId);
        var produto = assemblers.toDomainObject(produtoCompleta,Produto.class);
        produto.setRestaurante(restaurante);
        produto = produtoService.save(produto);
        return assemblers.toDTO(produto,ProdutoListagem.class);

    }

    @PutMapping("/{produtoId}")
    public ProdutoListagem update(@PathVariable Long restauranteId,
                                  @PathVariable Long produtoId,
                                  @RequestBody @Valid ProdutoCompleta produtoCompleta){
       var produtoAtual = produtoService.findById(restauranteId,produtoId);
       copyToDomainObject(produtoCompleta,produtoAtual);
       produtoAtual = produtoService.save(produtoAtual);
       return assemblers.toDTO(produtoAtual,ProdutoListagem.class);
    }

    @DeleteMapping("/{produtoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeFromRestaurante(@PathVariable Long restauranteId,
                                      @PathVariable Long produtoId){
        produtoService.removeFromRestaurante(restauranteId,produtoId);
    }

    private void copyToDomainObject(ProdutoCompleta produtoCompleta, Produto produto) {
        modelMapper.map(produtoCompleta, produto);
    }

}
