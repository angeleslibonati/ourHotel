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

    public Reserva buscarUnaReserva(int numeroReserva) throws ReservaInvalidaException {
        if (numeroReserva <= 0) {
            throw new ReservaInvalidaException("Debe ingresar un número mayor a 0");
        }

        for (Reserva reserva : reservas) {
            if (reserva.getIdReserva() == numeroReserva) {
                reserva.mostrarUnaReserva();
                return reserva;
            }
        }

        throw new ReservaInvalidaException("No se encontró ninguna reserva con el número: " + numeroReserva);
    }

    public ArrayList<Reserva> buscarReservasActiva() {
        ArrayList<Reserva> activas = new ArrayList<>();

        for (Reserva reserva : reservas) {

            if (reserva.getEstadoReserva() == Estado_Reserva.RESERVADO) {
                activas.add(reserva);
            }
        }

        if(reservas.isEmpty())
        {
            System.out.println("No se encontraron Reservas Activas");
        }

        return activas;
    }

}
