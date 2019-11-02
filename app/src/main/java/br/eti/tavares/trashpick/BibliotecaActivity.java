package br.eti.tavares.trashpick;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BibliotecaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biblioteca);

        onCreateView();
    }

    public void onCreateView() {
        BottomNavigationView menu = findViewById(R.id.bottomNavigationView);
        menu.setSelectedItemId(R.id.bottomNavigationBibliotecaMenuId);
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
//                        Intent iBiblioteca = new Intent(getApplicationContext(), BibliotecaActivity.class);
//                        startActivity(iBiblioteca);
                        break;

                    case "Ranking":
                        Intent iRanking = new Intent(getApplicationContext(), RankingActivity.class);
                        startActivity(iRanking);
                        break;
                    case "Perfil":
                        Intent iPerfil = new Intent(getApplicationContext(), PerfilActivity.class);
                        startActivity(iPerfil);
                        break;
                }
                return false;
            }
        });

    }

    public void OnClickAzulClaro(View v) {
        Intent iAzulClaro = new Intent(getApplicationContext(), AzulClaroActivity.class);
        startActivity(iAzulClaro);
    }

    public void OnClickLaranja(View v) {
        Intent iLaranja = new Intent(getApplicationContext(), LaranjaActivity.class);
        startActivity(iLaranja);
    }

    public void OnClickVermelho(View v) {
        Intent iVermelho = new Intent(getApplicationContext(), VermelhoActivity.class);
        startActivity(iVermelho);
    }

    public void OnClickPreto(View v) {
        Intent iPreto = new Intent(getApplicationContext(), PretoActivity.class);
        startActivity(iPreto);
    }
}

