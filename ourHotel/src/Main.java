import Clases.*;

import Gestores.GestorHotel;
import Gestores.GestorReserva;

import manejoJSON.GestorJson;
import org.json.JSONException;
import java.text.ParseException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ParseException {

        Scanner entrada = new Scanner(System.in);

        try {
            //Mapeo de Json Hotel
            GestorHotel miHotel = new GestorHotel();
            //Mapeo de Json Reserva
            GestorReserva reserva = new GestorReserva();
            
            Menu.menuPrincipal(entrada, miHotel,reserva);


            Menu.encabezadoMenu("Se estan guardando los cambios");
            miHotel.actualizarHotel();
            GestorJson.toJsonHotel(miHotel.getMiHotel());
            GestorJson.toJsonReservas(reserva.getReservas());

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }





    }
}