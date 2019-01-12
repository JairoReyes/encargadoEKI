package local.isaac.tt_2018_a031.PDO;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ConductorResponse {

    @SerializedName("lista")
    private List<ConductorRegistro> listaCondcutor;

    public ConductorResponse(){
    }

    public ConductorResponse(List<ConductorRegistro> listaCondcutor){
        this.listaCondcutor = listaCondcutor;
    }


    public List<ConductorRegistro> getListaCondcutor() {
        return listaCondcutor;
    }

    public void setListaCondcutor(List<ConductorRegistro> listaCondcutor) {
        this.listaCondcutor = listaCondcutor;
    }
}
