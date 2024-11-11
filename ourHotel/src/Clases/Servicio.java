package Clases;
import Enum.Servicio_Habitacion;

public class Servicio {

    protected Servicio_Habitacion nombreServicio;
    protected double costo;

    public Servicio(){

    }
    public Servicio(Servicio_Habitacion nombreServicio, double costo) {
        this.nombreServicio = nombreServicio;
        this.costo = costo;
    }

    public Servicio_Habitacion getNombreServicio() {
        return nombreServicio;
    }

    public double getCosto() {
        return costo;
    }

    public void setNombreServicio(Servicio_Habitacion nombreServicio) {
        this.nombreServicio = nombreServicio;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }


    public void mostrarServicio (){

        Menu.dibujarTerminacion();
        Menu.encabezadoMenu("Servicio Extra");
        Menu.centradoOpciones("Servicio: " + this.nombreServicio);
        Menu.centradoOpciones("Costo: $ " + this.costo);
    }

}
