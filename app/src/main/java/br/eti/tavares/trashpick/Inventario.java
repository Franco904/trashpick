package br.eti.tavares.trashpick;

public class Inventario {

    private String idJogador;
    private Lixo lixo;

    public Inventario(){}

    public Inventario(String idJogador, Lixo lixo) {
        this.idJogador = idJogador;
        this.lixo = lixo;
    }

    public Lixo getLixo() { return lixo; }
    public void setLixo(Lixo lixo) { this.lixo = lixo; }

    public String getIdJogador() { return idJogador; }
    public void setIdJogador(String idJogador) { this.idJogador = idJogador; }
}
