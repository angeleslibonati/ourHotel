package Gestores;

import Clases.*;
import manejoJSON.GestorJson;
import org.json.JSONException;

import java.util.ArrayList;

public class GestorHotel {

    protected Hotel miHotel;
    protected GestorEmpleado gestorEmpleado = new GestorEmpleado();
    protected GestorHabitacion gestorHabitacion = new GestorHabitacion();
    protected GestorPasajero gestorPasajero = new GestorPasajero();


    public GestorHotel() throws JSONException {
        this.miHotel = new Hotel();

        miHotel = GestorJson.fromJsonHotel();

        gestorEmpleado.setEmpleados(miHotel.getEmpleados());
        gestorHabitacion.setHabitaciones(miHotel.getHabitaciones());
        gestorPasajero.setPasajeros(miHotel.getPasajeros());
    }

    public Hotel getMiHotel() {
        return miHotel;
    }

    public ArrayList<Pasajero> getPasajeros(){
        return gestorPasajero.getPasajeros();
    }

    public ArrayList<Empleado> getEmpleados() {
        return gestorEmpleado.getEmpleados();
    }

    public ArrayList<Habitacion> getHabitacion() {
        return gestorHabitacion.getHabitaciones();
    }

    //Funcion chek in (llama a gestor reserva y gestor habitacion)
    public void hacerCheckIn(int idReserva){

        int numHabitacion = GestorReserva.cambiaEstadoPorCheckIn(idReserva);
        this.gestorHabitacion.cambioEstadoPorCheckIn(numHabitacion);

    }


    //Funcion check out
    public void hacerCheckOut(int numHabitacion, String dni){

        double consumos = this.gestorHabitacion.cambioEstadoPorCheckOut(numHabitacion);

        double costoHabitacion = GestorHabitacion.costoPorHabitacion(dni);

        Menu.centradoOpciones("Costo por habitacion: $ " + costoHabitacion);
        Menu.centradoOpciones("Sercios extras: $ " + consumos);
        Menu.centradoOpciones("--------------------");
        Menu.centradoOpciones("TOTAL : $ " + (costoHabitacion + consumos));

    }

    public void buscarHabitacion (int numHabitacion){

        this.gestorHabitacion.buscaHabitacion(numHabitacion);

    }

    public ArrayList<Habitacion> buscarHabitacionesLibres (){
        return this.gestorHabitacion.buscaHabitacionLibre();
    }

    public ArrayList<Habitacion> buscarHabitacionesOcupadas() {
        return this.gestorHabitacion.buscarHabitacionesOcupadas();
    }

    public void actualizarHotel (){
        this.miHotel.setEmpleados(gestorEmpleado.getEmpleados());
        this.miHotel.setHabitaciones(gestorHabitacion.getHabitaciones());
        this.miHotel.setPasajeros(gestorPasajero.getPasajeros());
    }












}
