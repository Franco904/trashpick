package br.eti.tavares.trashpick;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler handle = new Handler();
        handle.postDelayed(new Runnable() {
            @Override
            public void run() {
                mostrarCadastroActivity();
            }
        }, 2000);
    }
    private void mostrarCadastroActivity() {
        Intent iCadastro= new Intent(getApplicationContext(), CadastroActivity.class);
        startActivity(iCadastro);
        finish();
    }
}
