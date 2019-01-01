package local.isaac.tt_2018_a031.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.Entity;

@Entity(tableName = "MODELO")
public class Modelo {

    @PrimaryKey
    @ColumnInfo(name = "id_modelo")
    private Integer idModelo;

    @ColumnInfo(name = "capacidad")
    private Integer capacidad;

    @ColumnInfo(name = "marca")
    private String marca;

    @ColumnInfo(name = "anio")
    private Integer anio;

    public Modelo(Integer idModelo, Integer capacidad, String marca, Integer anio) {
        this.idModelo = idModelo;
        this.capacidad = capacidad;
        this.marca = marca;
        this.anio = anio;
    }

    public Integer getIdModelo() {
        return idModelo;
    }

    public void setIdModelo(Integer idModelo) {
        this.idModelo = idModelo;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }
}
