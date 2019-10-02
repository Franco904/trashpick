package br.eti.tavares.trashpick;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CadastroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
    }
    public void OnClickLogin (View v){
        Intent iLogin = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(iLogin);
    }

    public void OnClickMaps(View v) {

        EditText editNome = findViewById(R.id.editNome);
        EditText editEmail = findViewById(R.id.editEmail);
        EditText editSenha = findViewById(R.id.editSenha);
        EditText editConfirmarSenha = findViewById(R.id.editConfirmarSenha);

        String nome = editNome.getText().toString();
        String email = editEmail.getText().toString();
        String senha = editSenha.getText().toString();
        String confirmacaoSenha = editConfirmarSenha.getText().toString();

        if (nome.equals("")) {
            Toast.makeText(getApplicationContext(), "O campo nome é obrigatório!", Toast.LENGTH_SHORT).show();
        }

        if (email.equals("")) {
            Toast.makeText(getApplicationContext(), "O campo e-mail é obrigatório!", Toast.LENGTH_SHORT).show();
        }

        if (senha.equals("")) {
            Toast.makeText(getApplicationContext(), "O campo senha é obrigatório!", Toast.LENGTH_SHORT).show();
        }

        if (confirmacaoSenha.equals("")) {
            Toast.makeText(getApplicationContext(), "O campo confirmação de senha é obrigatório!", Toast.LENGTH_SHORT).show();
        }

        if (!confirmacaoSenha.equals(senha)){
            Toast.makeText(getApplicationContext(), "A senha não é a mesma informada!", Toast.LENGTH_SHORT).show();
        }

        else {
            //Objeto c1 da classe Carro
            Cadastro c1 = new Cadastro(nome, email, senha, confirmacaoSenha);

            Intent iMaps = new Intent(getApplicationContext(), MapsActivity.class);
            startActivity(iMaps);
        }

    }
}
