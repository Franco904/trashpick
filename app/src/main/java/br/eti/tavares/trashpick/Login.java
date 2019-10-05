package br.eti.tavares.trashpick;

public class Login {

    private String email;
    private String senha;

    public Login(String email, String senha) {
        this.email = email;
        this.senha = senha;
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

    public boolean autenticar() {
        if ((email.equals("franco@gmail.com")) & (senha.equals("1234")))
            return true;
        else
            return false;
    }
}
