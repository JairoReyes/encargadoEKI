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

    public AlertaRegistro(){
    }

    public AlertaRegistro(String tipo_alerta, String latitud, String longitud, String fecha){
        this.tipo_alerta = tipo_alerta;
        this.latitud = latitud;
        this.longitud = longitud;
        this.fecha = fecha;
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
