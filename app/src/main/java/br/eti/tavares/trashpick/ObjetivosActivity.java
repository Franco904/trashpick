package br.eti.tavares.trashpick;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ObjetivosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_objetivos);
    }

    public void OnClickMaps(View v) {
        Intent iMaps = new Intent(getApplicationContext(), MapsActivity.class);
        startActivity(iMaps);
    }
    public void OnClickRanking(View v) {
        Intent iRanking = new Intent(getApplicationContext(), RankingActivity.class);
        startActivity(iRanking);
    }
    public void OnClickPerfil(View v) {
        Intent iPerfil = new Intent(getApplicationContext(), PerfilActivity.class);
        startActivity(iPerfil);
    }
}


