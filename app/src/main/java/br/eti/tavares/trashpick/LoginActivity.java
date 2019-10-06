package br.eti.tavares.trashpick;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth auth;

    private EditText editEmail;
    private EditText editSenha;
    private TextView emailInvalido;
    private TextView senhaInvalida;
    private TextView erroAutenticar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        editEmail = findViewById(R.id.editEmail);
        editSenha = findViewById(R.id.editSenha);
        emailInvalido = findViewById(R.id.textEmailInvalido);
        senhaInvalida = findViewById(R.id.textSenhaInvalida);
        erroAutenticar = findViewById(R.id.textErroAutenticar);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        auth = FirebaseAuth.getInstance();
    }

    public void OnClickCadastro(View v) {
        Intent iCadastro = new Intent(getApplicationContext(), CadastroActivity.class);
        startActivity(iCadastro);
    }

    public void OnClickEntrar(View v) {

        this.emailInvalido.setText("");
        this.senhaInvalida.setText("");
        this.erroAutenticar.setText("");
        this.erroAutenticar.setBackgroundColor(Color.parseColor("#FFFFFF"));

        this.editEmail.setHintTextColor(Color.parseColor("#AAAAAA"));
        this.editSenha.setHintTextColor(Color.parseColor("#AAAAAA"));

        String email = editEmail.getText().toString();
        String senha = editSenha.getText().toString();
        boolean erro = false;


        if (email.equals("")) {
            this.emailInvalido.setText("Insira um e-mail válido!");
            this.editEmail.setHintTextColor(Color.parseColor("#FFCC0000"));
            erro = true;
        }

        if (senha.equals("")) {
            this.senhaInvalida.setText("Insira uma senha válida!");
            this.editSenha.setHintTextColor(Color.parseColor("#FFCC0000"));
            erro = true;
        }

        if (!erro) {
            //Objeto c1 da classe Carro
            Login l1 = new Login(email, senha);

            auth.signInWithEmailAndPassword(email, senha)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Intent iMaps = new Intent(getApplicationContext(), MapsActivity.class);
                                startActivity(iMaps);
                            } else {
                                erroAutenticar.setText("Não foi possível encontrar este usuário e/ou senha!");
                                erroAutenticar.setBackgroundColor(Color.parseColor("#A8F78B8B"));
                            }

                        }
                    });
        }
    }
}
