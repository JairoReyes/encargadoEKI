package local.isaac.tt_2018_a031.PDO;

import com.google.gson.annotations.SerializedName;

public class QuitarAlertaResponse {
    @SerializedName("resoponse")
    private String response;

    public QuitarAlertaResponse(){
    }

    public QuitarAlertaResponse(String response){
        this.response = response;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
