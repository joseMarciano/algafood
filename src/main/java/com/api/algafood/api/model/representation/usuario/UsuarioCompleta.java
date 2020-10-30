package com.api.algafood.api.model.representation.usuario;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UsuarioCompleta {

    @NotBlank
    private String nome;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String senha;

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
}
