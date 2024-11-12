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

        try {
            //Mapeo de Json Hotel
            GestorHotel miHotel = new GestorHotel();
            //Mapeo de Json Reserva
            GestorReserva reserva = new GestorReserva();
            
            Menu.menuPrincipal(entrada, miHotel);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }





    }
}