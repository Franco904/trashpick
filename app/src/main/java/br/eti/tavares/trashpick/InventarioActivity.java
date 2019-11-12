package br.eti.tavares.trashpick;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class InventarioActivity extends AppCompatActivity {

    private DatabaseReference dbInventario;
    private DatabaseReference inRef;
    private ValueEventListener inListener;
    private List<Inventario> lixoInventario = new ArrayList<>();

    private void GetLixosInventario(){
        dbInventario = FirebaseDatabase.getInstance().getReference();
        inRef = dbInventario.child("lixo");
        inListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot cl : dataSnapshot.getChildren()) {
                    String imagem = (String)cl.child("imagem").getValue();

                    lixoInventario.add(new Inventario(imagem));
                }
                populateGridInventario();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Could not successfully listen for data, log the error
                // Log.e(TAG, "messages:onCancelled:" + error.getMessage());
            }
        };
        inRef.addValueEventListener(inListener);

    }

    private void populateGridInventario() {
        final GridView inventarioLixos = (GridView) findViewById(R.id.gridInventario);
        AdapterGridViewInventario adapterGridViewInventario = new AdapterGridViewInventario(this, lixoInventario);
        inventarioLixos.setAdapter(adapterGridViewInventario);

//        objetivosDisponiveis.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> objetivosDisponiveis, View view, int position, long id) {
//                String itemSelecionado = (String) objetivosDisponiveis.getItemAtPosition(position);

//                final androidx.appcompat.app.AlertDialog dialog;
//                androidx.appcompat.app.AlertDialog.Builder builder = new AlertDialog.Builder(InventarioActivity.this);
//                builder.setTitle(lixoInventario.get(position).getTitulo());
//                builder.setMessage("Deseja iniciar o objetivo?");
//                builder.setPositiveButton("Iniciar", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface arg0, int arg1) {
//
//                        Toast.makeText(getApplicationContext(), "Objetivo iniciado!", Toast.LENGTH_SHORT).show();
//
//                    }
//                }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface arg0, int arg1) {
//                        //sem ação
//                    }
//                });
//
//                //define um botão como negativo.
//                //cria o AlertDialog
//                dialog = builder.create();
//
//                dialog.setOnShowListener(new DialogInterface.OnShowListener() {
//                    @Override
//                    public void onShow(DialogInterface arg0) {
////                        dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.colorTrashPick));
//                        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.colorTrashPick));
//                    }
//                });
//
//                dialog.show();
//            }
//        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventario);

        this.GetLixosInventario();
    }

    public void onClickVoltar(View v){
        finish();
        overridePendingTransition(R.anim.mover_direita, R.anim.fade_in);
    }
}
