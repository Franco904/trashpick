package br.eti.tavares.trashpick;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.eti.tavares.trashpick.adapter.AdapterListViewObjetivos;
import br.eti.tavares.trashpick.model.Objetivo;

public class ObjetivosActivity extends AppCompatActivity {

    private DatabaseReference dbObjetivo;
    private DatabaseReference obRef;
    private ValueEventListener obListener;
    private List<Objetivo> objetivos = new ArrayList<>();

    private void GetObjetivosDisponiveis() {
        dbObjetivo = FirebaseDatabase.getInstance().getReference();
        obRef = dbObjetivo.child("objetivos");
        obListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ob : dataSnapshot.getChildren()) {
                    String nome = (String) ob.child("nome").getValue();
                    String descricao = (String) ob.child("descricao").getValue();
                    String imagem = (String) ob.child("imagem").getValue();

                    objetivos.add(new Objetivo(nome, descricao, imagem));
                }
                populateListObjetivos();
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        };
        obRef.addValueEventListener(obListener);

    }

    private void populateListObjetivos() {
        final ListView objetivosDisponiveis = (ListView) findViewById(R.id.listDisponiveis);
        AdapterListViewObjetivos adapterobjetivos = new AdapterListViewObjetivos(this, objetivos);
        objetivosDisponiveis.setAdapter(adapterobjetivos);

        final Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);

        objetivosDisponiveis.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> objetivosDisponiveis, View view, int position, long id) {

                final androidx.appcompat.app.AlertDialog dialog;
                androidx.appcompat.app.AlertDialog.Builder builder = new AlertDialog.Builder(ObjetivosActivity.this);
                builder.setTitle(objetivos.get(position).getTitulo());
                builder.setMessage("Deseja iniciar o objetivo?");
                builder.setPositiveButton("Iniciar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {

                        if (Build.VERSION.SDK_INT >= 26) {
                            vibrator.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE));
                        } else {
                            vibrator.vibrate(200);
                        }
                        Toast.makeText(getApplicationContext(), "Objetivo iniciado!", Toast.LENGTH_SHORT).show();

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
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_objetivos);

        this.GetObjetivosDisponiveis();
        onCreateView();

    }

    public void onCreateView() {

        final BottomNavigationView menu = findViewById(R.id.bottomNavigationView);
        menu.setSelectedItemId(R.id.bottomNavigationObjetivosMenuId);

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
//                       Intent iObjetivos = new Intent(getApplicationContext(), ObjetivosActivity.class);
//                       startActivity(iObjetivos);
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
                        Intent iPerfil = new Intent(getApplicationContext(), PerfilActivity.class);
                        startActivity(iPerfil);
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
    }
}