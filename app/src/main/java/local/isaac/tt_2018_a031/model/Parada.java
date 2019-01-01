package local.isaac.tt_2018_a031.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.Entity;

@Entity(tableName = "PARADA")
public class Parada {

    @PrimaryKey
    @ColumnInfo(name = "id_parada")
    private Integer idParada;

    @ColumnInfo(name = "nombre")
    private String nombre;

    @ColumnInfo(name = "ubicacion_latitud")
    private double ubicacionLatitud;

    @ColumnInfo(name = "ubicacion_longitud")
    private double ubicacionLongitud;

    @Ignore
    private double distanciaA;

    public Parada(){
    }

    public Parada(Integer idParada, String nombre, double ubicacionLatitud, double ubicacionLongitud, double distaciaA) {
        this.idParada = idParada;
        this.nombre = nombre;
        this.ubicacionLatitud = ubicacionLatitud;
        this.ubicacionLongitud = ubicacionLongitud;
        this.distanciaA = distaciaA;
    }

    public Integer getIdParada() {
        return idParada;
    }

    public void setIdParada(Integer idParada) {
        this.idParada = idParada;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public double getDistanciaA() {
        return distanciaA;
    }

    public void setDistanciaA(double distanciaA){
        this.distanciaA = distanciaA;
    }
}
