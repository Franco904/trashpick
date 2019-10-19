package br.eti.tavares.trashpick;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.MenuItem;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private static final float ZOOM_CAMERA = 5f;
    private final List<Coordenada_lixo> lixos = new ArrayList<>();
    private DatabaseReference dbRef;
    private DatabaseReference clRef;
    private ValueEventListener clListener;

    private int debug;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        onCreateView();

        dbRef = FirebaseDatabase.getInstance().getReference();

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    public void onCreateView() {

        BottomNavigationView menu = findViewById(R.id.bottomNavigationView);

        menu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                String title = (String) item.getTitle();
                switch (title) {
                    case "Jogar":
//                        Intent iMap = new Intent(getApplicationContext(), MapsActivity.class);
//                        startActivity(iMap);
                        break;
                    case "Objetivos":
                        Intent iObjetivos = new Intent(getApplicationContext(), ObjetivosActivity.class);
                        startActivity(iObjetivos);
                        break;
                    case "Ranking":
                        Intent iRanking = new Intent(getApplicationContext(), RankingActivity.class);
                        startActivity(iRanking);
                        break;

                    case "Perfil":
                        Intent iPerfil = new Intent(getApplicationContext(), PerfilActivity.class);
                        startActivity(iPerfil);
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
    }


        @Override
    public void onStart() {
        super.onStart();
        //basicListen();
//        basicQuery();
//        basicQueryValueListener();
    }

    @Override
    public void onStop() {
        super.onStop();
        cleanBasicListener();
//        cleanBasicQuery();
    }

    public void basicListen(GoogleMap googleMap) {
        final GoogleMap oMap = googleMap;
        clRef = dbRef.child("coordenada_lixo");
        clListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot cl : dataSnapshot.getChildren()) {
                    Double latitude = (Double)cl.child("coordenada").child("latitude").getValue();
                    Double longitude = (Double)cl.child("coordenada").child("longitude").getValue();
                    String descricao = (String)cl.child("lixo").child("descricao").getValue();
                    String imagem = (String)cl.child("lixo").child("imagem").getValue();

                    lixos.add(new Coordenada_lixo(latitude, longitude, descricao, imagem));
                    debug = 0; // Apenas para colocar um brakepoint aqui;
                }
                criaMarcadores(oMap);
                debug = 0; // Apenas para colocar um brakepoint aqui;
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Could not successfully listen for data, log the error
                // Log.e(TAG, "messages:onCancelled:" + error.getMessage());
            }
        };
        clRef.addValueEventListener(clListener);
    }

    public void cleanBasicListener() {
        clRef.removeEventListener(clListener);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        basicListen(googleMap);
    }

    private void criaMarcadores(GoogleMap googleMap) {
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
                mMap.addMarker(new MarkerOptions().position(lixos.get(i).getLatLng()));
            }

        } else {
            //Define como padrão a localização do Senai

            mMap.moveCamera(CameraUpdateFactory.newLatLng(lixos.get(0).getLatLng()));
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

