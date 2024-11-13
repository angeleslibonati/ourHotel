package Gestores;

import Clases.*;
import Enum.*;
import Excepciones.FechaInvalidaException;
import Excepciones.ReservaInvalidaException;
import Interfaces.I_ABM;
import manejoJSON.GestorJson;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;

public class GestorReserva implements I_ABM {

     protected ArrayList<Reserva> reservas;

    public GestorReserva() throws ParseException {
        this.reservas = new ArrayList<>();

        reservas = GestorJson.mapeoReserva();
    }

    public  ArrayList<Reserva> getReservas() {
        return reservas;
    }

    public  void setReservas() {
        this.reservas = reservas;
    }

    public  void mostrarReservas(ArrayList<Reserva> misReservas) {

        for (int i = 0; i < misReservas.size(); i++) {
            Reserva reserva = misReservas.get(i);
            reserva.mostrarUnaReserva();
        }

    }

    public  Reserva buscarUnaReserva(int numeroReserva) throws ReservaInvalidaException {
        if (numeroReserva <= 0) {
            throw new ReservaInvalidaException("Debe ingresar un número mayor a 0");
        }

        for (Reserva reserva : reservas) {
            if (reserva.getIdReserva() == numeroReserva) {
                reserva.mostrarUnaReserva();
                return reserva;
            }
        }

        throw new ReservaInvalidaException("No se encontró ninguna reserva con el número: " + numeroReserva);
    }

    public  ArrayList<Reserva> buscarReservasActiva(String dni) {
        ArrayList<Reserva> activas = new ArrayList<>();

        for (Reserva reserva : reservas) {

            if (reserva.getPasajero().getDni().equals(dni) && reserva.getEstadoReserva().equals(Estado_Reserva.RESERVADO)) {
                activas.add(reserva);
            }
        }

        if (reservas.isEmpty()) {
            Menu.centradoOpciones("No se encontraron Reservas Activas");
        }

        return activas;
    }


    public void cancelarReserva(int numeroReserva) throws ReservaInvalidaException {
        Reserva reserva = buscarUnaReserva(numeroReserva);
        if (reserva != null) {

            reserva.setEstadoReserva(Estado_Reserva.CANCELADO);
            Menu.centradoOpciones("La reserva numero " + numeroReserva + " ha sido cancelada.");

            Habitacion habitacion = reserva.getHabitacion();
            if (habitacion != null) {
                habitacion.setEstadoHabitacion(Estado_Habitacion.LIBRE);
                Menu.centradoOpciones("La habitación " + habitacion.getNumHabitacion() + " ahora se encuentra Libre.");
            }
        } else {
            throw new ReservaInvalidaException("No se encontró ninguna reserva con el numero: " + numeroReserva);
        }
    }

    //cambia estado por reserva confirmada por check in
    public  int cambiaEstadoPorCheckIn(int idReserva) throws ReservaInvalidaException {
        int numHabitacion = 0;

        //Busca la reserva
        Reserva miReserva = buscarUnaReserva(idReserva);

        if (miReserva.getEstadoReserva().equals(Estado_Reserva.RESERVADO)) {
            miReserva.setEstadoReserva(Estado_Reserva.CONFIRMADO);
        } else {
            throw new ReservaInvalidaException("Numero Reserva invalida");
        }

        //trae el numero de habitacion y la retorna
        numHabitacion = miReserva.getHabitacion().getNumHabitacion();
        return numHabitacion;
    }

    public  long cantidadNoches(String dni) {

        Reserva reserva = buscaReservaPorDni(dni);

        long canDias = ChronoUnit.DAYS.between(reserva.getFechaInicio(), reserva.getFechaFin());

        return canDias;
    }


    public  ArrayList<Reserva> buscarReservasHistoricas(String dni) {
        ArrayList<Reserva> historicas = new ArrayList<>();

        for (Reserva reserva : reservas) {

            if (reserva.getPasajero().getDni().equals(dni) && reserva.getEstadoReserva().equals(Estado_Reserva.FINALIZADO)) {
                historicas.add(reserva);
            }
        }

        if (reservas.isEmpty()) {
            Menu.centradoOpciones("No se encontraron Reservas Finallizadas");
        }

        return historicas;
    }

    public  ArrayList<Reserva> buscarReservasActivas() {

        ArrayList<Reserva> activas = new ArrayList<>();

        for (Reserva reserva : reservas) {

            if (reserva.getEstadoReserva().equals(Estado_Reserva.RESERVADO)) {
                activas.add(reserva);
            }
        }

        if (reservas.isEmpty()) {
            Menu.centradoOpciones("No se encontraron Reservas Activas");
        }

        return activas;
    }

