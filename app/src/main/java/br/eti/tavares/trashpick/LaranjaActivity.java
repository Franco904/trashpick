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

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class LaranjaActivity extends AppCompatActivity {

    private DatabaseReference dbLixoLaranja;
    private DatabaseReference clRef;
    private ValueEventListener clListener;
    private Query queryLaranja;
    private List<ItemBiblioteca> itemLaranja = new ArrayList<>();

    private void GetItensBiblioteca(){

        dbLixoLaranja = FirebaseDatabase.getInstance().getReference();
        clRef = dbLixoLaranja.child("lixo");
        queryLaranja = clRef.orderByChild("categoria").equalTo("Laranja");

        clListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                itemLaranja.clear();
                for (DataSnapshot cl : dataSnapshot.getChildren()) {
                    String nome = (String) cl.child("nome").getValue();
                    String descricao = (String) cl.child("descricao").getValue();
                    String imagem = (String) cl.child("imagem").getValue();

                    itemLaranja.add(new ItemBiblioteca(nome, descricao, imagem));
                }
                populateListLaranja();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Could not successfully listen for data, log the error
                // Log.e(TAG, "messages:onCancelled:" + error.getMessage());
            }
        };
        queryLaranja.addValueEventListener(clListener);

    }


    private void populateListLaranja() {

        final ListView itensBiblioteca = (ListView) findViewById(R.id.listLaranja);
        AdapterListViewLaranja adapterLaranja = new AdapterListViewLaranja(this, itemLaranja);
        itensBiblioteca.setAdapter(adapterLaranja);

        itensBiblioteca.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> objetivosDisponiveis, View view, int position, long id) {

                final androidx.appcompat.app.AlertDialog dialog;
                androidx.appcompat.app.AlertDialog.Builder builder = new AlertDialog.Builder(LaranjaActivity.this);
                builder.setIcon(Imagens.getDrawable(itemLaranja.get(position).getImagem()));
                builder.setTitle(itemLaranja.get(position).getNome());
                builder.setMessage(itemLaranja.get(position).getDescricao());
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
        setContentView(R.layout.activity_laranja);

        this.GetItensBiblioteca();

        final ListView itensBiblioteca = (ListView) findViewById(R.id.listLaranja);
        AdapterListViewLaranja adapterLaranja = new AdapterListViewLaranja(this, itemLaranja);
        itensBiblioteca.setAdapter(adapterLaranja);

    }

    public void OnClickVoltar(View v){
        finish();
    }
}
