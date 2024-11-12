package Gestores;

import Clases.Empleado;
import Clases.Pasajero;
import Clases.Persona;
import Enum.*;
import java.util.ArrayList;

public class GestorPasajero {

    ArrayList<Pasajero>pasajeros;

    public GestorPasajero() {
        this.pasajeros = new ArrayList<>();
    }



    public static Pasajero buscarPasajero(String usuario, ArrayList<Pasajero>misPasajeros){

        Pasajero pasajero = new Pasajero();

        for(int i = 0; i< misPasajeros.size(); i++){

            pasajero = misPasajeros.get(i);

            if(pasajero.getUsuario().equals(usuario)){
                pasajero = misPasajeros.get(i);
            }
        }
        return pasajero;
    }

    public static void mostrarPasajero(Pasajero pasajero){

        pasajero.mostrarPasajero();
    }

}
