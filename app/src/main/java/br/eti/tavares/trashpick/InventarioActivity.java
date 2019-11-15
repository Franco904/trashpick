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
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class InventarioActivity extends AppCompatActivity {

    private DatabaseReference dbInventario;
    private DatabaseReference inRef;
    private ValueEventListener inListener;
    private Query queryInventario;
    private List<Inventario> lixoInventario = new ArrayList<>();
    private long pontos = 0;


    private void GetLixosInventario(){
        dbInventario = FirebaseDatabase.getInstance().getReference();
        inRef = dbInventario.child("inventario");
        queryInventario = inRef.orderByChild("idJogador").equalTo(FirebaseAuth.getInstance().getCurrentUser().getUid());

        inListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot item : dataSnapshot.getChildren()) {
                    String idLixo = item.child("lixo").child("id").getValue().toString();
                    String nomeLixo = item.child("lixo").child("nome").getValue().toString();
                    String descricaoLixo = item.child("lixo").child("descricao").getValue().toString();
                    String imagemLixo = item.child("lixo").child("imagem").getValue().toString();
                    String categoria = item.child("lixo").child("categoria").getValue().toString();
                    String idJogador = item.child("idJogador").getValue().toString();
                    LixoPayload lixo = new LixoPayload(idLixo, nomeLixo, imagemLixo, descricaoLixo, categoria);
                    lixoInventario.add(new Inventario(idJogador, lixo));
                }
                populateGridInventario();
                contarPontos();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Could not successfully listen for data, log the error
                // Log.e(TAG, "messages:onCancelled:" + error.getMessage());
            }
        };
        queryInventario.addValueEventListener(inListener);

    }

    private void populateGridInventario() {
        final GridView inventarioLixos = (GridView) findViewById(R.id.gridInventario);
        AdapterGridViewInventario adapterGridViewInventario = new AdapterGridViewInventario(this, lixoInventario);
        inventarioLixos.setAdapter(adapterGridViewInventario);
    }

    private void contarPontos() {

        TextView pontosAtual = findViewById(R.id.txtPontosAtual);
        pontosAtual.setText("");

        for (int i=0; i < lixoInventario.size(); i++){

            switch (lixoInventario.get(i).getLixo().getCategoria()) {
                case "Azul":
                    pontos = pontos + 20;
                    break;

                case "Laranja":
                    pontos = pontos + 25;
                    break;

                case "Vermelho":
                    pontos = pontos + 35;
                    break;

                case "Preto":
                    pontos = pontos + 50;
                    break;
            }
            pontosAtual.setText(String.valueOf(pontos));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventario);

        this.GetLixosInventario();
    }

    public void onClickVoltar(View v) {
        finish();
    }
}
