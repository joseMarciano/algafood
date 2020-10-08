package com.api.algafood.api.model.mixin;

import com.api.algafood.domain.model.Cozinha;
import com.api.algafood.domain.model.Endereco;
import com.api.algafood.domain.model.FormaPagamento;
import com.api.algafood.domain.model.Produto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
    private LocalDateTime dataCadastro;

    @JsonIgnore
    private LocalDateTime dataAtualizacao;

    @JsonIgnore
    private List<Produto> produtos;


}
