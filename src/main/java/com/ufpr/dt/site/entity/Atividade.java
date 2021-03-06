package com.ufpr.dt.site.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Atividade")
public class Atividade implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, name = "estado")
    private String estado;
    @ManyToOne
    @JoinColumn(name = "idTipoAtividade")
    private TipoAtividade tipoAtividade;
    @Column(name = "pin")
    private Long pin;
    @ManyToOne
    @JoinColumn(name="idLista")
    private Lista lista;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name="idPessoa")
    private Pessoa pessoa;
    @Column(name = "parear")
    private int parear;
    @Column(name = "pareou")
    private int pareou;
    @Column(name = "rounds")
    private int rounds;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Pessoa> pessoas;

    public Atividade(){

    }

    public Atividade(TipoAtividade tipoAtividade, Lista lista, Pessoa pessoa) {
        this.tipoAtividade = tipoAtividade;
        this.lista = lista;
        this.estado = "Aguardando";
        this.rounds = 1;
        this.pessoa = pessoa;
        pessoas = new ArrayList<Pessoa>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public TipoAtividade getTipoAtividade() {
        return tipoAtividade;
    }

    public void setTipoAtividade(TipoAtividade tipoAtividade) {
        this.tipoAtividade = tipoAtividade;
    }

    public Long getPin() {
        return pin;
    }

    public void setPin(Long pin) {
        this.pin = pin;
    }

    public Lista getLista() {
        return lista;
    }

    public void setLista(Lista lista) {
        this.lista = lista;
    }

    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    public void setPessoas(List<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }

    public void addPessoa(Pessoa pessoa){
        pessoas.add(pessoa);
    }

    public int getParear() {
        return parear;
    }

    public void setParear(int parear) {
        this.parear = parear;
    }

    public int getPareou() {
        return pareou;
    }

    public void setPareou(int pareou) {
        this.pareou = pareou;
    }

    public int getRounds() {
        return rounds;
    }

    public void setRounds(int rounds) {
        this.rounds = rounds;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
}
