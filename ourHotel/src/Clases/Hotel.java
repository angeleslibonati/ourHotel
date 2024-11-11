package Clases;

import java.util.ArrayList;
import java.util.List;

public class Hotel {



    List <Pasajero> pasajeros ;
    List <Empleado> empleados;
    List <Habitacion> habitaciones;

    //Constructor

    public Hotel() {
        this.pasajeros = new ArrayList<>();
        this.empleados = new ArrayList<>();
        this.habitaciones = new ArrayList<>();
    }

    public List<Pasajero> getPasajeros() {
        return pasajeros;
    }

    public void setPasajeros(List<Pasajero> pasajeros) {
        this.pasajeros = pasajeros;
    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<Empleado> empleados) {
        this.empleados = empleados;
    }

    public List<Habitacion> getHabitaciones() {
        return habitaciones;
    }

    public void setHabitaciones(List<Habitacion> habitaciones) {this.habitaciones = habitaciones;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "pasajeros=" + pasajeros +
                ", empleados=" + empleados +
                ", habitaciones=" + habitaciones +
                '}';
    }
}
