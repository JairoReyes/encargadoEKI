package local.isaac.tt_2018_a031.PDO;

import com.google.gson.annotations.SerializedName;

public class RegistroConductorPDO {

    @SerializedName("conductorResgistrosResponse")
    private RegistroConductorResponse registroConductorResponse;


    public void setRegistroConductorResponse(RegistroConductorResponse registroConductorResponse) {
        this.registroConductorResponse = registroConductorResponse;
    }

    public RegistroConductorResponse getRegistroConductorResponse() {
        return registroConductorResponse;
    }
}
