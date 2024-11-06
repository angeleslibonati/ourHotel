package Gestores;

import Clases.Habitacion;

public class GestorHabitacion {


    //Funcion para cambiar estado de habitacion por check in
    public static void cambioEstadoPorCheckIn (int numHabitacion){

        //Buscar una habitacion por numero
        //Cambia estado de LIBRE A OCUPADA
    }

    //Funcion check out
    public static double cambioEstadoPorCheckOut (int numHabitacion){
        double costoPorConsumos = 0;

        //Busca la habitacion por numero
        //cambia el estado de OCUPADA A LIMPIEZA
        //sumatoria de servicio

        return costoPorConsumos;
    }
    public static double costoPorHabitacion (String dni){

        int cantidadNoches = GestorReserva.cantidadNoches(dni);
        Habitacion habitacion = new Habitacion();

        double costoHospedaje = cantidadNoches * habitacion.getValorPorNoche();

        return costoHospedaje;
    }


}
