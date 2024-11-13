package Gestores;

import Clases.Empleado;
import Enum.*;
import Clases.Pasajero;

import java.util.ArrayList;

public class GestorEmpleado {

    ArrayList<Empleado> empleados ;
    ArrayList<Empleado>empleados ;

    public GestorEmpleado() {
        this.empleados = new ArrayList<>();
    }

    public ArrayList<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(ArrayList<Empleado> empleados) {
        this.empleados = empleados;
    }
}
