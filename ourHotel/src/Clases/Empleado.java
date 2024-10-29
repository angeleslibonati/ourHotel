package Clases;
import Enum.Tipo_Usuario;

public class Empleado extends Persona{

    protected Tipo_Usuario roll;
    protected double horasTrabajadas;

    //Constructores
    public Empleado(){

    }
    public Empleado(Tipo_Usuario roll, double horasTrabajadas) {
        this.roll = roll;
        this.horasTrabajadas = horasTrabajadas;
    }

    public Tipo_Usuario getRoll() {
        return roll;
    }

    public double getHorasTrabajadas() {
        return horasTrabajadas;
    }

    public void setRoll(Tipo_Usuario roll) {
        this.roll = roll;
    }

    public void setHorasTrabajadas(double horasTrabajadas) {
        this.horasTrabajadas = horasTrabajadas;
    }


    //Funciones:

    //Cargar pasajero (abm)
    //Hacer check in - check out
    //Ver estado de habitaciones
    //hacer reservas
    //ver historial de pasajeros
}
