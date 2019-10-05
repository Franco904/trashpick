package br.eti.tavares.trashpick;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

    public void OnClickMaps(View v) {

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

        if (nome.equals("")) {
            nomeInvalido.setText("O campo nome é obrigatório!"); //nomeInvalido.setHintTextColor("FFCC0000");
            }

            else if(!nome.equals("")){
                nomeInvalido.setText("");
            }

        else if (email.equals("")) {
            emailInvalido.setText("O campo e-mail é obrigatório!");

        }
            else if(!email.equals("")){
                emailInvalido.setText("");
            }

        else if (senha.equals("")) {
            senhaInvalido.setText("Insira uma senha válida!");

        }
            else if(!senha.equals("")){
                senhaInvalido.setText("");
            }

        else if (confirmacaoSenha.equals("")) {
            confirmacaoInvalido.setText("O campo confirmação de senha é obrigatório!");

        }
            else if(!confirmacaoSenha.equals("")){
                confirmacaoInvalido.setText("");
            }

        else if (!confirmacaoSenha.equals(senha)){
            confirmacaoInvalido.setText("A senha não é a mesma informada!");

        }
            else if(!nome.equals("")){
                nomeInvalido.setText("");
            }

        else {
            //Objeto c1 da classe Carro
            Cadastro c1 = new Cadastro(nome, email, senha, confirmacaoSenha);

            Intent iMaps = new Intent(getApplicationContext(), MapsActivity.class);
            startActivity(iMaps);
        }

    }
}
