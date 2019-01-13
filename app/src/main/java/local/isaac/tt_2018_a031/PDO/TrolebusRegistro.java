package local.isaac.tt_2018_a031.PDO;

import com.google.gson.annotations.SerializedName;

public class TrolebusRegistro {

    @SerializedName("id_trolebus")
    private String idtrolebus;

    @SerializedName("modelo")
    private String modelo;

    @SerializedName("capacidad")
    private String capacidad;

    @SerializedName("placa")
    private String placa;

    public TrolebusRegistro(){
    }

    public TrolebusRegistro(String idtrolebus, String modelo, String capacidad, String placa){
        this.idtrolebus = idtrolebus;
        this.modelo = modelo;
        this.capacidad = capacidad;
        this.placa = placa;
    }

    public String getIdtrolebus() {
        return idtrolebus;
    }

    public void setIdtrolebus(String idtrolebus) {
        this.idtrolebus = idtrolebus;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(String capacidad) {
        this.capacidad = capacidad;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }
}
