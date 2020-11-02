package com.api.algafood.domain.service;

import com.api.algafood.domain.Exception.EntidadeNaoEncontradaException;
import com.api.algafood.domain.model.Produto;
import com.api.algafood.domain.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ProdutoService {


    private static final String MSG_PRODUTO_EM_USO =
            "Entity 'Produto' with identifier %d is in use";

    private static final String MSG_PRODUTO_NAO_ENCONTRADO =
            "Entity 'Produto' with identifier %d not found";

    private ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;

    }

    @Transactional
    public Produto save(Produto produto) {
        return repository.save(produto);
    }

    @Transactional
    public void removeFromRestaurante(Long restauranteId,Long produtoId){
        var produto = findById(restauranteId,produtoId);
        repository.delete(produto);
    }

    public Produto findById(Long restauranteId, Long produtoId) {
        return repository.findById(restauranteId, produtoId).orElseThrow(() ->
                new EntidadeNaoEncontradaException(
                        String.format("Não existe um cadastro de produto com código %d para o restaurante de código %d",
                        produtoId, restauranteId)));
    }

}
