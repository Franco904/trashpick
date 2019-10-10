package br.eti.tavares.trashpick;

import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
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

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private static final float ZOOM_CAMERA = 17f;

    //private Polyline polyline;
    private List<Coordenada> pontos = new ArrayList<>();

    private void GetPontosCoordenadas() {

        pontos.add(0, new Coordenada(-27.5481014, -48.4980635, 3));
        pontos.add(1, new Coordenada(-27.547380, -48.496937, 4));
        pontos.add(2, new Coordenada(-27.548012, -48.498001, 5));
        pontos.add(3, new Coordenada(-27.548020, -48.498480, 6));
        pontos.add(4, new Coordenada(-27.548028, -48.497641, 10));
        pontos.add(5, new Coordenada(-27.548204, -48.498768, 8));
        pontos.add(6, new Coordenada(-27.548340, -48.499066, 3));
        pontos.add(7, new Coordenada(-27.547908, -48.497289, 6));
        pontos.add(8, new Coordenada(-27.5918307, -48.497605, 5));
        pontos.add(9, new Coordenada(-27.5918563, -48.5162041, 9));
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

            for (int i = 0; i < pontos.size(); i++) {

                //valor = new Random()
                //      if(valor > A1) & (valor < A2){
                mMap.addMarker(new MarkerOptions().position(pontos.get(i).localizacao()));
            }

        } else {
            //Define como padrão a localização do Senai

            mMap.moveCamera(CameraUpdateFactory.newLatLng(pontos.get(0).localizacao()));

        }
    }
   /* public void drawRoute() {
        PolylineOptions po;

        if (polyline == null) {
            po = new PolylineOptions();

            for (int i = 0; i < pontos.size(); i++) {
                po.add(pontos.get(i));

            }
            po.color(Color.BLUE);
            polyline = mMap.addPolyline(po);
        } else {

            polyline.setPoints(pontos);
        }
    }*/

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