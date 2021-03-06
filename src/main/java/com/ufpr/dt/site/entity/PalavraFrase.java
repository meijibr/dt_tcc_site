package com.ufpr.dt.site.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "PalavraFrase")
public class PalavraFrase implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 250, name = "frase")
    private String frase;
    @Column(length = 250, name = "traducao")
    private String traducao;
    //bi-directional many-to-one association to Palavra
    @ManyToOne
    @JoinColumn(name="idPalavra")
    private Palavra palavra;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFrase() {
        return frase;
    }

    public void setFrase(String frase) {
        this.frase = frase;
    }

    public Palavra getPalavra() {
        return palavra;
    }

    public void setPalavra(Palavra palavra) {
        this.palavra = palavra;
    }

    public String getTraducao() {
        return traducao;
    }

    public void setTraducao(String traducao) {
        this.traducao = traducao;
    }
}
