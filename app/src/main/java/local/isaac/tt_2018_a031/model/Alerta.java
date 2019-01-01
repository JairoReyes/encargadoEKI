package local.isaac.tt_2018_a031.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(tableName = "ALERTA",
        foreignKeys = @ForeignKey(entity = Usuario.class,
                    parentColumns = "id_usuario",
                    childColumns = "id_usuario",
                    onDelete = CASCADE))
public class Alerta {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_alerta")
    private Integer idAlerta;

    @ColumnInfo(name = "fecha")
    private String fecha;

    @ColumnInfo(name = "ubicacion_latitud")
    private double ubicacionLatitud;

    @ColumnInfo(name = "ubicacion_longitud")
    private double ubicacionLongitud;

    @ColumnInfo(name = "id_usuario")
    private double idUsuario;

    public Alerta(Integer idAlerta, String fecha, double ubicacionLatitud, double ubicacionLongitud, double idUsuario) {
        this.idAlerta = idAlerta;
        this.fecha = fecha;
        this.ubicacionLatitud = ubicacionLatitud;
        this.ubicacionLongitud = ubicacionLongitud;
        this.idUsuario = idUsuario;
    }

    public Alerta(){

    }

    public Integer getIdAlerta() {
        return idAlerta;
    }

    public void setIdAlerta(Integer idAlerta) {
        this.idAlerta = idAlerta;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getUbicacionLatitud() {
        return ubicacionLatitud;
    }

    public void setUbicacionLatitud(double ubicacionLatitud) {
        this.ubicacionLatitud = ubicacionLatitud;
    }

    public double getUbicacionLongitud() {
        return ubicacionLongitud;
    }

    public void setUbicacionLongitud(double ubicacionLongitud) {
        this.ubicacionLongitud = ubicacionLongitud;
    }

    public double getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(double idUsuario) {
        this.idUsuario = idUsuario;
    }
}
