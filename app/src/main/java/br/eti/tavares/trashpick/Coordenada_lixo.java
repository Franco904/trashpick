package br.eti.tavares.trashpick;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.content.ContentValues.TAG;

public class Coordenada_lixo extends Coordenada {

    private int coordenada_id;
    private int lixo_id;
    private DatabaseReference myRef;

    public Coordenada_lixo(){}

    public Coordenada_lixo(double latitude, double longitude, int lixo_id, int coordenada_id){
        super(latitude, longitude);
        this.coordenada_id = coordenada_id;
        this.lixo_id = lixo_id;

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        this.myRef = database.getReference("coordenada_lixo");
    }

    public int getCoordenada_id() {
        return coordenada_id;
    }

    public void setCoordenada_id(int coordenada_id) {
        this.coordenada_id = coordenada_id;
    }

    public int getLixo_id() {
        return lixo_id;
    }

    public void setLixo_id(int lixo_id) {
        this.lixo_id = lixo_id;
    }

    public String getCoordenada(){
        return "Latitude : " + this.latitude + ", Longitude : " + this.longitude;

    }
}
