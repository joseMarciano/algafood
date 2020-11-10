package com.api.algafood.api.model.itempedido;

import com.api.algafood.api.model.representation.produto.ProdutoAssociacao;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

public class ItemPedidoAssociacao {

    @Valid
    @NotNull
    private ProdutoAssociacao produto;

    @NotNull
    @PositiveOrZero
    private Integer quantidade;


    private String observacao;

    public ProdutoAssociacao getProduto() {
        return produto;
    }

    public void setProduto(ProdutoAssociacao produto) {
        this.produto = produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}
