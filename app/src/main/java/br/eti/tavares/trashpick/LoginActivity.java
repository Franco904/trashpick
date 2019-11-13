package br.eti.tavares.trashpick;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
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

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);

    auth = FirebaseAuth.getInstance();

    FirebaseUser user = auth.getCurrentUser();
  }

  public void OnClickCadastro(View v) {
    Intent iCadastro = new Intent(getApplicationContext(), CadastroActivity.class);
    startActivity(iCadastro);
  }

  public void OnClickEntrar(View v) {

    TextView erroAutenticar = findViewById(R.id.txtErroAutenticar);

    EditText editEmail = findViewById(R.id.editEmail);
    EditText editSenha = findViewById(R.id.editSenha);
    TextView emailInvalido = findViewById(R.id.txtEmailInvalido);
    TextView senhaInvalida = findViewById(R.id.txtSenhaInvalida);
    final TextView txtErroAutenticar = findViewById(R.id.txtErroAutenticar);

    emailInvalido.setText("");
    senhaInvalida.setText("");
    txtErroAutenticar.setText("");
    txtErroAutenticar.setBackgroundColor(Color.parseColor("#FFFFFF"));

    editEmail.setHintTextColor(Color.parseColor("#AAAAAA"));
    editSenha.setHintTextColor(Color.parseColor("#AAAAAA"));

//       String email = editEmail.getText().toString();
//       String senha = editSenha.getText().toString();
    String email = "francostavares2003@gmail.com";
    String senha = "minhasenha";
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

      //Firebase autentication
      auth.signInWithEmailAndPassword(email, senha)
              .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                  if (task.isSuccessful()) {
                    FirebaseUser user = auth.getCurrentUser();
                    Intent iMaps = new Intent(getApplicationContext(), MapsActivity.class);
                    startActivity(iMaps);
                    finish();
                    Toast.makeText(getApplicationContext(), "Bem-vindo(a) " + (user.getDisplayName()) + "!", Toast.LENGTH_LONG).show();
                  } else {
                    txtErroAutenticar.setText("Não foi possível encontrar este usuário e/ou senha!");
                    txtErroAutenticar.setBackgroundColor(Color.parseColor("#A8F78B8B"));
                  }

                }
              });
    }
  }
}