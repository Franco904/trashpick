package br.eti.tavares.trashpick;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
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
import com.google.android.gms.maps.model.CameraPosition;
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

import br.eti.tavares.trashpick.model.Coordenada;
import br.eti.tavares.trashpick.model.Coordenada_lixo;
import br.eti.tavares.trashpick.model.Inventario;
import br.eti.tavares.trashpick.model.LixoPayload;
import br.eti.tavares.trashpick.model.PessoaRanking;
import br.eti.tavares.trashpick.services.Imagens;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private static final float ZOOM_CAMERA = 18f;
    private final List<Coordenada_lixo> lixos = new ArrayList<>();
    private ArrayList<Marker> mMarkerArray = new ArrayList<Marker>();
    private DatabaseReference dbRef;
    private DatabaseReference clRef;
    private ValueEventListener clListener;
    private ValueEventListener rListener;

    private PessoaRanking jogador;

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

    private void createPessoaRanking() {
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        DatabaseReference rRef = FirebaseDatabase.getInstance().getReference("ranking/" + user.getUid());
        rListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                jogador = new PessoaRanking(user.getDisplayName(), (long) dataSnapshot.child("pontos").getValue(),"ic_account_circle_black_24dp");

            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        };
        rRef.addValueEventListener(rListener);

    }

    private int pontosLixo(String categoria) {
        int pontos = 0;
        switch (categoria) {
            case "Azul":
                pontos = 20;
                break;
            case "Laranja":
                pontos = 25;
                break;
            case "Vermelho":
                pontos = 35;
                break;
            case "Preto":
                pontos = 50;
                break;
        }
        return pontos;
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

    public void onClickInventario(View v) {
        Intent iInventario = new Intent(getApplicationContext(), InventarioActivity.class);
        startActivity(iInventario);
    }

    @Override
    public void onStart() {
        super.onStart();
        createPessoaRanking();
    }

    @Override
    public void onStop() {
        super.onStop();
        cleanBasicListener();
        clRef.removeEventListener(clListener);
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
                    Double latitude = (Double) cl.child("coordenada").child("latitude").getValue();
                    Double longitude = (Double) cl.child("coordenada").child("longitude").getValue();
                    String descricao = (String) cl.child("lixo").child("descricao").getValue();
                    String nome = (String) cl.child("lixo").child("nome").getValue();
                    String imagem = (String) cl.child("lixo").child("imagem").getValue();

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

        final GoogleMap myMap = googleMap;

        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        //Caso tenha permissão para localização via GPS
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ||
                checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            googleMap.setMyLocationEnabled(true);
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10, this.myLocationListener);

        } else {
            //Define como padrão a localização da coordenada_lixo da posição 0
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(-27.5481014, -48.4980635)));
        }

        basicListen(googleMap);
    }

    private LocationListener myLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            // called when the listener is notified with a location update from the GPS
            LatLng userPosition = new LatLng(location.getLatitude(),
                    location.getLongitude());
            if (mMap != null) {
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userPosition, ZOOM_CAMERA));
            }

        }

        @Override
        public void onProviderDisabled(String provider) {
            // called when the GPS provider is turned off (user turning off the GPS on the phone)

        }

        @Override
        public void onProviderEnabled(String provider) {
            // called when the GPS provider is turned on (user turning on the GPS on the phone)
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            // called when the status of the GPS provider changes
        }
    };

    private void eliminaMarcadores() {
        for (Marker marker : mMarkerArray) {
            marker.remove();
        }
    }

    private double rad(double x) {
        return x * Math.PI / 180;
    }

    private double distancia(Coordenada p1, Coordenada p2) {
        double R = 6378137; // Earth’s mean radius in meter
        double dLat = rad(p2.getLatitude() - p1.getLatitude());
        double dLong = rad(p2.getLongitude() - p1.getLongitude());
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(rad(p1.getLatitude())) * Math.cos(rad(p2.getLatitude())) *
                        Math.sin(dLong / 2) * Math.sin(dLong / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double d = R * c;
        return d;
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

            if (lixos.get(i).getLixo() != null) {
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

        final Coordenada coordenadaAtual;

        CameraPosition posicaoAtual = mMap.getCameraPosition();
        coordenadaAtual = new Coordenada(posicaoAtual.target.latitude, posicaoAtual.target.longitude);

        clRef = dbRef.child("coordenada_lixo/" + id);

        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Coordenada coordenadaLixo;

                Map<String, String> map = new HashMap<>();

                LixoPayload lixo;
                Object coordenadaLixoLida;

                coordenadaLixoLida = dataSnapshot.getValue();
                map = ((HashMap) coordenadaLixoLida);

                String id = (String) ((HashMap) ((Object) map.get("lixo"))).get("id");
                String nome = (String) ((HashMap) ((Object) map.get("lixo"))).get("nome");
                String descricao = (String) ((HashMap) ((Object) map.get("lixo"))).get("descricao");
                String imagem = (String) ((HashMap) ((Object) map.get("lixo"))).get("imagem");
                String categoria = (String) ((HashMap) ((Object) map.get("lixo"))).get("categoria");

                Double latitude = (Double) ((HashMap) ((Object) map.get("coordenada"))).get("latitude");
                Double longitude = (Double) ((HashMap) ((Object) map.get("coordenada"))).get("longitude");

                coordenadaLixo = new Coordenada(latitude, longitude);

                lixo = new LixoPayload(id, nome, imagem, descricao, categoria);


                // Calcular a distância entre o ponto atual e o ponto do ixo coletado
                double d = distancia(coordenadaAtual, coordenadaLixo);

                if (d <= 10) {
                    colocarNoInventario(lixo, id, clRef, this);
                } else {
                    Toast.makeText(getApplicationContext(), "Você está muito longe desse lixo. Chegue mais perto!", Toast.LENGTH_SHORT).show();
                }
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

        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        Inventario item = new Inventario(user.getUid(), lixo);

        DatabaseReference iRef = dbRef.child("inventario");
        String key = iRef.push().getKey();
        iRef.child(key).setValue(item)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        clRef.child("lixo").removeValue();
                        Toast.makeText(getApplicationContext(), "Colocando " + lixo.getNome() + " no inventário de " + user.getDisplayName(), Toast.LENGTH_LONG).show();
                        atualizaRanking(user.getUid(), lixo.getCategoria());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Erro ao gravar item no inventário", Toast.LENGTH_LONG).show();
                    }
                });
    }


    private void atualizaRanking(String IdUser, String categoria) {
        jogador.getNome().hashCode();
        jogador.addPontos(pontosLixo(categoria));

        Map<String, Object> mJogador = new HashMap<>();
        mJogador.put("nome", jogador.getNome());
        mJogador.put("foto", jogador.getFoto());
        mJogador.put("pontos", jogador.getPontos());


        DatabaseReference rRef = dbRef.child("ranking/" + IdUser);
        rRef.setValue(mJogador)
            .addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                }
            })
            .addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getApplicationContext(), "Erro ao gravar jogador no ranking", Toast.LENGTH_LONG).show();
                }
            });

    }

}