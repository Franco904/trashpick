package br.eti.tavares.trashpick;

import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.ClipData;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.Random;

import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private static final float ZOOM_CAMERA = 17f;

    private List<Coordenada_lixo> lixos = new ArrayList<>();

    private DatabaseReference myRef;

    private void GetPontosCoordenadas() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        this.myRef = database.getReference("coordenada_lixo");

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                for (DataSnapshot csls: dataSnapshot.getChildren()) {
                    Coordenada_lixo cl = csls.getValue(Coordenada_lixo.class);
                    Log.e("Get Coordenada_lixo", cl.getCoordenada());
                }

               int a=1;
        }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });

        int b=1;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        this.GetPontosCoordenadas();

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        //zoom da câmera
        mMap.moveCamera(CameraUpdateFactory.zoomTo(ZOOM_CAMERA));


        //Caso tenha permissão para localização via GPS
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ||
                checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);

            for (int i = 0; i < lixos.size(); i++) {

                //valor = new Random()
                //      if(valor > A1) & (valor < A2){
                mMap.addMarker(new MarkerOptions().position(lixos.get(i).localizacao()));
            }

        } else {
            //Define como padrão a localização do Senai

            mMap.moveCamera(CameraUpdateFactory.newLatLng(lixos.get(0).localizacao()));

        }
    }

    public void OnClickPerfil(View v) {
        Intent iPerfil = new Intent(getApplicationContext(), PerfilActivity.class);
        startActivity(iPerfil);
    }

    public void OnClickObjetivos(View v) {
        Intent iObjetivos = new Intent(getApplicationContext(), ObjetivosActivity.class);
        startActivity(iObjetivos);
    }

    public void OnClickRanking(View v) {
        Intent iRanking = new Intent(getApplicationContext(), RankingActivity.class);
        startActivity(iRanking);
    }
}