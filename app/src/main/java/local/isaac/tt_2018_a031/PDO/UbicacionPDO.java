package local.isaac.tt_2018_a031.PDO;

import com.google.gson.annotations.SerializedName;

public class UbicacionPDO {
    @SerializedName("ubicacionResponse")
    public UbicacionResponse ubicacionResponse;

    @SerializedName("error")
    public Error error;

    public UbicacionResponse getUbicacionResponse() {
        return ubicacionResponse;
    }

    public void setUbicacionResponse(UbicacionResponse ubicacionResponse) {
        this.ubicacionResponse = ubicacionResponse;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }
}
