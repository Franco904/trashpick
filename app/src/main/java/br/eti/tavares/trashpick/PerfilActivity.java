package br.eti.tavares.trashpick;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class PerfilActivity extends AppCompatActivity {
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        onCreateView();
        auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        final TextView txtNomeSobrenome = findViewById(R.id.txtUsuario);
        txtNomeSobrenome.setText(user.getDisplayName());
    }

    public void onCreateView() {
        final BottomNavigationView menu = findViewById(R.id.bottomNavigationView);
        menu.setSelectedItemId(R.id.bottomNavigationPerfilMenuId);
        menu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                String title = (String) item.getTitle();
                switch (title) {
                    case "Jogar":
                        Intent iMap = new Intent(getApplicationContext(), MapsActivity.class);
                        startActivity(iMap);

                        break;
                    case "Objetivos":
                        Intent iObjetivos = new Intent(getApplicationContext(), ObjetivosActivity.class);
                        startActivity(iObjetivos);
                        break;

                    case "Biblioteca":
                        Intent iBiblioteca = new Intent(getApplicationContext(), BibliotecaActivity.class);
                        startActivity(iBiblioteca);
                        break;

                    case "Ranking":
                        Intent iRanking = new Intent(getApplicationContext(), RankingActivity.class);
                        startActivity(iRanking);
                        break;
                    case "Perfil":
                        //Intent iPerfil = new Intent(getApplicationContext(), PerfilActivity.class);
                        //startActivity(iPerfil);
                        break;
                }
                return false;
            }
        });
    }

    public void OnClickSobre(View v){
        Intent iSobre = new Intent(getApplicationContext(), SobreActivity.class);
        startActivity(iSobre);
    }
    public void OnClickCreditos(View v) {
        Intent iCreditos = new Intent(getApplicationContext(), CreditosActivity.class);
        startActivity(iCreditos);
    }
    public void OnClickConfiguracoes(View v) {
        Intent iConfiguracoes = new Intent(getApplicationContext(), ConfiguracoesActivity.class);
        startActivity(iConfiguracoes);
    }

    public void onClickLogout(View v){
        final androidx.appcompat.app.AlertDialog dialog;
        androidx.appcompat.app.AlertDialog.Builder builder = new AlertDialog.Builder(PerfilActivity.this);
        builder.setTitle("Confirmar Logout");
        builder.setMessage("Deseja sair da conta?");
        builder.setPositiveButton("Fazer logout", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {

                FirebaseAuth.getInstance().signOut();
                Intent iSplash = new Intent(getApplicationContext(), SplashActivity.class);
                startActivity(iSplash);

            }
        }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                //sem ação
            }
        });
        //define um botão como negativo.

        //cria o AlertDialog
        dialog = builder.create();

        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface arg0) {

                dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.colorTrashPick));
            }
        });

        dialog.show();
    }
}