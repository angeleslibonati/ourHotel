package Gestores;

import Clases.Hotel;
import Clases.Menu;
import manejoJSON.GestorJson;
import org.json.JSONException;

public class GestorHotel {

    protected Hotel miHotel;

    public GestorHotel() throws JSONException {
        this.miHotel = new Hotel();

        miHotel = GestorJson.fromJsonHotel();
    }


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
