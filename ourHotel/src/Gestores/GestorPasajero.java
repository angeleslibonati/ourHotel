package Gestores;

import Clases.Empleado;
import Clases.Pasajero;
import Clases.Persona;
import Enum.*;
import java.util.ArrayList;

public class GestorPasajero {

    protected ArrayList<Pasajero>pasajeros;

    public GestorPasajero() {
        this.pasajeros = new ArrayList<>();
    }

    public ArrayList<Pasajero> getPasajeros() {
        return pasajeros;
    }

    public void setPasajeros(ArrayList<Pasajero> pasajeros) {
        this.pasajeros = pasajeros;
    }

    public static Pasajero buscarPasajero(String usuario, ArrayList<Pasajero>misPasajeros){

        Pasajero pasajeroEncontrado = new Pasajero();

        for(int i = 0; i< misPasajeros.size(); i++){

            Pasajero pasajero = misPasajeros.get(i);

            if(pasajero.getUsuario().equals(usuario)){
                return misPasajeros.get(i);
            }
        }
        return pasajeroEncontrado;
    }

    public static void mostrarPasajero(Pasajero pasajero){

        pasajero.mostrarPasajero();
    }

}
