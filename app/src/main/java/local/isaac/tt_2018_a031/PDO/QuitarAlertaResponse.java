package local.isaac.tt_2018_a031.PDO;

import com.google.gson.annotations.SerializedName;

public class QuitarAlertaResponse {
    @SerializedName("response")
    private Boolean response;

    public QuitarAlertaResponse(){
    }

    public QuitarAlertaResponse(Boolean response){
        this.response = response;
    }

    public Boolean getResponse() {
        return response;
    }

    public void setResponse(Boolean response) {
        this.response = response;
    }
}
