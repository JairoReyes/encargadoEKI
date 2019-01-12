package local.isaac.tt_2018_a031.PDO;

import com.google.gson.annotations.SerializedName;

public class ConductorPDO {

    @SerializedName("conductorResponse")
    public ConductorResponse conductorResponse;

    @SerializedName("error")
    public Error error;


    public void setConductorResponse(ConductorResponse conductorResponse) {
        this.conductorResponse = conductorResponse;
    }

    public void setError(Error error) {
        this.error = error;
    }

    public ConductorResponse getConductorResponse() {
        return conductorResponse;
    }

    public Error getError() {
        return error;
    }
}
