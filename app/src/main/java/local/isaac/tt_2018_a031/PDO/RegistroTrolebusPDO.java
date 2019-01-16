package local.isaac.tt_2018_a031.PDO;

import com.google.gson.annotations.SerializedName;

public class RegistroTrolebusPDO {
    @SerializedName("trolebusRegistrosResponse")
    private RegistroTrolebusResponse registroTrolebusResponse;


    public void setRegistroTrolebusResponse(RegistroTrolebusResponse registroTrolebusResponse) {
        this.registroTrolebusResponse = registroTrolebusResponse;
    }

    public RegistroTrolebusResponse getRegistroTrolebusResponse() {
        return registroTrolebusResponse;
    }
}
