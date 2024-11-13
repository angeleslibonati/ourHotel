package Clases;
import Enum.Tipo_Usuario;

import java.util.ArrayList;

public class Pasajero extends Persona{

    protected ArrayList<Pasajero>misPasajeros;

    protected boolean activo;

    //Constructores
    public Pasajero (){
        this.misPasajeros = new ArrayList<>();
    }

    public boolean isActivo() { return activo; }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public void mostrarPasajero (){

        super.mostarPersona();
        Menu.dibujarTerminacion();
    }



}
