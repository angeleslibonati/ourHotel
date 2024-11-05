package Clases;

import java.util.ArrayList;
import java.util.List;
import Enum.Estado_Habitacion;
import Excepciones.NumeroInvalidoException;


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


}
