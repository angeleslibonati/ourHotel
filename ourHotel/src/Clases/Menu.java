package Clases;

import Excepciones.HabitacionInvalidaException;
import Excepciones.ReservaInvalidaException;
import Excepciones.UsuarioYClaveIncorrectoException;
import Gestores.GestorHotel;
import Gestores.GestorPasajero;
import Gestores.GestorReserva;
import Enum.Tipo_Usuario;
import manejoJSON.GestorJson;
import org.json.JSONException;
import Enum.Servicio_Habitacion;
import Enum.Estado_Empleado;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

    //menu

    public static void menuPrincipal (Scanner scan,  GestorHotel miHotel, GestorReserva misReservas) throws JSONException, UsuarioYClaveIncorrectoException {
        // Tiene 3 reintentos al momento de ingresar

        int opc = 0;
        imprimirMenu();
        opc = elegirOpcion(scan);

        switch (opc) {

            case 1:
                //Empleado
                int flag = 0;
                String usuario = "";
                String contra = "";

                do{

                    usuario = ingresoUsuarioYClave(scan, "Usuario");
                    contra = ingresoUsuarioYClave(scan, "Contrasenia");

                    for (int i = 0; i< miHotel.getEmpleados().size(); i++){

                        Empleado e = new Empleado();

                        e = miHotel.getEmpleados().get(i);

                        try {

                            if (e.getUsuario().equals(usuario) && e.getContrasenia().equals(contra) && e.getEstadoEmpleado().equals(Estado_Empleado.ACTIVO)){

                                if (e.getRol().equals(Tipo_Usuario.RECEPCIONISTA)) {

                                    menuRecepcionista(scan,usuario,misReservas,miHotel, false);
                                    flag = 4;
                                } else {
                                    menuAdmin(scan, miHotel,usuario,misReservas, false);
                                    flag = 4;
                                }
                            }
                        }
                        catch (Exception ex) {
                            centradoOpciones(ex.getMessage());
                        }
                    }
                    flag ++;
                    if (flag < 3){
                        centradoOpciones("Usuario y/o Contraseña incorrecta");
                        centradoOpciones("\n");
                        centradoOpciones("Ingrese nuevamente");
                    }
                }while (flag < 3);

                break;
            case 2:
                //Pasajero

                flag = 0;
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

                               menuPasajero(scan,usuario,miHotel,dni,misReservas,true);
                                flag = 4;
                            }
                        } catch (Exception e) {
                            centradoOpciones(e.getMessage());
                        }
                    }
                    flag ++;
                    if (flag < 3){
                        centradoOpciones("Usuario y/o Contraseña incorrecta");
                        centradoOpciones("\n");
                        centradoOpciones("Ingrese nuevamente");
                    }
                }while (flag < 3);

                break;

            case 0:
                    //salir
                break;

            default:
                centradoOpciones("Opcion invalida");

        }


    }

    //Sub Menu para recepcionista
    public static void menuRecepcionista (Scanner scan, String usuario, GestorReserva misReservas, GestorHotel miHotel,Boolean esPasajero) throws JSONException {

        imprimirMenuRecepcion();
        int opc = elegirOpcion(scan);

        switch (opc) {

            case 1:
                menuAbmPasajero(scan, usuario, misReservas, miHotel, esPasajero);
                break;
            case 2:
                MenuAbmReserva(scan, usuario, misReservas, miHotel, esPasajero);
                break;

            case 3:
                //Hacer check in
                encabezadoMenu("Check in");

                centradoOpciones("Ingrese el numero de reserva");
                try {
                    int numReserva = elegirOpcion(scan);
                    miHotel.hacerCheckIn(numReserva, misReservas);
                    menuRecepcionista(scan, usuario, misReservas, miHotel, esPasajero);

                } catch (ReservaInvalidaException e) {
                    dibujarTerminacion();
                    encabezadoMenu("Primero debe realizar una Reserva");

                    MenuAbmReserva(scan,usuario,misReservas,miHotel,esPasajero);

                } catch (ParseException f) {
                    centradoOpciones(f.getMessage());
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

                miHotel.hacerCheckOut(numHabitacion, dni, misReservas);
                menuRecepcionista(scan, usuario, misReservas, miHotel, esPasajero);
                break;
            case 5:
                menuHabitacion(scan, usuario, misReservas, miHotel, esPasajero);

                break;
            case 0:
                menuPrincipal(scan, miHotel, misReservas);

                break;
            default:
                centradoOpciones("Opcion invalida");
        }

    }
    public static void menuAbmPasajero (Scanner scan,String usuario, GestorReserva misReservas, GestorHotel miHotel,boolean esPasajero) throws JSONException {
        imprimirAbmPasajero();
        int opc = elegirOpcion(scan);

        switch (opc){
            case 1:
                //alta pasajero
                miHotel.altaPasajero(scan, miHotel,esPasajero);
                menuAbmPasajero(scan,usuario,misReservas,miHotel,esPasajero);
                break;
            case 2:
                //modificar datos pasajero
                miHotel.modificarPasajero(scan, miHotel);

                menuAbmPasajero(scan,usuario,misReservas,miHotel,esPasajero);
                break;
            case 3:
                //Ver datos de un pasajero
                centradoIngreso("Ingrese el Dni del pasajero: ");
                String dni = scan.nextLine();
                Pasajero pasajero = miHotel.buscarPasajeroPorDni(dni);

                if(pasajero == null){

                    centradoOpciones("Pasajero no registrado");
                }else{
                    pasajero.mostrarPasajero();
                }

                menuAbmPasajero(scan,usuario,misReservas,miHotel,esPasajero);
                break;
            case 0:
                //volver atras
                menuRecepcionista(scan,usuario,misReservas,miHotel,esPasajero);
                break;

            default:
                centradoOpciones("Opcion invalida");
        }
    }
    public static void menuHabitacion (Scanner scan,String usuario,GestorReserva misReservas, GestorHotel miHotel,boolean esPasajero) throws JSONException {


        imprimirMenuHabitacion();
        int opc = elegirOpcion(scan);

        switch (opc){

            case 1:
                //buscar una habitacion
                encabezadoMenu("Buscar una habitacion");
                centradoIngreso("Ingrese el numero de habitacion: ");
                int numHabitacion = scan.nextInt();
                scan.nextLine();

                Habitacion habitacion = miHotel.buscarHabitacion(numHabitacion);
                habitacion.mostrarHabitacion();

                menuHabitacion(scan,usuario,misReservas,miHotel,esPasajero);
                break;
            case 2:
                //buscar segun estado libre.
                encabezadoMenu("Habitaciones Disponibles");

                ArrayList<Habitacion>habitacionesLibres = miHotel.buscarHabitacionesLibres();

                for(int i = 0; i<habitacionesLibres.size(); i++){

                    Habitacion hL = habitacionesLibres.get(i);
                    hL.mostrarHabitacion();
                }
                menuHabitacion(scan,usuario,misReservas,miHotel,esPasajero);
                break;
            case 3:
                //buscar segun estado ocupado.
                encabezadoMenu("Habitaciones Ocupadas");

                ArrayList<Habitacion>habitacionesOcupadas = miHotel.buscarHabitacionesOcupadas();

                for (int i = 0; i<habitacionesOcupadas.size(); i++){
                    Habitacion hO = habitacionesOcupadas.get(i);
                    hO.mostrarHabitacion();
                }
                menuHabitacion(scan,usuario,misReservas,miHotel,esPasajero);
                break;
            case 4:
                //modificar una habitacion.
                miHotel.modificarHabitacion(scan, miHotel);

                menuHabitacion(scan,usuario,misReservas,miHotel,esPasajero);
                break;
            case 0:
                //volver atras
                menuRecepcionista(scan,usuario,misReservas,miHotel,esPasajero);
                break;
            default:
        }       centradoOpciones("Opcion invalida");

    }
    public static void MenuAbmReserva (Scanner scan, String usuario, GestorReserva misReservas,GestorHotel miHotel,boolean esPasajero) throws JSONException {

        imprimirAbmReserva();
        int opc = elegirOpcion(scan);

        switch (opc){

            case 1:
                //nueva reserva (alta)
                encabezadoMenu("Nueva Reserva");
                misReservas.alta(scan, miHotel,esPasajero);

                MenuAbmReserva(scan,usuario,misReservas,miHotel,esPasajero);
                break;
            case 2:
                //cancelar reserva (baja)
                encabezadoMenu("Cancelar Reserva");
                misReservas.baja(scan);

                MenuAbmReserva(scan,usuario,misReservas,miHotel,esPasajero);
                break;
            case 3:
                //modificar una reserva
                encabezadoMenu("Modificar Reserva");
                misReservas.modificacion(scan, miHotel);

                MenuAbmReserva(scan,usuario,misReservas,miHotel,esPasajero);
                break;
            case 4:
                //ver una reserva
                encabezadoMenu("Ver Reserva");
                centradoIngreso("Ingrese Numero de reserva ");
                int numReserva = scan.nextInt();
                scan.nextLine();
                Reserva reserva = misReservas.buscarUnaReserva(numReserva);

                MenuAbmReserva(scan,usuario,misReservas,miHotel,esPasajero);
                break;
            case 5:
                //ver todas las reservas disponibles
                encabezadoMenu("Todas las Reservas");

                ArrayList<Reserva>reservasActivas =  misReservas.buscarReservasActivas();

                misReservas.mostrarReservas(reservasActivas);
                MenuAbmReserva(scan,usuario,misReservas,miHotel,esPasajero);

                break;
            case 0:
                menuRecepcionista(scan,usuario,misReservas,miHotel,esPasajero);
                break;
            default:
                centradoOpciones("Opcion invalida");
        }
    }
    // --

    //Sub Menu para administrador
    public static void menuAdmin (Scanner scan, GestorHotel miHotel,String usuario, GestorReserva misReservas,boolean esPasajero) throws JSONException {
        imprimirMenuAdmin ();
        int opc = elegirOpcion(scan);

        switch (opc){

            case 1:
                //abm empleado
                menuAbmEmpleado (scan, miHotel,usuario,misReservas,esPasajero);
                break;
            case 2:
                //abm habitacion
                menuAbmHabitacion (scan, miHotel,usuario,misReservas,esPasajero);
                break;
            case 3:
                menuRecepcionista(scan,usuario,misReservas,miHotel,esPasajero);
                break;
            case 4:
                //back up
                //Llama funcion para persistencia de datos
                encabezadoMenu("Back up");
                miHotel.actualizarHotel();
                GestorJson.toJsonHotel(miHotel.getMiHotel());

                menuAdmin(scan, miHotel, usuario,misReservas,esPasajero);
                break;
            case 0:
                //volver atras
                menuPrincipal(scan, miHotel,misReservas);
                break;
            default:
                centradoOpciones("Opcion invalida");

        }
    }
    public static void menuAbmEmpleado (Scanner scan, GestorHotel miHotel, String usuario,GestorReserva misReservas,boolean esPasajero) throws JSONException {
        imprimirAbmEmpleado();
        int opc = elegirOpcion(scan);

        switch (opc){

            case 1:
                //alta nuevo empleado
                miHotel.altaEmpleado(scan, miHotel,esPasajero);
                menuAbmEmpleado(scan, miHotel,usuario,misReservas,esPasajero);
                break;
            case 2:
                //baja de un empleado
                miHotel.bajaEmpleado(scan);

                menuAbmEmpleado(scan, miHotel,usuario,misReservas,esPasajero);
                break;
            case 3:
                //modificar datos de un empleado
                miHotel.modificacionEmpleado(scan, miHotel);

                menuAbmEmpleado(scan, miHotel,usuario,misReservas,esPasajero);
                break;
            case 4:
                //Ver datos de un empleado
                centradoIngreso("Ingrese Numero de legajo: ");
                int numId = scan.nextInt();
                scan.nextLine();
                miHotel.mostrarEmpleado(miHotel.buscarEmpleadoXLegajo(numId));
                menuAbmEmpleado(scan, miHotel, usuario, misReservas,esPasajero);
                break;
            case 0:
                //volver atras
                menuAdmin(scan, miHotel,usuario,misReservas,esPasajero);
                break;

            default:
                centradoOpciones("Opcion invalida");

        }
    }
    public static void menuAbmHabitacion (Scanner scan, GestorHotel miHotel,String usuario,GestorReserva misReservas,boolean esPasajero) throws JSONException {
        imprimirAbmHabitacion();
        int opc = elegirOpcion(scan);

        switch (opc){

            case 1:
                //alta nueva habitacion
                miHotel.altaHabitacion(scan, miHotel,esPasajero);

                menuAbmHabitacion(scan, miHotel,usuario,misReservas,esPasajero);
                break;
            case 2:
                //baja de habitacion
                miHotel.bajaHabitacion(scan);

                menuAbmHabitacion(scan, miHotel,usuario,misReservas,esPasajero);
                break;
            case 3:
                //modificar una habitacion.
                miHotel.modificarHabitacion(scan, miHotel);

                menuAbmHabitacion(scan, miHotel,usuario,misReservas,esPasajero);
                break;
            case 0:
                menuAdmin(scan, miHotel,usuario,misReservas,esPasajero);
                break;
            default:
                centradoOpciones("Opcion invalida");
        }

    }
    // --

    //Sub Menu para pasajero
    public static void menuPasajero (Scanner scan, String usuario, GestorHotel mihotel, String dni, GestorReserva misReservas,boolean esPasajero) throws JSONException {

        imprimirMenuPasajero();
        int opc = elegirOpcion(scan);

        switch (opc){

            case 1:
                //Datos Personales
                subMenuDatosPersonales(scan,mihotel,usuario,dni,misReservas,esPasajero);

                break;
            case 2:
                //reservas
                menuReserva(scan,usuario,mihotel,misReservas,esPasajero);

                break;
            case 3:
                //Servicios extra
                menuServiciosExtras(scan,usuario,mihotel,dni,misReservas,esPasajero);

                break;
            case 0:
                //Volver atras
                menuPrincipal(scan, mihotel,misReservas);
                break;
            default:
                centradoOpciones("Opcion invalida");
        }
    }
    public static void subMenuDatosPersonales (Scanner scan, GestorHotel miHotel, String usuario, String dni, GestorReserva misReservas, boolean esPasajero){
        imprimirMenuDatosPersonales();
        int opc = elegirOpcion(scan);

        switch (opc){

            case 1:
                encabezadoMenu("Datos Pasajero");
                GestorPasajero.mostrarPasajero(miHotel.buscarPasajero(usuario));
                subMenuDatosPersonales(scan,miHotel,usuario,dni,misReservas,esPasajero);
                break;
            case 2:
                miHotel.modificarPasajero(scan, miHotel);
                subMenuDatosPersonales(scan, miHotel, usuario,dni,misReservas,esPasajero);
                break;
            case 0:
                try {
                    menuPasajero(scan,usuario,miHotel,dni,misReservas,esPasajero);
                } catch (JSONException e) {

                }
                break;
            default:
                centradoOpciones("Opcion invalida");
        }
    }
    public static void menuReserva (Scanner scan,String usuario, GestorHotel miHotel, GestorReserva misReservas,boolean esPasajero) throws JSONException {
        imprimirMenuReserva();
        int opc = elegirOpcion(scan);
        String dni = "";

        switch (opc){

            case 1:
                //hacer reserva
                encabezadoMenu("Realizar Reserva");
                misReservas.alta(scan,miHotel,esPasajero);
                menuReserva(scan, usuario, miHotel,misReservas,esPasajero);
                break;
            case 2:
                //cancelar reserva
                encabezadoMenu("Cancelacion de Reserva");
                centradoIngreso("Ingrese el numero de reserva ");
                int numReserva = scan.nextInt();
                scan.nextLine();
                misReservas.cancelarReserva(numReserva);

                menuReserva(scan, usuario, miHotel,misReservas,esPasajero);
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

                menuReserva(scan, usuario,  miHotel,misReservas,esPasajero);
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

                menuReserva(scan, usuario, miHotel,misReservas,esPasajero);
                break;
            case 0:
                menuPasajero(scan,usuario,miHotel,dni,misReservas,esPasajero);
                break;
            default:
                centradoOpciones("Opcion invalida");
        }
    }
    public static void menuServiciosExtras (Scanner scan,String usuario, GestorHotel miHotel,String dni,GestorReserva misReservas,boolean esPasajero) throws JSONException {
        imprimirMenuServExtras();
        int opc = elegirOpcion(scan);

        switch (opc){

            case 1:
                //Actividades disponibles.
                menuActividades(scan,usuario,miHotel,dni,misReservas,esPasajero);
                break;
            case 2:
                //Servicio a la habitacion
                menuServHabitacion(scan,usuario,miHotel,dni,misReservas,esPasajero);
                break;
            case 0:
                menuPasajero(scan,usuario,miHotel,dni,misReservas, esPasajero);
                break;
            default:
                centradoOpciones("Opcion invalida");
        }
    }
    public static void menuActividades(Scanner scan,String usuario,GestorHotel miHotel, String dni, GestorReserva misReservas,boolean esPasajero) throws JSONException {
        if ( misReservas.buscarUnaHabitacionDni(dni) == -1){
            Menu.dibujarTerminacion();
            Menu.encabezadoMenu("Nuestras Actividades");
            Menu.centradoOpciones("Masajes          ........................ $ 1.500.-");
            Menu.centradoOpciones("Spa              ........................ $ 2.500.-");
            Menu.centradoOpciones("Sauna            ........................ $ 3.500.-");
            Menu.centradoOpciones("Hidromasaje      ........................ $ 1.000.-");
            Menu.dibujarTerminacion();
            menuServiciosExtras(scan, usuario, miHotel, dni, misReservas, esPasajero);
        }
        else {
            imprimirMenuActividades();
            int opc = elegirOpcion(scan);

            switch (opc){
                case 1:
                    try {
                        //masajes
                        encabezadoMenu("Masajes");

                        confirmacionServicio(scan,Servicio_Habitacion.MASAJE,dni,misReservas,miHotel);

                        menuActividades(scan, usuario, miHotel,dni,misReservas,esPasajero);
                    } catch (HabitacionInvalidaException e) {
                        centradoOpciones(e.getMessage());
                    }
                    break;
                case 2:
                    try {

                        //spa
                        encabezadoMenu("Spa");
                        confirmacionServicio(scan,Servicio_Habitacion.SPA,dni,misReservas,miHotel);

                        menuActividades(scan, usuario, miHotel,dni,misReservas,esPasajero);
                    } catch (JSONException e) {
                        centradoOpciones(e.getMessage());
                    }
                    break;
                case 3:
                    try {

                        //sauna
                        encabezadoMenu("Sauna");
                        confirmacionServicio(scan,Servicio_Habitacion.SAUNA,dni,misReservas,miHotel);

                        menuActividades(scan, usuario, miHotel,dni,misReservas,esPasajero);
                    }catch (JSONException e) {
                        centradoOpciones(e.getMessage());
                    }
                    break;
                case 4:
                    try {
                        //hidromasaje
                        encabezadoMenu("Hidromasaje");
                        confirmacionServicio(scan,Servicio_Habitacion.HIDROMASAJE,dni,misReservas,miHotel);

                        menuActividades(scan, usuario, miHotel,dni,misReservas,esPasajero);
                    }catch (JSONException e) {
                        centradoOpciones(e.getMessage());
                    }
                    break;
                case 0:
                    menuServiciosExtras(scan,usuario,miHotel,dni,misReservas,esPasajero);
                    break;
                default:
                    centradoOpciones("Opcion invalida");
            }
        }

    }
    public static void menuServHabitacion (Scanner scan, String usuario, GestorHotel miHotel,String dni,GestorReserva misReservas,boolean esPasajero) throws JSONException {
        if ( misReservas.buscarUnaHabitacionDni(dni) == -1){
            Menu.dibujarTerminacion();
            Menu.encabezadoMenu("Nuestra Carta");
            Menu.centradoOpciones("Desayuno              ........................ $ 1.500.-");
            Menu.centradoOpciones("Almuerzo-Cena         ........................ $ 2.500.-");
            Menu.centradoOpciones("Servicio de Brindis   ........................ $ 3.500.-");
            Menu.centradoOpciones("Bebida sin Alcohol    ........................ $ 1.000.-");
            Menu.dibujarTerminacion();
            menuServiciosExtras(scan, usuario, miHotel, dni, misReservas, esPasajero);
        }
        else {
            imprimirMenuServHabitacion();
            int opc = elegirOpcion(scan);

            switch (opc){

                case 1:
                    try {
                        //Solicitar desayuno en la habitacion
                        encabezadoMenu("Desayuno");
                        confirmacionServicio(scan,Servicio_Habitacion.DESAYUNO,dni,misReservas,miHotel);

                        menuServHabitacion(scan, usuario, miHotel,dni,misReservas,esPasajero);

                    }catch (JSONException e) {
                        centradoOpciones(e.getMessage());
                    }
                    break;
                case 2:
                    try {
                        //Solicitar almuerzo o cena en la habitacion.
                        encabezadoMenu("Almuerzo-Cena");
                        confirmacionServicio(scan,Servicio_Habitacion.ALMUERZO_CENA,dni,misReservas,miHotel);

                        menuServHabitacion(scan, usuario, miHotel,dni,misReservas,esPasajero);

                    }catch (JSONException e) {
                        centradoOpciones(e.getMessage());
                    }
                    break;
                case 3:
                    try {
                        //Servicio de brindis
                        encabezadoMenu("Servicio de Brindis");
                        confirmacionServicio(scan,Servicio_Habitacion.SERVICIO_BRINDIS,dni,misReservas, miHotel);

                        menuServHabitacion(scan, usuario, miHotel,dni,misReservas,esPasajero);

                    }catch (JSONException e) {
                        centradoOpciones(e.getMessage());
                    }
                    break;
                case 4:
                    try {
                        //bebida sin alcohol
                        encabezadoMenu("Bebidas");
                        confirmacionServicio(scan,Servicio_Habitacion.BEBIDA_SIN_ALCOHOL,dni, misReservas, miHotel);

                        menuServHabitacion(scan, usuario, miHotel,dni,misReservas,esPasajero);

                    }catch (JSONException e) {
                        centradoOpciones(e.getMessage());
                    }
                    break;
                case 0:
                    menuServiciosExtras(scan,usuario, miHotel,dni,misReservas,esPasajero);
                    break;
                default:
                    centradoOpciones("opcion invalida");

            }
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
        centradoOpciones("2. Modificacion datos Pasajero");
        centradoOpciones("3. Ver datos Pasajero");
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
        centradoOpciones("4. Ver datos Empleado");
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
    public static void imprimirMenuDatosPersonales () {
        dibujarTerminacion();
        encabezadoMenu("Datos Personales");
        centradoOpciones("1. Ver datos personales");
        centradoOpciones("2. Modificar datos personales");
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

    public static void confirmacionServicio (Scanner scan, Servicio_Habitacion servicioHabitacion, String dni, GestorReserva misReservas, GestorHotel miHotel)  {

        Servicio servicio = new Servicio(servicioHabitacion, servicioHabitacion.getCosto());
        ArrayList<Servicio>servicios = new ArrayList<>();

        int confirmar = 0;

        centradoOpciones("Valor: $ " + servicioHabitacion.getCosto() + ".-");
        centradoOpciones("1. Confirmar");
        centradoOpciones("0. Rechazar");
        dibujarTerminacion();
        confirmar = elegirOpcion(scan);

        if(confirmar == 1){

            int numHabitacion = misReservas.buscarUnaHabitacionDni(dni);
            Habitacion h = miHotel.buscarHabitacion(numHabitacion);

            if (h != null){
                h.getServicios().add(servicio);
                h.ticketServicios();

            }else {
                throw new HabitacionInvalidaException("Habitacion no encontrada para el pasajero");
            }
        }
    }



}
