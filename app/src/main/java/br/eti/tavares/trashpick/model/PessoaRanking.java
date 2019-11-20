package br.eti.tavares.trashpick.model;

public class PessoaRanking implements Comparable<PessoaRanking> {

    private long pontos;
    private String nome;
    private String foto;

    public PessoaRanking(String n, long p, String f) {
        pontos = p;
        nome = n;
        foto = f;
    }

    public int compareTo(PessoaRanking outraPessoa) {
        if (this.pontos < outraPessoa.pontos) {
            return -1;
        }
        if (this.pontos > outraPessoa.pontos) {
            return 1;
        }
        return 0;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getPontos() {
        return pontos;
    }

    public void setPontos(long pontos) {
        this.pontos = pontos;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public void addPontos(long p) {
        this.pontos += p;
    }

}