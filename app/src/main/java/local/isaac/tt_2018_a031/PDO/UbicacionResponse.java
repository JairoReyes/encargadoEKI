package local.isaac.tt_2018_a031.PDO;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UbicacionResponse {
    @SerializedName("list")
    private List<Unidad> listaUnidades;

    UbicacionResponse(){}

    public UbicacionResponse(List<Unidad> listaUnidades) {
        this.listaUnidades = listaUnidades;
    }

    public List<Unidad> getListaUnidades() {
        return listaUnidades;
    }

    public void setListaUnidades(List<Unidad> listaUnidades) {
        this.listaUnidades = listaUnidades;
    }
}
