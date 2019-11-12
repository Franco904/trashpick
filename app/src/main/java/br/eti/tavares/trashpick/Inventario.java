package br.eti.tavares.trashpick;

public class Inventario {

    private String id, imagem;

    public Inventario(){}

    public Inventario(String imagem){
        this.imagem = imagem;

    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
}
