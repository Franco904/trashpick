package br.eti.tavares.trashpick;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AzulClaroActivity extends AppCompatActivity {

    private List<ItemBiblioteca> itemAzul = new ArrayList<>();

    private void GetItensBiblioteca(){

        itemAzul.add(0, new ItemBiblioteca("Algodão", R.drawable.ic_algodao_round));
        itemAzul.add(1, new ItemBiblioteca("Cascas de Frutas", R.drawable.ic_cascas_frutas_round));
        itemAzul.add(2, new ItemBiblioteca("Chiclete", R.drawable.ic_chiclete_round));
        itemAzul.add(3, new ItemBiblioteca("Copos Plásticos", R.drawable.ic_copoplastico_round));
        itemAzul.add(4, new ItemBiblioteca("Embalagem de Papel", R.drawable.ic_embalagempapel_round));
        itemAzul.add(5, new ItemBiblioteca("Embalagens Pet", R.drawable.ic_embalagempet_round));
        itemAzul.add(6, new ItemBiblioteca("Fralda Descartável", R.drawable.ic_fralda_round));
        itemAzul.add(7, new ItemBiblioteca("Garrafas Plásticas", R.drawable.ic_garrafaplastica_round));
        itemAzul.add(8, new ItemBiblioteca("Guardanapos de Papel", R.drawable.ic_guardanapo_round));
        itemAzul.add(9, new ItemBiblioteca("Jornal", R.drawable.ic_jornal_round));
        itemAzul.add(10, new ItemBiblioteca("Nylon", R.drawable.ic_nylon_round));
        itemAzul.add(11, new ItemBiblioteca("Palito de Fósforo", R.drawable.ic_palitofosforo_round));
        itemAzul.add(12, new ItemBiblioteca("Folha de Papel", R.drawable.ic_folha_papel_round));
        itemAzul.add(13, new ItemBiblioteca("Saco Plástico", R.drawable.ic_sacoplastico_round));
        itemAzul.add(14, new ItemBiblioteca("Tampinha de Garrafa", R.drawable.ic_tampinha_round));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_azul_claro);

        this.GetItensBiblioteca();

        final ListView itensBiblioteca = (ListView) findViewById(R.id.listAzulClaro);
        AdapterListViewAzulClaro adapterAzulClaro = new AdapterListViewAzulClaro(this, itemAzul);
        itensBiblioteca.setAdapter(adapterAzulClaro);

        itensBiblioteca.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> objetivosDisponiveis, View view, int position, long id) {
//                String itemSelecionado = (String) objetivosDisponiveis.getItemAtPosition(position);

                final androidx.appcompat.app.AlertDialog dialog;
                androidx.appcompat.app.AlertDialog.Builder builder = new AlertDialog.Builder(AzulClaroActivity.this);
                builder.setTitle(itemAzul.get(position).getNome());
                builder.setMessage("Aqui vão as informações\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                builder.setPositiveButton("Ir ao mapa", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {

                        Intent iMap = new Intent(getApplicationContext(), MapsActivity.class);
                        startActivity(iMap);
                    }
                });

                //define um botão como negativo.
                builder.setNegativeButton("Fechar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        //sem ação
                    }
                });
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

    public void OnClickVoltar(View v){
        finish();
    }
}
