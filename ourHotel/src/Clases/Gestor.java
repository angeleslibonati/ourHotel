package Clases;

import Excepciones.NumeroInvalidoException;
import Enum.Estado_Habitacion;
import Enum.Estado_Reserva;
import java.util.ArrayList;
import java.util.List;

public class Gestor {

    ArrayList<Pasajero> pasajeros ;
    ArrayList <Empleado> empleados;
    ArrayList <Habitacion> habitaciones;
    ArrayList<Reserva> reservas;


    public Gestor() {
        this.pasajeros = new ArrayList<>();
        this.empleados = new ArrayList<>();
        this.habitaciones = new ArrayList<>();
        this.reservas = new ArrayList<>();
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
            if (habitacion.getEstadoHabitacion().equals(Estado_Habitacion.LIBRE))
            {
                libres.add(habitacion);
            }
        }
        return libres;
    }


    public List<Habitacion> buscarHabitacionesOcupadas() {
        List<Habitacion> ocupadas = new ArrayList<>();
        for (Habitacion habitacion : habitaciones) {
            if (habitacion.getEstadoHabitacion().equals(Estado_Habitacion.OCUPADA))
            {
                ocupadas.add(habitacion);
            }
        }
        return ocupadas;
    }


    public Empleado buscarEmpleado(){

        return null;
    }

    public Reserva buscarUnaReserva(int numeroReserva){


        return null;
    }

    public Reserva buscarReservasActiva()
    {
        for(Reserva reserva : reservas)
        {

        }

        return null;
    }




}


