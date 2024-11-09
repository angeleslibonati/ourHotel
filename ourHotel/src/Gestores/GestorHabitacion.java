package Gestores;
import Enum.Servicio_Habitacion;
import Clases.Habitacion;
import Clases.Menu;
import Clases.Servicio;

import static Enum.Servicio_Habitacion.*;

public class GestorHabitacion {


    //Funcion para cambiar estado de habitacion por check in
    public static void cambioEstadoPorCheckIn (int numHabitacion){

        //Buscar una habitacion por numero
        //Cambia estado de LIBRE A OCUPADA
    }

    //Funcion check out
    public static double cambioEstadoPorCheckOut (int numHabitacion){
        double costoPorConsumos = 0;
        Habitacion habitacion = new Habitacion();

       // habitacion = //Busca la habitacion por numero  (devolver una HABITACION)
        //cambia el estado de OCUPADA A LIMPIEZA

        //sumatoria de servicio
        costoPorConsumos = consumosExtra(numHabitacion, habitacion);

        return costoPorConsumos;
    }

    //Funcion para saber los consumos extras. sino  tuvo devuelve valor $0
    public static double consumosExtra (int numHabitacion, Habitacion h){
        double costoPorConsumo = 0;

        if (h.getNumHabitacion() == numHabitacion){

            for (int i = 0; i<h.getServicios().size(); i++){

                Servicio servicio = new Servicio();

                servicio = h.getServicios().get(i);
                double costoUnitario = 0;

                if (servicio.equals(Servicio_Habitacion.MASAJE) ||
                        servicio.equals(Servicio_Habitacion.SPA) ||
                        servicio.equals(Servicio_Habitacion.SAUNA) ||
                        servicio.equals(Servicio_Habitacion.HIDROMASAJE) ||
                        servicio.equals(Servicio_Habitacion.DESAYUNO) ||
                        servicio.equals(Servicio_Habitacion.ALMUERZO_CENA) ||
                        servicio.equals(Servicio_Habitacion.SERVICIO_BRINDIS) ||
                        servicio.equals(Servicio_Habitacion.BEBIDA_SIN_ALCOHOL)) {

                    costoUnitario = servicio.getCosto();

                }
                costoPorConsumo = costoPorConsumo + costoUnitario;
            }
        }

        return costoPorConsumo;
    }


    //Chequear dudosaa !!!!!!!!!!!!!
    public static double costoPorHabitacion (String dni){

        int cantidadNoches = GestorReserva.cantidadNoches(dni);
        Habitacion habitacion = new Habitacion();

        double costoHospedaje = cantidadNoches * habitacion.getValorPorNoche();

        return costoHospedaje;
    }


}
