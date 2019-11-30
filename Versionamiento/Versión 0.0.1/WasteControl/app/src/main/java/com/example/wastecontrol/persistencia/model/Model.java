package com.example.wastecontrol.persistencia.model;

import java.io.Serializable;
import java.util.Map;

public abstract class Model implements IModel, Serializable {
    private String id;
    public static class Key{
        public static final String ID = "id";
    }
    public Model(){}

    public Model(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public abstract String NODE_NAME();
    public abstract Map<String, Object> toMap();

}
