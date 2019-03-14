package local.isaac.tt_2018_a031.PDO;

import com.google.gson.annotations.SerializedName;

public class ZonaRoja {

    @SerializedName("ubicacion_latitud")
    private String latitud;

    @SerializedName("ubicacion_longitud")
    private String longitud;

    public ZonaRoja() {
    }

    public ZonaRoja(String latitud, String longitud) {
        this.latitud = latitud;
        this.longitud = longitud;
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
}
