package Clases;
import Enum.Tipo_Usuario;

import java.util.ArrayList;

public class Pasajero extends Persona{

    protected ArrayList<Pasajero>misPasajeros;

    //Constructores
    public Pasajero (){
        this.misPasajeros = new ArrayList<>();
    }



    public void mostrarPasajero (){

        super.mostarPersona();
        Menu.dibujarTerminacion();
    }

}
