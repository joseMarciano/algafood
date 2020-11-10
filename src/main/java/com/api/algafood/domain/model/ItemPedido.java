package com.api.algafood.domain.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "ITENS_PEDIDOS")
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "PRECO_UNITARIO")
    private BigDecimal precoUnitario;

    @Column(name = "PRECO_TOTAL")
    private BigDecimal precoTotal;

    @Column(name = "OBSERVACAO")
    private String observacao;

    @Column(name = "QUANTIDADE")
    private Integer quantidade;

    @ManyToOne
    @JoinColumn(name = "ID_PRODUTOS")
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "ID_PEDIDOS")
    private Pedido pedido;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(BigDecimal precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public BigDecimal getPrecoTotal() {
        return precoTotal;
    }

    public void setPrecoTotal(BigDecimal precoTotal) {
        this.precoTotal = precoTotal;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public void calcularPrecoTotal(){
        BigDecimal precoUnitario = this.getPrecoUnitario();
        Integer quantidade = this.getQuantidade();

        if(precoUnitario == null) precoUnitario = BigDecimal.ZERO;
        if(quantidade == null) quantidade = Integer.valueOf(0);

        this.setPrecoTotal(precoUnitario.multiply(new BigDecimal(quantidade)));
    }
}

