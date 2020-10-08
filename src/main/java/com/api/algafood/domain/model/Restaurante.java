package com.api.algafood.domain.model;

import com.api.algafood.core.validation.Groups;
import com.api.algafood.core.validation.ValorZeroIncluirDescricao;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    /*Fazendo validação em cascata ---> Só colocando
     *valid ele entra na entidade e faz as validações que estão la dentro
     */
    @Valid
    @ConvertGroup(from = Default.class, to = Groups.CozinhaId.class)
    @NotNull
    @ManyToOne
    @JoinColumn(name = "ID_COZINHAS")
    private Cozinha cozinha;

    @Embedded
    private Endereco endereco;

    @ManyToMany
    @JoinTable(name = "RESTAURANTES_FORMAS_PAGAMENTO",
            joinColumns = @JoinColumn(name = "ID_RESTAURANTES"),
            inverseJoinColumns = @JoinColumn(name = "ID_FORMAS_PAGAMENTO"))
    private List<FormaPagamento> formasPagamento = new ArrayList<>();

    @Column(name = "DATA_CADASTRO", nullable = false)
    @CreationTimestamp
//informa que a propriedade anotada deve ser atribuida com data/hora atual no momento que entidade foi salva pela primeira vez
    private LocalDateTime dataCadastro;

    @Column(name = "DATA_ATUALIZACAO", nullable = false)
    @UpdateTimestamp
// informa que a propriedade anotada deve ser atribuida com data/hora atual no momento que a entidade foi atualizada(update)
    private LocalDateTime dataAtualizacao;

    @OneToMany(mappedBy = "restaurante")
    private List<Produto> produtos = new ArrayList<>();

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

    public List<FormaPagamento> getFormasPagamento() {
        return formasPagamento;
    }

    public void setFormasPagamento(List<FormaPagamento> formasPagamento) {
        this.formasPagamento = formasPagamento;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
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
}
