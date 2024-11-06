package Clases;
import Enum.Tipo_Usuario;

import java.util.ArrayList;

public class Pasajero extends Persona{



    //Constructores
    public Pasajero (){

    }



    public void mostrarPasajero (){

        super.mostarPersona();
        Menu.dibujarTerminacion();
    }

}
