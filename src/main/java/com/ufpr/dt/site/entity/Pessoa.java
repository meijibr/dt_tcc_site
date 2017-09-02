package com.ufpr.dt.site.entity;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;

@Entity(name = "Pessoa")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pessoa")
    private Long id;

    @Column(nullable = false, length = 50, name = "nome_pessoa")
    @NotBlank(message = "Nome é uma informação obrigatória.")
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

