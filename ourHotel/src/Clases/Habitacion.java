package Clases;
import Enum.Estado_Habitacion;
import Enum.Tipo_Cama;
import Enum.Tipo_Habitacion;

import java.util.ArrayList;

public class Habitacion {
    protected int numHabitacion;
    protected double valorPorNoche;
    protected int cantPersonas;
    protected Tipo_Habitacion tipoHabitacion;
    protected Tipo_Cama tipoCama;
    protected Estado_Habitacion estadoHabitacion;
    protected ArrayList<Servicio> servicios;
    //Constructores
    public Habitacion (){

    }
    public Habitacion(int numHabitacion, double valorPorNoche, int cantPersonas, Tipo_Habitacion tipoHabitacion,
                      Tipo_Cama tipoCama, Estado_Habitacion estadoHabitacion) {
        this.numHabitacion = numHabitacion;
        this.valorPorNoche = valorPorNoche;
        this.cantPersonas = cantPersonas;
        this.tipoHabitacion = tipoHabitacion;
        this.tipoCama = tipoCama;
        this.estadoHabitacion = estadoHabitacion;
        this.servicios = new ArrayList<>();
    }

    public int getNumHabitacion() {
        return numHabitacion;
    }

    public double getValorPorNoche() {
        return valorPorNoche;
    }

    public int getCantPersonas() {
        return cantPersonas;
    }

    public Tipo_Habitacion getTipoHabitacion() {
        return tipoHabitacion;
    }

    public Tipo_Cama getTipoCama() {
        return tipoCama;
    }

    public Estado_Habitacion getEstadoHabitacion() {
        return estadoHabitacion;
    }

    public ArrayList<Servicio> getServicios() {
        return servicios;
    }

    public void setNumHabitacion(int numHabitacion) {
        this.numHabitacion = numHabitacion;
    }

    public void setValorPorNoche(double valorPorNoche) {
        this.valorPorNoche = valorPorNoche;
    }

    public void setCantPersonas(int cantPersonas) {
        this.cantPersonas = cantPersonas;
    }

    public void setTipoHabitacion(Tipo_Habitacion tipoHabitacion) {
        this.tipoHabitacion = tipoHabitacion;
    }

    public void setTipoCama(Tipo_Cama tipoCama) {
        this.tipoCama = tipoCama;
    }

    public void setEstadoHabitacion(Estado_Habitacion estadoHabitacion) {
        this.estadoHabitacion = estadoHabitacion;
    }

    public void setServicios(ArrayList<Servicio> servicios) {
        this.servicios = servicios;
    }

    public void mostrarHabitacion (){

        Menu.dibujarTerminacion();
        Menu.encabezadoMenu("Habitacion");
        Menu.centradoOpciones("Numero Habitacion: " + this.numHabitacion);
        Menu.centradoOpciones("Valor por Noche: $ " + this.valorPorNoche);
        Menu.centradoOpciones("Cantidad de personas: " + this.cantPersonas);
        Menu.centradoOpciones("Tipo de Habitacion: " + this.tipoHabitacion);
        Menu.centradoOpciones("Tipo de Cama: " + this.tipoCama);
        Menu.centradoOpciones("Estado de la Habitacion: " + this.estadoHabitacion);
        Menu.dibujarTerminacion();
    }
}
