package br.eti.tavares.trashpick;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ObjetivosActivity extends AppCompatActivity {
// implements TabLayout.OnTabSelectedListener

    private DatabaseReference dbObjetivo;
    private DatabaseReference obRef;
    private ValueEventListener obListener;
    private List<Objetivo> objetivos = new ArrayList<>();
//    TabLayout tabLayout;


    private void GetObjetivosDisponiveis(){
        dbObjetivo = FirebaseDatabase.getInstance().getReference();
        obRef = dbObjetivo.child("objetivos");
        obListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot cl : dataSnapshot.getChildren()) {
                    String nome = (String) cl.child("nome").getValue();
                    String descricao = (String) cl.child("descricao").getValue();
                    String imagem = (String)cl.child("imagem").getValue();

                    objetivos.add(new Objetivo(nome, descricao, imagem));
                }
                populateListObjetivos();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Could not successfully listen for data, log the error
                // Log.e(TAG, "messages:onCancelled:" + error.getMessage());
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
//                String itemSelecionado = (String) objetivosDisponiveis.getItemAtPosition(position);

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
//                        dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.colorTrashPick));
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
//                        Intent iObjetivos = new Intent(getApplicationContext(), ObjetivosActivity.class);
//                        startActivity(iObjetivos);
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