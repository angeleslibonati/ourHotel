package Gestores;

import Clases.Reserva;

import java.util.ArrayList;

public class GestorReserva {

    public static void mostrarReservas(ArrayList<Reserva> misReservas){

        for (int i = 0; i < misReservas.size(); i++){
            Reserva reserva = misReservas.get(i);
            reserva.mostrarUnaReserva();
        }

    }
}
