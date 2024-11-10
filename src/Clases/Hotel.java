package Clases;

import java.util.ArrayList;

public class Hotel {



    ArrayList<Pasajero> pasajeros ;
    ArrayList <Empleado> empleados;
    ArrayList <Habitacion> habitaciones;

    //Constructor

    public Hotel() {
        this.pasajeros = new ArrayList<>();
        this.empleados = new ArrayList<>();
        this.habitaciones = new ArrayList<>();
    }

    public ArrayList<Pasajero> getPasajeros() {
        return pasajeros;
    }

    public void setPasajeros(ArrayList<Pasajero> pasajeros) {
        this.pasajeros = pasajeros;
    }

    public ArrayList<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(ArrayList<Empleado> empleados) {
        this.empleados = empleados;
    }

    public ArrayList<Habitacion> getHabitaciones() {
        return habitaciones;
    }

    public void setHabitacions(ArrayList<Habitacion> habitaciones) {
        this.habitaciones = habitaciones;
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
