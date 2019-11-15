package br.eti.tavares.trashpick;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

  private GoogleMap mMap;
  private static final float ZOOM_CAMERA = 17f;
  private final List<Coordenada_lixo> lixos = new ArrayList<>();
  private ArrayList<Marker> mMarkerArray = new ArrayList<Marker>();
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

  public void onClickInventario(View v){
    Intent iInventario = new Intent(getApplicationContext(), InventarioActivity.class);
    startActivity(iInventario);
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
    clRef.removeEventListener(clListener);
//        cleanBasicQuery();
  }

  public void basicListen(GoogleMap googleMap) {
    final GoogleMap oMap = googleMap;
    //zoom da câmera
    oMap.moveCamera(CameraUpdateFactory.zoomTo(ZOOM_CAMERA));
    clRef = dbRef.child("coordenada_lixo");
    clListener = new ValueEventListener() {
      @Override
      public void onDataChange(DataSnapshot dataSnapshot) {
        lixos.clear();
        for (DataSnapshot cl : dataSnapshot.getChildren()) {
          String key = cl.getKey();
          Double latitude = (Double)cl.child("coordenada").child("latitude").getValue();
          Double longitude = (Double)cl.child("coordenada").child("longitude").getValue();
          String descricao = (String)cl.child("lixo").child("descricao").getValue();
          String nome = (String)cl.child("lixo").child("nome").getValue();
          String imagem = (String)cl.child("lixo").child("imagem").getValue();

          lixos.add(new Coordenada_lixo(key, latitude, longitude, nome, descricao, imagem));
        }
        eliminaMarcadores();
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

    //Caso tenha permissão para localização via GPS
    if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ||
            checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
      googleMap.setMyLocationEnabled(true);
    } else {
      //Define como padrão a localização do Senai
      googleMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(-27.5481014, -48.4980635)));
    }

    basicListen(googleMap);
  }

  private void eliminaMarcadores(){
    for (Marker marker : mMarkerArray) {
      marker.remove();
    }
  }


  private void criaMarcadores(GoogleMap googleMap) {
    String nomeDrawable;
    int idDrawable;
    mMap = googleMap;

    mMap.setOnMarkerClickListener(new OnMarkerClickListener() {

      @Override
      public boolean onMarkerClick(final Marker marker) {

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

        //cria o AlertDialog
        dialog = builder.create();

        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
          @Override
          public void onShow(DialogInterface arg0) {
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.colorTrashPick));
          }
        });

        dialog.show();

        return true;
      }
    });

    for (int i = 0; i < lixos.size(); i++) {

      if (lixos.get(i).lixo != null) {
        nomeDrawable = lixos.get(i).getImagemLixo();

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(lixos.get(i).getLatLng())
                .title("Lixo " + Integer.toString(i))
                .snippet(lixos.get(i).getNomeLixo() + ";" + lixos.get(i).getImagemLixo() + ";" + lixos.get(i).getId())
                .icon(BitmapDescriptorFactory.fromResource(Imagens.getDrawable(nomeDrawable)));

        Marker m = mMap.addMarker(markerOptions);
        mMarkerArray.add(m);
      }
    }

  }

  private void coletarLixo(final String id) {
    clRef = dbRef.child("coordenada_lixo/" + id + "/lixo");

    ValueEventListener listener = new ValueEventListener() {
      @Override
      public void onDataChange(DataSnapshot dataSnapshot) {
        Map<String, String> map = new HashMap<>();

        LixoPayload lixo;
        Object lixoLido;

        lixoLido = dataSnapshot.getValue();
        map = ((HashMap) lixoLido);

        lixo = new LixoPayload(map.get("id"), map.get("nome"), map.get("imagem"), map.get("descricao"), map.get("categoria"));
        colocarNoInventario(lixo, id, clRef, this);
      }

      @Override
      public void onCancelled(DatabaseError error) {
        // Could not successfully listen for data, log the error
        // Log.e(TAG, "messages:onCancelled:" + error.getMessage());
      }
    };

    clRef.addValueEventListener(listener);
  }

  private void colocarNoInventario(final LixoPayload lixo, final String id, final DatabaseReference clRef, ValueEventListener listener) {

    // Remover ValueEventListener do clRef
    clRef.removeEventListener(listener);

    final FirebaseUser jogador = FirebaseAuth.getInstance().getCurrentUser();
    Inventario item = new Inventario(jogador.getUid(), lixo);

    DatabaseReference iRef = dbRef.child("inventario");
    String key = iRef.push().getKey();
    iRef.child(key).setValue(item)
      .addOnSuccessListener(new OnSuccessListener<Void>() {
        @Override
        public void onSuccess(Void aVoid) {
          clRef.removeValue();
          Toast.makeText(getApplicationContext(), "Colocando " + lixo.getNome() + " no inventário de " + jogador.getDisplayName(), Toast.LENGTH_LONG).show();
        }
      })
      .addOnFailureListener(new OnFailureListener() {
        @Override
        public void onFailure(@NonNull Exception e) {
          Toast.makeText(getApplicationContext(), "Erro ao gravar item no inventário", Toast.LENGTH_LONG).show();
        }
      });
  }

}