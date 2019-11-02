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

public class LaranjaActivity extends AppCompatActivity {

    private List<ItemBiblioteca> itemLaranja = new ArrayList<>();

    private void GetItensBiblioteca(){

        itemLaranja.add(0, new ItemBiblioteca("Pano", R.drawable.ic_pano_round));
        itemLaranja.add(1, new ItemBiblioteca("Papel Plastificado", R.drawable.ic_papelplastificado_round));
        itemLaranja.add(2, new ItemBiblioteca("Meias de Lã", R.drawable.ic_meiasla_round));
        itemLaranja.add(3, new ItemBiblioteca("Madeira Pintada", R.drawable.ic_madeirapintada_round));
        itemLaranja.add(4, new ItemBiblioteca("Plástico", R.drawable.ic_plastico_round));
        itemLaranja.add(5, new ItemBiblioteca("Metal", R.drawable.ic_metal_round));
        itemLaranja.add(6, new ItemBiblioteca("Couro", R.drawable.ic_couro_round));
        itemLaranja.add(7, new ItemBiblioteca("Alumínio", R.drawable.ic_aluminio_round));
        itemLaranja.add(8, new ItemBiblioteca("Garrafas de Vidro", R.drawable.ic_garrafavidro_round));
        itemLaranja.add(9, new ItemBiblioteca("Latas de Alumínio", R.drawable.ic_lataaluminio_round));
        itemLaranja.add(10, new ItemBiblioteca("Latas de Aço", R.drawable.ic_lataaco_round));
        itemLaranja.add(11, new ItemBiblioteca("Louças", R.drawable.ic_loucas_round));
        itemLaranja.add(12, new ItemBiblioteca("Entulho", R.drawable.ic_entulho_round));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laranja);

        this.GetItensBiblioteca();

        final ListView itensBiblioteca = (ListView) findViewById(R.id.listLaranja);
        AdapterListViewLaranja adapterLaranja = new AdapterListViewLaranja(this, itemLaranja);
        itensBiblioteca.setAdapter(adapterLaranja);

        itensBiblioteca.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> objetivosDisponiveis, View view, int position, long id) {
//                String itemSelecionado = (String) objetivosDisponiveis.getItemAtPosition(position);

                final androidx.appcompat.app.AlertDialog dialog;
                androidx.appcompat.app.AlertDialog.Builder builder = new AlertDialog.Builder(LaranjaActivity.this);
                builder.setTitle(itemLaranja.get(position).getNome());
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
