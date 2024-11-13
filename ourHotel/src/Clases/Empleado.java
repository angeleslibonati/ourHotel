package Clases;
import Enum.Estado_Empleado;

import java.util.ArrayList;

public class Empleado extends Persona{

    private int id = ++Empleado.contador;
    private static int contador = 0;
    protected double horasTrabajadas;
    protected Estado_Empleado estadoEmpleado;

    //Constructores
    public Empleado(){

    }

    public Empleado(double horasTrabajadas, Estado_Empleado estadoEmpleado) {
        this.horasTrabajadas = horasTrabajadas;
        this.estadoEmpleado = estadoEmpleado;
    }


    public int getId() {
        return id;
    }

    public double getHorasTrabajadas() {
        return horasTrabajadas;
    }
    public void setHorasTrabajadas(double horasTrabajadas) {
        this.horasTrabajadas = horasTrabajadas;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Estado_Empleado getEstadoEmpleado() {
        return estadoEmpleado;
    }
    public void setEstadoEmpleado(Estado_Empleado estadoEmpleado) {
        this.estadoEmpleado = estadoEmpleado;
    }


    public void mostrarEmpleado () {

        super.mostarPersona();
        Menu.centradoOpciones("Id: " + this.id);
        Menu.centradoOpciones("Horas Trabajadas: " + this.horasTrabajadas);
        Menu.centradoOpciones("Estado del Empleado: " + this.estadoEmpleado);
        Menu.dibujarTerminacion();
    }

    public Empleado buscarEmpleadoXLegajo (int legajo, ArrayList <Empleado> empleados) {
        Empleado empl = new Empleado();

        for (Empleado e : empleados) {
            if (e.getId() == legajo) {
                empl = e;
            }
        }

        return empl;
    }


}
