package Clases;

public class Reserva {

    protected Habitacion habitacion;
    protected Pasajero pasajero;
    protected Empleado empleado;       //quien hace la reserva o no (ver)

    //Constructor
    public Reserva (){

    }
    public Reserva(Habitacion habitacion, Pasajero pasajero, Empleado empleado) {
        this.habitacion = habitacion;
        this.pasajero = pasajero;
        this.empleado = empleado;
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

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }

    public void setPasajero(Pasajero pasajero) {
        this.pasajero = pasajero;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }


    //Funciones
    //Reservar (abm)
}
