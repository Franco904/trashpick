package br.eti.tavares.trashpick;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

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
        final TextView txtNome = findViewById(R.id.txtNome);
        txtNome.setText(user.getDisplayName());
    }

    public void onCreateView() {
        BottomNavigationView menu = findViewById(R.id.bottomNavigationView);
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
                        Intent iObjetivos = new Intent(getApplicationContext(), ObjetivosTabActivity.class);
                        startActivity(iObjetivos);
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
}
