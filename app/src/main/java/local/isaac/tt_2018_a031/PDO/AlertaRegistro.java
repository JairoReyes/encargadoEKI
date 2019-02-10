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

    @SerializedName("estado")
    private String estado;

    @SerializedName("id_alerta")
    private String id_alerta;

    public AlertaRegistro(){
    }

    public AlertaRegistro(String tipo_alerta, String latitud, String longitud, String fecha, String estado, String id_alerta){
        this.tipo_alerta = tipo_alerta;
        this.latitud = latitud;
        this.longitud = longitud;
        this.fecha = fecha;
        this.estado = estado;
        this.id_alerta = id_alerta;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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
