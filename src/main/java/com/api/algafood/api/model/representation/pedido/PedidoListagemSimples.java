package com.api.algafood.api.model.representation.pedido;

import com.api.algafood.api.model.representation.restaurante.RestauranteListagemSimples;
import com.api.algafood.api.model.representation.usuario.UsuarioListagem;
import com.api.algafood.domain.model.enums.StatusPedido;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class PedidoListagemSimples {

    private Long id;
    private String codigo;
    private StatusPedido status;
    private BigDecimal taxaFrete;
    private BigDecimal valorTotal;
    private OffsetDateTime dataCriacao;
    private BigDecimal subTotal;
    private UsuarioListagem usuario;
    private RestauranteListagemSimples restaurante;


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

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    public UsuarioListagem getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioListagem usuario) {
        this.usuario = usuario;
    }

    public RestauranteListagemSimples getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(RestauranteListagemSimples restaurante) {
        this.restaurante = restaurante;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