    public  Habitacion buscarUnaHabitacionDni(String dni)  {
        Habitacion habitacion = new Habitacion();

        for (Reserva reserva : reservas) {
            if (reserva.getPasajero().getDni().equals(dni)) {
                if (reserva.getEstadoReserva().equals(Estado_Reserva.CONFIRMADO)){
                    habitacion = reserva.getHabitacion();
                }
            }
        }
        return habitacion;
    }

    public boolean reservaSuperpuesta (LocalDate fFin, int nroHab) {
        Boolean supuerpuesta = false;
        Reserva r = new Reserva();
        int cont = 0;

        while (cont < buscarReservasActivas().size() && !supuerpuesta) {
            r = buscarReservasActivas().get(cont);
            if (r.getHabitacion().getNumHabitacion() == nroHab) {
                if (r.getFechaInicio().isBefore(fFin)) {
                    supuerpuesta = true;
                }
            }
            cont++;
        }
        return supuerpuesta;
    }

    public boolean reservaDisponible(LocalDate fInic, LocalDate fFin, int nroHab) {
        Boolean disponible = true;

        Reserva r = new Reserva();
        int cont = 0;
        ArrayList<Reserva> activas = buscarReservasActivas();

        while (cont < activas.size() && disponible) {
            r = activas.get(cont);
            if (r.getHabitacion().getNumHabitacion() == nroHab) {
                if (fInic.isAfter(r.getFechaInicio()) && fInic.isBefore(r.getFechaFin())) {
                    disponible = false;
                }
            }
            cont++;
        }

        if(disponible) {
            cont = 0;
            while (cont < activas.size() && disponible) {
                r = activas.get(cont);
                if (r.getHabitacion().getNumHabitacion() == nroHab && r.getFechaInicio().isAfter(fInic)) {
                    if (fFin.isAfter(r.getFechaInicio())) {
                        disponible = false;
                    }
                }
                cont++;
            }
        }
        return disponible;
    }

    @Override
    public void alta (Scanner scan,GestorHotel miHotel) {
        Reserva reserva = new Reserva();
        ArrayList<Reserva> resAct = buscarReservasActivas();
        Boolean ocupada;
        int cont = 0;

        Menu.centradoIngreso("Ingrese Fecha de ingreso (yyyy-mm-dd): ");
        LocalDate fIngreso = LocalDate.parse(scan.nextLine());

        if (fIngreso.isBefore( LocalDate.now())){
            throw new FechaInvalidaException("Fecha invalida");
        }

        Menu.centradoIngreso("Ingrese Fecha de egreso (yyyy-mm-dd): ");
        LocalDate fEgreso = LocalDate.parse(scan.nextLine());
        if (fEgreso.isBefore(fIngreso)){
            throw new FechaInvalidaException("Fecha invalida");
        }

        ArrayList<Habitacion> disponibles = new ArrayList<>();
        for (Habitacion h : miHotel.getHabitacion()){
            if(reservaDisponible(fIngreso,fEgreso,h.getNumHabitacion()) && h.getEstadoHabitacion().equals(Estado_Habitacion.LIBRE)){
                disponibles.add(h);
            }
        }

        miHotel.mostrarHabitaciones(disponibles);
        Menu.centradoIngreso("Ingrese numero de Habitación deseada: ");
        int nroHab = scan.nextInt();
        scan.nextLine();

        Habitacion hab = new Habitacion();
        ArrayList <Habitacion> habis = miHotel.getHabitacion();
        hab.buscarPorNroHabitacion(nroHab, habis);
        ocupada = reservaSuperpuesta(fEgreso, nroHab);

        if (fIngreso == LocalDate.now()) {
            if(hab.getEstadoHabitacion().equals(Estado_Habitacion.LIBRE) && ocupada == false) {
                reserva.setHabitacion(hab);
                reserva.setFechaFin(fIngreso);
                reserva.setFechaFin(fEgreso);

                ArrayList<Pasajero> pasas = miHotel.getPasajeros();
                ArrayList<Persona> pers = new ArrayList<>();
                pers.addAll(pasas);

                Menu.centradoIngreso("Ingrese DNI pasajero: ");
                Pasajero pas = pasas.get(Pasajero.buscarPorDni(scan.nextLine(),pers));
                reserva.setPasajero(pas);


                ArrayList<Empleado> empls = miHotel.getEmpleados();
                Empleado empl = new Empleado();
                Menu.centradoIngreso("Ingrese Legajo del empleado: ");
                empl.buscarEmpleadoXLegajo(scan.nextInt(), empls);
                scan.nextLine();
                reserva.setEmpleado(empl);

                reserva.setEstadoReserva(Estado_Reserva.CONFIRMADO);


            } else {
                Menu.centradoOpciones("La habitación no se encuentra disponible");
            }

        } else {
            Boolean disponible = reservaDisponible(fIngreso, fEgreso, nroHab);

            if (disponible) {
                reserva.setHabitacion(hab);
                reserva.setFechaFin(fIngreso);
                reserva.setFechaFin(fEgreso);

                ArrayList<Pasajero> pasas = miHotel.getPasajeros();
                ArrayList<Persona> pers = new ArrayList<>();
                pers.addAll(pasas);

                Menu.centradoIngreso("Ingrese DNI pasajero: ");
                Pasajero pas = pasas.get(Persona.buscarPorDni(scan.nextLine(),pers));
                reserva.setPasajero(pas);


                ArrayList<Empleado> empls = miHotel.getEmpleados();
                Empleado empl = new Empleado();
                Menu.centradoIngreso("Ingrese Legajo del empleado: ");
                empl.buscarEmpleadoXLegajo(scan.nextInt(), empls);
                scan.nextLine();
                reserva.setEmpleado(empl);

                reserva.setEstadoReserva(Estado_Reserva.RESERVADO);
            } else {

                Menu.centradoOpciones("La habitación no se encuentra disponible");

            }

        }

    }


