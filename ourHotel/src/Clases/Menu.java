package Clases;

import Excepciones.HabitacionInvalidaException;
import Excepciones.UsuarioYClaveIncorrectoException;

import Gestores.GestorEmpleado;
import Gestores.GestorHotel;
import Gestores.GestorPasajero;
import Gestores.GestorReserva;
import Enum.Tipo_Usuario;
import manejoJSON.GestorJson;
import org.json.JSONException;
import Enum.Servicio_Habitacion;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

    //menu

    public static void menuPrincipal (Scanner scan,  GestorHotel miHotel, GestorReserva misReservas) throws JSONException, UsuarioYClaveIncorrectoException {

        int opc = 0;
        imprimirMenu();
        opc = elegirOpcion(scan);

        switch (opc) {

            case 1:
                //Empleado
                String usuario = ingresoUsuarioYClave(scan, "Usuario");
                String contra = ingresoUsuarioYClave(scan, "Contrasenia");

                for (int i = 0; i< miHotel.getEmpleados().size(); i++){

                    Persona e = new Empleado();

                    e = miHotel.getEmpleados().get(i);

                    try {

                        if (e.getUsuario().equals(usuario) && e.getContrasenia().equals(contra)){

                            if (e.getRol().equals(Tipo_Usuario.RECEPCIONISTA)) {

                                menuRecepcionista(scan,usuario,misReservas,miHotel);
                            } else {
                                menuAdmin(scan, miHotel,usuario,misReservas);
                            }
                        }
                    } catch (Exception ex) {
                        throw new UsuarioYClaveIncorrectoException("Usuario y/o Contrasenia Incorrecta");
                    }
                }
                break;

            case 2:
                //Pasajero
                // Tiene 3 reintentos al momento de ingresar
                int flag = 0;
                String dni = "";
                do {
                   usuario = ingresoUsuarioYClave(scan, "Usuario");
                   contra = ingresoUsuarioYClave(scan, "Contrasenia");

                    for (int i = 0; i< miHotel.getPasajeros().size(); i++){

                        Persona p = new Pasajero ();
                        p = miHotel.getPasajeros().get(i);

                        try {

                            if(p.getUsuario().equals(usuario) && p.getContrasenia().equals(contra)){
                                dni = p.getDni();

                               menuPasajero(scan,usuario,miHotel,dni,misReservas);
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
                //Persistencia de informacion.
                encabezadoMenu("Se estan guardando los cambios");
                miHotel.actualizarHotel();
                GestorJson.toJsonHotel(miHotel.getMiHotel());
                GestorJson.toJsonReservas(misReservas.getReservas());
                break;

            default:
        }


    }

    //Sub Menu para recepcionista
    public static void menuRecepcionista (Scanner scan, String usuario, GestorReserva misReservas, GestorHotel miHotel) throws JSONException {


        imprimirMenuRecepcion();
        int opc = elegirOpcion(scan);

        switch (opc){

            case 1:
                menuAbmPasajero (scan,usuario,misReservas,miHotel);
                break;
            case  2:
                MenuAbmReserva(scan,usuario,misReservas,miHotel);
                break;

            case 3:
                //Hacer check in
                encabezadoMenu("Check in");
                centradoOpciones("Ingrese el numero de reserva");
                int numReserva = elegirOpcion(scan);

                try {
                    miHotel.hacerCheckIn(numReserva,misReservas);
                    menuRecepcionista(scan,usuario,misReservas,miHotel);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                break;
            case 4:
                //hacer check out
                encabezadoMenu("Check out");
                centradoIngreso("Ingrese el dni del pasajero ");
                String dni = scan.nextLine();

                centradoIngreso("Ingrese el numero de Habitacion ");
                int numHabitacion = scan.nextInt();
                scan.nextLine();

                miHotel.hacerCheckOut(numHabitacion,dni,misReservas);
                menuRecepcionista(scan,usuario,misReservas,miHotel);
            case 5:
                menuHabitacion(scan,usuario,misReservas,miHotel);

                break;
            case 0:
                menuPrincipal(scan, miHotel,misReservas);

                break;
            default:
        }       centradoOpciones("Opcion invalida");
    }
    public static void menuAbmPasajero (Scanner scan,String usuario, GestorReserva misReservas, GestorHotel miHotel) throws JSONException {
        imprimirAbmPasajero();
        int opc = elegirOpcion(scan);

        switch (opc){
            case 1:
                //alta pasajero
                miHotel.altaPasajero(scan);
                menuAbmPasajero(scan,usuario,misReservas,miHotel);
                break;
            case 2:
                //baja pasajero
                miHotel.bajaPasajero(scan);
                menuAbmPasajero(scan,usuario,misReservas,miHotel);
                break;
            case 3:
                //modificar datos pasajero
                miHotel.modificarPasajero(scan);

                menuAbmPasajero(scan,usuario,misReservas,miHotel);
                break;
            case 4:
                //Ver datos de un pasajero
                centradoIngreso("Ingrese el Dni del pasajero: ");
                String dni = scan.nextLine();
                GestorPasajero.mostrarPasajero(miHotel.buscarPasajeroPorDni(dni));
                menuAbmPasajero(scan,usuario,misReservas,miHotel);
                break;
            case 0:
                //volver atras
                menuRecepcionista(scan,usuario,misReservas,miHotel);
                break;

            default:
                centradoOpciones("Opcion invalida");
        }
    }
    public static void menuHabitacion (Scanner scan,String usuario,GestorReserva misReservas, GestorHotel miHotel) throws JSONException {


        imprimirMenuHabitacion();
        int opc = elegirOpcion(scan);

        switch (opc){

            case 1:
                //buscar una habitacion
                encabezadoMenu("Buscar una habitacion");
                centradoIngreso("Ingrese el numero de habitacion: ");
                int numHabitacion = scan.nextInt();
                scan.nextLine();

                miHotel.buscarHabitacion(numHabitacion);
                menuHabitacion(scan,usuario,misReservas,miHotel);
                break;
            case 2:
                //buscar segun estado libre.
                encabezadoMenu("Habitaciones Disponibles");

                ArrayList<Habitacion>habitacionesLibres = miHotel.buscarHabitacionesLibres();

                for(int i = 0; i<habitacionesLibres.size(); i++){

                    Habitacion hL = habitacionesLibres.get(i);
                    hL.mostrarHabitacion();
                }
                menuHabitacion(scan,usuario,misReservas,miHotel);
                break;
            case 3:
                //buscar segun estado ocupado.
                encabezadoMenu("Habitaciones Ocupadas");

                ArrayList<Habitacion>habitacionesOcupadas = miHotel.buscarHabitacionesOcupadas();

                for (int i = 0; i<habitacionesOcupadas.size(); i++){
                    Habitacion hO = habitacionesOcupadas.get(i);
                    hO.mostrarHabitacion();
                }
                menuHabitacion(scan,usuario,misReservas,miHotel);
                break;
            case 4:
                //modificar una habitacion.
                miHotel.modificarHabitacion(scan);

                menuHabitacion(scan,usuario,misReservas,miHotel);
                break;
            case 0:
                //volver atras
                menuRecepcionista(scan,usuario,misReservas,miHotel);
                break;
            default:
        }       centradoOpciones("Opcion invalida");

    }
    public static void MenuAbmReserva (Scanner scan, String usuario, GestorReserva misReservas,GestorHotel miHotel) throws JSONException {

        imprimirAbmReserva();
        int opc = elegirOpcion(scan);

        switch (opc){

            case 1:
                //nueva reserva (alta)
                encabezadoMenu("Nueva Reserva");

                MenuAbmReserva(scan,usuario,misReservas,miHotel);
                break;
            case 2:
                //cancelar reserva (baja)
                encabezadoMenu("Cancelar Reserva");

                MenuAbmReserva(scan,usuario,misReservas,miHotel);
                break;
            case 3:
                //modificar una reserva
                encabezadoMenu("Modificar Reserva");

                MenuAbmReserva(scan,usuario,misReservas,miHotel);
                break;
            case 4:
                //ver una reserva
                encabezadoMenu("Ver Reserva");
                centradoIngreso("Ingrese Numero de reserva ");
                int numReserva = scan.nextInt();
                scan.nextLine();
                Reserva reserva = misReservas.buscarUnaReserva(numReserva);

                MenuAbmReserva(scan,usuario,misReservas,miHotel);
                break;
            case 5:
                //ver todas las reservas disponibles
                encabezadoMenu("Todas las Reservas");

                ArrayList<Reserva>reservasActivas =  misReservas.buscarReservasActivas();

                misReservas.mostrarReservas(reservasActivas);
                MenuAbmReserva(scan,usuario,misReservas,miHotel);

                break;
            case 0:
                menuRecepcionista(scan,usuario,misReservas,miHotel);
                break;
            default:
                centradoOpciones("Opcion invalida");
        }
    }
    // --

    //Sub Menu para administrador
    public static void menuAdmin (Scanner scan, GestorHotel miHotel,String usuario, GestorReserva misReservas) throws JSONException {
        imprimirMenuAdmin ();
        int opc = elegirOpcion(scan);

        switch (opc){

            case 1:
                //abm empleado
                menuAbmEmpleado (scan, miHotel,usuario,misReservas);

                break;
            case 2:
                //abm habitacion
                menuAbmHabitacion (scan, miHotel,usuario,misReservas);
                break;
            case 3:
                menuRecepcionista(scan,usuario,misReservas,miHotel);
                break;
            case 4:
                //back up
                //Llama funcion para persistencia de datos
                encabezadoMenu("Back up");

                miHotel.actualizarHotel();
                GestorJson.toJsonHotel(miHotel.getMiHotel());
                // falta agregar carga a reserva

                menuAdmin(scan, miHotel, usuario,misReservas);
                break;
            case 0:
                //volver atras
                menuPrincipal(scan, miHotel,misReservas);
                break;
            default:
                centradoOpciones("Opcion invalida");

        }
    }
    public static void menuAbmEmpleado (Scanner scan, GestorHotel miHotel, String usuario,GestorReserva misReservas) throws JSONException {
        imprimirAbmEmpleado();
        int opc = elegirOpcion(scan);

        switch (opc){

            case 1:
                //alta nuevo empleado
                miHotel.altaEmpleado(scan);

                menuAbmEmpleado(scan, miHotel,usuario,misReservas);
                break;
            case 2:
                //baja de un empleado
                miHotel.bajaEmpleado(scan);

                menuAbmEmpleado(scan, miHotel,usuario,misReservas);
                break;
            case 3:
                //modificar datos de un empleado
                miHotel.modificacionEmpleado(scan);

                menuAbmEmpleado(scan, miHotel,usuario,misReservas);
                break;
            case 4:
                //Ver datos de un empleado
                centradoIngreso("Ingrese Numero de legajo: ");
                int numId = scan.nextInt();
                scan.nextLine();
                miHotel.mostrarEmpleado(miHotel.buscarEmpleadoXLegajo(numId));
                menuAbmEmpleado(scan, miHotel, usuario, misReservas);
                break;
            case 0:
                //volver atras
                menuAdmin(scan, miHotel,usuario,misReservas);
                break;

            default:
                centradoOpciones("Opcion invalida");

        }
    }
    public static void menuAbmHabitacion (Scanner scan, GestorHotel miHotel,String usuario,GestorReserva misReservas) throws JSONException {
        imprimirAbmHabitacion();
        int opc = elegirOpcion(scan);

        switch (opc){

            case 1:
                //alta nueva habitacion
                miHotel.altaHabitacion(scan);

                menuAbmHabitacion(scan, miHotel,usuario,misReservas);
                break;
            case 2:
                //baja de habitacion
                miHotel.bajaHabitacion(scan);

                menuAbmHabitacion(scan, miHotel,usuario,misReservas);
                break;
            case 3:
                //modificar una habitacion.
                miHotel.modificarHabitacion(scan);

                menuAbmHabitacion(scan, miHotel,usuario,misReservas);
                break;
            case 0:
                menuAdmin(scan, miHotel,usuario,misReservas);
                break;
            default:
                centradoOpciones("Opcion invalida");
        }

    }
    // --

    //Sub Menu para pasajero
    public static void menuPasajero (Scanner scan, String usuario, GestorHotel mihotel, String dni, GestorReserva misReservas) throws JSONException {

        imprimirMenuPasajero();
        int opc = elegirOpcion(scan);

        switch (opc){

            case 1:
                encabezadoMenu("Datos Personales");
                GestorPasajero.mostrarPasajero(mihotel.buscarPasajero(usuario));

                menuPasajero(scan, usuario, mihotel,dni,misReservas);
                break;
            case 2:
                //reservas
                menuReserva(scan,usuario,mihotel,misReservas);

                break;
            case 3:
                //Servicios extra
                menuServiciosExtras(scan,usuario,mihotel,dni,misReservas);

                break;
            case 0:
                //Volver atras
                menuPrincipal(scan, mihotel,misReservas);
                break;
            default:
                centradoOpciones("Opcion invalida");
        }
    }
    public static void menuReserva (Scanner scan,String usuario, GestorHotel miHotel, GestorReserva misReservas) throws JSONException {
        imprimirMenuReserva();
        int opc = elegirOpcion(scan);
        String dni = "";

        switch (opc){

            case 1:
                //hacer reserva
                menuReserva(scan, usuario, miHotel,misReservas);
                break;
            case 2:

                //cancelar reserva
                encabezadoMenu("Cancelacion de Reserva");
                centradoIngreso("Ingrese el numero de reserva ");
                int numReserva = scan.nextInt();
                scan.nextLine();
                misReservas.cancelarReserva(numReserva);

                menuReserva(scan, usuario, miHotel,misReservas);
                break;
            case 3:
                //ver reservas activas
                encabezadoMenu("Reservas Activas");

                for (int i = 0; i<miHotel.getPasajeros().size(); i++){
                    Pasajero p = miHotel.getPasajeros().get(i);
                    
                    if(p.getUsuario().equals(usuario)){
                       dni = p.getDni(); 
                    }
                }
                ArrayList<Reserva>reservasActivas =  misReservas.buscarReservasActiva(dni);

                misReservas.mostrarReservas(reservasActivas);

                menuReserva(scan, usuario,  miHotel,misReservas);
                break;
            case 4:
                //ver historico
                encabezadoMenu("Reservas Finalizadas");

                for (int i = 0; i<miHotel.getPasajeros().size(); i++){
                    Pasajero p = miHotel.getPasajeros().get(i);

                    if(p.getUsuario().equals(usuario)){
                        dni = p.getDni();
                    }
                }
                ArrayList<Reserva>reservasFinalizadas = misReservas.buscarReservasHistoricas(dni);
                misReservas.mostrarReservas(reservasFinalizadas);

                menuReserva(scan, usuario, miHotel,misReservas);
                break;
            case 0:
                menuPasajero(scan,usuario,miHotel,dni,misReservas);
                break;
            default:
                centradoOpciones("Opcion invalida");
        }
    }
    public static void menuServiciosExtras (Scanner scan,String usuario, GestorHotel miHotel,String dni,GestorReserva misReservas) throws JSONException {
        imprimirMenuServExtras();
        int opc = elegirOpcion(scan);

        switch (opc){

            case 1:
                //Actividades disponibles.
                menuActividades(scan,usuario,miHotel,dni,misReservas);
                break;
            case 2:
                //Servicio a la habitacion
                menuServHabitacion(scan,usuario,miHotel,dni,misReservas);
                break;
            case 0:
                menuPasajero(scan,usuario,miHotel,dni,misReservas);
                break;
            default:
                centradoOpciones("Opcion invalida");
        }
    }
    public static void menuActividades(Scanner scan,String usuario,GestorHotel miHotel, String dni, GestorReserva misReservas) throws JSONException {

        imprimirMenuActividades();
        int opc = elegirOpcion(scan);

        switch (opc){
            case 1:
                try {
                    //masajes
                    encabezadoMenu("Masajes");

                    confirmacionServicio(scan,Servicio_Habitacion.MASAJE,dni,misReservas);

                    menuActividades(scan, usuario, miHotel,dni,misReservas);
                } catch (HabitacionInvalidaException e) {
                    centradoOpciones(e.getMessage());
                }
                break;
            case 2:
                //spa
                encabezadoMenu("Spa");
                confirmacionServicio(scan,Servicio_Habitacion.SPA,dni,misReservas);

                menuActividades(scan, usuario, miHotel,dni,misReservas);
                break;
            case 3:
                //sauna
                encabezadoMenu("Sauna");
                confirmacionServicio(scan,Servicio_Habitacion.SAUNA,dni,misReservas);

                menuActividades(scan, usuario, miHotel,dni,misReservas);
                break;
            case 4:
                //hidromasaje
                encabezadoMenu("Hidromasaje");
                confirmacionServicio(scan,Servicio_Habitacion.HIDROMASAJE,dni,misReservas);

                menuActividades(scan, usuario, miHotel,dni,misReservas);
                break;
            case 0:
                menuServiciosExtras(scan,usuario,miHotel,dni,misReservas);
                break;
            default:
                centradoOpciones("Opcion invalida");
        }
    }
    public static void menuServHabitacion (Scanner scan, String usuario, GestorHotel miHotel,String dni,GestorReserva misReservas) throws JSONException {

        imprimirMenuServHabitacion();
        int opc = elegirOpcion(scan);

        switch (opc){

            case 1:
                //Solicitar desayuno en la habitacion
                encabezadoMenu("Desayuno");
                confirmacionServicio(scan,Servicio_Habitacion.DESAYUNO,dni,misReservas);

                menuServHabitacion(scan, usuario, miHotel,dni,misReservas);
                break;
            case 2:
                //Solicitar almuerzo o cena en la habitacion.
                encabezadoMenu("Almuerzo-Cena");
                confirmacionServicio(scan,Servicio_Habitacion.ALMUERZO_CENA,dni,misReservas);

                menuServHabitacion(scan, usuario, miHotel,dni,misReservas);
                break;
            case 3:
                //Servicio de brindis
                encabezadoMenu("Servicio de Brindis");
                confirmacionServicio(scan,Servicio_Habitacion.SERVICIO_BRINDIS,dni,misReservas);

                menuServHabitacion(scan, usuario, miHotel,dni,misReservas);
                break;
            case 4:
                //bebida sin alcohol
                encabezadoMenu("Bebidas");
                confirmacionServicio(scan,Servicio_Habitacion.BEBIDA_SIN_ALCOHOL,dni, misReservas);

                menuServHabitacion(scan, usuario, miHotel,dni,misReservas);
                break;
            case 0:
                menuServiciosExtras(scan,usuario, miHotel,dni,misReservas);
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
        centradoOpciones("1. Alta Pasajero");
        centradoOpciones("2. Baja Pasajero");
        centradoOpciones("3. Modificacion datos Pasajero");
        centradoOpciones("4. Ver datos Pasajero");
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
        centradoOpciones("0. Volver Atras");
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
        centradoOpciones("0. Volver Atras");
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

    public static void confirmacionServicio (Scanner scan, Servicio_Habitacion servicioHabitacion, String dni, GestorReserva misReservas)  {

        Servicio servicio = new Servicio(servicioHabitacion, servicioHabitacion.getCosto());
        ArrayList<Servicio>servicios = new ArrayList<>();

        int confirmar = 0;

        centradoOpciones("Valor: $ " + servicioHabitacion.getCosto() + ".-");
        centradoOpciones("1. Confirmar");
        centradoOpciones("0. Rechazar");
        dibujarTerminacion();
        confirmar = elegirOpcion(scan);

        if(confirmar == 1){
           // servicios.add(servicio);
            Habitacion h = misReservas.buscarUnaHabitacionDni(dni);
            if (h != null){
                h.getServicios().add(servicio);
                h.ticketServicios();

            }else {
                throw new HabitacionInvalidaException("Habitacion no encontrada para el pasajero");
            }
        }
    }



}
