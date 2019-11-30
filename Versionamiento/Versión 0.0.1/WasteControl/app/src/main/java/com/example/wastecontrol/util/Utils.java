package com.example.wastecontrol.util;


import android.content.Context;
import android.location.Location;
import android.preference.PreferenceManager;

import com.example.wastecontrol.R;
import com.example.wastecontrol.persistencia.model.PuntoRecoleccionModel;
import com.example.wastecontrol.persistencia.model.PuntoRecoleccionModel.Key;

import java.text.DateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Utils {

    static final String KEY_REQUESTING_LOCATION_UPDATES = "requesting_locaction_updates";

    public static boolean requestingLocationUpdates(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getBoolean(KEY_REQUESTING_LOCATION_UPDATES, false);
    }

    public static void setRequestingLocationUpdates(Context context, boolean requestingLocationUpdates) {
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putBoolean(KEY_REQUESTING_LOCATION_UPDATES, requestingLocationUpdates)
                .apply();
    }

    public static String getLocationText(Location location) {
        return location == null ? "Unknown location" :
                "(" + location.getLatitude() + ", " + location.getLongitude() + ")";
    }

    public static String getLocationTitle(Context context) {
        return context.getString(R.string.location_updated, DateFormat.getDateTimeInstance().format(new Date()));
    }

    public static PuntoRecoleccionModel transforPuntoRecoleccionModel(ObjetoParametros objetoParametros){
        PuntoRecoleccionModel puntoRecoleccionModel = new PuntoRecoleccionModel();
        puntoRecoleccionModel.setNombrePuntoRecoleccion( objetoParametros.get(Key.NOMBRE_PUNTO_RECOLECCION,String.class));
        puntoRecoleccionModel.setDireccion(objetoParametros.get(Key.DIRECCION,String.class));
        puntoRecoleccionModel.setCiudad(objetoParametros.get(Key.CIUDAD,String.class));
        puntoRecoleccionModel.setDepartamento(objetoParametros.get(Key.DEPARTAMENTO,String.class));
        puntoRecoleccionModel.setPais(objetoParametros.get(Key.PAIS,String.class));
        puntoRecoleccionModel.setCategoriaResiduo(objetoParametros.get(Key.CATEGORIA_RESIDUO,String.class));
        puntoRecoleccionModel.setTipoResiduo(objetoParametros.get(Key.TIPO_RESIDUO,String.class));
        puntoRecoleccionModel.setNombreResiduo(objetoParametros.get(Key.NOMBRE_RESIDUO,String.class));
        puntoRecoleccionModel.setUbicacion(objetoParametros.get(Key.UBICACION,String.class));
        puntoRecoleccionModel.setHorario(objetoParametros.get(Key.HORARIO,String.class));
        puntoRecoleccionModel.setNombrePrograma(objetoParametros.get(Key.NOMBRE_PROGRAMA,String.class));
        puntoRecoleccionModel.setPersonaContacto(objetoParametros.get(Key.PERSONA_CONTACTO,String.class));
        puntoRecoleccionModel.setEmail(objetoParametros.get(Key.EMAIL,String.class));
        Utils.setLatLng(puntoRecoleccionModel, puntoRecoleccionModel.getUbicacion());
        return puntoRecoleccionModel;
    }

    public static PuntoRecoleccionModel setLatLng(PuntoRecoleccionModel puntoRecoleccionModel, String latLng){
        String lat = "", lng = "";
        lat = latLng.split(",")[0].replace("(", "").trim();
        lng = latLng.split(",")[1].replace(")", "").trim();
        // System.out.println(String.format("lat: %s lng: %s", lat,lng));
        puntoRecoleccionModel.setLatitud(lat);
        puntoRecoleccionModel.setLongitud(lng);
        // System.out.println(puntoRecoleccionModel.getUbicacion());

        return puntoRecoleccionModel;
    }

    public static List<PuntoRecoleccionModel> transformPuntoRecoleccionModels(Collection<ObjetoParametros> collection){
        return collection.stream()
                .map(objetoParametros -> transforPuntoRecoleccionModel(objetoParametros))
                .collect(Collectors.toList());
    }
}