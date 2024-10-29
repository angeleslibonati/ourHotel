package Clases;

import java.util.ArrayList;

public class Hotel {

    ArrayList<Pasajero> pasajeros ;
    ArrayList <Empleado> empleados;
    ArrayList <Habitacion> habitacions;

    //Constructor

    public Hotel() {
        this.pasajeros = new ArrayList<>();
        this.empleados = new ArrayList<>();
        this.habitacions = new ArrayList<>();
    }
}
