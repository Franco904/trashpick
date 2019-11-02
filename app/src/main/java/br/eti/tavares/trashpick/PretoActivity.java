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

public class PretoActivity extends AppCompatActivity {

    private List<ItemBiblioteca> itemPreto = new ArrayList<>();

    private void GetItensBiblioteca(){

        itemPreto.add(0, new ItemBiblioteca("Pilhas e Baterias", R.drawable.ic_pilhabateria_round));
        itemPreto.add(1, new ItemBiblioteca("Pneu", R.drawable.ic_pneu_round));
        itemPreto.add(2, new ItemBiblioteca("Lixo Eletrônico", R.drawable.ic_lixoeletronico_round));
        itemPreto.add(3, new ItemBiblioteca("Lata de Óleo Contaminada", R.drawable.ic_oleocontaminado_round));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preto);

        this.GetItensBiblioteca();

        final ListView itensBiblioteca = (ListView) findViewById(R.id.listPreto);
        AdapterListViewPreto adapterPreto = new AdapterListViewPreto(this, itemPreto);
        itensBiblioteca.setAdapter(adapterPreto);

        itensBiblioteca.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> objetivosDisponiveis, View view, int position, long id) {
//                String itemSelecionado = (String) objetivosDisponiveis.getItemAtPosition(position);

                final androidx.appcompat.app.AlertDialog dialog;
                androidx.appcompat.app.AlertDialog.Builder builder = new AlertDialog.Builder(PretoActivity.this);
                builder.setTitle(itemPreto.get(position).getNome());
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
