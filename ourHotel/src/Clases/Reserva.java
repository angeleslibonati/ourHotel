package Clases;
import Enum.Estado_Reserva;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

    public Estado_Reserva getEstadoReserva() {
        return estadoReserva;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public Date getFechaInicio() {
        return fechaInicio;
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

    private String getFormattedDate(Date date, String format){
        return new SimpleDateFormat(format).format(date);
    }


    public void mostrarUnaReserva (){

        Menu.dibujarTerminacion();
        Menu.encabezadoMenu("Reserva");
        Menu.centradoOpciones("Id Reserva: " + this.getIdReserva());
        Menu.centradoOpciones("Num. Habitacion: " + this.habitacion.getNumHabitacion());
        Menu.centradoOpciones("DNI Pasajero: " + this.pasajero.getDni());
        Menu.centradoOpciones("Id Empleado: " + this.empleado.getId());
        Menu.centradoOpciones("Fecha Inicio: " + this.getFormattedDate(getFechaInicio(), "yyyy-MM-dd"));
        Menu.centradoOpciones("Fecha Fin: " + this.getFormattedDate(getFechaFin(), "yyyy-MM-dd"));
        Menu.centradoOpciones("Estado Reserva: " + this.getEstadoReserva());
        Menu.dibujarTerminacion();

    }


}
