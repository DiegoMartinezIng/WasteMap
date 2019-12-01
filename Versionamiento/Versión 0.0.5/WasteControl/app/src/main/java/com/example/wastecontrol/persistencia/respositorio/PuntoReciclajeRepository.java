package com.example.wastecontrol.persistencia.respositorio;

import com.example.wastecontrol.persistencia.model.PuntoReciclajeModel;
import com.example.wastecontrol.persistencia.model.TipoReciclajeModel;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.Query;

public class PuntoReciclajeRepository extends Repository<PuntoReciclajeModel> {
    @Override
    protected Query queryFilter(PuntoReciclajeModel filtro, CollectionReference collection) {
        Query query = collection;
        query = (filtro.getId() != null)? query.whereEqualTo(PuntoReciclajeModel.Key.ID, filtro.getId()): query;
        query = (filtro.getIdTipoReciclaje() != null)? query.whereEqualTo(PuntoReciclajeModel.Key.ID_TIPO_RECICLAJE, filtro.getIdTipoReciclaje()): query;
        query = (filtro.getIdUbicacion() != null)? query.whereEqualTo(PuntoReciclajeModel.Key.ID_UBICACION, filtro.getIdUbicacion()): query;
        return query;
    }

    @Override
    protected Class getClassModel() {
        return PuntoReciclajeModel.class;
    }

    public static Repository getInstance(){
        if(intance == null){
            intance = new PuntoReciclajeRepository();
        }
        return intance;
    }
}
