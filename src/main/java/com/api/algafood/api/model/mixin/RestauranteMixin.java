package com.api.algafood.api.model.mixin;

import com.api.algafood.domain.model.Cozinha;
import com.api.algafood.domain.model.embeddable.DataHoraCadastroAtualizacao;
import com.api.algafood.domain.model.embeddable.Endereco;
import com.api.algafood.domain.model.FormaPagamento;
import com.api.algafood.domain.model.Produto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Embedded;
import java.util.List;

/*Criando uma classe abstrada já que ela nunca vai ser instanciada*/
public abstract class RestauranteMixin {


    @JsonIgnoreProperties(value = "nome", allowGetters = true)  /* Sempre que eu adicionar uma cozinha, eu ignoro a propriedade "nome",aceitando apenas o Id*/
    private Cozinha cozinha;

    @JsonIgnore
    private Endereco endereco;

    @JsonIgnore
    private List<FormaPagamento> formasPagamento;

    @JsonIgnore
    private List<Produto> produtos;

    //@JsonIgnore
    private DataHoraCadastroAtualizacao dataHoraCadastroAtualizacao;


}
