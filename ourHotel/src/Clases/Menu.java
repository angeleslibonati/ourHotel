package Clases;

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
                String usuario = ingresoUsuarioYClave(scan, "Usuario");
                String clave = ingresoUsuarioYClave(scan, "Clave");

                //if ( usuario ok && clave ok ){
                //  if (tipo = recepcionista{

                menuRecepcionista(scan);
                //    }
                //else {

                menuAdmin(scan);
                //}
                // else {
                //   usuario y/o contrasenia incorrecta
                //}
                // }*/

                break;

            case 2:
                //Pasajero
                usuario = ingresoUsuarioYClave(scan, "Usuario");
                clave = ingresoUsuarioYClave(scan, "Clave");
                //if (usuario ok && clave ok)


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
                //abm pasajero
                menuAbmPasajero (scan);

            break;

            case  2:
                encabezadoMenu("Reserva");

                break;

            case 3:
                //Hacer check in

                break;
            case 4:
                //hacer check out

            case 5:
                //menu habitacion
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
        imprimirMenuHabitacion();
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
        encabezadoMenu("A-B-M Pasajero");
        centradoOpciones("1. Alta");
        centradoOpciones("2. Baja");
        centradoOpciones("3. Modificacion");
        centradoOpciones("0. Volver Atras");
        dibujarTerminacion();
    }

    public static void imprimirMenuRecepcion(){

        dibujarTerminacion();
        encabezadoMenu("Menu Recepcion");
        centradoOpciones("1. a-b-m Pasajero");
        centradoOpciones("2. Reserva");
        centradoOpciones("3. Check In");
        centradoOpciones("4. Check Out");
        centradoOpciones("5. Habitacion");
        centradoOpciones("0. Volver Atras");
        dibujarTerminacion();
    }
    public static void imprimirMenuAdmin (){

        dibujarTerminacion();
        encabezadoMenu("Menu Administracion");
        centradoOpciones("1. a-b-m Empleado");
        centradoOpciones("2. a-b-m Habitacion");
        centradoOpciones("3. Menu Recepcion");
        centradoOpciones("4. Backup");
        centradoOpciones("0. Volver Atras");
        dibujarTerminacion();
    }
    public static void imprimirMenuHabitacion (){

        dibujarTerminacion();
        encabezadoMenu("Habitacion");
        centradoOpciones("1. Ver una Habitacion");
        centradoOpciones("2. Ver Libres");
        centradoOpciones("3. Ver Ocupadas");
        centradoOpciones("4. Modificar una Habitacion");
        centradoOpciones("0. Volver atras");
        dibujarTerminacion();
    }
    public static void imprimirAbmEmpleado (){

        dibujarTerminacion();
        encabezadoMenu("A-B-M Empleado");
        centradoOpciones("1. Alta empleado");
        centradoOpciones("2. Baja empleado");
        centradoOpciones("3. Modificacion empleado");
        centradoIngreso("0. Volver atras");
        dibujarTerminacion();
    }
    public static void imprimirAbmHabitacion (){

        dibujarTerminacion();
        encabezadoMenu("A-B-M Hbitacion");
        centradoOpciones("1. Alta"); //habitacion nueva
        centradoOpciones("2. Baja");  // momentaneamente o no dada de baja, por reformas por ejemplo
        centradoOpciones("3. Modificacion"); //cambio de camas o categoria por ejemplo
        centradoOpciones("0. Volver atras");
        dibujarTerminacion();
    }


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

        System.out.print("\t\t\t\t\t\t\t\t\t\t+");
        for (int i = 0; i < 100; i++) {
            System.out.print("-");
        }
        System.out.println("+");
    }
    public static void encabezadoMenu (String mensaje){

        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t --- " + mensaje + " ---");
    }
    public static void centradoOpciones (String opcionIngreso){
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t " + opcionIngreso);
    }
    public static void centradoIngreso (String opcionIngreso){
        System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t " + opcionIngreso);
    }

    public static String ingresoUsuarioYClave (Scanner scan, String mensaje){

        centradoIngreso(mensaje + " : ");
        return scan.nextLine();
    }



}
