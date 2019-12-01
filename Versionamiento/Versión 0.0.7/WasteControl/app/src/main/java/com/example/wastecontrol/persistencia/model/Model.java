package com.example.wastecontrol.persistencia.model;

import androidx.annotation.NonNull;

import com.google.gson.Gson;

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

    @NonNull
    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public abstract String NODE_NAME();
    public abstract Map<String, Object> toMap();

}
