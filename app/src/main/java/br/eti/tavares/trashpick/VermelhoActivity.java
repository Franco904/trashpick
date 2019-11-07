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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class VermelhoActivity extends AppCompatActivity {

    private List<ItemBiblioteca> itemVermelho = new ArrayList<>();

    private DatabaseReference dbLixoVermelho;
    private DatabaseReference clRef;
    private ValueEventListener clListener;
    private Query queryVermelho;

    private void GetItensBiblioteca(){

        dbLixoVermelho = FirebaseDatabase.getInstance().getReference();
        clRef = dbLixoVermelho.child("lixo");
        queryVermelho = clRef.orderByChild("categoria").equalTo("Vermelho");
        clListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                itemVermelho.clear();
                for (DataSnapshot cl : dataSnapshot.getChildren()) {
                    String nome = (String) cl.child("nome").getValue();
                    String descricao = (String) cl.child("descricao").getValue();
                    String imagem = (String) cl.child("imagem").getValue();
                    String categoria = (String) cl.child("categoria").getValue();

                    itemVermelho.add(new ItemBiblioteca(nome, descricao, imagem));
                }
                populateListVermelho();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Could not successfully listen for data, log the error
                // Log.e(TAG, "messages:onCancelled:" + error.getMessage());
            }
        };
        queryVermelho.addValueEventListener(clListener);
    }

    private void populateListVermelho() {

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
                builder.setMessage(itemVermelho.get(position).getDescricao());
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vermelho);

        this.GetItensBiblioteca();
    }

    public void OnClickVoltar(View v){
        finish();
    }
}
