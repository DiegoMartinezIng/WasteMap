package waste.map;

public class RegistroPqr {
    String nombre;
    String correo;
    String telefono;
    String tipoReq;
    String observaciones;
    String registroId;

    public RegistroPqr() {
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
        this.tipoReq = tipoReq;
        this.observaciones = observaciones;
        this.registroId = registroId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTipoReq() {
        return tipoReq;
    }

    public void setTipoReq(String tipoReq) {
        this.tipoReq = tipoReq;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getRegistroId() {
        return registroId;
    }

    public void setRegistroId(String registroId) {
        this.registroId = registroId;
    }
}
