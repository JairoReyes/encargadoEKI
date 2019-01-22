package local.isaac.tt_2018_a031.PDO;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AlertaResponse {

    @SerializedName("list")
    private List<AlertaRegistro> listaAlerta;
    

    public AlertaResponse(){
    }

    public AlertaResponse(List<AlertaRegistro> listaAlerta){
        this.listaAlerta = listaAlerta;
    }


    public List<AlertaRegistro> getListaAlerta() {
        return listaAlerta;
    }

    public void setListaAlerta(List<AlertaRegistro> listaAlerta) {
        this.listaAlerta = listaAlerta;
    }
}
