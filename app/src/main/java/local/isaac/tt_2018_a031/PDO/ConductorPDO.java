package local.isaac.tt_2018_a031.PDO;

import com.google.gson.annotations.SerializedName;

public class ConductorPDO {

    @SerializedName("conductoresResponse")
    private ConductorResponse conductorResponse;


    public void setConductorResponse(ConductorResponse conductorResponse) {
        this.conductorResponse = conductorResponse;
    }

    public ConductorResponse getConductorResponse() {
        return conductorResponse;
    }

}
