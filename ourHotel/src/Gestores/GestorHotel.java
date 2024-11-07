package Gestores;

import Clases.Menu;
import Clases.Persona;
import Excepciones.UsuarioYClaveIncorrectoException;

import java.util.ArrayList;
import java.util.Scanner;

public class GestorHotel {



    //Funcion chek in (llama a gestor reserva y gestor habitacion)
    public static void hacerCheckIn (int idReserva){

        int numHabitacion = GestorReserva.cambiaEstadoPorCheckIn(idReserva);
        GestorHabitacion.cambioEstadoPorCheckIn(numHabitacion);
    }


    //Funcion check out
    public static void hacerCheckOut (int numHabitacion, String dni){

        double consumos = GestorHabitacion.cambioEstadoPorCheckOut(numHabitacion);
        double costoHabitacion = GestorHabitacion.costoPorHabitacion(dni);

        Menu.centradoOpciones("Costo por habitacion: $ " + costoHabitacion);
        Menu.centradoOpciones("Sercios extras: $ " + consumos);
        Menu.centradoOpciones("--------------------");
        Menu.centradoOpciones("TOTAL : $ " + (costoHabitacion + consumos));

    }

    public static String ingresoUsuarioYClave (Scanner scan, String mensaje){

        Menu.centradoIngreso(mensaje + " : ");
        return scan.nextLine();
    }

    public static void ingresoYValidacion (Scanner scan,  ArrayList<Persona> personas) throws UsuarioYClaveIncorrectoException {

        try {
            String usuario = ingresoUsuarioYClave(scan, "Usuario");
            String contra = ingresoUsuarioYClave(scan, "Contrasenia");

            for (Persona persona : personas) {

                if (usuario.equals(persona.getUsuario()) && contra.equals(persona.getContrasenia())) {

                    if (persona.getRol().equals("RECEPCIONISTA")) {

                        Menu.menuRecepcionista(scan, personas);

                    } else if (persona.getRol().equals("ADMINISTRADOR")) {

                        Menu.menuAdmin(scan, personas);

                    } else {

                        Menu.menuPasajero(scan, personas);
                        
                    }
                    break;
                }
            }
        } catch (Exception e) {
            throw new UsuarioYClaveIncorrectoException("Usuario y/o Contrasenia incorrecta");
        }
    }



}
