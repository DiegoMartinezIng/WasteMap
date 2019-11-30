package com.example.wastecontrol.persistencia.model;

import java.util.HashMap;
import java.util.Map;

public class PuntoRecoleccionModel extends Model {

    public static String NODE_NAME = "punto_recoleccion";

    public static class Key {
        public static final String ID = Model.Key.ID;
        public static final String NOMBRE_PUNTO_RECOLECCION = "nombrePuntoRecoleccion";
        public static final String DIRECCION = "direccion";
        public static final String CIUDAD = "ciudad";
        public static final String DEPARTAMENTO = "departamento";
        public static final String PAIS = "pais";
        public static final String CATEGORIA_RESIDUO = "categoriaResiduo";
        public static final String TIPO_RESIDUO = "tipoResiduo";
        public static final String NOMBRE_RESIDUO = "nombreResiduo";
        public static final String UBICACION = "ubicacion";
        public static final String HORARIO = "horario";
        public static final String NOMBRE_PROGRAMA = "nombrePrograma";
        public static final String PERSONA_CONTACTO = "personaContacto";
        public static final String EMAIL = "email";
        public static final String LATITUD = "latitud";
        public static final String LONGITUD = "longitud";
    }

    private String nombrePuntoRecoleccion;
    private String direccion;
    private String ciudad;
    private String departamento;
    private String pais;
    private String categoriaResiduo;
    private String tipoResiduo;
    private String nombreResiduo;
    private String ubicacion;
    private String horario;
    private String nombrePrograma;
    private String personaContacto;
    private String email;
    private  String latitud;

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    private  String longitud;

    public String getNombrePuntoRecoleccion() {
        return nombrePuntoRecoleccion;
    }

    public void setNombrePuntoRecoleccion(String nombrePuntoRecoleccion) {
        this.nombrePuntoRecoleccion = nombrePuntoRecoleccion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCategoriaResiduo() {
        return categoriaResiduo;
    }

    public void setCategoriaResiduo(String categoriaResiduo) {
        this.categoriaResiduo = categoriaResiduo;
    }

    public String getTipoResiduo() {
        return tipoResiduo;
    }

    public void setTipoResiduo(String tipoResiduo) {
        this.tipoResiduo = tipoResiduo;
    }

    public String getNombreResiduo() {
        return nombreResiduo;
    }

    public void setNombreResiduo(String nombreResiduo) {
        this.nombreResiduo = nombreResiduo;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getNombrePrograma() {
        return nombrePrograma;
    }

    public void setNombrePrograma(String nombrePrograma) {
        this.nombrePrograma = nombrePrograma;
    }

    public String getPersonaContacto() {
        return personaContacto;
    }

    public void setPersonaContacto(String personaContacto) {
        this.personaContacto = personaContacto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String NODE_NAME() {
        return NODE_NAME;
    }

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put(Key.ID,getId());
        map.put(Key.NOMBRE_PUNTO_RECOLECCION,getNombrePuntoRecoleccion());
        map.put(Key.DIRECCION,getDireccion());
        map.put(Key.CIUDAD,getCiudad());
        map.put(Key.DEPARTAMENTO,getDepartamento());
        map.put(Key.PAIS,getPais());
        map.put(Key.CATEGORIA_RESIDUO,getCategoriaResiduo());
        map.put(Key.TIPO_RESIDUO,getTipoResiduo());
        map.put(Key.NOMBRE_RESIDUO,getNombreResiduo());
        map.put(Key.UBICACION,getUbicacion());
        map.put(Key.HORARIO,getHorario());
        map.put(Key.NOMBRE_PROGRAMA,getNombrePrograma());
        map.put(Key.PERSONA_CONTACTO,getPersonaContacto());
        map.put(Key.EMAIL,getEmail());
        map.put(Key.LATITUD,getLatitud());
        map.put(Key.LONGITUD,getLongitud());
        return map;
    }
}
