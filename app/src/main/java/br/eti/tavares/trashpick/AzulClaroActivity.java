package br.eti.tavares.trashpick;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import br.eti.tavares.trashpick.adapter.AdapterListViewAzulClaro;
import br.eti.tavares.trashpick.model.ItemBiblioteca;

public class AzulClaroActivity extends AppCompatActivity {

    private List<ItemBiblioteca> itemAzul = new ArrayList<>();

    private DatabaseReference dbLixoAzul;
    private DatabaseReference aRef;
    private ValueEventListener aListener;
    private Query queryAzul;


    private void GetItensBiblioteca(){

        dbLixoAzul = FirebaseDatabase.getInstance().getReference();
        aRef = dbLixoAzul.child("lixo");
        queryAzul = aRef.orderByChild("categoria").equalTo("Azul");
        aListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                itemAzul.clear();
                for (DataSnapshot a : dataSnapshot.getChildren()) {
                    String nome = (String) a.child("nome").getValue();
                    String descricao = (String) a.child("descricao").getValue();
                    String imagem = (String) a.child("imagem").getValue();

                    itemAzul.add(new ItemBiblioteca(nome, descricao, imagem));
                }
                populateListAzul();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Erro ao fazer o listen de dados
            }
        };
        queryAzul.addValueEventListener(aListener);

    }

    private void populateListAzul() {
        final ListView itensBiblioteca = (ListView) findViewById(R.id.listAzulClaro);
        AdapterListViewAzulClaro adapterAzulClaro = new AdapterListViewAzulClaro(this, itemAzul);
        itensBiblioteca.setAdapter(adapterAzulClaro);

        itensBiblioteca.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> objetivosDisponiveis, View view, int position, long id) {

                final androidx.appcompat.app.AlertDialog dialog;
                androidx.appcompat.app.AlertDialog.Builder builder = new AlertDialog.Builder(AzulClaroActivity.this);
                builder.setTitle(itemAzul.get(position).getNome());
                builder.setMessage(itemAzul.get(position).getDescricao());
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
        setContentView(R.layout.activity_azul_claro);

        this.GetItensBiblioteca();
    }

    public void OnClickVoltar(View v){
        finish();
    }
}
