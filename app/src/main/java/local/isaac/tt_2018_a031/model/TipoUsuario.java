package local.isaac.tt_2018_a031.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.Entity;

@Entity(tableName = "TIPO_USUARIO")
public class TipoUsuario {

    @PrimaryKey
    @ColumnInfo(name = "id_tipo_usuario")
    private Integer idTipoUsuario;

    @ColumnInfo(name = "nombre")
    private String nombre;

    public TipoUsuario(Integer idTipoUsuario, String nombre) {
        this.idTipoUsuario = idTipoUsuario;
        this.nombre = nombre;
    }

    public Integer getIdTipoUsuario() {
        return idTipoUsuario;
    }

    public void setIdTipoUsuario(Integer idTipoUsuario) {
        this.idTipoUsuario = idTipoUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
