package Gestores;

import Clases.Habitacion;
import Clases.Menu;
import Clases.Reserva;
import Enum.*;
import Excepciones.ReservaInvalidaException;
import manejoJSON.GestorJson;
import java.text.ParseException;
import java.util.ArrayList;

public class GestorReserva {

    static ArrayList<Reserva> reservas;

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

    public static Reserva buscarUnaReserva(int numeroReserva) throws ReservaInvalidaException {
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

    public static ArrayList<Reserva> buscarReservasActiva() {
        ArrayList<Reserva> activas = new ArrayList<>();

        for (Reserva reserva : reservas) {

            if (reserva.getEstadoReserva() == Estado_Reserva.RESERVADO) {
                activas.add(reserva);
            }
        }

        if(reservas.isEmpty())
        {
            Menu.centradoOpciones("No se encontraron Reservas Activas");
        }

        return activas;
    }



    public static void cancelarReserva(int numeroReserva) throws ReservaInvalidaException {
        Reserva reserva = buscarUnaReserva(numeroReserva);
        if (reserva != null) {

            reserva.setEstadoReserva(Estado_Reserva.CANCELADO);
            Menu.centradoOpciones("La reserva numero " + numeroReserva + " ha sido cancelada.");

            Habitacion habitacion = reserva.getHabitacion();
            if (habitacion != null) {
                habitacion.setEstadoHabitacion(Estado_Habitacion.LIBRE);
                Menu.centradoOpciones("La habitación " + habitacion.getNumHabitacion() + " ahora se encuentra Libre.");
            }
        } else {
            throw new ReservaInvalidaException("No se encontró ninguna reserva con el numero: " + numeroReserva);
        }
    }

    //cambia estado por reserva confirmada por check in
    public static int cambiaEstadoPorCheckIn (int idReserva){
        int numHabitacion = 0;

        //Busca la reserva
        //Cambia el estado de "Reservado" a Confirmado.
        //trae el numero de habitacion y la retorna

        return numHabitacion;
    }

    public static int cantidadNoches (String dni){

        //Busca la reserva por dni.
        Reserva reserva = new Reserva();
        int canDias = (int) ((reserva.getFechaInicio().getTime() - reserva.getFechaFin().getTime()));
      //  int dias = (int) ((fechaInicio.getTime() - fechaactual.getTime()));

        return canDias;
    }


    public static ArrayList<Reserva> buscarReservasHistoricas() {
        ArrayList<Reserva> historicas = new ArrayList<>();

        for (Reserva reserva : reservas) {

            if (reserva.getEstadoReserva() == Estado_Reserva.FINALIZADO) {
                historicas.add(reserva);
            }
        }

        if(reservas.isEmpty())
        {
            Menu.centradoOpciones("No se encontraron Reservas Finallizadas");
        }

        return historicas;
    }
}
