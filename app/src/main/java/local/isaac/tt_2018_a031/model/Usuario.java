package local.isaac.tt_2018_a031.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import static android.arch.persistence.room.ForeignKey.SET_NULL;

@Entity(tableName = "USUARIO",
        foreignKeys = @ForeignKey(entity = TipoUsuario.class,
                    parentColumns = "id_tipo_usuario",
                    childColumns = "id_tipo_usuario",
                    onDelete = SET_NULL))
public class Usuario {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_usuario")
    private Integer idUsuario;

    @ColumnInfo(name = "nombre")
    private String nombre;

    @ColumnInfo(name = "apellido")
    private String apellido;

    @ColumnInfo(name = "edad")
    private Integer edad;

    @ColumnInfo(name = "correo")
    private String correo;

    @ColumnInfo(name = "contrasenia")
    private String contrasenia;

    @ColumnInfo(name = "foto")
    private String foto;

    @ColumnInfo(name = "id_tipo_usuario")
    private Integer idTipoUsuario;

    public Usuario(String nombre, String apellido, Integer edad, String correo, String contrasenia, String foto, Integer idTipoUsuario) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.correo = correo;
        this.contrasenia = contrasenia;
        this.foto = foto;
        this.idTipoUsuario = idTipoUsuario;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
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

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Integer getIdTipoUsuario() {
        return idTipoUsuario;
    }

    public void setIdTipoUsuario(Integer idTipoUsuario) {
        this.idTipoUsuario = idTipoUsuario;
    }
}
