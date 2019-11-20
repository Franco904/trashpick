package br.eti.tavares.trashpick;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.eti.tavares.trashpick.adapter.AdapterListViewRank;
import br.eti.tavares.trashpick.model.PessoaRanking;

public class RankingActivity extends AppCompatActivity {

    private FirebaseAuth auth;

    private DatabaseReference dbRanking;
    private DatabaseReference rRef;
    private ValueEventListener rListener;
    private Query queryPontos;

    private long meusPontos;

    private List<PessoaRanking> jogadores = new ArrayList<>();

    private void GetPessoasRanking() {

        final String uId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        dbRanking = FirebaseDatabase.getInstance().getReference();
        rRef = dbRanking.child("ranking");
        queryPontos = rRef.orderByChild("pontos");

        rListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                jogadores.clear();
                for (DataSnapshot r : dataSnapshot.getChildren()) {
                    String nome = (String) r.child("nome").getValue();
                    long pontos = (long) r.child("pontos").getValue();
                    String foto = (String) r.child("foto").getValue();

                    jogadores.add(new PessoaRanking(nome, pontos, foto));

                    if(uId.equals(r.getKey())){
                        meusPontos = pontos;
                    }
                }

                Collections.sort(jogadores, Collections.reverseOrder());
                populateHeader();
                populateListRanking();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Erro ao fazer o listen de dados
            }
        };
        queryPontos.addValueEventListener(rListener);

    }


    private void populateHeader() {
        TextView pontosAtual = findViewById(R.id.textPontosAtual);
        pontosAtual.setText(String.valueOf(meusPontos));
    }

    private void populateListRanking() {

        ListView ranking = (ListView) findViewById(R.id.listranking);
        AdapterListViewRank adapterrank = new AdapterListViewRank(this, jogadores);
        ranking.setAdapter(adapterrank);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        this.GetPessoasRanking();
        onCreateView();

        auth = FirebaseAuth.getInstance();

        FirebaseUser user = auth.getCurrentUser();

        final TextView txtNome = findViewById(R.id.txtNome);
        txtNome.setText(user.getDisplayName());

    }

    public void onCreateView() {

        final BottomNavigationView menu = findViewById(R.id.bottomNavigationView);
        menu.setSelectedItemId(R.id.bottomNavigationRankingMenuId);

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
                        Intent iBiblioteca = new Intent(getApplicationContext(), BibliotecaActivity.class);
                        startActivity(iBiblioteca);
                        break;

                    case "Ranking":
//                       Intent iRanking = new Intent(getApplicationContext(), RankingActivity.class);
//                       startActivity(iRanking);
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