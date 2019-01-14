package local.isaac.tt_2018_a031.PDO;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RegistroConductorResponse {

    @SerializedName("list")
    private List<RegistroConductorRegistro> listaRegistroCondcutor;

    public RegistroConductorResponse(){
    }

    public RegistroConductorResponse(List<RegistroConductorRegistro> listaRegistroCondcutor){
        this.listaRegistroCondcutor = listaRegistroCondcutor;
    }


    public List<RegistroConductorRegistro> getListaRegistroCondcutor() {
        return listaRegistroCondcutor;
    }

    public void setListaRegistroCondcutor(List<RegistroConductorRegistro> listaRegistroCondcutor) {
        this.listaRegistroCondcutor = listaRegistroCondcutor;
    }
}
