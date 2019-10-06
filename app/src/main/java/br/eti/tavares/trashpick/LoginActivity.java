package br.eti.tavares.trashpick;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void OnClickCadastro(View v) {
        Intent iCadastro = new Intent(getApplicationContext(), CadastroActivity.class);
        startActivity(iCadastro);
    }

    public void OnClickEntrar(View v) {

        EditText editEmail = findViewById(R.id.editEmail);
        EditText editSenha = findViewById(R.id.editSenha);
        TextView emailInvalido = findViewById(R.id.textEmailInvalido);
        TextView senhaInvalida = findViewById(R.id.textSenhaInvalida);
        TextView erroAutenticar = findViewById(R.id.textErroAutenticar);

        emailInvalido.setText("");
        senhaInvalida.setText("");
        erroAutenticar.setText("");
        erroAutenticar.setBackgroundColor(Color.parseColor("#FFFFFF"));

        editEmail.setHintTextColor(Color.parseColor("#AAAAAA"));
        editSenha.setHintTextColor(Color.parseColor("#AAAAAA"));

        String email = editEmail.getText().toString();
        String senha = editSenha.getText().toString();
        boolean erro = false;


        if (email.equals("")) {
            emailInvalido.setText("Insira um e-mail válido!");
            editEmail.setHintTextColor(Color.parseColor("#FFCC0000"));
            erro = true;
        }

        if (senha.equals("")) {
            senhaInvalida.setText("Insira uma senha válida!");
            editSenha.setHintTextColor(Color.parseColor("#FFCC0000"));
            erro = true;
        }

        if (!erro) {
            //Objeto c1 da classe Carro
            Login l1 = new Login(email, senha);

            if (l1.autenticar()) {
                Intent iMaps = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(iMaps);
            } else {
                erroAutenticar.setText("Não foi possível encontrar este usuário e/ou senha!");
                erroAutenticar.setBackgroundColor(Color.parseColor("#A8F78B8B"));
            }

        }
    }
}
