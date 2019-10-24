package br.eti.tavares.trashpick;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.eti.tavares.trashpick.ui.main.SectionsPagerAdapter;

public class ObjetivosTabActivity extends AppCompatActivity {

    private List<Objetivo> objetivos = new ArrayList<>();

    private void GetObjetivos(){

        objetivos.add(0, new Objetivo("O Rei do Papel", "Ache e recolha 10 guardanapos - 20 pts", 1));
        objetivos.add(1,new Objetivo("Notícias do Dia!", "Ache e recolha 1 jornal - 10 pts", 1));
        objetivos.add(2,new Objetivo("Algodão Doce", "Ache e recolha 20 algodões - 30 pts", 1));
        objetivos.add(3,new Objetivo("O Salvador Animal", "Ache e recolha 5 embalagens pet - 20 pts", 1));
        objetivos.add(4,new Objetivo("Luvas de Ouro", "Ache e recolha 2 luvas de borracha - 50 pts", 1));
        objetivos.add(5,new Objetivo("O Robô", "Ache e recolha 5 lixos eletrônicos - 40 pts", 1));
        objetivos.add(6,new Objetivo("É um Ovo de Tartaruga?", "Ache e recolha 2 isopores - 30 pts", 1));
        objetivos.add(7,new Objetivo("Bob Esponja","Ache e recolha 3 esponjas - 40 pts" , 1));
        objetivos.add(8,new Objetivo("Senhor do Ferro Velho", "Ache e recolha 10 latas de aço - 30 pts", 1));
        objetivos.add(9,new Objetivo("Missão Olhos de Vidro", "Ache e recolha 2 garrafas de vidro - 35 pts", 1));
        objetivos.add(10,new Objetivo("Energizado", "Ache e recolha 4 pilhas ou baterias - 45 pts", 1));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_objetivos_tab);

        this.GetObjetivos();

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        ListView objetivosDisponiveis = (ListView) findViewById(R.id.listDisponiveis);

    }
}