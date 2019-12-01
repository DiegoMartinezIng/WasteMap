package com.example.wastecontrol.persistencia.respositorio;

import com.example.wastecontrol.persistencia.model.NotaModel;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.Query;

public class NotaRepository extends Repository<NotaModel> {
    @Override
    protected Query queryFilter(NotaModel filtro, CollectionReference collection) {
        Query query = collection;
        query = (filtro.getId() != null)? query.whereEqualTo(NotaModel.Key.ID, filtro.getId()): query;
        query = (filtro.getTitulo() != null)? query.whereEqualTo(NotaModel.Key.TITULO, filtro.getTitulo()): query;
        query = (filtro.getContenido() != null)? query.whereEqualTo(NotaModel.Key.CONTENIDO, filtro.getContenido()): query;
        query = (filtro.getContenido() != null)? query.whereEqualTo(NotaModel.Key.FECHA, filtro.getFecha()): query;
        return query;
    }

    @Override
    protected Class getClassModel() {
        return NotaModel.class;
    }

    public static Repository getInstance(){
        if(intance == null){
            intance = new NotaRepository();
        }
        return intance;
    }
}
