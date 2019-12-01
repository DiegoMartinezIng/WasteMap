package com.example.wastecontrol.persistencia.model;

import java.util.HashMap;
import java.util.Map;

public class UbicacionModel extends Model {
    public static String NODE_NAME= "ubicacion";

    private String latitud;
    private String longitud;
    private String direccion;

    public static class Key {
        public static final String ID = Model.Key.ID;
        public static final String LATITUD = "latitud";
        public static final String LONGITUD = "longitud";
        public static final String DIRECCION = "direccion";
    }

    public UbicacionModel(){}

    @Override
    public String NODE_NAME() {
        return NODE_NAME;
    }

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put(Key.ID, getId());
        map.put(Key.LATITUD, getLatitud());
        map.put(Key.LONGITUD, getLongitud());
        map.put(Key.DIRECCION, getDireccion());
        return map;
    }

    public UbicacionModel(String id, String laltitud, String longitud, String direccion) {
        super(id);
        this.latitud = laltitud;
        this.longitud = longitud;
        this.direccion = direccion;
    }


    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

}
