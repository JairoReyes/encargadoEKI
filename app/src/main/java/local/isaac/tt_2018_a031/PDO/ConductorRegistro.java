package local.isaac.tt_2018_a031.PDO;

import com.google.gson.annotations.SerializedName;

public class ConductorRegistro {

    @SerializedName("id_usuario")
    private String idUsuario;

    @SerializedName("foto")
    private String foto;

    @SerializedName("nombre")
    private String nombre;

    @SerializedName("apellido")
    private String apellido;

    @SerializedName("edad")
    private String edad;

    @SerializedName("correo")
    private String expediente;

    @SerializedName("calificacion")
    private String calificacion;

    public ConductorRegistro(){
    }

    public ConductorRegistro(String idUsuario,String foto, String nombre, String apellido, String edad, String expediente,String calificacion) {
        this.idUsuario = idUsuario;
        this.foto = foto;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.expediente = expediente;
        this.calificacion = calificacion;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getExpediente() {
        return expediente;
    }

    public void setExpediente(String expediente) {
        this.expediente = expediente;
    }

    public String getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(String calificacion) {
        this.calificacion = calificacion;
    }
}
