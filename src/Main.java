import Clases.*;
//import Gestores.GestorReserva;
import manejoJSON.JSONUtiles;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;
import manejoJSON.JSONUtiles;
import static manejoJSON.GestorJson.fromJsonHotel;
import static manejoJSON.GestorJson.toJsonHotel;


public class Main {
    public static void main(String[] args) {


        // Probar el método fromJsonHotel
        Hotel hotel = fromJsonHotel();

        // Imprimir los detalles del hotel para verificar la carga desde JSON
        System.out.println("Habitaciones cargadas:");
        for (Habitacion habitacion : hotel.getHabitaciones()) {
            System.out.println(habitacion);
        }

        System.out.println("\nPasajeros cargados:");
        for (Pasajero pasajero : hotel.getPasajeros()) {
            System.out.println(pasajero);
        }

        System.out.println("\nEmpleados cargados:");
        for (Empleado empleado : hotel.getEmpleados()) {
            System.out.println(empleado);
        }

        // Prueba de guardar en JSON
        toJsonHotel(hotel);
        System.out.println("\n Hotel guardado en JSON con éxito.");






//        Scanner entrada = new Scanner(System.in);
////
////        //Mapeo de Json Reserva
////        ArrayList<Reserva> misReservas = new ArrayList<>();
////        try {
////            misReservas = GestorJson.mapeoReserva();
////
////        } catch (ParseException e) {
////            throw new RuntimeException(e);
////        }
//
//        //Inicio de menu
//
//        Menu.menuPrincipal(entrada);

    }
}





