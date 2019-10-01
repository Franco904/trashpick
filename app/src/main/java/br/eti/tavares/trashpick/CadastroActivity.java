package br.eti.tavares.trashpick;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CadastroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
    }
    public void OnClickMaps(View v) {
        Intent iMaps = new Intent(getApplicationContext(), MapsActivity.class);
        startActivity(iMaps);
    }
    public void OnClickLogin(View v){
        Intent iLogin = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(iLogin);
    }
}
