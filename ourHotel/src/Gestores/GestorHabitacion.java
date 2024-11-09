package Gestores;

import Clases.Habitacion;
import Excepciones.NumeroInvalidoException;
import Enum.*;

import java.util.ArrayList;
import java.util.List;

public class GestorHabitacion {


    ArrayList<Habitacion> habitaciones;

    public GestorHabitacion() {
        this.habitaciones = new ArrayList<>();
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


}
