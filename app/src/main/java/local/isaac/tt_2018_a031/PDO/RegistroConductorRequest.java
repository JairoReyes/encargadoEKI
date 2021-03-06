package local.isaac.tt_2018_a031.PDO;

import com.google.gson.annotations.SerializedName;

public class RegistroConductorRequest {
    @SerializedName("id_usuario")
    private String idusuario;

    @SerializedName("inicio")
    private String inicio;

    @SerializedName("fin")
    private String fin;

    public RegistroConductorRequest(){
    }

    public RegistroConductorRequest(String idusuario, String inicio, String fin){
        this.idusuario = idusuario;
        this.inicio = inicio;
        this.fin = fin;
    }

    public String getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(String idusuario) {
        this.idusuario = idusuario;
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
