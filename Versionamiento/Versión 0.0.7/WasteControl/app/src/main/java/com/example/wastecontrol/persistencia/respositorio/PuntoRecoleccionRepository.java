package com.example.wastecontrol.persistencia.respositorio;

import com.example.wastecontrol.persistencia.model.PuntoRecoleccionModel;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.Query;

public class PuntoRecoleccionRepository extends Repository<PuntoRecoleccionModel> {
    @Override
    protected Query queryFilter(PuntoRecoleccionModel filtro, CollectionReference collection) {
        Query query = collection;
        query = (filtro.getId() != null)? query.whereEqualTo(PuntoRecoleccionModel.Key.ID, filtro.getId()): query;
        query = (filtro.getNombrePuntoRecoleccion() != null)? query.whereEqualTo(PuntoRecoleccionModel.Key.NOMBRE_PUNTO_RECOLECCION, filtro.getNombrePuntoRecoleccion()): query;
        query = (filtro.getDireccion() != null)? query.whereEqualTo(PuntoRecoleccionModel.Key.DIRECCION, filtro.getDireccion()): query;
        query = (filtro.getCiudad() != null)? query.whereEqualTo(PuntoRecoleccionModel.Key.CIUDAD, filtro.getCiudad()): query;
        query = (filtro.getDepartamento() != null)? query.whereEqualTo(PuntoRecoleccionModel.Key.DEPARTAMENTO, filtro.getDepartamento()): query;
        query = (filtro.getPais() != null)? query.whereEqualTo(PuntoRecoleccionModel.Key.PAIS, filtro.getPais()): query;
        query = (filtro.getCategoriaResiduo() != null)? query.whereEqualTo(PuntoRecoleccionModel.Key.CATEGORIA_RESIDUO, filtro.getCategoriaResiduo()): query;
        query = (filtro.getTipoResiduo() != null)? query.whereEqualTo(PuntoRecoleccionModel.Key.TIPO_RESIDUO, filtro.getTipoResiduo()): query;
        query = (filtro.getNombreResiduo() != null)? query.whereEqualTo(PuntoRecoleccionModel.Key.NOMBRE_RESIDUO, filtro.getNombreResiduo()): query;
        query = (filtro.getUbicacion() != null)? query.whereEqualTo(PuntoRecoleccionModel.Key.UBICACION, filtro.getUbicacion()): query;
        query = (filtro.getHorario() != null)? query.whereEqualTo(PuntoRecoleccionModel.Key.HORARIO, filtro.getHorario()): query;
        query = (filtro.getNombrePrograma() != null)? query.whereEqualTo(PuntoRecoleccionModel.Key.NOMBRE_PROGRAMA, filtro.getNombrePrograma()): query;
        query = (filtro.getPersonaContacto() != null)? query.whereEqualTo(PuntoRecoleccionModel.Key.PERSONA_CONTACTO, filtro.getPersonaContacto()): query;
        query = (filtro.getEmail() != null)? query.whereEqualTo(PuntoRecoleccionModel.Key.EMAIL, filtro.getEmail()): query;
        query = (filtro.getLatitud() != null)? query.whereEqualTo(PuntoRecoleccionModel.Key.LATITUD, filtro.getLatitud()): query;
        query = (filtro.getLongitud() != null)? query.whereEqualTo(PuntoRecoleccionModel.Key.LONGITUD, filtro.getLongitud()): query;
        return query;
    }

    @Override
    protected Class getClassModel() {
        return PuntoRecoleccionModel.class;
    }

    public static Repository getInstance(){
        if(intance == null){
            intance = new PuntoRecoleccionRepository();
        }
        return intance;
    }
}
