package br.eti.tavares.trashpick.model;

import com.google.firebase.auth.FirebaseAuth;

public class Cadastro {

    private String nome;
    private String email;
    private String senha;
    private String confirmacaoSenha;
    private FirebaseAuth auth;

    public Cadastro(String nome, String email, String senha, String confirmacaoSenha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.confirmacaoSenha = confirmacaoSenha;
        auth = FirebaseAuth.getInstance();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getConfirmacaoSenha() {
        return confirmacaoSenha;
    }

    public void setConfirmacaoSenha(String confirmacaoSenha) {
        this.confirmacaoSenha = confirmacaoSenha;
    }
}