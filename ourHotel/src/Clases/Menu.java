package Clases;

import Excepciones.UsuarioYClaveIncorrectoException;
import Gestores.GestorHabitacion;
import Gestores.GestorHotel;
import Gestores.GestorPasajero;
import Gestores.GestorReserva;
import manejoJSON.GestorJson;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

    //menu

    public static void menuPrincipal (Scanner scan, ArrayList<Persona>personas, ArrayList<Pasajero>pasajeros){

        int opc = 0;
        imprimirMenu();
        opc = elegirOpcion(scan);

        switch (opc) {

            case 1:
                //Empleado
                String usuario = ingresoUsuarioYClave(scan, "Usuario");
                String contra = ingresoUsuarioYClave(scan, "Contrasenia");

//                for (int i = 0; i< personas.size(); i++){
//                    Persona e = new Empleado();
//                    e = personas.get(i);
//
//                    try {
//
//                        if (e.getUsuario().equals(usuario) && e.getContrasenia().equals(contra)){
//
//                            if (e.getRol().equals("RECEPCIONISTA")) {
//
                                menuRecepcionista(scan, personas,pasajeros);
//                            } else {
                                menuAdmin(scan, personas,pasajeros);
//                            }
//                        }
//                    } catch (Exception ex) {
//                        throw new UsuarioYClaveIncorrectoException("Usuario y/o Contrasenia Incorrecta");
//                    }
//                }
                break;

            case 2:
                //Pasajero
                // Tiene 3 reintentos al momento de ingresar
                int flag = 0;

                do {
                   usuario = ingresoUsuarioYClave(scan, "Usuario");
                    contra = ingresoUsuarioYClave(scan, "Contrasenia");

                    for (int i = 0; i<personas.size(); i++){

                        Persona p = new Pasajero ();
                        p = personas.get(i);
                        try {

                            if(p.getUsuario().equals(usuario) && p.getContrasenia().equals(contra)){

                               menuPasajero(scan, personas, usuario,pasajeros);
                                flag = 4;
                            }
                        } catch (Exception e) {
                            throw new UsuarioYClaveIncorrectoException("Usuario y/o Contrasenia Incorrecta");
                        }
                    }
                    flag ++;
                    if (flag < 3){
                        centradoOpciones("\n");
                        centradoOpciones("Ingrese nuevamente");
                    }
                }while (flag < 3);

                break;

            case 0:
                //Salir
                //Deberia hacer el llamado a cargar el json para persistencia de informacion.
                Hotel miHotel = new Hotel();

                GestorJson.toJsonHotel(miHotel);
                break;

            default:
        }


    }

    //Sub Menu para recepcionista
    public static void menuRecepcionista (Scanner scan, ArrayList<Persona>personas,ArrayList<Pasajero>pasajeros){

        imprimirMenuRecepcion();
         int opc = elegirOpcion(scan);

        switch (opc){

            case 1:
                menuAbmPasajero (scan, personas,pasajeros);
                break;
            case  2:
                MenuAbmReserva(scan,personas,pasajeros);
                break;

            case 3:
                //Hacer check in
                encabezadoMenu("Check in");
                centradoOpciones("Ingrese el numero de reserva");
                int numReserva = elegirOpcion(scan);
                GestorHotel.hacerCheckIn(numReserva);

                break;
            case 4:
                //hacer check out
                encabezadoMenu("Check out");
                centradoIngreso("Ingrese el dni del pasajero ");
                String dni = scan.nextLine();

                centradoIngreso("Ingrese el numero de Habitacion ");
                int numHabitacion = scan.nextInt();
                scan.nextLine();

                GestorHotel.hacerCheckOut(numHabitacion,dni);

            case 5:
                menuHabitacion(scan,personas,pasajeros);

                break;
            case 0:
                menuPrincipal(scan, personas,pasajeros);

                break;
            default:
        }       centradoOpciones("Opcion invalida");
    }
    public static void menuAbmPasajero (Scanner scan, ArrayList<Persona>personas, ArrayList<Pasajero>pasajeros){
        imprimirAbmPasajero();
        int opc = elegirOpcion(scan);

        switch (opc){
            case 1:
                //alta pasajero

                break;
            case 2:
                //baja pasajero

                break;
            case 3:
                //modificar datos pasajero
                centradoOpciones("Ingrese opcion a modificar");
                // case para datos


                break;
            case 0:
                //volver atras
                menuRecepcionista(scan, personas,pasajeros);
                break;

            default:
                centradoOpciones("Opcion invalida");
        }
    }
    public static void menuHabitacion (Scanner scan,ArrayList<Persona>personas,ArrayList<Pasajero>pasajeros){
        imprimirMenuHabitacion();
        int opc = elegirOpcion(scan);

        switch (opc){

            case 1:
                //buscar una habitacion
                encabezadoMenu("Buscar una habitacion");
                centradoIngreso("Ingrese el numero de habitacion: ");
                int numHabitacion = scan.nextInt();
                scan.nextLine();

                Habitacion habitacion = GestorHabitacion.buscaHabitacion(numHabitacion);
                habitacion.mostrarHabitacion();

                break;
            case 2:
                //buscar segun estado libre.
                encabezadoMenu("Habitaciones Disponibles");

                ArrayList<Habitacion>habitacionesLibres = GestorHabitacion.buscaHabitacionLibre();
                for(int i = 0; i<habitacionesLibres.size(); i++){
                    Habitacion hL = habitacionesLibres.get(i);
                    hL.mostrarHabitacion();
                }

                break;
            case 3:
                //buscar segun estado ocupado.
                encabezadoMenu("Habitaciones Ocupadas");

                ArrayList<Habitacion>habitacionesOcupadas = GestorHabitacion.buscarHabitacionesOcupadas();
                for (int i = 0; i<habitacionesOcupadas.size(); i++){
                    Habitacion hO = habitacionesOcupadas.get(i);
                    hO.mostrarHabitacion();
                }

                break;
            case 4:
                //modificar una habitacion.

                break;
            case 0:
                //volver atras
                menuRecepcionista(scan,personas,pasajeros);
                break;
            default:
        }       centradoOpciones("Opcion invalida");

    }
    public static void MenuAbmReserva (Scanner scan,ArrayList<Persona>personas,ArrayList<Pasajero>pasajeros){

        imprimirAbmReserva();
        int opc = elegirOpcion(scan);

        switch (opc){

            case 1:
                //nueva reserva (alta)
                encabezadoMenu("Nueva Reserva");
                break;
            case 2:
                //cancelar reserva (baja)
                encabezadoMenu("Cancelar Reserva");
                break;
            case 3:
                //modificar una reserva
                encabezadoMenu("Modificar Reserva");
                break;
            case 4:
                //ver una reserva
                encabezadoMenu("Ver Reserva");
                break;
            case 5:
                //ver todas las reservas disponibles
                encabezadoMenu("Todas las Reservas");

                break;
            case 0:
                menuRecepcionista(scan,personas,pasajeros);
                break;
            default:
                centradoOpciones("Opcion invalida");
        }
    }
    // --

    //Sub Menu para administrador
    public static void menuAdmin (Scanner scan,ArrayList<Persona>personas,ArrayList<Pasajero>pasajeros){
        imprimirMenuAdmin ();
        int opc = elegirOpcion(scan);

        switch (opc){

            case 1:
                //abm empleado
                menuAbmEmpleado (scan,personas,pasajeros);

                break;
            case 2:
                //abm habitacion
                menuAbmHabitacion (scan,personas,pasajeros);
                break;
            case 3:
                menuRecepcionista(scan,personas,pasajeros);
                break;
            case 4:
                //back up
                break;
            case 0:
                //volver atras
                menuPrincipal(scan,personas,pasajeros);
                break;
            default:
                centradoOpciones("Opcion invalida");

        }
    }
    public static void menuAbmEmpleado (Scanner scan,ArrayList<Persona>personas,ArrayList<Pasajero>pasajeros){
        imprimirAbmEmpleado();
        int opc = elegirOpcion(scan);

        switch (opc){

            case 1:
                //alta nuevo empleado
                break;
            case 2:
                //baja de un empleado
                break;
            case 3:
                //modificar datos de un empleado
                break;
            case 0:
                //volver atras
                menuAdmin(scan,personas,pasajeros);
                break;

            default:
                centradoOpciones("Opcion invalida");

        }
    }
    public static void menuAbmHabitacion (Scanner scan,ArrayList<Persona>personas,ArrayList<Pasajero>pasajeros){
        imprimirAbmHabitacion();
        int opc = elegirOpcion(scan);

        switch (opc){

            case 1:
                //alta nueva habitacion
                break;
            case 2:
                //baja de habitacion
                break;
            case 3:
                //modificar una habitacion.
                break;
            case 0:
                menuAdmin(scan,personas,pasajeros);
                break;
            default:
                centradoOpciones("Opcion invalida");
        }

    }
    // --

    //Sub Menu para pasajero
    public static void menuPasajero (Scanner scan,ArrayList<Persona>personas, String usuario, ArrayList<Pasajero>pasajeros){

        imprimirMenuPasajero();
        int opc = elegirOpcion(scan);

        switch (opc){

            case 1:
                encabezadoMenu("Datos Personales");
                GestorPasajero.mostrarPasajero(GestorPasajero.buscarPasajero(usuario,pasajeros));

                break;
            case 2:
                //reservas
                menuReserva(scan,personas,usuario,pasajeros);

                break;
            case 3:
                //Servicios extra
                menuServiciosExtras(scan,personas,usuario,pasajeros);

                break;
            case 0:
                //Volver atras
                menuPrincipal(scan,personas,pasajeros);
                break;
            default:
                centradoOpciones("Opcion invalida");
        }
    }
    public static void menuReserva (Scanner scan,ArrayList<Persona>personas,String usuario, ArrayList<Pasajero>pasajeros){
        imprimirMenuReserva();
        int opc = elegirOpcion(scan);

        switch (opc){

            case 1:
                //hacer reserva

                break;
            case 2:

                //cancelar reserva
                encabezadoMenu("Cancelacion de Reserva");
                centradoIngreso("Ingrese el numero de reserva");
                int numReserva = scan.nextInt();
                scan.nextLine();
                GestorReserva.cancelarReserva(numReserva);

                break;
            case 3:
                //ver reservas activas
                encabezadoMenu("Reservas Activas");

                ArrayList<Reserva>reservasActivas =  GestorReserva.buscarReservasActiva();

                GestorReserva.mostrarReservas(reservasActivas);

                menuReserva(scan, personas, usuario, pasajeros);
                break;
            case 4:
                //ver historico
                encabezadoMenu("Reservas Finalizadas");

                ArrayList<Reserva>reservasFinalizadas = GestorReserva.buscarReservasHistoricas();
                GestorReserva.mostrarReservas(reservasFinalizadas);
                menuReserva(scan, personas, usuario, pasajeros);
                break;
            case 0:
                menuPasajero(scan,personas,usuario,pasajeros);
                break;
            default:
                centradoOpciones("Opcion invalida");
        }
    }
    public static void menuServiciosExtras (Scanner scan,ArrayList<Persona>personas,String usuario, ArrayList<Pasajero>pasajeros){
        imprimirMenuServExtras();
        int opc = elegirOpcion(scan);

        switch (opc){

            case 1:
                //Actividades disponibles.
                menuActividades(scan,personas,usuario,pasajeros);
                break;
            case 2:
                //Servicio a la habitacion
                menuServHabitacion(scan,personas,usuario,pasajeros);
                break;
            case 0:
                menuPasajero(scan,personas,usuario,pasajeros);
                break;
            default:
                centradoOpciones("Opcion invalida");
        }
    }
    public static void menuActividades(Scanner scan,ArrayList<Persona>personas,String usuario, ArrayList<Pasajero>pasajeros){

        imprimirMenuActividades();
        int opc = elegirOpcion(scan);

        switch (opc){
            case 1:
                //masajes
                encabezadoMenu("Masajes");
                confirmacionServicio(scan, personas,usuario,pasajeros);
                break;
            case 2:
                //spa
                encabezadoMenu("Spa");
                confirmacionServicio(scan, personas,usuario,pasajeros);
                break;
            case 3:
                //sauna
                encabezadoMenu("Sauna");
                confirmacionServicio(scan, personas,usuario,pasajeros);
                break;
            case 4:
                //hidromasaje
                encabezadoMenu("Hidromasaje");
                confirmacionServicio(scan, personas,usuario,pasajeros);
                break;
            case 0:
                menuServiciosExtras(scan, personas,usuario,pasajeros);
                break;
            default:
                centradoOpciones("Opcion invalida");
        }
    }
    public static void menuServHabitacion (Scanner scan,ArrayList<Persona>personas, String usuario, ArrayList<Pasajero>pasajeros){

        imprimirMenuServHabitacion();
        int opc = elegirOpcion(scan);

        switch (opc){

            case 1:
                //Solicitar desayuno en la habitacion
                encabezadoMenu("Desayuno");
                confirmacionServicio(scan, personas,usuario,pasajeros);
                break;
            case 2:
                //Solicitar almuerzo o cena en la habitacion.
                encabezadoMenu("Almuerzo-Cena");
                confirmacionServicio(scan, personas,usuario,pasajeros);
                break;
            case 3:
                //Servicio de brindis
                encabezadoMenu("Servicio de Brindis");
                confirmacionServicio(scan, personas,usuario,pasajeros);
                break;
            case 4:
                //bebida sin alcohol
                encabezadoMenu("Bebidas");
                confirmacionServicio(scan, personas,usuario,pasajeros);
                break;
            case 0:
                menuServiciosExtras(scan,personas,usuario, pasajeros);
                break;
            default:
                centradoOpciones("opcion invalida");

        }
    }
    // --


    //Impresiones de los menu
    public static void imprimirMenu (){

        dibujarTerminacion();
        encabezadoMenu("Menu Principal");
        centradoOpciones("1. Empleado");
        centradoOpciones("2. Pasajero");
        centradoOpciones("0. Salir");
        dibujarTerminacion();
    }
    public static void imprimirAbmPasajero (){

        dibujarTerminacion();
        encabezadoMenu("Administracion de Pasajeros");
        centradoOpciones("1. Alta");
        centradoOpciones("2. Baja");
        centradoOpciones("3. Modificacion");
        centradoOpciones("0. Volver Atras");
        dibujarTerminacion();
    }
    public static void imprimirMenuRecepcion(){

        dibujarTerminacion();
        encabezadoMenu("Menu Recepcion");
        centradoOpciones("1. Administracion de Pasajeros");
        centradoOpciones("2. Gestion de Reserva");
        centradoOpciones("3. Check In");
        centradoOpciones("4. Check Out");
        centradoOpciones("5. Gestion de Habitaciones");
        centradoOpciones("0. Volver Atras");
        dibujarTerminacion();
    }
    public static void imprimirMenuAdmin (){

        dibujarTerminacion();
        encabezadoMenu("Menu Administracion");
        centradoOpciones("1. Administracion de Empleados");
        centradoOpciones("2. Administracion de Habitaciones");
        centradoOpciones("3. Menu Recepcion");
        centradoOpciones("4. Backup");
        centradoOpciones("0. Volver Atras");
        dibujarTerminacion();
    }
    public static void imprimirAbmReserva (){

        dibujarTerminacion();
        encabezadoMenu("Reserva");
        centradoOpciones("1. Realizar Reserva");
        centradoOpciones("2. Cancelar Reserva");
        centradoOpciones("3. Modificar Reserva");
        centradoOpciones("4. Ver una Reserva");
        centradoOpciones("5. Ver todas Reservas");
        dibujarTerminacion();
    }
    public static void imprimirMenuHabitacion (){

        dibujarTerminacion();
        encabezadoMenu("Habitaciones");
        centradoOpciones("1. Ver una Habitacion");
        centradoOpciones("2. Ver Libres");
        centradoOpciones("3. Ver Ocupadas");
        centradoOpciones("4. Modificar una Habitacion");
        centradoOpciones("0. Volver Atras");
        dibujarTerminacion();
    }
    public static void imprimirAbmEmpleado (){

        dibujarTerminacion();
        encabezadoMenu("Administracion de Empleados");
        centradoOpciones("1. Alta Empleado");
        centradoOpciones("2. Baja Empleado");
        centradoOpciones("3. Modificacion Empleado");
        centradoIngreso("0. Volver Atras");
        dibujarTerminacion();
    }
    public static void imprimirAbmHabitacion (){

        dibujarTerminacion();
        encabezadoMenu("Administracion de Habitaciones");
        centradoOpciones("1. Alta Habitacion"); //habitacion nueva
        centradoOpciones("2. Baja Habitacion");  // momentaneamente o no dada de baja, por reformas por ejemplo
        centradoOpciones("3. Modificacion Habitacion"); //cambio de camas o categoria por ejemplo
        centradoOpciones("0. Volver Atras");
        dibujarTerminacion();
    }
    public static void imprimirMenuPasajero(){

        dibujarTerminacion();
        encabezadoMenu("Menu Pasajeros");
        centradoOpciones("1. Datos Personales");
        centradoOpciones("2. Reservas");
        centradoOpciones("3. Servicios Extras");
        centradoOpciones("0. Volver Atras");
        dibujarTerminacion();
    }
    public static void imprimirMenuReserva (){

        dibujarTerminacion();
        encabezadoMenu("Sistema Reservas");
        centradoOpciones("1. Realizar Reserva");
        centradoOpciones("2. Cancelar Reserva");
        centradoOpciones("3. Ver Reserva Activas");
        centradoOpciones("4. Historico de Reservas");
        centradoOpciones("0. Volver Atras");
        dibujarTerminacion();
    }
    public static void imprimirMenuServExtras (){

        dibujarTerminacion();
        encabezadoMenu("Servivios Extras");
        centradoOpciones("1. Actividades");
        centradoOpciones("2. Servicio a la Habitacion");
        centradoOpciones("0. Volver Atras");
        dibujarTerminacion();
    }
    public static void imprimirMenuActividades (){

        dibujarTerminacion();
        encabezadoMenu("Actividades Disponibles");
        centradoOpciones("1. Masajes");
        centradoOpciones("2. Spa");
        centradoOpciones("3. Sauna");
        centradoOpciones("4. Hidromasaje");
        centradoOpciones("0. Volver Atras");
        dibujarTerminacion();
    }
    public static void imprimirMenuServHabitacion(){

        dibujarTerminacion();
        encabezadoMenu("Servicio a la Habitacion");
        centradoOpciones("1. Desayuno");
        centradoOpciones("2. Almuerzo - Cena");
        centradoOpciones("3. Servicio de Brindis");
        centradoOpciones("4. Bebidas Sin Alcohol");
        centradoOpciones("0. Volver Atras");
        dibujarTerminacion();
    }
    // --


    //Visual y Centrado para la consola.
    public static int elegirOpcion(Scanner scan){
        int opc = 0;
        centradoIngreso("Ingrese una opcion - ");
        opc = scan.nextInt();
        scan.nextLine();
        return opc;
    }
    public static void dibujarTerminacion (){
        // Dibuja la línea superior o inferior
        for (int i = 0; i<10; i++){
            System.out.print("\t");
        }
        System.out.print("+");
        for (int i = 0; i < 100; i++) {
            System.out.print("-");
        }
        System.out.println("+");
    }
    public static void encabezadoMenu (String mensaje){
        for (int i = 0; i<20; i++){
            System.out.print("\t ");
        }
        System.out.println("--- " + mensaje + " ---");

    }
    public static void centradoOpciones (String opcionIngreso){
        for(int i = 0; i<21; i++){
            System.out.print("\t");
        }
        System.out.println(" " + opcionIngreso);
    }
    public static void centradoIngreso (String opcionIngreso){
        for (int i = 0; i<21; i++){
            System.out.print("\t");
        }
        System.out.print(" " + opcionIngreso );

    }
    // --



    public static String ingresoUsuarioYClave (Scanner scan, String mensaje){

        Menu.centradoIngreso(mensaje + " : ");
        return scan.nextLine();
    }

    public static void confirmacionServicio (Scanner scan, ArrayList<Persona>personas,String usuario,ArrayList<Pasajero>pasajeros){
        Servicio servicio = new Servicio();
        ArrayList<Servicio>servicios = new ArrayList<>();
        int confirmar = 0;

        centradoOpciones("Valor: $ " + servicio.getCosto() + ".-");
        centradoOpciones("1. Confirmar");
        centradoOpciones("0. Rechazar");
        dibujarTerminacion();
        confirmar = elegirOpcion(scan);

        if(confirmar == 1){
            servicios.add(servicio);
        }
        else {
            menuActividades(scan,personas,usuario,pasajeros);
        }
    }

}
