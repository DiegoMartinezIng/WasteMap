package com.example.wastecontrol.persistencia.model;

import java.util.HashMap;
import java.util.Map;

public class TipoReciclajeModel extends Model {
    public static String NODE_NAME = "tipo_reciclaje";
    private String codigo;
    private String etiqueta;

    public static class Key {
        public static final String ID = Model.Key.ID;
        public static final String CODIGO = "codigo";
        public static final String ETIQUETA = "etiqueta";
    }

    public TipoReciclajeModel(){}

    public TipoReciclajeModel(String id, String codigo, String etiqueta) {
        super(id);
        this.codigo = codigo;
        this.etiqueta = etiqueta;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    @Override
    public String NODE_NAME() {
        return TipoReciclajeModel.NODE_NAME;
    }

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put(Key.ID, getId());
        map.put(Key.CODIGO, getCodigo());
        map.put(Key.ETIQUETA, getEtiqueta());
        return map;
    }
}
