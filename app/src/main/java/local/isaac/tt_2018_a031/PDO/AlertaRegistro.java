package local.isaac.tt_2018_a031.PDO;

import com.google.gson.annotations.SerializedName;

public class AlertaRegistro {

    @SerializedName("tipo_alerta")
    private String tipo_alerta;

    @SerializedName("ubicacion_latitud")
    private String latitud;

    @SerializedName("ubicacion_longitud")
    private String longitud;

    @SerializedName("fecha")
    private String fecha;

    @SerializedName("id_alerta")
    private String id_alerta;

    @SerializedName("id_trolebus")
    private String id_trolebus;

    @SerializedName("nombre")
    private String nombre;

    @SerializedName("placa")
    private String placa;

    public AlertaRegistro(){
    }

    public AlertaRegistro(String tipo_alerta, String latitud, String longitud, String fecha, String id_alerta, String id_trolebus,String nombre, String placa){
        this.tipo_alerta = tipo_alerta;
        this.latitud = latitud;
        this.longitud = longitud;
        this.fecha = fecha;
        this.id_alerta = id_alerta;
        this.id_trolebus = id_trolebus;
        this.nombre = nombre;
        this.placa = placa;
    }

    public String getId_alerta() {
        return id_alerta;
    }

    public void setId_alerta(String id_alerta) {
        this.id_alerta = id_alerta;
    }

    public String getTipo_alerta() {
        return tipo_alerta;
    }

    public void setTipo_alerta(String tipo_alerta) {
        this.tipo_alerta = tipo_alerta;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public String getId_trolebus() {
        return id_trolebus;
    }

    public void setId_trolebus(String id_trolebus) {
        this.id_trolebus = id_trolebus;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
