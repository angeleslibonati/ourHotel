package Gestores;

import Clases.*;
import Enum.*;
import Interfaces.I_ABM;

import java.util.ArrayList;
import java.util.Scanner;

public class GestorPasajero implements I_ABM {

    protected ArrayList<Pasajero>pasajeros;

    public GestorPasajero() {
        this.pasajeros = new ArrayList<>();
    }

    public ArrayList<Pasajero> getPasajeros() {
        return pasajeros;
    }

    public void setPasajeros(ArrayList<Pasajero> pasajeros) {
        this.pasajeros = pasajeros;
    }

    public Pasajero buscarPasajero(String usuario, ArrayList<Pasajero>misPasajeros){

        Pasajero pasajeroEncontrado = new Pasajero();

        for(int i = 0; i< misPasajeros.size(); i++){

            Pasajero pasajero = misPasajeros.get(i);

            if(pasajero.getUsuario().equals(usuario)){
                return misPasajeros.get(i);
            }
        }
        return pasajeroEncontrado;
    }

    public static void mostrarPasajero(Pasajero pasajero){

        pasajero.mostrarPasajero();
    }


    @Override
    public void alta(Scanner scan, GestorHotel miHotel) {
        Pasajero pas = new Pasajero();

        Menu.centradoIngreso("Ingrese DNI: ");
        String dni = scan.nextLine();

        pas = buscarPasajeroPorDni(dni);

        if(pas.getDni().equals("-1")) {
            pas.setDni(dni);
            Menu.centradoIngreso("Ingrese Nombre: ");
            pas.setNombre(scan.nextLine());
            Menu.centradoIngreso("Ingrese Apellido: ");
            pas.setApellido(scan.nextLine());
            Menu.centradoIngreso("Ingrese Teléfono: ");
            pas.setTelefono(scan.nextLine());
            Menu.centradoIngreso("Ingrese Correo Electrónico: ");
            pas.setEmail(scan.nextLine());

            Direccion dir = new Direccion();
            Menu.centradoOpciones("Dirección");
            Menu.centradoIngreso("Ingrese Calle: ");
            dir.setCalle(scan.nextLine());
            Menu.centradoIngreso("Ingrese Altura: ");
            dir.setAltura(scan.nextInt());
            scan.nextLine();
            Menu.centradoIngreso("Ingrese Ciudad: ");
            dir.setCiudad(scan.nextLine());
            pas.setDireccion(dir);
            pas.setRol(Tipo_Usuario.PASAJERO);
            pas.setActivo(true);
            pas.setUsuario(pas.getNombre() + "_" + pas.getApellido());
            pas.setContrasenia("1234");

            pasajeros.add(pas);

        } else {
            Menu.centradoOpciones("El pasajero ya existe");
            pas.mostrarPasajero();
        }
    }

    @Override
    public void baja(Scanner scan, GestorHotel miHotel) {
        Pasajero pas = new Pasajero();

        Menu.centradoIngreso("Ingrese el número de DNI:");
        pas = buscarPasajeroPorDni(scan.nextLine());

        pas.setActivo(false);
    }


    @Override
    public void modificacion(Scanner scan,GestorHotel miHotel) {
        Pasajero pas = new Pasajero();
        char opcion = 'S';

        Menu.centradoIngreso("DNI del Pasajero: ");
        pas = buscarPasajeroPorDni(scan.nextLine());

        pas.mostrarPasajero();

        while (opcion == 'S') {
            Menu.centradoIngreso("Igrese el campo a modificar: ");
            String campo = scan.nextLine();

            if (campo.equalsIgnoreCase("nombre")) {
                Menu.centradoIngreso("Ingrese el nuevo nombre: ");
                pas.setNombre(scan.nextLine());
            } else if (campo.equalsIgnoreCase("apellido")) {
                Menu.centradoIngreso("Ingrese el nuevo Apellido: ");
                pas.setApellido(scan.nextLine());
            } else if (campo.equalsIgnoreCase("telefono")) {
                Menu.centradoIngreso("Ingrese el nuevo teléfono: ");
                pas.setTelefono(scan.nextLine());
            } else if (campo.equalsIgnoreCase("email")) {
                Menu.centradoIngreso("Ingrese la nueva dirección email: ");
                pas.setEmail(scan.nextLine());
            } else if (campo.equalsIgnoreCase("direccion") || campo.equalsIgnoreCase("dirección")) {
                Direccion dir = pas.getDireccion();
                Menu.centradoIngreso("Ingrese calle: ");
                dir.setCalle(scan.nextLine());
                Menu.centradoIngreso("Ingrese altura: ");
                dir.setAltura(scan.nextInt());
                Menu.centradoIngreso("Ingrese ciudad: ");
                dir.setCiudad(scan.nextLine());
            } else {
                Menu.centradoOpciones("La opción ingresada es inválida o no se puede modificar");
            }

            Menu.centradoIngreso("Desea modificar otro dato S/N: ");
            opcion = scan.nextLine().toUpperCase().charAt(0);

        }
    }

    public Pasajero buscarPasajeroPorDni (String dni){
        Pasajero p = new Pasajero();
        p.setDni("-1");

        for(Pasajero pasajero : pasajeros){
            if(pasajero.getDni().equals(dni)){

                p = pasajero;
            }
        }
        return p;
    }

}
