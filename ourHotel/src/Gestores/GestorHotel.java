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

        double consumos = this.gestorHabitacion.cambioEstadoPorCheckOut(numHabitacion,dni,misReservas);

        double costoHabitacion = 0;
        try {

            costoHabitacion = this.gestorHabitacion.costoPorHabitacion(dni,misReservas,numHabitacion);
            Menu.centradoOpciones("Costo por habitacion: $ " + costoHabitacion);
            Menu.centradoOpciones("Sercios extras: $ " + consumos);
            Menu.centradoOpciones("--------------------");
            Menu.centradoOpciones("TOTAL : $ " + (costoHabitacion + consumos));


        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }

    public void mostrarHabitaciones(ArrayList<Habitacion> habitaciones){
        for (Habitacion h : habitaciones){
            h.mostrarHabitacion();
        }
    }

    public Habitacion buscarHabitacion (int numHabitacion){

        return this.gestorHabitacion.buscaHabitacion(numHabitacion);

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

    public void altaEmpleado (Scanner scan, GestorHotel miHotel){
        this.gestorEmpleado.alta(scan, miHotel);
    }

    public void bajaEmpleado (Scanner scan){
        this.gestorEmpleado.baja(scan);
    }

    public void modificacionEmpleado (Scanner scan,GestorHotel miHotel){
        this.gestorEmpleado.modificacion(scan,miHotel);
    }

    public void altaPasajero (Scanner scan, GestorHotel miHotel){
        this.gestorPasajero.alta(scan, miHotel);
    }

    public void bajaPasajero (Scanner scan){
        this.gestorPasajero.baja(scan);
    }
    public void modificarPasajero (Scanner scan,GestorHotel miHotel){
        this.gestorPasajero.modificacion(scan,miHotel);
    }


    public void altaHabitacion (Scanner scan, GestorHotel miHotel){
        this.gestorHabitacion.alta(scan, miHotel);
    }
    public void bajaHabitacion (Scanner scan){
        this.gestorHabitacion.baja(scan);
    }
    public void modificarHabitacion(Scanner scan, GestorHotel miHotel){
        this.gestorHabitacion.modificacion(scan, miHotel);
    }


    public Pasajero buscarPasajeroPorDni (String dni){
        return this.gestorPasajero.buscarPasajeroPorDni(dni);
    }
    public Pasajero buscarPasajero (String usuario){
        return this.gestorPasajero.buscarPasajero(usuario,getPasajeros());
    }
    public Empleado buscarEmpleadoXLegajo (int numId){
        return this.gestorEmpleado.buscarEmpleadoXLegajo(numId);
    }
    public void mostrarEmpleado(Empleado empleado){
        this.gestorEmpleado.mostrarEmpleado(empleado);
    }














}
