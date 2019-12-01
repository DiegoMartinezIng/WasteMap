package com.example.wastecontrol.persistencia.respositorio;

import com.example.wastecontrol.persistencia.model.UbicacionModel;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.Query;

public class UbicacionRepository extends Repository<UbicacionModel> {
    @Override
    protected Query queryFilter(UbicacionModel filtro, CollectionReference collection) {
        Query query = collection;
        query = (filtro.getId() != null)? query.whereEqualTo(UbicacionModel.Key.ID, filtro.getId()): query;
        query = (filtro.getLatitud() != null)? query.whereEqualTo(UbicacionModel.Key.LATITUD, filtro.getLatitud()): query;
        query = (filtro.getLongitud() != null)? query.whereEqualTo(UbicacionModel.Key.LONGITUD, filtro.getLongitud()): query;
        query = (filtro.getDireccion() != null)? query.whereEqualTo(UbicacionModel.Key.DIRECCION, filtro.getDireccion()): query;
        return query;
    }

    @Override
    protected Class getClassModel() {
        return UbicacionModel.class;
    }

    public static Repository getInstance(){
        if(intance == null){
            intance = new UbicacionRepository();
        }
        return intance;
    }
}
