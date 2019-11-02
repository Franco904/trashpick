package br.eti.tavares.trashpick;

public class ItemBiblioteca {

    private String nomeLixo;
    int imagem;

    public ItemBiblioteca(){}

    public ItemBiblioteca(String nomeLixo, int imagem){
        this.nomeLixo = nomeLixo;
        this.imagem = imagem;
    }

    public String getNome() {
        return nomeLixo;
    }

    public void setTitulo(String titulo) {
        this.nomeLixo = nomeLixo;
    }

    public int getImagem() {
        return imagem;
    }

    public void setImagem(int imagem) {
        this.imagem = imagem;
    }
}
