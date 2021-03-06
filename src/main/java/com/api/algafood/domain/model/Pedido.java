package com.api.algafood.domain.model;

import com.api.algafood.domain.Exception.NegocioException;
import com.api.algafood.domain.model.embeddable.Endereco;
import com.api.algafood.domain.model.enums.StatusPedido;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    @Column(name = "CODIGO")
    private String codigo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_FORMAS_PAGAMENTO")
    private FormaPagamento formaPagamento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_RESTAURANTES")
    private Restaurante restaurante;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
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

    private void setStatus(StatusPedido novoStatus) {
        if(getStatus().naoPodeAlterarPara(novoStatus))  {
            throw new NegocioException(
                    String.format(
                            "Status do pedido %s não pode ser alterado de %s para %s",
                            getCodigo(),getStatus().getDescricao(),novoStatus.getDescricao()));
        }
        this.status = novoStatus;
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

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void calcularValorTotal(){
        getItens().forEach(ItemPedido::calcularPrecoTotal);

        this.subTotal = getItens().stream().map(itemPedido -> itemPedido.getPrecoTotal())
        .reduce(BigDecimal::add).get();

        this.valorTotal = this.subTotal.add(this.taxaFrete);
    }
    public void definirTaxaFrete(){
        setTaxaFrete(getRestaurante().getTaxaFrete());
    }
    public void atribuirPedidoAosItens(){
       getItens().forEach(itemPedido -> itemPedido.setPedido(this));
    }

    public void confirmar(){
        setStatus(StatusPedido.CONFIRMADO);
        setDataConfirmacao(OffsetDateTime.now());
    }
    public void entregar(){
        setStatus(StatusPedido.ENTREGUE);
        setDataEntrega(OffsetDateTime.now());
    }
    public void cancelar(){
        setStatus(StatusPedido.CANCELADO);
        setDataCancelamento(OffsetDateTime.now());
    }

    @PrePersist
    private void gerarCodigo(){
        setCodigo(UUID.randomUUID().toString());
    }



}
