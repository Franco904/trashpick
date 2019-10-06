package br.eti.tavares.trashpick;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class Login {

    private String email;
    private String senha;
    private FirebaseAuth auth;


    public Login(String email, String senha) {
        this.email = email;
        this.senha = senha;
        auth = FirebaseAuth.getInstance();
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
