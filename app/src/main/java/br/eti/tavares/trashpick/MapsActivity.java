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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private static final float ZOOM_CAMERA = 17f;

    private List<Coordenada> pontos = new ArrayList<>();

    private void GetPontosCoordenadas(){

        pontos.add(0, new Coordenada(-27.5481014, -48.4980635, "SENAI CTAI Florianópolis"));
        pontos.add(1, new Coordenada(-27.452139, -48.4581527, "Terminal de Integração de Canasvieiras"));
        pontos.add(2, new Coordenada(-27.5842091,-48.5227018,  "Terminal de Integração da Trindade"));
        pontos.add(3, new Coordenada(-27.5874017, -48.4998144, "CIASC"));
        pontos.add(4, new Coordenada(-27.554041, -48.498309, "Floripa Shopping"));
        pontos.add(5, new Coordenada(-27.5898513,-48.5174582,  "Iguatemi Florianópolis"));
        pontos.add(6, new Coordenada(-27.5849209, -48.5450034, "Beiramar Shopping"));
        pontos.add(7, new Coordenada(-27.5429536, -48.5234776, "Hotel Sesc Cacupé"));
        pontos.add(8, new Coordenada(-27.5918307, -48.4931127, "FIESC"));
        pontos.add(9, new Coordenada(-27.5918563, -48.5162041, "Forneria Catarina"));
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

        mMap.moveCamera(CameraUpdateFactory.zoomTo(ZOOM_CAMERA));//zoom da câmera
        //mMap.setTrafficEnabled(true);//tráfeto de carros

        //Caso tenha permissão para localização via GPS

        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ||
            checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        } else {
            for(int i=0; i<pontos.size(); i++){

                mMap.addMarker(new MarkerOptions().position(pontos.get(i).localizacao()).title(pontos.get(i).getTitulo()));
            }

            //Define como padrão a Localização do Senai

            mMap.moveCamera(CameraUpdateFactory.newLatLng(pontos.get(0).localizacao()));

        }
        //mMap.addMarker(new MarkerOptions().position(LOCALIZACAO_SENAI).title(TITULOSENAI));
        //for(cont=0;cont<NUMERO_COORDENADAS;cont++){
          //  mMap.addMarker(new MarkerOptions().position(LOCALIZACAO_SENAI).title(TITULOSENAI));
     //   }
        
    }
    //public void Exibir(View v) {

    //   Random RandomPoint1 = new Random();
    //   Random RandomPoint2 = new Random();
    //   Random RandomPoint3 = new Random();
    //   Random RandomPoint4 = new Random();
    //    Random RandomPoint5 = new Random();
    //   Random RandomPoint6 = new Random();
    // }

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
