package com.api.algafood.domain.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "GRUPOS")
public class Grupo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NOME")
    private String nome;

    @ManyToMany
    @JoinTable(name = "GRUPOS_PERMISSOES",
            joinColumns = @JoinColumn(name = "ID_GRUPOS"),
            inverseJoinColumns = @JoinColumn(name = "ID_PERMISSOES"))
    private Set<Permissao> permissoes = new HashSet<>();

    public Boolean removerPermissao(Permissao permissao){
        return getPermissoes().remove(permissao);
    }

    public Boolean adicionarPermissao(Permissao permissao){
        return getPermissoes().add(permissao);
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

    public Set<Permissao> getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(Set<Permissao> permissoes) {
        this.permissoes = permissoes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Grupo grupo = (Grupo) o;

        return id.equals(grupo.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
