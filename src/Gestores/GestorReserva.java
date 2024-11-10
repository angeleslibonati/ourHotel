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


}