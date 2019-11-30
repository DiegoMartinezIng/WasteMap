package com.example.wastecontrol.persistencia.respositorio;

import com.example.wastecontrol.persistencia.model.Model;
import com.example.wastecontrol.persistencia.model.TipoReciclajeModel;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.Query;

public class TipoReciclajeRepository extends Repository<TipoReciclajeModel> {

    @Override
    protected Query queryFilter(TipoReciclajeModel filtro, CollectionReference collection) {
        Query query = collection;
        query = (filtro.getId() != null)? query.whereEqualTo(TipoReciclajeModel.Key.ID, filtro.getId()): query;
        query = (filtro.getCodigo() != null)? query.whereEqualTo(TipoReciclajeModel.Key.CODIGO, filtro.getCodigo()): query;
        query = (filtro.getEtiqueta() != null)? query.whereEqualTo(TipoReciclajeModel.Key.ETIQUETA, filtro.getEtiqueta()): query;
        return query;
    }

    @Override
    protected Class getClassModel() {
        return TipoReciclajeModel.class;
    }



    public static Repository getInstance(){
        if(intance == null){
            intance = new TipoReciclajeRepository();
        }
        return intance;
    }
}
