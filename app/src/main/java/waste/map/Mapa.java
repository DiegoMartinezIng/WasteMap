package waste.map;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

public class Mapa extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {
    private String filtro;
    String[] arregloJson;
    private GoogleMap mMap;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private ListView lista_filtro_tipo;
    private ArrayList<String> arregloLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_mapa);
        arregloJson = new String[2];
        lista_filtro_tipo = findViewById(R.id.vista_lista_filtro_tipo);
        arregloLista = new ArrayList<>();
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        lista_filtro_tipo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                filtro = lista_filtro_tipo.getItemAtPosition(i).toString();
                filtro();
            }
        });
    }

    public void AbrirApp(View view) {
        Intent launcher = getPackageManager().getLaunchIntentForPackage("com.mygdx.juego");
        startActivity(launcher);
    }
    public void noticias(View view){
        Intent intent = new Intent(getApplicationContext(),Noticias.class);
        startActivity(intent);
    }

    public void pqr(View view) {
        Intent intent = new Intent(getApplicationContext(), pqr.class);
        startActivity(intent);
    }

    public void filtro() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        if (filtro == null) {
            final ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext()
                    , R.layout.support_simple_spinner_dropdown_item, arregloLista);
            mMap = googleMap;
            db.collection("PuntosAcopio")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    String Cadena = document.getData().get("Puntos").toString();
                                    try {
                                        JSONObject json = new JSONObject(Cadena);
                                        JSONArray arreglo = json.getJSONArray("Puntos");
                                        LatLng marcador = null;
                                        for (int i = 0; i < arreglo.length(); i++) {
                                            arregloJson = arreglo.getJSONObject(i).get("Ubicacion").toString().split(",");
                                            Double coordenadaX = Double.valueOf(arregloJson[0].substring(1));
                                            Double coordenadaY = Double.valueOf(arregloJson[1].substring(0, arregloJson[1].length() - 1));
                                            String nombrePuntero = arreglo.getJSONObject(i).getString("Nombre Punto de Recolección");
                                            String tipo = arreglo.getJSONObject(i).getString("Categoria residuo");
                                            marcador = new LatLng(coordenadaX, coordenadaY);
                                            if (tipo.contains("Plaguicidas")) {
                                                mMap.addMarker(new MarkerOptions().position(new LatLng(coordenadaX, coordenadaY)).title(nombrePuntero).icon(BitmapDescriptorFactory.fromResource(R.drawable.plaguicidaicono)));
                                            }
                                            if (tipo.contains("Bombillas")) {
                                                mMap.addMarker(new MarkerOptions().position(new LatLng(coordenadaX, coordenadaY)).title(nombrePuntero).icon(BitmapDescriptorFactory.fromResource(R.drawable.bombillaicono)));
                                            }
                                            if (tipo.contains("Neveras")) {
                                                mMap.addMarker(new MarkerOptions().position(new LatLng(coordenadaX, coordenadaY)).title(nombrePuntero).icon(BitmapDescriptorFactory.fromResource(R.drawable.neveraicono)));
                                            }
                                            if (tipo.contains("Baterias")) {
                                                mMap.addMarker(new MarkerOptions().position(new LatLng(coordenadaX, coordenadaY)).title(nombrePuntero).icon(BitmapDescriptorFactory.fromResource(R.drawable.bateriaplomoicono)));
                                            }
                                            if (tipo.contains("Llantas")) {
                                                mMap.addMarker(new MarkerOptions().position(new LatLng(coordenadaX, coordenadaY)).title(nombrePuntero).icon(BitmapDescriptorFactory.fromResource(R.drawable.llantaicono)));

                                            }
                                            if (arregloLista.contains(arreglo.getJSONObject(i).getString("Categoria residuo"))) {
                                                continue;
                                            } else {
                                                adapter.add(arreglo.getJSONObject(i).getString("Categoria residuo"));
                                            }

                                        }
                                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marcador, 10));
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            } else {

                            }
                        }
                    });
            lista_filtro_tipo.setAdapter(adapter);
            mMap.setOnMarkerClickListener(this);
        } else if (filtro != null) {
            mMap = googleMap;
            mMap.clear();
            db.collection("PuntosAcopio")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    String Cadena = document.getData().get("Puntos").toString();
                                    try {
                                        JSONObject json = new JSONObject(Cadena);
                                        JSONArray arreglo = json.getJSONArray("Puntos");
                                        LatLng marcador = null;
                                        for (int i = 0; i < arreglo.length(); i++) {
                                            if (arreglo.getJSONObject(i).getString("Categoria residuo").equals(filtro)) {
                                                arregloJson = arreglo.getJSONObject(i).get("Ubicacion").toString().split(",");
                                                Double coordenadaX = Double.valueOf(arregloJson[0].substring(1));
                                                Double coordenadaY = Double.valueOf(arregloJson[1].substring(0, arregloJson[1].length() - 1));
                                                String nombrePuntero = arreglo.getJSONObject(i).getString("Nombre Punto de Recolección");
                                                String tipo = arreglo.getJSONObject(i).getString("Categoria residuo");
                                                marcador = new LatLng(coordenadaX, coordenadaY);

                                                if (tipo.contains("Plaguicidas")) {
                                                    mMap.addMarker(new MarkerOptions().position(new LatLng(coordenadaX, coordenadaY)).title(nombrePuntero).icon(BitmapDescriptorFactory.fromResource(R.drawable.plaguicidaicono)));
                                                }
                                                if (tipo.contains("Bombillas")) {
                                                    mMap.addMarker(new MarkerOptions().position(new LatLng(coordenadaX, coordenadaY)).title(nombrePuntero).icon(BitmapDescriptorFactory.fromResource(R.drawable.bombillaicono)));
                                                }
                                                if (tipo.contains("Neveras")) {
                                                    mMap.addMarker(new MarkerOptions().position(new LatLng(coordenadaX, coordenadaY)).title(nombrePuntero).icon(BitmapDescriptorFactory.fromResource(R.drawable.neveraicono)));
                                                }
                                                if (tipo.contains("Baterías")) {
                                                    mMap.addMarker(new MarkerOptions().position(new LatLng(coordenadaX, coordenadaY)).title(nombrePuntero).icon(BitmapDescriptorFactory.fromResource(R.drawable.bateriaplomoicono)));
                                                }
                                                if (tipo.contains("Llantas")) {
                                                    mMap.addMarker(new MarkerOptions().position(new LatLng(coordenadaX, coordenadaY)).title(nombrePuntero).icon(BitmapDescriptorFactory.fromResource(R.drawable.llantaicono)));
                                                }
                                            }
                                        }
                                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marcador, 10));
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }
                    });
            mMap.setOnMarkerClickListener(this);
        }
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        final String nombre_marcador_pulsado = marker.getTitle();
        final JSONObject[] puntero_pulsado = new JSONObject[1];
        db.collection("PuntosAcopio")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String Cadena = document.getData().get("Puntos").toString();
                                try {
                                    JSONObject json = new JSONObject(Cadena);
                                    JSONArray arreglo = json.getJSONArray("Puntos");
                                    for (int i = 0; i < arreglo.length(); i++) {
                                        if (arreglo.getJSONObject(i).getString("Nombre Punto de Recolección").equals(nombre_marcador_pulsado)) {
                                            Intent intent = new Intent(getApplicationContext(), Pop_up.class);
                                            intent.putExtra("Coordenadas", arreglo.getJSONObject(i).getString("Ubicacion"));
                                            intent.putExtra("Nombre", arreglo.getJSONObject(i).getString("Nombre Punto de Recolección"));
                                            intent.putExtra("Direccion", arreglo.getJSONObject(i).getString("Dirección punto de recolección"));
                                            intent.putExtra("Tipo", arreglo.getJSONObject(i).getString("Tipo Residuo"));
                                            intent.putExtra("Persona", arreglo.getJSONObject(i).getString("Persona Contacto"));
                                            intent.putExtra("Nombre_programa", arreglo.getJSONObject(i).getString("Nombre Programa Posconsumo"));
                                            intent.putExtra("Horario", arreglo.getJSONObject(i).getString("Horario"));
                                            intent.putExtra("Correo", arreglo.getJSONObject(i).getString("Correo electrónico"));
                                            intent.putExtra("Categoria", arreglo.getJSONObject(i).getString("Categoria residuo"));
                                            startActivity(intent);
                                            break;
                                        }
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                });
        return false;
    }
}