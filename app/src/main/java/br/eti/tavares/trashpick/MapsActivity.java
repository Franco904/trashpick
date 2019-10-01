package br.eti.tavares.trashpick;

import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import java.util.Random;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private static final double LATITUDE = -27.5481014; //altitude e longetude do Senai
    private static final double LONGITUDE = -48.4980635;
    private static final double LATTICAN = -27.452139; //altitude e longetude do Tican
    private static final double LONGTICAN = -48.4581527;

    private static final float NUMERO_PONTOS = 10;
    private static final float ZOOM_CAMERA = 17f;
    private static final LatLng LOCALIZACAO_SENAI = new LatLng(LATITUDE, LONGITUDE);
    private static final LatLng LOCALIZACAO_TICAN = new LatLng(LATTICAN, LONGTICAN);
    private static final String TITULOTICAN = "Terminal de Integração de Canasvieiras";
    private static final String TITULOSENAI = "Florianópolis";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.moveCamera(CameraUpdateFactory.zoomTo(ZOOM_CAMERA));//zoom da câmera
        //mMap.setTrafficEnabled(true);//tráfeto de carros


        //Caso tenha permissão para localização via GPS

        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ||
                checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        } else {
            //Define como padrão a Localização do Senai
            mMap.addMarker(new MarkerOptions().position(LOCALIZACAO_SENAI).title(TITULOSENAI));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(LOCALIZACAO_SENAI));

        }
        mMap.addMarker(new MarkerOptions().position(LOCALIZACAO_SENAI).title(TITULOSENAI));

        
    }
    public void Exibir(View v) {

        Random RandomPoint1 = new Random();
        Random RandomPoint2 = new Random();
        Random RandomPoint3 = new Random();
        Random RandomPoint4 = new Random();
        Random RandomPoint5 = new Random();
        Random RandomPoint6 = new Random();
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
