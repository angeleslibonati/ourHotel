package Gestores;

import Clases.*;
import Enum.*;
import Interfaces.I_ABM;
import java.util.Scanner;

import java.util.ArrayList;

public class GestorEmpleado implements I_ABM {

    ArrayList<Empleado> empleados ;


    public GestorEmpleado() {
        this.empleados = new ArrayList<>();
    }

    public Empleado buscarEmpleadoXLegajo (int legajo) {
        Empleado empl = new Empleado();

        for (Empleado e : empleados) {
            if (e.getId() == legajo) {
                empl = e;
            }
        }

        return empl;
    }

    @Override
    public void alta(Scanner scan, GestorHotel miHotel) {
        Empleado empleado = new Empleado();
        Menu.centradoIngreso("Ingrese DNI: ");
        String dni = scan.nextLine();

        ArrayList<Persona> personas = new ArrayList<>();
        personas.addAll(empleados);

        int index = Persona.buscarPorDni(dni, personas);

        if(index == -1) {
            empleado.setDni(dni);
            Menu.centradoIngreso("Ingrese Nombre: ");
            empleado.setNombre(scan.nextLine());
            Menu.centradoIngreso("Ingrese Apellido: ");
            empleado.setApellido(scan.nextLine());
            Menu.centradoIngreso("Ingrese Teléfono: ");
            empleado.setTelefono(scan.nextLine());
            Menu.centradoIngreso("Ingrese Correo Electrónico: ");
            empleado.setEmail(scan.nextLine());

            Direccion dir = new Direccion();
            Menu.centradoOpciones("Dirección:");
            Menu.centradoIngreso("Ingrese Calle: ");
            dir.setCalle(scan.nextLine());
            Menu.centradoIngreso("Ingrese Altura: ");
            dir.setAltura(scan.nextInt());
            scan.nextLine();
            Menu.centradoIngreso("Ingrese Ciudad: ");
            dir.setCiudad(scan.nextLine());
            empleado.setDireccion(dir);

            Menu.centradoIngreso("Tipo de Usuario: ");
            empleado.setRol(Tipo_Usuario.valueOf(scan.nextLine().toUpperCase()));

            empleado.setEstadoEmpleado(Estado_Empleado.ACTIVO);
            empleado.setUsuario(empleado.getNombre() + "_" + empleado.getApellido());
            empleado.setContrasenia("1234");

            empleados.add(empleado);

            Menu.centradoOpciones("CARGA EXITOSA");

        } else {
            Menu.centradoOpciones("El empleado ya existe");
            empleados.get(index).mostrarEmpleado();
        }
    }

    @Override
    public void baja(Scanner scan) {
        Empleado empl = new Empleado();

        Menu.centradoIngreso("Ingrese el número de Legajo: ");
        empl = buscarEmpleadoXLegajo(scan.nextInt());

        empl.setEstadoEmpleado(Estado_Empleado.INACTIVO);

        Menu.centradoOpciones("EMPLEADO DADO DE BAJA");
    }

    @Override
    public void modificacion(Scanner scan, GestorHotel miHotel) {
        Empleado empl = new Empleado();
        char opcion = 'S';

        Menu.centradoIngreso("Legajo del Empleado: ");
        empl = buscarEmpleadoXLegajo(scan.nextInt());

        empl.mostrarEmpleado();

        while (opcion == 'S') {
            Menu.centradoIngreso("Ingrese el campo a modificar: ");
            String campo = scan.nextLine();

            if (campo.equalsIgnoreCase("nombre")) {
                Menu.centradoIngreso("Ingrese el nuevo nombre: ");
                empl.setNombre(scan.nextLine());
            } else if (campo.equalsIgnoreCase("apellido")) {
                Menu.centradoIngreso("Ingrese el nuevo Apellido: ");
                empl.setApellido(scan.nextLine());
            } else if (campo.equalsIgnoreCase("telefono")) {
                Menu.centradoIngreso("Ingrese el nuevo teléfono: ");
                empl.setTelefono(scan.nextLine());
            } else if (campo.equalsIgnoreCase("email")) {
                Menu.centradoIngreso("Ingrese la nueva dirección email: ");
                empl.setEmail(scan.nextLine());
            } else if (campo.equalsIgnoreCase("direccion") || campo.equalsIgnoreCase("dirección")) {
                Direccion dir = empl.getDireccion();
                Menu.centradoIngreso("Ingrese calle: ");
                dir.setCalle(scan.nextLine());
                Menu.centradoIngreso("Ingrese altura: ");
                dir.setAltura(scan.nextInt());
                scan.nextLine();
                Menu.centradoIngreso("Ingrese ciudad: ");
                dir.setCiudad(scan.nextLine());
            } else {
                Menu.centradoOpciones("La opción ingresada es inválida o no se puede modificar");
            }

            Menu.centradoIngreso("Desea modificar otro dato S/N: ");
            opcion = scan.nextLine().toUpperCase().charAt(0);

            Menu.encabezadoMenu("Empleado modificado");
            empl.mostrarEmpleado();

        }
    }
    public ArrayList<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(ArrayList<Empleado> empleados) {
        this.empleados = empleados;
    }

    public void mostrarEmpleado (Empleado empleado){
        empleado.mostrarEmpleado();
    }
}
