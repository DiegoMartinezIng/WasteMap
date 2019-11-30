package com.example.wastecontrol.fragmen;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.wastecontrol.R;
import com.example.wastecontrol.persistencia.model.PuntoRecoleccionModel;
import com.example.wastecontrol.servicios.LocationService;
import com.example.wastecontrol.util.General;
import com.example.wastecontrol.util.ObjetoParametros;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MapaFragmentListener} interface
 * to handle interaction events.
 * Use the {@link MapaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MapaFragment extends Fragment implements OnMapReadyCallback {

    private MapView mapView;
    private GoogleMap mMap;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "ARG_PARAM1";
    private static final String ARG_PARAM2 = "ARG_PARAM2";

    // TODO: Rename and change types of parameters
    private GoogleMap.OnMarkerDragListener markerDragListener;
    private String mParam2;

    private MapaFragmentListener mListener;
    private View rootView;
    private UiSettings mUiSettings;
    private Geocoder geocoder;

    public MapaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param markerDragListener Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MapaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MapaFragment newInstance(Parcelable markerDragListener, String param2) {
        MapaFragment fragment = new MapaFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PARAM1, markerDragListener);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        markerDragListener = getMarkerDragListener();
        if (getArguments() != null) {
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.map_fragment, container, false);
        return rootView;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mapView = (MapView) rootView.findViewById(R.id.mapView);
        if(mapView != null){
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
        }
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            // mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MapaFragmentListener) {
            mListener = (MapaFragmentListener) context;
            markerDragListener = (mListener.getMarkerDragListener() != null)? mListener.getMarkerDragListener() : getMarkerDragListener();
            geocoder = (mListener.getGeocoder() == null)? new Geocoder(getContext(), Locale.getDefault()) : mListener.getGeocoder();
        } else {
//            throw new RuntimeException(context.toString()+ " must implement MapaFragmentListener");
        }
    }

    private GoogleMap.OnMarkerDragListener getMarkerDragListener() {
        return new GoogleMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDragStart(Marker marker) {

            }

            @Override
            public void onMarkerDrag(Marker marker) {

            }

            @Override
            public void onMarkerDragEnd(Marker marker) {
                if(mListener == null) return;
                List<Address> addresses = null;
                ObjetoParametros parametros = new ObjetoParametros();

                double lat = marker.getPosition().latitude;
                double lng = marker.getPosition().longitude;

                String ubicacion = String.format("(%s, %s)", lat, lng);

                parametros.set(PuntoRecoleccionModel.Key.UBICACION, ubicacion);
                parametros.set(PuntoRecoleccionModel.Key.LATITUD, Double.toString(lat));
                parametros.set(PuntoRecoleccionModel.Key.LONGITUD, Double.toString(lng));

                try {
                    addresses = geocoder.getFromLocation(lat, lng, 1);
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(getContext(), "No se encuentran direcciones",Toast.LENGTH_SHORT).show();
                }

                String direccion = "",
                    ciudad = "",
                    departamento = "",
                    pais = "";

                if( addresses != null && addresses.get(0) != null){
                    Address address = addresses.get(0);
                    direccion = address.getAddressLine(0);
                    ciudad = address.getLocality();
                    departamento = address.getAdminArea();
                    pais = address.getCountryName();
                }

                parametros.set(PuntoRecoleccionModel.Key.DIRECCION, direccion);
                parametros.set(PuntoRecoleccionModel.Key.CIUDAD, ciudad);
                parametros.set(PuntoRecoleccionModel.Key.DEPARTAMENTO, departamento);
                parametros.set(PuntoRecoleccionModel.Key.PAIS, pais);

                mListener.updateUbicacion(parametros);
            }
        };
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.mMap = googleMap;
        mMap.setOnMarkerDragListener(markerDragListener);
        this.configureMapSettings(mMap);
        LatLng actual ;
        if(LocationService.isRunning){
            Location location = LocationService.instance.getLocation();
            actual = new LatLng(location.getLatitude(), location.getLongitude());
        } else {
            actual = General.LOCATION_BOGOTA();
            Toast.makeText(this.getContext(), "Ubicacion por defecto",Toast.LENGTH_SHORT).show();
        }
        crearMarcador(actual, "Estoy aqui", true);
    }



    private void crearMarcador(LatLng latLng,String label, boolean moveCamera) {
        if(mMap == null) return;

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.draggable(Boolean.TRUE);
        markerOptions.title(label);
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
        mMap.addMarker(markerOptions);

        if (!moveCamera) return;

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, General.Cons.DEFAULT_ZOOM));
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

    public interface MapaFragmentListener {
        // TODO: Update argument type and name
        // void onFragmentInteraction(Uri uri);
        default GoogleMap.OnMarkerDragListener getMarkerDragListener(){return null;};
        default Geocoder getGeocoder(){return null;};
        default void updateUbicacion(ObjetoParametros parametros){};

    }
}
