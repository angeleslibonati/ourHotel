import Clases.Menu;
import Clases.Reserva;
import Gestores.GestorReserva;
import manejoJSON.GestorJson;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ParseException {

        Scanner entrada = new Scanner(System.in);

        //Mapeo de Json Reserva
        ArrayList<Reserva> misReservas = new ArrayList<>();
        try {
            misReservas = GestorJson.mapeoReserva();

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }



     //  Menu.menuPrincipal(entrada);

    }
}