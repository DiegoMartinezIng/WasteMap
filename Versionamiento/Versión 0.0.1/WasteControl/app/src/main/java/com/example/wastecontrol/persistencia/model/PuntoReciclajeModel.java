package com.example.wastecontrol.persistencia.model;

import java.util.HashMap;
import java.util.Map;

public class PuntoReciclajeModel extends Model {

    public static String NODE_NAME = "punto_reciclaje";

    private String idTipoReciclaje;
    private String idUbicacion;

    public static class Key {
        public static final String ID = Model.Key.ID;
        public static final String ID_TIPO_RECICLAJE = "idTipoReciclaje";
        public static final String ID_UBICACION = "idUbicacion";
    }

    public PuntoReciclajeModel(){}

    @Override
    public String NODE_NAME() {
        return PuntoReciclajeModel.NODE_NAME;
    }

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put(Key.ID, getId());
        map.put(Key.ID_TIPO_RECICLAJE, getIdTipoReciclaje());
        map.put(Key.ID_UBICACION, getIdUbicacion());
        return map;
    }

    public PuntoReciclajeModel(String id, String idTipoReciclaje, String idUbicacion) {
        super(id);
        this.idTipoReciclaje = idTipoReciclaje;
        this.idUbicacion = idUbicacion;
    }

    public String getIdTipoReciclaje() {
        return idTipoReciclaje;
    }

    public void setIdTipoReciclaje(String idTipoReciclaje) {
        this.idTipoReciclaje = idTipoReciclaje;
    }

    public String getIdUbicacion() {
        return idUbicacion;
    }

    public void setIdUbicacion(String idUbicacion) {
        this.idUbicacion = idUbicacion;
    }
}
