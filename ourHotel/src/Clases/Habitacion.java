package Clases;
import Enum.Estado_Habitacion;
import Enum.Tipo_Cama;
import Enum.Tipo_Habitacion;

public class Habitacion {
    protected int numHabitacion;
    protected double valorPorNoche;
    protected int cantPersonas;
    protected Tipo_Habitacion tipoHabitacion;
    protected Tipo_Cama tipoCama;
    protected Estado_Habitacion estadoHabitacion;

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
}
