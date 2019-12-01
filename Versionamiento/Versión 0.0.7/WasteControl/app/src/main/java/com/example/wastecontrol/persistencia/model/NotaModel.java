package com.example.wastecontrol.persistencia.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class NotaModel extends Model {

    public static String NODE_NAME= "nota";

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    private String titulo;
    private String contenido;
    private Date fecha;

    public static class Key {
        public static final String ID = Model.Key.ID;
        public static final String TITULO = "titulo";
        public static final String CONTENIDO = "contenido";
        public static final String FECHA = "fecha";
    }

    @Override
    public String NODE_NAME() {
        return NODE_NAME;
    }

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put(Key.ID, getId());
        map.put(Key.TITULO, getTitulo());
        map.put(Key.CONTENIDO, getContenido());
        map.put(Key.FECHA, getFecha());
        return map;
    }


}
