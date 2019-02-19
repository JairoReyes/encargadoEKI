package local.isaac.tt_2018_a031.PDO;

import com.google.gson.annotations.SerializedName;

public class QuitarAlertaRequest {

    @SerializedName("estado")
    private String estado;

    @SerializedName("id_alerta")
    private String id_alerta;

    public QuitarAlertaRequest() {
    }

    public QuitarAlertaRequest(String estado, String id_alerta) {
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
}
