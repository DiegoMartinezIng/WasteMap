package com.example.wastecontrol.util;

import com.example.wastecontrol.persistencia.model.UbicacionModel;
import com.google.android.gms.maps.model.LatLng;

public class UbicacionUtil {


    public static LatLng getLatLng(UbicacionModel ubicacion){

        if(!(ubicacion != null
                && ubicacion.getLatitud() != null
                && !ubicacion.getLatitud().isEmpty()
                && ubicacion.getLongitud() != null
                && !ubicacion.getLongitud().isEmpty())

        ) return null;

        LatLng latLng = new LatLng(Double.parseDouble(ubicacion.getLatitud()), Double.parseDouble(ubicacion.getLongitud()));
        return latLng;
    }
}
