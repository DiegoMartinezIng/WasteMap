package com.example.wastecontrol.activities;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.example.wastecontrol.R;
import com.example.wastecontrol.persistencia.model.PuntoRecoleccionModel;
import com.example.wastecontrol.persistencia.respositorio.PuntoRecoleccionRepository;
import com.example.wastecontrol.persistencia.respositorio.Repository;
import com.example.wastecontrol.util.General;
import com.example.wastecontrol.util.SystemUtil;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class MapaActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener
{
    private static final String TAG = MapaActivity.class.getSimpleName();
    private GoogleMap mMap;
    private UiSettings mUiSettings;


    Location mLastLocation;
    Marker mCurrLocationMarker;
    private GoogleApiClient mGoogleApiClient;
    private boolean ejecutarDibujarPuntos = false;
    private PuntoRecoleccionModel model;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        Intent intent = getIntent();
        Bundle bundleExtra = intent.getBundleExtra(Data.BUNDLE);
        model = (PuntoRecoleccionModel) bundleExtra.getSerializable(Data.PUNTO_RECOLECCION_MODEL);
        ejecutarDibujarPuntos = bundleExtra.getBoolean(Data.ACCION_DIBUJAR_PUNTOS);
        mapFragment.getMapAsync(this);

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(SystemUtil.isGrantedPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)){
            if(mGoogleApiClient.isConnected()) {
                // LocationServices.getFusedLocationProviderClient(this).requestLocationUpdates(mGoogleApiClient, mLocationRequest);
            }
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        this.configureMapSettings(mMap);

        // Add a marker in Sydney and move the camera
        LatLng bogota = new LatLng(4.6785076,-74.0591655 );
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(bogota);
        markerOptions.title("Estoy aqui");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
        mMap.addMarker(markerOptions);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bogota, 11));
        // mMap.animateCamera(CameraUpdateFactory.zoomBy(DEFAULT_ZOOM));

        this.dibujarPuntos(this.ejecutarDibujarPuntos);
    }

    private void dibujarPuntos(boolean ejecutarDibujarPuntos) {
        if(!ejecutarDibujarPuntos) return;

        final Dialog mensaje = showMensaje("Cargando los puntos...");
        mensaje.show();

        Repository repository = PuntoRecoleccionRepository.getInstance();
        repository.buscar(this.model,list -> {
            mensaje.dismiss();
            List<PuntoRecoleccionModel> list1 = (List<PuntoRecoleccionModel>) list;
            MapaActivity.this.crearMarcadoresPuntoRecoleccion(list1);
        }, e -> {
            Toast.makeText(this,"Error inesperado", Toast.LENGTH_LONG).show();
        });
    }

    private void crearMarcadoresPuntoRecoleccion(List<PuntoRecoleccionModel> list1) {
        String label = "";
        LatLng ubicacion;

        Dialog mensaje = showMensaje("Agregando marcadores...");
        mensaje.show();
        for(PuntoRecoleccionModel punt : list1){
            label = String.format("%s (%s)", punt.getNombrePuntoRecoleccion(), punt.getDireccion());
            double lat = Double.parseDouble(punt.getLatitud());
            double lng = Double.parseDouble(punt.getLongitud());
            ubicacion = new LatLng(lat, lng);
            crearMarcador(ubicacion,label,false);
        }
        mensaje.dismiss();
        showMensaje("Marcadores agregados.").show();
    }


    private void crearMarcador(LatLng latLng,String label, boolean moveCamera) {
        if(mMap == null) return;

        System.out.println("dibujando: "+label);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.draggable(Boolean.FALSE);
        markerOptions.title(label);
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
        mMap.addMarker(markerOptions);

        if (!moveCamera) return;

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, General.Cons.DEFAULT_ZOOM));
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        // Toast.makeText(this, "conectado con google.maps", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onConnectionSuspended(int i) {
        // Toast.makeText(this, "CONEXION GMAPS SUSPENDIDA", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        // Toast.makeText(this.getApplicationContext(), "ERROR: conexion maps.google.com", Toast.LENGTH_LONG).show();
    }

    private void configureMapSettings(GoogleMap mMap) {
        mUiSettings = mMap.getUiSettings();

        // Keep the UI Settings state in sync with the checkboxes.
        mUiSettings.setZoomControlsEnabled(true);
        mUiSettings.setCompassEnabled(true);
        mUiSettings.setMyLocationButtonEnabled(true);
        mMap.setMyLocationEnabled(true);
        mUiSettings.setScrollGesturesEnabled(true);
        mUiSettings.setZoomGesturesEnabled(true);
        mUiSettings.setTiltGesturesEnabled(false);
        mUiSettings.setRotateGesturesEnabled(true);
    }

    public static class Data {
        public static final String PUNTO_RECOLECCION_MODEL = "PUNTO_RECOLECCION_MODEL";
        public static final String BUNDLE = "BUNDLE";
        public static final String ACCION_DIBUJAR_PUNTOS = "ACCION_DIBUJAR_PUNTOS";
        public static final String _DISPONIBLE = "ACCION_ACTUALIZAR";
    }

    private Dialog showMensaje(String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message)
                .setPositiveButton("OK", (dialogInterface, i) -> dialogInterface.dismiss());
        return builder.create();
    }
}
