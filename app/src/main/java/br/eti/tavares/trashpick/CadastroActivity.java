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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import br.eti.tavares.trashpick.model.Cadastro;
import br.eti.tavares.trashpick.model.PessoaRanking;

public class CadastroActivity extends AppCompatActivity {

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        auth = FirebaseAuth.getInstance();
    }

    public void OnClickLogin(View v) {
        Intent iLogin = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(iLogin);
    }

    public void OnClickEntrar(View v) {

        final EditText editNome = findViewById(R.id.editNome);
        EditText editEmail = findViewById(R.id.editEmail);
        EditText editSenha = findViewById(R.id.editSenha);
        EditText editConfirmarSenha = findViewById(R.id.editConfirmarSenha);
        TextView nomeInvalido = findViewById(R.id.textNomeInvalido);
        TextView emailInvalido = findViewById(R.id.txtEmailInvalido);
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

        if (senha.length() < 8) {
            senhaInvalido.setText("A senha deve ter pelo menos 8 caracteres!");
            editSenha.setHintTextColor(Color.parseColor("#FFCC0000"));
            erro = true;
        }
        if (confirmacaoSenha.equals("")) {
            confirmacaoInvalido.setText("O campo confirmação de senha é obrigatório!");
            editConfirmarSenha.setHintTextColor(Color.parseColor("#FFCC0000"));
            erro = true;
        }

        if (!confirmacaoSenha.equals(senha)) {
            confirmacaoInvalido.setText("A senha não é a mesma informada!");
            editConfirmarSenha.setHintTextColor(Color.parseColor("#FFCC0000"));
            erro = true;
        }

        if (!erro) {
            //Objeto c1 da classe Cadastro
            Cadastro c1 = new Cadastro(nome, email, senha, confirmacaoSenha);

            //Firebase
            auth.createUserWithEmailAndPassword(email, senha)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser user = auth.getCurrentUser();

                                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                        .setDisplayName(editNome.getText().toString())
                                        .build();

                                user.updateProfile(profileUpdates)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    // Não faz nada
                                                }
                                            }
                                        });

                                FirebaseUser jogador = auth.getCurrentUser();

                                PessoaRanking pessoaRanking = new PessoaRanking(jogador.getDisplayName(), 0, "ic_account_circle_black_24dp");


                                DatabaseReference rRef = FirebaseDatabase.getInstance().getReference("ranking/" + jogador.getUid());
                                rRef.setValue(pessoaRanking)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {

                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(getApplicationContext(), "Erro ao colocar jogador ao ranking", Toast.LENGTH_LONG).show();
                                            }
                                        });

                                Intent iMaps = new Intent(getApplicationContext(), MapsActivity.class);
                                startActivity(iMaps);
                                Toast.makeText(getApplicationContext(), "Cadastro efetuado com sucesso!", Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(getApplicationContext(), "Falha na criação do usuário!", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
        }
    }


}