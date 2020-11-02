package com.api.algafood.domain.model;

import com.api.algafood.core.validation.Groups;
import com.api.algafood.core.validation.ValorZeroIncluirDescricao;
import com.api.algafood.domain.model.embeddable.DataHoraCadastroAtualizacao;
import com.api.algafood.domain.model.embeddable.Endereco;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@ValorZeroIncluirDescricao(valorField = "taxaFrete", descricaoField = "nome", descricaoObrigatoria = "Frete Grátis")
@Entity
@Table(name = "RESTAURANTES")
public class Restaurante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NOME")
    @NotBlank
    private String nome;

    @Column(name = "TAXA_FRETE")
    @PositiveOrZero
    @NotNull
    private BigDecimal taxaFrete;

    @Column(name = "FL_ATIVO")
    private Boolean flAtivo = Boolean.TRUE;

    /*Fazendo validação em cascata ---> Só colocando
     *valid ele entra na entidade e faz as validações que estão la dentro
     */
    @Valid
    @ConvertGroup(from = Default.class, to = Groups.CozinhaId.class)
    @NotNull
    @ManyToOne
    @JoinColumn(name = "ID_COZINHAS")
    private Cozinha cozinha;


    @ManyToMany
    @JoinTable(name = "RESTAURANTES_FORMAS_PAGAMENTO",
            joinColumns = @JoinColumn(name = "ID_RESTAURANTES"),
            inverseJoinColumns = @JoinColumn(name = "ID_FORMAS_PAGAMENTO"))
    private Set<FormaPagamento> formasPagamento = new HashSet<>(); /*Set não permite que objetos
                                                                     considerados iguais pelo
                                                                     .equals/.hashCode sejam adicionados
                                                                     no conjunto, evitando duplicidade de formas de
                                                                     pagamento em restaurante*/

    @OneToMany(mappedBy = "restaurante")
    private List<Produto> produtos = new ArrayList<>();

    @Embedded
    private Endereco endereco;

    @Embedded
    private DataHoraCadastroAtualizacao dataHoraCadastroAtualizacao = new DataHoraCadastroAtualizacao();

    public void ativar(){
        setFlAtivo(true);
    }

    public void inativar(){
        setFlAtivo(false);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getTaxaFrete() {
        return taxaFrete;
    }

    public void setTaxaFrete(BigDecimal taxaFrete) {
        this.taxaFrete = taxaFrete;
    }

    public Cozinha getCozinha() {
        return cozinha;
    }

    public void setCozinha(Cozinha cozinha) {
        this.cozinha = cozinha;
    }

    public Set<FormaPagamento> getFormasPagamento() {
        return formasPagamento;
    }

    public void setFormasPagamento(Set<FormaPagamento> formasPagamento) {
        this.formasPagamento = formasPagamento;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public DataHoraCadastroAtualizacao getDataHoraCadastroAtualizacao() {
        return dataHoraCadastroAtualizacao;
    }

    public void setDataHoraCadastroAtualizacao(DataHoraCadastroAtualizacao dataHoraCadastroAtualizacao) {
        this.dataHoraCadastroAtualizacao = dataHoraCadastroAtualizacao;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public Boolean getFlAtivo() {
        return flAtivo;
    }

    public void setFlAtivo(Boolean flAtivo) {
        this.flAtivo = flAtivo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Restaurante that = (Restaurante) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    public Boolean removerFormaPagamento(FormaPagamento formaPagamento) {
        return getFormasPagamento().remove(formaPagamento);
    }

    public Boolean adicionarFormaPagamento(FormaPagamento formaPagamento) {
        return getFormasPagamento().add(formaPagamento);
    }
}
