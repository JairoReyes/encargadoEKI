package local.isaac.tt_2018_a031.PDO;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TrolebusResponse {

    @SerializedName("list")
    private List<TrolebusRegistro> listaTrolebus;

    public TrolebusResponse(){
    }

    public TrolebusResponse(List<TrolebusRegistro> listaTrolebus){
        this.listaTrolebus = listaTrolebus;
    }


    public List<TrolebusRegistro> getListaTrolebus() {
        return listaTrolebus;
    }

    public void setListaTrolebus(List<TrolebusRegistro> listaTrolebus) {
        this.listaTrolebus = listaTrolebus;
    }
}