package br.eti.tavares.trashpick.model;

public class Inventario {

    private String idJogador;
    private LixoPayload lixo;

    public Inventario() {
    }

    public Inventario(String idJogador, LixoPayload lixo) {
        this.idJogador = idJogador;
        this.lixo = lixo;
    }

    public LixoPayload getLixo() {
        return lixo;
    }

    public void setLixo(LixoPayload lixo) {
        this.lixo = lixo;
    }

    public String getIdJogador() {
        return idJogador;
    }

    public void setIdJogador(String idJogador) {
        this.idJogador = idJogador;
    }
}
