package Gestores;

import Clases.Menu;
import Enum.*;
import Clases.Habitacion;
import Clases.Servicio;
import java.util.ArrayList;
import java.util.Scanner;

import Enum.Estado_Habitacion;
import Excepciones.HabitacionNoDisponibleException;
import Interfaces.I_ABM;

import static Enum.Servicio_Habitacion.*;

public class GestorHabitacion implements I_ABM {


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

    public ArrayList<Habitacion> buscaHabitacionLibre()  {

        ArrayList<Habitacion>habitacionesLibres = new ArrayList<>();


        for (Habitacion habitacion : habitaciones) {

            Habitacion habitacionLibre = habitacion;

            if (habitacion.getEstadoHabitacion().equals(Estado_Habitacion.LIBRE)) {
                habitacionLibre = habitacion;

                habitacionesLibres.add(habitacionLibre);
            }

        }

        return habitacionesLibres;
    }

    public int buscarPorNroHabitacion(int nroHab) {
        int index = -1;
        int cont = 0;

        while (cont < (habitaciones.size()-1) && index == -1) {

            if (nroHab == habitaciones.get(cont).getNumHabitacion()) {
                index = cont;
            }
            cont ++;
        }
        return index;
    }


    @Override
    public void alta(Scanner scan) {
        Habitacion hab = new Habitacion();

        Menu.centradoIngreso("Ingrese Número de habitación: ");
        int numHab = scan.nextInt();

        int index = buscarPorNroHabitacion(numHab);

        if(index == -1) {
            hab.setNumHabitacion(numHab);
            Menu.centradoIngreso("Ingrese Valor por noche:");
            hab.setValorPorNoche(scan.nextDouble());
            Menu.centradoIngreso("Ingrese Capacidad Máxima de pasajero:");
            hab.setCantPersonas(scan.nextInt());
            Menu.centradoIngreso("Ingrese Tipo de Habitación:");
            hab.setTipoHabitacion(Tipo_Habitacion.valueOf(scan.nextLine().toUpperCase()));
            Menu.centradoIngreso("Ingrese Tipo de cama:");
            hab.setTipoCama(Tipo_Cama.valueOf(scan.nextLine().toUpperCase()));
            hab.setEstadoHabitacion(Estado_Habitacion.LIBRE);

            habitaciones.add(hab);

        } else {
            Menu.centradoOpciones("La habitación ya existe");
            Menu.centradoOpciones(habitaciones.get(index).toString());
        }
    }

    @Override
    public void baja(Scanner scan) {
        Habitacion hab = new Habitacion();

        Menu.centradoIngreso("Ingrese número de habitación:");
        hab = habitaciones.get(buscarPorNroHabitacion(scan.nextInt()));

        Menu.centradoIngreso("Ingrese el motivo de baja:");
        int motivo = scan.nextInt();
        scan.nextLine();

        switch (motivo) {
            case 1:
                hab.setEstadoHabitacion(Estado_Habitacion.REPARACION);
                break;
            case 2:
                hab.setEstadoHabitacion(Estado_Habitacion.OCUPADA);
                break;
            case 3:
                hab.setEstadoHabitacion(Estado_Habitacion.LIMPIEZA);
                break;
            default:
                Menu.centradoOpciones("El motivo ingresado es inválido.");
        }
    }

    @Override
    public void modificacion(Scanner scan) {
        Habitacion hab = new Habitacion();
        char opcion = 'S';

        Menu.centradoIngreso("Número de habitación:");
        hab = habitaciones.get(buscarPorNroHabitacion(scan.nextInt()));

        System.out.println(hab);

        while (opcion == 'S') {
            Menu.centradoIngreso("Igrese el campo a modificar:");
            String campo = scan.nextLine();

            if (campo.equalsIgnoreCase("valor por noche")) {
                Menu.centradoIngreso("Ingrese el nuevo valor:");
                hab.setValorPorNoche(scan.nextDouble());
            } else if (campo.equalsIgnoreCase("cantidad de personas")) {
                Menu.centradoIngreso("Ingrese la nueva cantidad:");
                hab.setCantPersonas(scan.nextInt());
            } else if (campo.equalsIgnoreCase("tipo de habitacion")) {
                Menu.centradoIngreso("Ingrese tipo de habitación:");
                hab.setTipoHabitacion(Tipo_Habitacion.valueOf(scan.nextLine().toUpperCase()));
            } else if (campo.equalsIgnoreCase("tipo de cama")) {
                Menu.centradoIngreso("Ingrese tipo de cama:");
                hab.setTipoHabitacion(Tipo_Habitacion.valueOf(scan.nextLine().toUpperCase()));
            } else if (campo.equalsIgnoreCase("estado de habitacion") || campo.equalsIgnoreCase("dirección")) {
                hab.setEstadoHabitacion(Estado_Habitacion.valueOf(scan.nextLine().toUpperCase()));
            } else {
                Menu.centradoOpciones("La opción ingresada es inválida o no se puede modificar");
            }

            Menu.centradoIngreso("Desea modificar otro dato S/N:");
            opcion = scan.nextLine().toUpperCase().charAt(0);

        }
    }




}
