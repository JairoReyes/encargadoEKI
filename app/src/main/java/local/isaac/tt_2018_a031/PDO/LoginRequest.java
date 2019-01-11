package local.isaac.tt_2018_a031.PDO;

import com.google.gson.annotations.SerializedName;
public class LoginRequest {

    @SerializedName("correo")
    private String expediente;

    @SerializedName("contrasena")
    private String contrasena;

    public LoginRequest() {
    }

    public LoginRequest(String expediente, String contrasena) {
        this.expediente = expediente;
        this.contrasena = contrasena;
    }

    public String getExpediente() {
        return expediente;
    }

    public void setExpediente(String expediente) {
        this.expediente = expediente;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}
