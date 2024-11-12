import Clases.*;
//import Gestores.GestorReserva;
import Gestores.GestorHotel;
import Gestores.GestorReserva;
import manejoJSON.GestorJson;
import org.json.JSONException;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ParseException {

        Scanner entrada = new Scanner(System.in);

        //Mapeo de Json Hotel
        Hotel hotel = new Hotel();

        try {
            GestorHotel miHotel = new GestorHotel();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }


        //Mapeo de Json Reserva

        GestorReserva reserva = new GestorReserva();

        ArrayList<Persona>misPersonas = new ArrayList<>();
        ArrayList<Pasajero>misPasjeros =new ArrayList<>();

        //Inicio de menu

        try {
            Menu.menuPrincipal(entrada, misPersonas,misPasjeros);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }


    }
}