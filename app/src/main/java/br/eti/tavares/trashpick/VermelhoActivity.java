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

public class VermelhoActivity extends AppCompatActivity {

    private List<ItemBiblioteca> itemVermelho = new ArrayList<>();

    private void GetItensBiblioteca(){

        itemVermelho.add(0, new ItemBiblioteca("Cigarro", R.drawable.ic_cigarro_round));
        itemVermelho.add(1, new ItemBiblioteca("Borracha", R.drawable.ic_borracha_round));
        itemVermelho.add(2, new ItemBiblioteca("Cerâmica", R.drawable.ic_ceramica_round));
        itemVermelho.add(3, new ItemBiblioteca("Esponjas", R.drawable.ic_esponja_round));
        itemVermelho.add(4, new ItemBiblioteca("Luvas de Borracha", R.drawable.ic_luvasborracha_round));
        itemVermelho.add(5, new ItemBiblioteca("Copo de Isopor", R.drawable.ic_copoisopor_round));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vermelho);

        this.GetItensBiblioteca();

        final ListView itensBiblioteca = (ListView) findViewById(R.id.listVermelho);
        AdapterListViewVermelho adapterVermelho = new AdapterListViewVermelho(this, itemVermelho);
        itensBiblioteca.setAdapter(adapterVermelho);

        itensBiblioteca.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> objetivosDisponiveis, View view, int position, long id) {
//                String itemSelecionado = (String) objetivosDisponiveis.getItemAtPosition(position);

                final androidx.appcompat.app.AlertDialog dialog;
                androidx.appcompat.app.AlertDialog.Builder builder = new AlertDialog.Builder(VermelhoActivity.this);
                builder.setTitle(itemVermelho.get(position).getNome());
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
