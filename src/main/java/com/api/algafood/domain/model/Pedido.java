package com.api.algafood.domain.model;

import com.api.algafood.domain.model.embeddable.Endereco;
import com.api.algafood.domain.model.enums.StatusPedido;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PEDIDOS")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private StatusPedido status = StatusPedido.CRIADO;

    @Column(name = "TAXA_FRETE")
    private BigDecimal taxaFrete;

    @Column(name = "VALOR_TOTAL")
    private BigDecimal valorTotal;

    @Column(name = "DATA_CRIACAO")
    @CreationTimestamp
    private OffsetDateTime dataCriacao;

    @Column(name = "DATA_CONFIRMACAO")
    private OffsetDateTime dataConfirmacao;

    @Column(name = "DATA_CANCELAMENTO")
    private OffsetDateTime dataCancelamento;

    @Column(name = "DATA_ENTREGA")
    private OffsetDateTime dataEntrega;

    @Column(name = "SUB_TOTAL")
    private BigDecimal subTotal;

    @Embedded
    private Endereco endereco;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_USUARIOS")
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_FORMAS_PAGAMENTO")
    private FormaPagamento formaPagamento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_RESTAURANTES")
    private Restaurante restaurante;

    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> itens = new ArrayList<>();

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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }

    public void calcularValorTotal(){
        this.subTotal = getItens().stream().map(itemPedido -> itemPedido.getPrecoTotal())
        .reduce(BigDecimal.ZERO,BigDecimal::add);

        this.valorTotal = this.subTotal.add(this.taxaFrete);
    }
    public void definirTaxaFrete(){
        setTaxaFrete(getRestaurante().getTaxaFrete());
    }
    public void atribuirPedidoAosItens(){
       getItens().forEach(itemPedido -> itemPedido.setPedido(this));
    }

}