    @Override
    public void baja(Scanner scan) {
        Menu.centradoIngreso("Ingrese número de reserva");
        cancelarReserva(scan.nextInt());
    }

    @Override
    public void modificacion(Scanner scan) {
        char opcion = 'S';
        Menu.centradoIngreso("Ingrese el número de reserva: ");
        Reserva reserva = buscarUnaReserva(scan.nextInt());
        scan.nextLine();
        Habitacion hab = new Habitacion();
        ArrayList <Habitacion> habis = new Hotel().getHabitaciones();


        reserva.mostrarUnaReserva();

        Menu.centradoIngreso("Ingrese el campo a modificar");

        while (opcion == 'S') {
            Menu.centradoIngreso("Igrese el campo a modificar:");
            String campo = scan.nextLine();

            if (campo.equalsIgnoreCase("habitacion") || campo.equalsIgnoreCase("habitación")) {
                Menu.centradoIngreso("Ingrese nueva habitación: ");
                int nroHab = scan.nextInt();
                scan.nextLine();
                if(reservaDisponible(reserva.getFechaInicio(), reserva.getFechaFin(), nroHab)) {
                    hab.buscarPorNroHabitacion(nroHab, habis);
                    reserva.setHabitacion(hab);
                } else {
                    Menu.centradoOpciones("La habitación no se encuentra disponible");
                }

            } else if (campo.equalsIgnoreCase("ingreso")) {
                Menu.centradoIngreso("Ingrese la nueva fecha de ingreso:");
                LocalDate fInic = LocalDate.parse(scan.nextLine());
                if(reservaDisponible(fInic, reserva.getFechaFin(), reserva.getHabitacion().getNumHabitacion())) {
                    reserva.setFechaInicio(fInic);
                } else {
                    Menu.centradoOpciones("La habitación no se encuentra disponible");
                }

            } else if (campo.equalsIgnoreCase("egreso")) {
                Menu.centradoIngreso("Ingrese la nueva fecha de egreso:");
                LocalDate fFin = LocalDate.parse(scan.nextLine());
                if (reservaDisponible(reserva.getFechaInicio(), fFin, reserva.getHabitacion().getNumHabitacion())) {
                    reserva.setFechaFin(fFin);
                } else {
                    Menu.centradoOpciones("La habitación no se encuentra disponible");
                }
            } else {
                Menu.centradoOpciones("La opción ingresada es inválida o no se puede modificar");
            }

            Menu.centradoIngreso("Desea modificar otro dato S/N:");
            opcion = scan.nextLine().toUpperCase().charAt(0);

        }


    }

    public Reserva buscaReservaPorDni(String dni){

        for (Reserva reserva:reservas){
            if(reserva.getPasajero().getDni().equals(dni)){
                return reserva;
            }
        }
        return null;
    }

}
