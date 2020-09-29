package com.api.algafood.domain.model;


import com.api.algafood.Groups;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "ESTADOS")
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(groups = Groups.EstadoId.class)
    private Long id;

    @Column(name = "NOME")
    @NotBlank(message = "O campo 'nome' é obrigatório")
    private String nome;

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

}
