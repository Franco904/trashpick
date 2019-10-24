package br.eti.tavares.trashpick;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class RankingActivity extends AppCompatActivity {

  private FirebaseAuth auth;

  private List<PessoaRanking> jogadores = new ArrayList<>();

  private void GetPessoasRanking(){

    jogadores.add(0, new PessoaRanking("Paul Battist", 11569, 1));
    jogadores.add(1,new PessoaRanking("Will Jones", 10567, 1));
    jogadores.add(2,new PessoaRanking("Earl Oliver", 10300, 1));
    jogadores.add(3,new PessoaRanking("Ruth Shermann", 9978, 1));
    jogadores.add(4,new PessoaRanking("Dora Morton", 8900, 1));
    jogadores.add(5,new PessoaRanking("Gary Warren", 8876, 1));
    jogadores.add(6,new PessoaRanking("Andre Houston", 8769, 1));
    jogadores.add(7,new PessoaRanking("Taylor Cage", 8704, 1));
    jogadores.add(8,new PessoaRanking("Augusta Abbott", 8408, 1));
    jogadores.add(9,new PessoaRanking("Scarlett Wilson", 8405, 1));
    jogadores.add(10,new PessoaRanking("Tony Neff", 8357, 1));
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

    ListView ranking = (ListView) findViewById(R.id.listranking);
    AdapterListViewRank adapter = new AdapterListViewRank(this, jogadores);
    ranking.setAdapter(adapter);

  }

  public void onCreateView() {

    BottomNavigationView menu = findViewById(R.id.bottomNavigationView);
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
            Intent iObjetivos = new Intent(getApplicationContext(), ObjetivosTabActivity.class);
            startActivity(iObjetivos);
            break;
          case "Ranking":
//                        Intent iRanking = new Intent(getApplicationContext(), RankingActivity.class);
//                        startActivity(iRanking);
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