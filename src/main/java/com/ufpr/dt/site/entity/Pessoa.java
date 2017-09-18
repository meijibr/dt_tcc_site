package com.ufpr.dt.site.entity;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;

@Entity(name = "Pessoa")
public class Pessoa {

    private Long id;
    private String nome;
    private String sobrenome;
    private String senha;
    private String email;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(nullable = false, length = 60, name = "nome")
    @NotBlank(message = "Nome é uma informação obrigatória.")
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Column(nullable = false, length = 60, name = "sobrenome")
    @NotBlank(message = "Sobrenome é uma informação obrigatória.")
    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    @Column(name = "senha", nullable = false, length = 60)
    @NotBlank(message = "Senha é uma informação obrigatória.")
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Column(name = "email", nullable = false, length = 60)
    @NotBlank(message = "E-mail é uma informação obrigatória.")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



}

