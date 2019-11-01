package br.eti.tavares.trashpick;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ObjetivosTabActivity extends AppCompatActivity {
// implements TabLayout.OnTabSelectedListener

    private List<Objetivo> objetivos = new ArrayList<>();
//    TabLayout tabLayout;


    private void GetObjetivosDisponiveis(){

        objetivos.add(0, new Objetivo("A Papelada", "Ache e recolha 10 folhas de papel - 20 pts", R.drawable.ic_jornal_round));
        objetivos.add(1,new Objetivo("Notícias do Dia!", "Ache e recolha 1 jornal - 10 pts", R.drawable.ic_folha_papel_round));
        objetivos.add(2,new Objetivo("Algodão Doce", "Ache e recolha 20 algodões - 30 pts", R.drawable.ic_algodao_round));
        objetivos.add(3,new Objetivo("O Salvador Animal", "Ache e recolha 5 embalagens pet - 20 pts", R.drawable.ic_embalagempet_round));
        objetivos.add(4,new Objetivo("Luvas de Ouro", "Ache e recolha 2 luvas de borracha - 50 pts",  R.drawable.ic_luvasborracha_round));
        objetivos.add(5,new Objetivo("O Robô", "Ache e recolha 5 lixos eletrônicos - 40 pts", R.drawable.ic_lixoeletronico_round));
        objetivos.add(6,new Objetivo("Uma Pausa para o Café", "Ache e recolha 2 copos de isopor - 30 pts", R.drawable.ic_copoisopor_round));
        objetivos.add(7,new Objetivo("Rei Esponja","Ache e recolha 3 esponjas - 40 pts" , R.drawable.ic_esponja_round));
        objetivos.add(8,new Objetivo("Senhor do Ferro Velho", "Ache e recolha 10 latas de aço - 30 pts", R.drawable.ic_lataaco_round));
        objetivos.add(9,new Objetivo("Missão Olhos de Vidro", "Ache e recolha 2 garrafas de vidro - 35 pts", R.drawable.ic_garrafavidro_round));
        objetivos.add(10,new Objetivo("Energizado", "Ache e recolha 4 pilhas ou baterias - 45 pts", R.drawable.ic_pilhabateria_round));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_objetivos_tab);

        this.GetObjetivosDisponiveis();
        onCreateView();

        ListView objetivosDisponiveis = (ListView) findViewById(R.id.listDisponiveis);
        AdapterListViewObjetivos adapterobjetivos = new AdapterListViewObjetivos(this, objetivos);
        objetivosDisponiveis.setAdapter(adapterobjetivos);


    }

    public void onCreateView() {

        BottomNavigationView menu = findViewById(R.id.bottomNavigationView);
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
//                        Intent iObjetivos = new Intent(getApplicationContext(), ObjetivosTabActivity.class);
//                        startActivity(iObjetivos);
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
//        onCreateView();

//        tabLayout = (TabLayout) findViewById(R.id.tabs);
//        tabLayout.addTab(tabLayout.newTab().setText("Disponíveis"));
//        tabLayout.addTab(tabLayout.newTab().setText("Em Andamento"));
//
//        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
//
//        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                int position = tab.getPosition();
//                switch (position) {
//                    case 0:
//                        getSupportFragmentManager().beginTransaction()
//                                .replace(R.id.constraintLayout, new DisponivelFragment()).commit();
//                        break;
//                    case 1:
//                        getSupportFragmentManager().beginTransaction()
//                                .replace(R.id.containerFragmentos, new EmAndamentoFragment()).commit();
//                        break;
//                    default:
//                        break;
//                }
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//            }
//        });
//
//    }
//
//    @Override
//    public void onTabSelected(TabLayout.Tab tab) { }
//
//    @Override
//    public void onTabUnselected(TabLayout.Tab tab) { }
//
//    @Override
//    public void onTabReselected(TabLayout.Tab tab) { }
//
//    }



