package Clases;

import Gestores.GestorHotel;
import Gestores.GestorReserva;
import manejoJSON.GestorJson;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

    //menu

    public static void menuPrincipal (Scanner scan){

        int opc = 0;
        imprimirMenu();
        opc = elegirOpcion(scan);

        switch (opc) {

            case 1:
                //Empleado
                //validar usuario y clave
                String usuario = GestorHotel.ingresoUsuarioYClave(scan, "Usuario");
                String clave = GestorHotel.ingresoUsuarioYClave(scan, "Clave");

                //if ( usuario ok && clave ok ){
                //  if (tipo = recepcionista{


               // menuRecepcionista(scan);
                //    }
                //else {

                //menuAdmin(scan);
                //}
                // else {
                //   usuario y/o contrasenia incorrecta
                //}
                // }*/

                break;

            case 2:
                //Pasajero
                usuario = GestorHotel.ingresoUsuarioYClave(scan, "Usuario");
                clave = GestorHotel.ingresoUsuarioYClave(scan, "Clave");

                //if (usuario ok && clave ok)
                    menuPasajero(scan);
                 //
                break;

            case 0:
                //Salir

                break;

            default:
        }


    }

    //Sub Menu para recepcionista
    public static void menuRecepcionista (Scanner scan){

        imprimirMenuRecepcion();
         int opc = elegirOpcion(scan);

        switch (opc){

            case 1:
                menuAbmPasajero (scan);
                break;
            case  2:
                MenuAbmReserva(scan);
                break;

            case 3:
                //Hacer check in
                encabezadoMenu("Check in");
                break;
            case 4:
                //hacer check out
                encabezadoMenu("Check out");
            case 5:
                menuHabitacion(scan);

                break;
            case 0:
                menuPrincipal(scan);

                break;
            default:
        }       centradoOpciones("Opcion invalida");
    }
    public static void menuAbmPasajero (Scanner scan){
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

                break;
            case 0:
                //volver atras
                menuRecepcionista(scan);
                break;

            default:
                centradoOpciones("Opcion invalida");
        }
    }
    public static void menuHabitacion (Scanner scan){
        imprimirMenuHabitacion();
        int opc = elegirOpcion(scan);

        switch (opc){

            case 1:
                //buscar una habitacion
                break;
            case 2:
                //buscar segun estado libre.
                break;
            case 3:
                //buscar segun estado ocupado.
                break;
            case 4:
                //modificar una habitacion.
                break;
            case 0:
                //volver atras
                menuRecepcionista(scan);
                break;
            default:
        }       centradoOpciones("Opcion invalida");

    }
    public static void MenuAbmReserva (Scanner scan){

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
                menuRecepcionista(scan);
                break;
            default:
                centradoOpciones("Opcion invalida");
        }
    }
    // --

    //Sub Menu para administrador
    public static void menuAdmin (Scanner scan){
        imprimirMenuAdmin ();
        int opc = elegirOpcion(scan);

        switch (opc){

            case 1:
                //abm empleado
                menuAbmEmpleado (scan);

                break;
            case 2:
                //abm habitacion
                menuAbmHabitacion (scan);
                break;
            case 3:
                menuRecepcionista(scan);
                break;
            case 4:
                //back up
                break;
            case 0:
                //volver atras
                menuPrincipal(scan);
                break;
            default:
                centradoOpciones("Opcion invalida");

        }
    }
    public static void menuAbmEmpleado (Scanner scan){
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
                menuAdmin(scan);
                break;

            default:
                centradoOpciones("Opcion invalida");

        }
    }
    public static void menuAbmHabitacion (Scanner scan){
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
                menuAdmin(scan);
                break;
            default:
                centradoOpciones("Opcion invalida");
        }

    }
    // --

    //Sub Menu para pasajero
    public static void menuPasajero (Scanner scan){
        imprimirMenuPasajero();
        int opc = elegirOpcion(scan);


        switch (opc){

            case 1:
                encabezadoMenu("Datos Personales");
                //muestra datos del pasajero.
                break;
            case 2:
                //reservas
                menuReserva(scan);
                break;
            case 3:
                //Servicios extra
                menuServiciosExtras(scan);
                break;
            case 0:
                //Volver atras
                menuPrincipal(scan);
                break;
            default:
                centradoOpciones("Opcion invalida");
        }
    }
    public static void menuReserva (Scanner scan){
        imprimirMenuReserva();
        int opc = elegirOpcion(scan);

        switch (opc){

            case 1:
                //hacer reserva

                break;
            case 2:
                //cancelar reserva
                break;
            case 3:
                //ver reservas activas
                break;
            case 4:
                //ver historico
                break;
            case 0:
                menuPasajero(scan);
                break;
            default:
                centradoOpciones("Opcion invalida");
        }
    }
    public static void menuServiciosExtras (Scanner scan){
        imprimirMenuServExtras();
        int opc = elegirOpcion(scan);

        switch (opc){

            case 1:
                //Actividades disponibles.
                menuActividades(scan);
                break;
            case 2:
                //Servicio a la habitacion
                menuServHabitacion(scan);
                break;
            case 0:
                menuPasajero(scan);
                break;
            default:
                centradoOpciones("Opcion invalida");
        }
    }
    public static void menuActividades(Scanner scan){

        imprimirMenuActividades();
        int opc = elegirOpcion(scan);

        switch (opc){
            case 1:
                //masajes
                //costo, horarios, lugar, si se inscribe, debe sumar el monto de plata a los cargos.
                break;
            case 2:
                //spa
                //costo, horarios, lugar, si se inscribe, debe sumar el monto de plata a los cargos.
                break;
            case 3:
                //sauna
                //costo, horarios, lugar, si se inscribe, debe sumar el monto de plata a los cargos.
                break;
            case 4:
                //hidromasaje
                //costo, horarios, lugar, si se inscribe, debe sumar el monto de plata a los cargos.
                break;
            case 0:
                menuServiciosExtras(scan);
                break;
            default:
                centradoOpciones("Opcion invalida");
        }
    }
    public static void menuServHabitacion (Scanner scan){

        imprimirMenuServHabitacion();
        int opc = elegirOpcion(scan);

        switch (opc){

            case 1:
                //Solicitar desayuno en la habitacion
                //Adicional el costo.
                break;
            case 2:
                //Solicitar almuerzo o cena en la habitacion.
                //Adicional el costo del servicio mas el valor de la comida/menu
                break;
            case 3:
                //Servicio de brindis
                //Adicional el costo mas la bebida que se lleve
                break;
            case 4:
                //bebida sin alcohol
                //adicional el costo de las bebidas y cantidades.
                break;
            case 0:
                menuServiciosExtras(scan);
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







}
