package local.isaac.tt_2018_a031.PDO;

import com.google.gson.annotations.SerializedName;

public class AlertaPDO {

    @SerializedName("alertasResponse")
    private AlertaResponse alertaResponse;


    public void setAlertaResponse(AlertaResponse alertaResponse) {
        this.alertaResponse = alertaResponse;
    }

    public AlertaResponse getAlertaResponse() {
        return alertaResponse;
    }
}
