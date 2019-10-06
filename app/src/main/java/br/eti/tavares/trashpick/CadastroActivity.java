package br.eti.tavares.trashpick;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
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

    public void OnClickEntrar(View v) {

        EditText editNome = findViewById(R.id.editNome);
        EditText editEmail = findViewById(R.id.editEmail);
        EditText editSenha = findViewById(R.id.editSenha);
        EditText editConfirmarSenha = findViewById(R.id.editConfirmarSenha);
        TextView nomeInvalido = findViewById(R.id.textNomeInvalido);
        TextView emailInvalido = findViewById(R.id.textEmailInvalido);
        TextView senhaInvalido = findViewById(R.id.textSenhaInvalido);
        TextView confirmacaoInvalido = findViewById(R.id.textConfirmacaoInvalido);

        String nome = editNome.getText().toString();
        String email = editEmail.getText().toString();
        String senha = editSenha.getText().toString();
        String confirmacaoSenha = editConfirmarSenha.getText().toString();
        boolean erro = false;

        nomeInvalido.setText("");
        emailInvalido.setText("");
        senhaInvalido.setText("");
        confirmacaoInvalido.setText("");

        editNome.setHintTextColor(Color.parseColor("#AAAAAA"));
        editEmail.setHintTextColor(Color.parseColor("#AAAAAA"));
        editSenha.setHintTextColor(Color.parseColor("#AAAAAA"));
        editConfirmarSenha.setHintTextColor(Color.parseColor("#AAAAAA"));

        if (nome.equals("")) {
            nomeInvalido.setText("O campo nome é obrigatório!");
            editNome.setHintTextColor(Color.parseColor("#FFCC0000"));
            erro = true;
            }

        if (email.equals("")) {
            emailInvalido.setText("O campo e-mail é obrigatório!");
            editEmail.setHintTextColor(Color.parseColor("#FFCC0000"));
            erro = true;
        }

        if (senha.length() < 8){
            senhaInvalido.setText("A senha deve ter pelo menos 8 caracteres!");
            editSenha.setHintTextColor(Color.parseColor("#FFCC0000"));
            erro = true;
        }
        if (confirmacaoSenha.equals("")) {
            confirmacaoInvalido.setText("O campo confirmação de senha é obrigatório!");
            editConfirmarSenha.setHintTextColor(Color.parseColor("#FFCC0000"));
            erro = true;
        }

        if (!confirmacaoSenha.equals(senha)){
            confirmacaoInvalido.setText("A senha não é a mesma informada!");
            editConfirmarSenha.setHintTextColor(Color.parseColor("#FFCC0000"));
            erro = true;
        }

        if(!erro){
            //Objeto c1 da classe Carro
            Cadastro c1 = new Cadastro(nome, email, senha, confirmacaoSenha);

            Intent iMaps = new Intent(getApplicationContext(), MapsActivity.class);
            startActivity(iMaps);
        }

    }
}
