package local.isaac.tt_2018_a031.PDO;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RegistroTrolebusResponse {

    @SerializedName("list")
    private List<RegistroTrolebusRegistro> listaRegistroTrolebus;

    public RegistroTrolebusResponse(){
    }

    public RegistroTrolebusResponse(List<RegistroTrolebusRegistro> listaRegistroTrolebus){
        this.listaRegistroTrolebus = listaRegistroTrolebus;
    }


    public List<RegistroTrolebusRegistro> getListaRegistroTrolebus() {
        return listaRegistroTrolebus;
    }

    public void setListaRegistroTrolebus(List<RegistroTrolebusRegistro> listaRegistroTrolebus) {
        this.listaRegistroTrolebus = listaRegistroTrolebus;
    }
}
