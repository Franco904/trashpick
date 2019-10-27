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

public class ObjetivosTabActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {

    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_objetivos_tab);
//        onCreateView();

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.addTab(tabLayout.newTab().setText("Disponíveis"));
        tabLayout.addTab(tabLayout.newTab().setText("Em Andamento"));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                switch (position) {
                    case 0:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.containerFragmentos, new DisponivelFragment()).commit();
                        break;
                    case 1:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.containerFragmentos, new EmAndamentoFragment()).commit();
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) { }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) { }

    @Override
    public void onTabReselected(TabLayout.Tab tab) { }

//    public void onCreateView() {
//
//        BottomNavigationView menu = findViewById(R.id.bottomNavigationView);
//        menu.setSelectedItemId(R.id.bottomNavigationObjetivosMenuId);
//
//        menu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                String title = (String) item.getTitle();
//                switch (title) {
//                    case "Jogar":
//                        Intent iMap = new Intent(getApplicationContext(), MapsActivity.class);
//                        startActivity(iMap);
//                        break;
//                    case "Objetivos":
////                        Intent iObjetivos = new Intent(getApplicationContext(), ObjetivosTabActivity.class);
////                        startActivity(iObjetivos);
//                        break;
//                    case "Ranking":
//                        Intent iRanking = new Intent(getApplicationContext(), RankingActivity.class);
//                        startActivity(iRanking);
//                        break;
//
//                    case "Perfil":
//                        Intent iPerfil = new Intent(getApplicationContext(), PerfilActivity.class);
//                        startActivity(iPerfil);
//                        break;
//                    default:
//                        break;
//                }
//                return false;
//            }
//        });
//    }
}