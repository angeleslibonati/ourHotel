package Clases;
import Enum.Tipo_Usuario;

public class Pasajero extends Persona{

    protected Tipo_Usuario roll;

    //Constructores
    public Pasajero (){

    }
    public Pasajero(Tipo_Usuario roll) {
        this.roll = roll;
    }

    public Tipo_Usuario getRoll() {
        return roll;
    }

    public void setRoll(Tipo_Usuario roll) {
        this.roll = roll;
    }


    //Funciones:

    //Realizar una reserva
    //Actividades extra
    //Servicio a la habitacion
    //Informacion de periodos, actual y anteriores (listado historial)
    //Habitacion en uso, reserva proxima(si tiene) y anteriores. (listado con historial)
}
