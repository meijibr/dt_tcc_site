package com.ufpr.dt.site.dto;

public class AtividadeLista {
    private String tipoAtividade;
    private String nomeLista;
    private String rounds;
    private String pin;

    public String getTipoAtividade() {
        return tipoAtividade;
    }

    public void setTipoAtividade(String tipoAtividade) {
        this.tipoAtividade = tipoAtividade;
    }

    public String getNomeLista() {
        return nomeLista;
    }

    public void setNomeLista(String nomeLista) {
        this.nomeLista = nomeLista;
    }

    public String getRounds() {
        return rounds;
    }

    public void setRounds(String rounds) {
        this.rounds = rounds;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }
}
