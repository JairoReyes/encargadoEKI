package local.isaac.tt_2018_a031.PDO;

import com.google.gson.annotations.SerializedName;

public class RegistroTrolebusRegistro {

    @SerializedName("nombre")
    private String nombre;

    @SerializedName("apellido")
    private String apellido;

    @SerializedName("inicio")
    private String fecha_inicio;

    @SerializedName("parada_inicio")
    private String parada_inicio;

    @SerializedName("fin")
    private String fecha_fin;

    @SerializedName("parada_fin")
    private String parada_fin;

    @SerializedName("tiempo")
    private String tiempo;

    @SerializedName("calificacion")
    private String calificacion;

    public RegistroTrolebusRegistro(){
    }

    public RegistroTrolebusRegistro(String nombre, String apellido, String fecha_inicio, String parada_inicio, String fecha_fin, String parada_fin, String tiempo, String calificacion){
        this.nombre = nombre;
        this.apellido= apellido;
        this.fecha_inicio = fecha_inicio;
        this.parada_inicio = parada_inicio;
        this.fecha_fin = fecha_fin;
        this.parada_fin = parada_fin;
        this.tiempo = tiempo;
        this.calificacion = calificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public String getParada_inicio() {
        return parada_inicio;
    }

    public void setParada_inicio(String parada_inicio) {
        this.parada_inicio = parada_inicio;
    }

    public String getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(String fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public String getParada_fin() {
        return parada_fin;
    }

    public void setParada_fin(String parada_fin) {
        this.parada_fin = parada_fin;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public String getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(String calificacion) {
        this.calificacion = calificacion;
    }
}
