package br.eti.tavares.trashpick;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void OnClickCadastro(View v) {
        finish();
    }

    public void OnClickMaps(View v) {
        Intent iMaps = new Intent(getApplicationContext(), MapsActivity.class);
        startActivity(iMaps);


    }
}
