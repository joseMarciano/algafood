package com.api.algafood.api.model.representation.pedido;

import com.api.algafood.api.model.itempedido.ItemPedidoListagemSimples;
import com.api.algafood.api.model.representation.endereco.EnderecoCompletaListagem;
import com.api.algafood.api.model.representation.formapagamento.FormaPagamentoCompletaListagem;
import com.api.algafood.api.model.representation.restaurante.RestauranteListagemSimples;
import com.api.algafood.api.model.representation.usuario.UsuarioListagem;
import com.api.algafood.domain.model.enums.StatusPedido;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

public class PedidoListagem {

    private Long id;
    private String codigo;
    private StatusPedido status;
    private BigDecimal taxaFrete;
    private BigDecimal valorTotal;
    private OffsetDateTime dataCriacao;
    private OffsetDateTime dataConfirmacao;
    private OffsetDateTime dataCancelamento;
    private OffsetDateTime dataEntrega;
    private BigDecimal subTotal;
    private EnderecoCompletaListagem endereco;
    private UsuarioListagem usuario;
    private FormaPagamentoCompletaListagem formaPagamento;
    private RestauranteListagemSimples restaurante;
    private List<ItemPedidoListagemSimples> itens;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }

    public BigDecimal getTaxaFrete() {
        return taxaFrete;
    }

    public void setTaxaFrete(BigDecimal taxaFrete) {
        this.taxaFrete = taxaFrete;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public OffsetDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(OffsetDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public OffsetDateTime getDataConfirmacao() {
        return dataConfirmacao;
    }

    public void setDataConfirmacao(OffsetDateTime dataConfirmacao) {
        this.dataConfirmacao = dataConfirmacao;
    }

    public OffsetDateTime getDataCancelamento() {
        return dataCancelamento;
    }

    public void setDataCancelamento(OffsetDateTime dataCancelamento) {
        this.dataCancelamento = dataCancelamento;
    }

    public OffsetDateTime getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(OffsetDateTime dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    public EnderecoCompletaListagem getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoCompletaListagem endereco) {
        this.endereco = endereco;
    }

    public UsuarioListagem getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioListagem usuario) {
        this.usuario = usuario;
    }

    public FormaPagamentoCompletaListagem getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamentoCompletaListagem formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public RestauranteListagemSimples getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(RestauranteListagemSimples restaurante) {
        this.restaurante = restaurante;
    }

    public List<ItemPedidoListagemSimples> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedidoListagemSimples> itens) {
        this.itens = itens;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
