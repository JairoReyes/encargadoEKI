package local.isaac.tt_2018_a031.PDO;

import com.google.gson.annotations.SerializedName;

public class RegistroTrolebusRequest {

    @SerializedName("id_trolebus")
    private String idtrolebus;

    @SerializedName("inicio")
    private String inicio;

    @SerializedName("fin")
    private String fin;

    public RegistroTrolebusRequest(){
    }

    public RegistroTrolebusRequest(String idtrolebus, String inicio, String fin){
        this.idtrolebus = idtrolebus;
        this.inicio = inicio;
        this.fin = fin;
    }

    public String getIdtrolebus() {
        return idtrolebus;
    }

    public void setIdtrolebus(String idtrolebus) {
        this.idtrolebus = idtrolebus;
    }

    public String getInicio() {
        return inicio;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    public String getFin() {
        return fin;
    }

    public void setFin(String fin) {
        this.fin = fin;
    }
}
