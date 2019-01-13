package local.isaac.tt_2018_a031.PDO;

import com.google.gson.annotations.SerializedName;

public class TrolebusPDO {

    @SerializedName("trolebusesResponse")
    private TrolebusResponse trolebusResponse;


    public void setTrolebusResponse(TrolebusResponse trolebusResponse) {
        this.trolebusResponse = trolebusResponse;
    }

    public TrolebusResponse getTrolebusResponse() {
        return trolebusResponse;
    }

}
