package com.ufpr.dt.site.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "ListaPalavraFrase")
public class ListaPalavraFrase implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name="idLista")
    private Lista lista;

    @ManyToOne
    @JoinColumn(name="idPalavraFrase")
    private PalavraFrase palavraFrase;


    public Lista getLista() {
        return lista;
    }

    public void setLista(Lista lista) {
        this.lista = lista;
    }

    public PalavraFrase getPalavraFrase() {
        return palavraFrase;
    }

    public void setPalavraFrase(PalavraFrase palavraFrase) {
        this.palavraFrase = palavraFrase;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
