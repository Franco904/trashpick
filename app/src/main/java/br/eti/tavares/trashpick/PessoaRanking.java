package br.eti.tavares.trashpick;

public class PessoaRanking {

    private String nome;
    private int pontos;
    private int foto;

    public PessoaRanking(String nome, int pontos, int foto){
        this.nome = nome;
        this.pontos = pontos;
        this.foto = foto;
    }

    protected String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    @Override
    public String toString() {
        return "PessoaRanking{" +
                "nome='" + nome + '\'' +
                ", pontos=" + pontos +
                ", foto='" + foto + '\'' +
                '}';
    }
}
