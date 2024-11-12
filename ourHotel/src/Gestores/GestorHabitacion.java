package Gestores;

import Clases.Menu;
import Enum.Servicio_Habitacion;
import Clases.Habitacion;
import Clases.Servicio;
import java.util.ArrayList;
import Enum.Estado_Habitacion;
import Excepciones.HabitacionNoDisponibleException;

import static Enum.Servicio_Habitacion.*;

public class GestorHabitacion {


    protected  ArrayList<Habitacion> habitaciones;

    public GestorHabitacion() {
        this.habitaciones = new ArrayList<>();
    }

    public ArrayList<Habitacion> getHabitaciones() {
        return habitaciones;
    }

    public void setHabitaciones(ArrayList<Habitacion> habitaciones) {
        this.habitaciones = habitaciones;
    }

    public void mostrarTodasLasHabitaciones() {
        for (Habitacion habitacion : habitaciones) {
            habitacion.mostrarHabitacion();
        }
    }

    //Funcion check out
    public double cambioEstadoPorCheckOut(int numHabitacion) {

        double costoPorConsumos = 0;
        Habitacion habitacion = buscaHabitacion(numHabitacion);

        if (habitacion.getEstadoHabitacion().equals(Estado_Habitacion.OCUPADA)){

            //cambia el estado de OCUPADA A LIMPIEZA
            habitacion.setEstadoHabitacion(Estado_Habitacion.LIMPIEZA);
            Menu.centradoOpciones("Servicio Limpieza en proceso");
        }
        //sumatoria de servicio
        costoPorConsumos = consumosExtra( habitacion);

        return costoPorConsumos;
    }

    //Funcion para saber los consumos extras. sino  tuvo devuelve valor $0
    public static double consumosExtra(Habitacion h) {

        double costoPorConsumo = 0;

        for (int i = 0; i < h.getServicios().size(); i++) {

            Servicio servicio = h.getServicios().get(i);

            if (servicio.equals(Servicio_Habitacion.fromString(String.valueOf(MASAJE))) ||
                    servicio.equals(Servicio_Habitacion.fromString(String.valueOf(SPA))) ||
                    servicio.equals(Servicio_Habitacion.fromString(String.valueOf(SAUNA))) ||
                    servicio.equals(Servicio_Habitacion.fromString(String.valueOf(HIDROMASAJE))) ||
                    servicio.equals(Servicio_Habitacion.fromString(String.valueOf(DESAYUNO))) ||
                    servicio.equals(Servicio_Habitacion.fromString(String.valueOf(ALMUERZO_CENA))) ||
                    servicio.equals(Servicio_Habitacion.fromString(String.valueOf(SERVICIO_BRINDIS))) ||
                    servicio.equals(Servicio_Habitacion.fromString(String.valueOf(BEBIDA_SIN_ALCOHOL)))) {

                costoPorConsumo = costoPorConsumo + servicio.getCosto();
            }
        }
        return costoPorConsumo;
    }

    public ArrayList<Habitacion> buscarHabitacionesOcupadas() {

        ArrayList<Habitacion> ocupadas = new ArrayList<>();

        for (Habitacion habitacion : habitaciones) {

            if (habitacion.getEstadoHabitacion().equals(Estado_Habitacion.OCUPADA)) {
                ocupadas.add(habitacion);
            }
        }

        return ocupadas;
    }


    //Funcion para cambiar estado de habitacion por check in
    public void cambioEstadoPorCheckIn(int numHabitacion) throws HabitacionNoDisponibleException{

        //Buscar una habitacion por numero
        Habitacion habitacion =new Habitacion();

        habitacion = buscaHabitacion(numHabitacion);

        //Cambia estado de LIBRE A OCUPADA
        if (habitacion.getEstadoHabitacion().equals(Estado_Habitacion.LIBRE)){

            habitacion.setEstadoHabitacion(Estado_Habitacion.OCUPADA);
            Menu.centradoOpciones("Check in completado");
        }
        else {
            throw new HabitacionNoDisponibleException("Habitacion no disponible");
        }

    }


    public static double costoPorHabitacion(String dni) {

        int cantidadNoches = GestorReserva.cantidadNoches(dni);
        Habitacion habitacion = new Habitacion();

        double costoHospedaje = cantidadNoches * habitacion.getValorPorNoche();

        return costoHospedaje;
    }

    public Habitacion buscaHabitacion(int numHabitacion){

        Habitacion habitacion = new Habitacion();

        for (Habitacion h : habitaciones){

            if (h.getNumHabitacion() == numHabitacion){
                habitacion = h;
                habitacion.mostrarHabitacion();
            }
        }
        return habitacion;
    }

    public ArrayList<Habitacion> buscaHabitacionLibre() throws HabitacionNoDisponibleException {

        ArrayList<Habitacion>habitacionesLibres = new ArrayList<>();


        for (Habitacion habitacion : habitaciones) {
            Habitacion habitacionLibre = new Habitacion();

            if (habitacion.getEstadoHabitacion().equals(Estado_Habitacion.LIBRE)) {
                habitacionLibre = habitacion;
                habitacionesLibres.add(habitacionLibre);
            }
            else {
                throw new HabitacionNoDisponibleException("No contamos con Habitaciones Libres");
            }
        }

        return habitacionesLibres;
    }


}
