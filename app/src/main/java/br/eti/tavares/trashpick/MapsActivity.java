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

import com.google.android.material.badge.BadgeDrawable;
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
  private static final float ZOOM_CAMERA = 17f;
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

    final BottomNavigationView menu = findViewById(R.id.bottomNavigationView);

    menu.setSelectedItemId(R.id.bottomNavigationJogarMenuId);
//    BadgeDrawable badgeDrawable = menu.getOrCreateBadge(R.id.bottomNavigationJogarMenuId);

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
            Intent iObjetivos = new Intent(getApplicationContext(), ObjetivosActivity.class);
            startActivity(iObjetivos);
            break;

          case "Biblioteca":
            Intent iBiblioteca = new Intent(getApplicationContext(), BibliotecaActivity.class);
            startActivity(iBiblioteca);
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
          String key = cl.getKey();
          Double latitude = (Double)cl.child("coordenada").child("latitude").getValue();
          Double longitude = (Double)cl.child("coordenada").child("longitude").getValue();
          String descricao = (String)cl.child("lixo").child("descricao").getValue();
          String nome = (String)cl.child("lixo").child("descricao").getValue();
          String imagem = (String)cl.child("lixo").child("imagem").getValue();

          lixos.add(new Coordenada_lixo(key, latitude, longitude, nome, descricao, imagem));
        }
        criaMarcadores(oMap);
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
      public boolean onMarkerClick(final Marker marker) {
//        marker.showInfoWindow();

        final androidx.appcompat.app.AlertDialog dialog;
        androidx.appcompat.app.AlertDialog.Builder builder = new AlertDialog.Builder(MapsActivity.this);

        final String[] snippet = marker.getSnippet().split(";");


        builder.setIcon(Imagens.getDrawable(snippet[1]));
        builder.setTitle("Lixo encontrado no chão!");
        builder.setMessage("Veja! Parece que você encontrou " + snippet[0].toLowerCase() + " enquanto andava!");
        builder.setPositiveButton("Coletar", new DialogInterface.OnClickListener() {
          public void onClick(DialogInterface arg0, int arg1) {

            coletarLixo(snippet[2]);
          }
        });

        //define um botão como negativo.
//        builder.setNegativeButton("Sair", new DialogInterface.OnClickListener() {
//          public void onClick(DialogInterface arg0, int arg1) {
//            //sem ação
//          }
//        });
        //cria o AlertDialog
        dialog = builder.create();

        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
          @Override
          public void onShow(DialogInterface arg0) {
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.colorTrashPick));
          }
        });

        dialog.show();
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
    } else {
      //Define como padrão a localização do Senai
      mMap.moveCamera(CameraUpdateFactory.newLatLng(lixos.get(0).getLatLng()));
    }

    for (int i = 0; i < lixos.size(); i++) {

      nomeDrawable = lixos.get(i).getImagemLixo();

      MarkerOptions markerOptions = new MarkerOptions();
      markerOptions.position(lixos.get(i).getLatLng())
              .title("Lixo " + Integer.toString(i))
              .snippet(lixos.get(i).getNomeLixo() + ";" + lixos.get(i).getImagemLixo() + ";" + lixos.get(i).getId())
              .icon(BitmapDescriptorFactory.fromResource(Imagens.getDrawable(nomeDrawable)));

//      InfoWindowData info = new InfoWindowData();
//      info.setImagem(nomeDrawable);
//      info.setNome_lixo(lixos.get(i).getDescricaoLixo());
//      info.setDetalhes_lixo("Lixo " + Integer.toString(i));
//
//      CustomInfoWindowGoogleMap customInfoWindow = new CustomInfoWindowGoogleMap(this);
//      mMap.setInfoWindowAdapter(customInfoWindow);


//         chamar getColetar para aparecer o AlertDialog quando InfoWindow clicada
//        customInfoWindow(getColetar(View v))
//
      Marker m = mMap.addMarker(markerOptions);
//      m.setTag(info);
    }

  }

  private void coletarLixo(String id) {
    String idLixo = "0"; // Alterar isso para o valor que vira na Coordenada Lixo

    clRef = dbRef.child("coordenada_lixo/" + id);


    Toast.makeText(getApplicationContext(), "Vou coletar da Coordenada Lixo: " + id, Toast.LENGTH_LONG).show();
    //colocarNaSacola(idLixo);
  }

  private void colocarNaSacola(String idLixo) {
    // TODO
    Toast.makeText(getApplicationContext(), "Colocando Lixo: " + idLixo + " na sacola do jogador", Toast.LENGTH_LONG).show();
  }

}
