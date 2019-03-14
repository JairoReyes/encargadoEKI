package local.isaac.tt_2018_a031.PDO;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Unidad implements Cloneable {
    @SerializedName("id_trolebus")
    private int id_trolebus;

    @SerializedName("id_parada")
    private int id_parada;

    @SerializedName("ubicacion_latitud")
    private double latitud;

    @SerializedName("ubicacion_longitud")
    private double longitud;

    @SerializedName("id_viaje")
    private int id_viaje;

    @SerializedName("num_pasajeros")
    private int pasajeros;

    @SerializedName("id_modelo")
    private int modelo;

    private double distancia;

    @SerializedName("nombre")
    private String nombre;

    @SerializedName("calificacion")
    private float calificacion;

    @SerializedName("capacidad")
    private int capacidad;

    @SerializedName("placa")
    private String placa;

    public Unidad(){}

    public Unidad(int id_trolebus, int id_parada, double latitud, double longitud, int id_viaje, int pasajeros, int modelo, String nombre, float calificacion, int capacidad, String placa) {
        this.id_trolebus = id_trolebus;
        this.id_parada = id_parada;
        this.latitud = latitud;
        this.longitud = longitud;
        this.id_viaje = id_viaje;
        this.pasajeros = pasajeros;
        this.modelo = modelo;
        this.nombre = nombre;
        this.distancia = 0;
        this.calificacion = calificacion;
        this.capacidad = capacidad;
        this.placa = placa;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Unidad clone() throws CloneNotSupportedException{
        Unidad clon = (Unidad) super.clone();
        return clon;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public float getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(float calificacion) {
        this.calificacion = calificacion;
    }

    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }

    public int getId_trolebus() {
        return id_trolebus;
    }

    public void setId_trolebus(int id_trolebus) {
        this.id_trolebus = id_trolebus;
    }

    public int getId_parada() {
        return id_parada;
    }

    public void setId_parada(int id_parada) {
        this.id_parada = id_parada;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public int getId_viaje() {
        return id_viaje;
    }

    public void setId_viaje(int id_viaje) {
        this.id_viaje = id_viaje;
    }

    public int getPasajeros() {
        return pasajeros;
    }

    public void setPasajeros(int pasajeros) {
        this.pasajeros = pasajeros;
    }

    public int getModelo() {
        return modelo;
    }

    public void setModelo(int modelo) {
        this.modelo = modelo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
