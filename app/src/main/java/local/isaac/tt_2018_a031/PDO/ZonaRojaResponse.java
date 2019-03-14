package local.isaac.tt_2018_a031.PDO;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ZonaRojaResponse {

    @SerializedName("list")
    private List<ZonaRoja> zonaRojaList;

    public ZonaRojaResponse() {
    }

    public ZonaRojaResponse(List<ZonaRoja> zonaRojaList) {
        this.zonaRojaList = zonaRojaList;
    }

    public List<ZonaRoja> getZonaRojaList() {
        return zonaRojaList;
    }

    public void setZonaRojaList(List<ZonaRoja> zonaRojaList) {
        this.zonaRojaList = zonaRojaList;
    }
}
