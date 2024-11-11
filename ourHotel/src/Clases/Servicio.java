package Clases;
import Enum.Servicio_Habitacion;

public class Servicio {

    protected Servicio_Habitacion nombre;
    protected double costo;

    public Servicio(){

    }
    public Servicio(Servicio_Habitacion nombreServicio, double costo) {
        this.nombre = nombre;
        this.costo = costo;
    }

    public Servicio_Habitacion getNombre() {
        return nombre;
    }

    public double getCosto() {
        return costo;
    }

    public void setNombre(Servicio_Habitacion nombre) {
        this.nombre = nombre;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }


}