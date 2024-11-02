package Clases;
import Enum.Tipo_Usuario;

import java.util.ArrayList;

public class Pasajero extends Persona{

    protected Tipo_Usuario rol;
    protected ArrayList<Integer> reservas;

    //Constructores
    public Pasajero (){
        this.reservas = new ArrayList<>();
    }
    public Pasajero(Tipo_Usuario roll) {
        this.rol = roll;
    }

    public Tipo_Usuario getRol() {
        return rol;
    }

    public void setRol(Tipo_Usuario rol) {
        this.rol = rol;
    }


    //Funciones:

    //Realizar una reserva
    //Actividades extra
    //Servicio a la habitacion
    //Informacion de periodos, actual y anteriores (listado historial)
    //Habitacion en uso, reserva proxima(si tiene) y anteriores. (listado con historial)
}
