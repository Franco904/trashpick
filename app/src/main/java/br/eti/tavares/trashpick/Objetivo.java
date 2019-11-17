package br.eti.tavares.trashpick;

public class Objetivo {

    private String id, titulo, descricao, imagem;

    public Objetivo() {
    }

    public Objetivo(String titulo, String descricao, String imagem) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.imagem = imagem;

    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
}
