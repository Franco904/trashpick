package br.eti.tavares.trashpick;

public class ItemBiblioteca {

    private String nomeLixo, descricao;
    String imagem;

    public ItemBiblioteca() {
    }

    public ItemBiblioteca(String nomeLixo, String descricao, String imagem) {
        this.nomeLixo = nomeLixo;
        this.descricao = descricao;
        this.imagem = imagem;
    }

    public String getNome() {
        return nomeLixo;
    }

    public void setTitulo(String titulo) {
        this.nomeLixo = nomeLixo;
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
