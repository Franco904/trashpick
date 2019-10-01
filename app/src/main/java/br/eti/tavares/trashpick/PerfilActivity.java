package br.eti.tavares.trashpick;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PerfilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
    }
    public void OnClickMaps(View v) {
        Intent iMaps = new Intent(getApplicationContext(), MapsActivity.class);
        startActivity(iMaps);
    }
    public void OnClickObjetivos(View v) {
        Intent iObjetivos = new Intent(getApplicationContext(), ObjetivosActivity.class);
        startActivity(iObjetivos);
    }
    public void OnClickRanking(View v) {
        Intent iRanking = new Intent(getApplicationContext(), RankingActivity.class);
        startActivity(iRanking);
    }
}
