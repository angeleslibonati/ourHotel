package Gestores;

import Clases.Reserva;
import Enum.*;
import Excepciones.ReservaInvalidaException;
import manejoJSON.GestorJson;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

public class GestorReserva {

    ArrayList<Reserva> reservas;

    public GestorReserva() throws ParseException {
        this.reservas = new ArrayList<>();

        reservas = GestorJson.mapeoReserva();
    }

    public static void mostrarReservas(ArrayList<Reserva> misReservas) {

        for (int i = 0; i < misReservas.size(); i++) {
            Reserva reserva = misReservas.get(i);
            reserva.mostrarUnaReserva();
        }

    }

    public boolean buscarUnaReserva(int numeroReserva) throws ReservaInvalidaException {
        Reserva reserva = new Reserva();
        int i = 0;
        boolean bandera = false;
        if (numeroReserva <= 0) {
            throw new ReservaInvalidaException("Debe ingresar un numero mayor a 0");
        }
        while (i < reservas.size()) {
            if (reservas.get(i).getIdReserva() == numeroReserva) {
                reserva = reservas.get(i);
                reserva.mostrarUnaReserva();
                bandera = true;
            }
            i++;
        }

        return bandera;
    }

    public Reserva buscarReservasActiva() {
        for (Reserva reserva : reservas) {


        }

        return null;
    }




}
