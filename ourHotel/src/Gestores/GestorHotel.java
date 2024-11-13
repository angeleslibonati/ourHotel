package Gestores;

import Clases.*;
import manejoJSON.GestorJson;
import org.json.JSONException;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

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
    public void hacerCheckIn(int idReserva, GestorReserva misReservas) throws ParseException {

        int numHabitacion = misReservas.cambiaEstadoPorCheckIn(idReserva);
        this.gestorHabitacion.cambioEstadoPorCheckIn(numHabitacion);


    }


   // Funcion check out
    public void hacerCheckOut(int numHabitacion, String dni,GestorReserva misReservas){

        double consumos = this.gestorHabitacion.cambioEstadoPorCheckOut(numHabitacion);

        double costoHabitacion = 0;
        try {

            costoHabitacion = this.gestorHabitacion.costoPorHabitacion(dni,misReservas);
            Menu.centradoOpciones("Costo por habitacion: $ " + costoHabitacion);
            Menu.centradoOpciones("Sercios extras: $ " + consumos);
            Menu.centradoOpciones("--------------------");
            Menu.centradoOpciones("TOTAL : $ " + (costoHabitacion + consumos));


        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

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

    public void altaEmpleado (Scanner scan){
        this.gestorEmpleado.alta(scan);
    }

    public void bajaEmpleado (Scanner scan){
        this.gestorEmpleado.baja(scan);
    }

    public void modificacionEmpleado (Scanner scan){
        this.gestorEmpleado.modificacion(scan);
    }

    public void altaPasajero (Scanner scan){
        this.gestorPasajero.alta(scan);
    }

    public void bajaPasajero (Scanner scan){
        this.gestorPasajero.baja(scan);
    }
    public void modificarPasajero (Scanner scan){
        this.gestorPasajero.modificacion(scan);
    }


    public void altaHabitacion (Scanner scan){
        this.gestorHabitacion.alta(scan);
    }
    public void bajaHabitacion (Scanner scan){
        this.gestorHabitacion.baja(scan);
    }
    public void modificarHabitacion(Scanner scan){
        this.gestorHabitacion.modificacion(scan);
    }


    public Pasajero buscarPasajeroPorDni (String dni){
        return this.gestorPasajero.buscarPasajeroPorDni(dni);
    }
    public Pasajero buscarPasajero (String usuario){
        return this.gestorPasajero.buscarPasajero(usuario,getPasajeros());
    }
    public Empleado buscarEmpleadoXLegajo (int numId){

    }














}
