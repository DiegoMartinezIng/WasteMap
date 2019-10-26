public class PuntoAcopio {
    private String nombre;
    private String dirreccion;
    private String categoria;
    private String tipo;
    private String ubicacion;
    private String horario;
    private String nombrePrograma;
    private String nombrePersona;
    private String corrreo;

    public PuntoAcopio(String nombre, String dirreccion, String categoria, String tipo, String ubicacion, String horario, String nombrePrograma, String nombrePersona, String corrreo) {
        this.nombre = nombre;
        this.dirreccion = dirreccion;
        this.categoria = categoria;
        this.tipo = tipo;
        this.ubicacion = ubicacion;
        this.horario = horario;
        this.nombrePrograma = nombrePrograma;
        this.nombrePersona = nombrePersona;
        this.corrreo = corrreo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDirreccion() {
        return dirreccion;
    }

    public void setDirreccion(String dirreccion) {
        this.dirreccion = dirreccion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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

    public String getNombrePersona() {
        return nombrePersona;
    }

    public void setNombrePersona(String nombrePersona) {
        this.nombrePersona = nombrePersona;
    }

    public String getCorrreo() {
        return corrreo;
    }

    public void setCorrreo(String corrreo) {
        this.corrreo = corrreo;
    }
}
