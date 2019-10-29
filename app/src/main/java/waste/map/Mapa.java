package waste.map;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
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

public class Mapa extends FragmentActivity implements OnMapReadyCallback {
    String[] arregloJson;
    private GoogleMap mMap;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);
        arregloJson = new String[2];
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
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
                                    for (int i = 0;i<arreglo.length();i++){
                                        arregloJson = arreglo.getJSONObject(i).get("Ubicacion").toString().split(",");
                                        Double coordenadaX = Double.valueOf(arregloJson[0].substring(1));
                                        Double coordenadaY = Double.valueOf(arregloJson[1].substring(0,arregloJson[1].length()-1));
                                        String nombrePuntero = arreglo.getJSONObject(i).getString("Nombre Punto de Recolección");
                                        mMap.addMarker(new MarkerOptions().position(new LatLng(coordenadaX,coordenadaY)).title(nombrePuntero));
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        } else {

                        }
                    }
                });
        // Add a marker in Sydney and move the camera
    }
}
