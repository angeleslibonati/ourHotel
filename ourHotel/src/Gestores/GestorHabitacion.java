package Gestores;

import Clases.Habitacion;
import Excepciones.NumeroInvalidoException;
import Enum.*;

import java.util.ArrayList;
import java.util.List;

import Enum.Servicio_Habitacion;
import Clases.Habitacion;
import Clases.Menu;
import Clases.Servicio;

import static Enum.Servicio_Habitacion.*;

public class GestorHabitacion {


    ArrayList<Habitacion> habitaciones;

    public GestorHabitacion() {
        this.habitaciones = new ArrayList<>();
    }

    public void mostrarTodasLasHabitaciones() {
        for (Habitacion habitacion : habitaciones) {
            System.out.println(habitacion);
        }
    }


    public Habitacion buscarHabitacionPorNumero(int numeroHabitacion) throws NumeroInvalidoException {
        if (numeroHabitacion >= 0) {
            throw new NumeroInvalidoException("Debe ingresar un numero mayor a 0");
        }
        for (Habitacion habitacion : habitaciones) {
            if (habitacion.getNumHabitacion() == numeroHabitacion) {
                return habitacion;
            }
        }
        return null;
    }


    public List<Habitacion> buscarHabitacionesLibres() {
        List<Habitacion> libres = new ArrayList<>();
        for (Habitacion habitacion : habitaciones) {
            if (habitacion.getEstadoHabitacion().equals(Estado_Habitacion.LIBRE)) {
                libres.add(habitacion);
            }
        }
        return libres;
    }


    public List<Habitacion> buscarHabitacionesOcupadas() {
        List<Habitacion> ocupadas = new ArrayList<>();
        for (Habitacion habitacion : habitaciones) {
            if (habitacion.getEstadoHabitacion().equals(Estado_Habitacion.OCUPADA)) {
                ocupadas.add(habitacion);
            }
        }
        return ocupadas;
    }




    //Funcion para cambiar estado de habitacion por check in
    public static void cambioEstadoPorCheckIn(int numHabitacion) {

        //Buscar una habitacion por numero
        //Cambia estado de LIBRE A OCUPADA
    }

    //Funcion check out
    public static double cambioEstadoPorCheckOut(int numHabitacion) {
        double costoPorConsumos = 0;
        Habitacion habitacion = new Habitacion();

        // habitacion = //Busca la habitacion por numero  (devolver una HABITACION)
        //cambia el estado de OCUPADA A LIMPIEZA

        //sumatoria de servicio
        costoPorConsumos = consumosExtra( habitacion);

        return costoPorConsumos;
    }

    //Funcion para saber los consumos extras. sino  tuvo devuelve valor $0
    public static double consumosExtra(Habitacion h) {

        double costoPorConsumo = 0;

        for (int i = 0; i < h.getServicios().size(); i++) {

            Servicio servicio = h.getServicios().get(i);

            if (servicio.equals(Servicio_Habitacion.MASAJE) ||
                    servicio.equals(Servicio_Habitacion.SPA) ||
                    servicio.equals(Servicio_Habitacion.SAUNA) ||
                    servicio.equals(Servicio_Habitacion.HIDROMASAJE) ||
                    servicio.equals(Servicio_Habitacion.DESAYUNO) ||
                    servicio.equals(Servicio_Habitacion.ALMUERZO_CENA) ||
                    servicio.equals(Servicio_Habitacion.SERVICIO_BRINDIS) ||
                    servicio.equals(Servicio_Habitacion.BEBIDA_SIN_ALCOHOL)) {

                costoPorConsumo = costoPorConsumo + servicio.getCosto();
                ;
            }
        }

        return costoPorConsumo;
    }


    //Chequear dudosaa !!!!!!!!!!!!!
    public static double costoPorHabitacion(String dni) {

        int cantidadNoches = GestorReserva.cantidadNoches(dni);
        Habitacion habitacion = new Habitacion();

        double costoHospedaje = cantidadNoches * habitacion.getValorPorNoche();

        return costoHospedaje;
    }


}
