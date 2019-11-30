package com.example.wastecontrol.util;

import androidx.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

public class ObjetoParametros {
    private Map<String, Object> objectMap;

    public ObjetoParametros(){
        objectMap = new HashMap<>();
    }

    public <T extends Object> T get(String key, Class<T> typeValue){
        return typeValue.cast(objectMap.get(key));
    }

    public Object set(String key, Object value){
        if(objectMap.containsKey(key)){
            return objectMap.replace(key,value);
        } else {
            return objectMap.put(key,value);
        }
    }

    @NonNull
    @Override
    public String toString() {
        return objectMap.toString();
    }
}
