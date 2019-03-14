package local.isaac.tt_2018_a031.PDO;

import com.google.gson.annotations.SerializedName;

public class ZonasRojasPDO {

    @SerializedName("zonasRojasResponse")
    private ZonaRojaResponse zonaRojaResponse;

    private Error error;

    public ZonasRojasPDO() {
    }

    public ZonasRojasPDO(ZonaRojaResponse zonaRojaResponse) {
        this.zonaRojaResponse = zonaRojaResponse;
    }

    public ZonaRojaResponse getZonaRojaResponse() {
        return zonaRojaResponse;
    }

    public void setZonaRojaResponse(ZonaRojaResponse zonaRojaResponse) {
        this.zonaRojaResponse = zonaRojaResponse;
    }

    public void setError(Error error) {
        this.error = error;
    }

    public Error getError() {
        return error;
    }
}
