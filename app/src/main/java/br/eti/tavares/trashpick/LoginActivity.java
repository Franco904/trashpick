package br.eti.tavares.trashpick;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void OnClickCadastro(View v) {
        finish();
    }

    public void OnClickMaps(View v) {

        EditText editEmail = findViewById(R.id.editEmail);
        EditText editSenha = findViewById(R.id.editSenha);

        String email = editEmail.getText().toString();
        String senha = editSenha.getText().toString();

        if (email.equals("")) {
            Toast.makeText(getApplicationContext(), "O campo e-mail é obrigatório!", Toast.LENGTH_SHORT).show();
        }

        else if (senha.equals("")) {
            Toast.makeText(getApplicationContext(), "O campo senha é obrigatório!", Toast.LENGTH_SHORT).show();
        }

        else {
            //Objeto c1 da classe Carro
            Login l1 = new Login(email, senha);

            Intent iMaps = new Intent(getApplicationContext(), MapsActivity.class);
            startActivity(iMaps);
        }
    }
}
