package com.api.algafood.api.model.representation.pedido;

import com.api.algafood.api.model.itempedido.ItemPedidoAssociacao;
import com.api.algafood.api.model.representation.endereco.EnderecoCompleta;
import com.api.algafood.api.model.representation.formapagamento.FormaPagamentoAssociacao;
import com.api.algafood.api.model.representation.restaurante.RestauranteAssociacao;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class PedidoCompleta {

    @Valid
    @NotNull
    private RestauranteAssociacao restaurante;

    @Valid
    @NotNull
    private FormaPagamentoAssociacao formaPagamento;

    @Valid
    @NotNull
    private EnderecoCompleta endereco;

    @Valid
    @NotNull
    @Size(min = 1)
    private List<ItemPedidoAssociacao> itens;

    public RestauranteAssociacao getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(RestauranteAssociacao restaurante) {
        this.restaurante = restaurante;
    }

    public FormaPagamentoAssociacao getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamentoAssociacao formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public EnderecoCompleta getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoCompleta endereco) {
        this.endereco = endereco;
    }

    public List<ItemPedidoAssociacao> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedidoAssociacao> itens) {
        this.itens = itens;
    }
}
