package com.api.algafood.domain.model;


import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "USUARIOS")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "SENHA")
    private String senha;

    @Column(name = "DH_CADASTRO")
    @CreationTimestamp
    private OffsetDateTime dataCadastro;


    @ManyToMany
    @JoinTable(name = "USUARIOS_GRUPOS",
            joinColumns = @JoinColumn(name = "ID_USUARIOS"),
            inverseJoinColumns = @JoinColumn(name = "ID_GRUPOS"))
    private Set<Grupo> grupos = new HashSet<>();

    public Boolean adicionarGrupo(Grupo grupo){
        return getGrupos().add(grupo);
    }

    public Boolean removerGrupo(Grupo grupo){
        return getGrupos().remove(grupo);
    }

    public Boolean senhaCoincideCom(String senha){
        return getSenha().equals(senha);
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public OffsetDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(OffsetDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Set<Grupo> getGrupos() {
        return grupos;
    }

    public void setGrupos(Set<Grupo> grupos) {
        this.grupos = grupos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Usuario usuario = (Usuario) o;

        return id.equals(usuario.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
