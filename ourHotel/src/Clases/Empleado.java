package Clases;
import Enum.Tipo_Usuario;
import Enum.Estado_Empleado;

public class Empleado extends Persona{

    private final int id = ++Empleado.contador;
    private static int contador = 0;
    protected Tipo_Usuario rol;
    protected double horasTrabajadas;
    protected Estado_Empleado estadoEmpleado;

    //Constructores
    public Empleado(){

    }
    public Empleado(Tipo_Usuario roll, double horasTrabajadas, Estado_Empleado estadoEmpleado) {
        this.rol = roll;
        this.horasTrabajadas = horasTrabajadas;
        this.estadoEmpleado = estadoEmpleado;
    }


    public int getId() {
        return id;
    }

    public Tipo_Usuario getRol() {
        return rol;
    }

    public double getHorasTrabajadas() {
        return horasTrabajadas;
    }

    public void setRol(Tipo_Usuario rol) {
        this.rol = rol;
    }

    public void setHorasTrabajadas(double horasTrabajadas) {
        this.horasTrabajadas = horasTrabajadas;
    }

    public Estado_Empleado getEstadoEmpleado() {
        return estadoEmpleado;
    }

    public void setEstadoEmpleado(Estado_Empleado estadoEmpleado) {this.estadoEmpleado = estadoEmpleado;
    }

    //Funciones:

    //Cargar pasajero (abm)
    //Hacer check in - check out
    //Ver estado de habitaciones
    //hacer reservas
    //ver historial de pasajeros
}
