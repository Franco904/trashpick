package br.eti.tavares.trashpick;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

public class ConfiguracoesActivity extends AppCompatActivity {

    private Switch vibrar;
    private Button btnSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracoes);

        vibrar = (Switch) findViewById(R.id.switchVibrar);
        btnSalvar = (Button)findViewById(R.id.btnSalvar);

        btnSalvar.setOnClickListener(new View.OnClickListener() {

            final Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);

            @Override
            public void onClick(View v) {

                if (vibrar.isChecked()) {
                    Toast.makeText(ConfiguracoesActivity.this, "As vibrações foram ativadas", Toast.LENGTH_SHORT).show();
                    finish();
                }else
                    vibrator.cancel();
                    Toast.makeText(ConfiguracoesActivity.this, "Configurações salvas!", Toast.LENGTH_SHORT).show();
                    finish();
            }
        });
    }

}
