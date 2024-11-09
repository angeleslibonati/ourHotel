import Clases.Menu;
import Clases.Persona;
import Clases.Reserva;
import Gestores.GestorReserva;
import manejoJSON.GestorJson;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);

        //Mapeo de Json Hotel



        //Mapeo de Json Reserva
        ArrayList<Reserva> misReservas = new ArrayList<>();
        try {
            misReservas = GestorJson.mapeoReserva();

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        ArrayList<Persona>misPersonas = new ArrayList<>();
        //Inicio de menu

       Menu.menuPrincipal(entrada, misPersonas);

    }
}