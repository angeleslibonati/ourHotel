package Clases;
import Enum.Estado_Reserva;
import org.json.JSONObject;
import java.util.Date;

public class Reserva {

    protected  int idReserva = ++Reserva.contador;
    private static int contador = 0;
    protected Habitacion habitacion; //traer num habitacion
    protected Pasajero pasajero; //traer dni
    protected Empleado empleado;       //traer id
    protected Date fechaInicio;
    protected Date fechaFin;
    protected Estado_Reserva estadoReserva;

    //Constructor
    public Reserva (){

    }

    public Reserva(Habitacion habitacion, Pasajero pasajero, Empleado empleado, Estado_Reserva estadoReserva) {
        this.habitacion = habitacion;
        this.pasajero = pasajero;
        this.empleado = empleado;
        this.fechaInicio = new Date();
        this.fechaFin = new Date();
        this.estadoReserva = estadoReserva;
    }

    public int getIdReserva() {
        return idReserva;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public Pasajero getPasajero() {
        return pasajero;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public void setEstadoReserva(Estado_Reserva estadoReserva) {
        this.estadoReserva = estadoReserva;
    }

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }

    public void setPasajero(Pasajero pasajero) {
        this.pasajero = pasajero;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    @Override
    public String toString() {
        return "Reserva:" +
                "\nidReserva=" + idReserva +
                "\nhabitacion=" + habitacion +
                "\npasajero=" + pasajero +
                "\nempleado=" + empleado +
                "\nfechaInicio=" + fechaInicio +
                "\nfechaFin=" + fechaFin +
                "\nestadoReserva=" + estadoReserva;
    }
}
