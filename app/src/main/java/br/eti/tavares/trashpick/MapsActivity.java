package br.eti.tavares.trashpick;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

  private GoogleMap mMap;
  private static final float ZOOM_CAMERA = 5f;
  private final List<Coordenada_lixo> lixos = new ArrayList<>();
  private DatabaseReference dbRef;
  private DatabaseReference clRef;
  private ValueEventListener clListener;

//    private ConstraintLayout main_constraint;
//
//    private MapsActivity mapsFragment;
//    private ObjetivosActivity objetivosFragment;
//    private RankingActivity rankingFragment;
//    private PerfilActivity perfilFragment;

  private int debug;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_maps);

    onCreateView();

//        main_constraint = (ConstraintLayout) findViewById(R.id.map);
//        mapsFragment = new MapsActivity();
//        objetivosFragment = new ObjetivosActivity();
//        rankingFragment = new RankingActivity();
//        perfilFragment = new PerfilActivity();

    dbRef = FirebaseDatabase.getInstance().getReference();

    // Obtain the SupportMapFragment and get notified when the map is ready to be used.
    SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
            .findFragmentById(R.id.map);
    mapFragment.getMapAsync(this);
  }

  public void onCreateView() {

    BottomNavigationView menu = findViewById(R.id.bottomNavigationView);

    menu.setSelectedItemId(R.id.bottomNavigationJogarMenuId);

    menu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
      @Override
      public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        String title = (String) item.getTitle();
        switch (title) {
          case "Jogar":
//           Intent iMap = new Intent(getApplicationContext(), MapsActivity.class);
//           startActivity(iMap);
            break;

          case "Objetivos":
            Intent iObjetivos = new Intent(getApplicationContext(), ObjetivosTabActivity.class);
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

//    private void setFragment(Fragment fragment) {
//        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//        fragmentTransaction.replace(R.id.map, fragment);
//        fragmentTransaction.commit();
//
//    }


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
    String nomeDrawable;
    int idDrawable;
    mMap = googleMap;
    //zoom da câmera
    mMap.moveCamera(CameraUpdateFactory.zoomTo(ZOOM_CAMERA));

    mMap.setOnMarkerClickListener(new OnMarkerClickListener() {

      @Override
      public boolean onMarkerClick(Marker marker) {
        marker.showInfoWindow();
//                Toast.makeText(MapsActivity.this, marker.getTitle(), Toast.LENGTH_SHORT).show();
//                synchronized (DataStorage.paradas) {
//                    Parada p = DataStorage.paradas.get(Integer.parseInt(marker.getTitle()));
//                    DialogFragment f = MostrarInfoParada.newInstance(p.getId());
//                    f.show(getActivity().getSupportFragmentManager(), "Info");
//                }

        return true;
      }
    });

    //Caso tenha permissão para localização via GPS
    if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ||
            checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
      mMap.setMyLocationEnabled(true);

      for (int i = 0; i < lixos.size(); i++) {

        nomeDrawable = lixos.get(i).getImagemLixo();

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(lixos.get(i).getLatLng())
                .title("Lixo " + Integer.toString(i))
                .snippet(lixos.get(i).getDescricaoLixo())
                .icon(BitmapDescriptorFactory.fromResource(Imagens.getDrawable(nomeDrawable)));

        InfoWindowData info = new InfoWindowData();
        info.setImagem(nomeDrawable);
        info.setNome_lixo(lixos.get(i).getDescricaoLixo());
        info.setDetalhes_lixo("Lixo " + Integer.toString(i));

        CustomInfoWindowGoogleMap customInfoWindow = new CustomInfoWindowGoogleMap(this);
        mMap.setInfoWindowAdapter(customInfoWindow);

//         chamar getColetar para aparecer o AlertDialog quando InfoWindow clicada
//        customInfoWindow(getColetar(View v))
//
        Marker m = mMap.addMarker(markerOptions);
        m.setTag(info);
      }

    } else {
      //Define como padrão a localização do Senai
      mMap.moveCamera(CameraUpdateFactory.newLatLng(lixos.get(0).getLatLng()));
    }
  }

  public void getColetar(View v){

    androidx.appcompat.app.AlertDialog dialog;
    androidx.appcompat.app.AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setTitle("Informações do lixo");
    builder.setMessage("Este é seu lixo encontrado!");
    builder.setPositiveButton("Coletar", new DialogInterface.OnClickListener() {
      public void onClick(DialogInterface arg0, int arg1) {

        Toast.makeText(getApplicationContext(), "Lixo coletado! :)", Toast.LENGTH_SHORT).show();
      }
    });

    //define um botão como negativo.
    builder.setNegativeButton("Sair", new DialogInterface.OnClickListener() {
      public void onClick(DialogInterface arg0, int arg1) {
        //sem ação
      }
    });
    //cria o AlertDialog
    dialog = builder.create();
    dialog.show();
  }

//


  private int getLixoImagem(String imagem) {
    Resources resources = this.getResources();
    return resources.getIdentifier(imagem, "drawable", this.getPackageName());
  }

}

//    findViewById().setOnClickListener(new View.OnClickListener() {
//@Override
//public void onClick(View v) {
//        exibirInformacoes();
//        }
//        });


//    public void exibirInformacoes(){
//        AlertDialog.Builder magBox = new AlertDialog.Builder(this);
//        magBox.setTitle("");
//        magBox.setIcon(android.R.drawable.ic_dialog_map);
//        magBox.setMessage("Este é seu lixo coletado!");
//        magBox.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                Toast.makeText(MapsActivity.this, "Lixo coletado!", Toast.LENGTH_SHORT).show();
//            }
//        });
//        magBox.show();
//    }