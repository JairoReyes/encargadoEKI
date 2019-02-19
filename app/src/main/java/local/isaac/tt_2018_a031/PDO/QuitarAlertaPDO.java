package local.isaac.tt_2018_a031.PDO;

import com.google.gson.annotations.SerializedName;

public class QuitarAlertaPDO {

    @SerializedName("estadoResponse")
    private QuitarAlertaResponse quitarAlertaResponse;

    @SerializedName("error")
    public Error error;

    public void setQuitarAlertaResponse(QuitarAlertaResponse quitarAlertaResponse){
        this.quitarAlertaResponse = quitarAlertaResponse;
    }

    public QuitarAlertaResponse getQuitarAlertaResponse() {
        return quitarAlertaResponse;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }
}
