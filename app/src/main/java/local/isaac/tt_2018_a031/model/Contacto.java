package local.isaac.tt_2018_a031.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(tableName = "CONTACTO",
        foreignKeys = @ForeignKey(entity = Usuario.class,
                parentColumns = "id_usuario",
                childColumns = "id_usuario",
                onDelete = CASCADE))
public class Contacto {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_contacto")
    private Integer idContacto;

    @ColumnInfo(name = "nombre")
    private String nombre;

    @ColumnInfo(name = "telefono")
    private String telefono;

    @ColumnInfo(name = "id_usuario")
    private Integer idUsuario;

    public Contacto(){

    }

    public Contacto (String nombre, String telefono, Integer idUsuario) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.idUsuario = idUsuario;
    }

    public Integer getIdContacto() {
        return idContacto;
    }

    public void setIdContacto(Integer idContacto) {
        this.idContacto = idContacto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }
}
