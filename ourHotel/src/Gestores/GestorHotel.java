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











}
