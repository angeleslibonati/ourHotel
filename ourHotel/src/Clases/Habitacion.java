package Clases;
import Enum.Estado_Habitacion;
import Enum.Tipo_Cama;
import Enum.Tipo_Habitacion;
import manejoJSON.JSONUtiles;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import Enum.*;

public class Habitacion {
    protected int numHabitacion;
    protected double valorPorNoche;
    protected int cantPersonas;
    protected Tipo_Habitacion tipoHabitacion;
    protected Tipo_Cama tipoCama;
    protected Estado_Habitacion estadoHabitacion;

    //Constructores
    public Habitacion() {

    }

    public Habitacion(int numHabitacion, double valorPorNoche, int cantPersonas, Tipo_Habitacion tipoHabitacion,
                      Tipo_Cama tipoCama, Estado_Habitacion estadoHabitacion) {
        this.numHabitacion = numHabitacion;
        this.valorPorNoche = valorPorNoche;
        this.cantPersonas = cantPersonas;
        this.tipoHabitacion = tipoHabitacion;
        this.tipoCama = tipoCama;
        this.estadoHabitacion = estadoHabitacion;
    }


    public int getNumHabitacion() {
        return numHabitacion;
    }

    public double getValorPorNoche() {
        return valorPorNoche;
    }

    public int getCantPersonas() {
        return cantPersonas;
    }

    public Tipo_Habitacion getTipoHabitacion() {
        return tipoHabitacion;
    }

    public Tipo_Cama getTipoCama() {
        return tipoCama;
    }

    public Estado_Habitacion getEstadoHabitacion() {
        return estadoHabitacion;
    }

    public void setNumHabitacion(int numHabitacion) {
        this.numHabitacion = numHabitacion;
    }

    public void setValorPorNoche(double valorPorNoche) {
        this.valorPorNoche = valorPorNoche;
    }

    public void setCantPersonas(int cantPersonas) {
        this.cantPersonas = cantPersonas;
    }

    public void setTipoHabitacion(Tipo_Habitacion tipoHabitacion) {
        this.tipoHabitacion = tipoHabitacion;
    }

    public void setTipoCama(Tipo_Cama tipoCama) {
        this.tipoCama = tipoCama;
    }

    public void setEstadoHabitacion(Estado_Habitacion estadoHabitacion) {
        this.estadoHabitacion = estadoHabitacion;
    }

    @Override
    public String toString() {
        return "Habitacion{" +
                "numHabitacion=" + numHabitacion +
                ", valorPorNoche=" + valorPorNoche +
                ", cantPersonas=" + cantPersonas +
                ", tipoHabitacion=" + tipoHabitacion +
                ", tipoCama=" + tipoCama +
                ", estadoHabitacion=" + estadoHabitacion +
                '}';
    }

//Implementar código para cargar habitación desde Json

    public static List<Habitacion> fromJsonHabitacion() {


        List<Habitacion> habitaciones = new ArrayList<>();

        try {

            JSONObject json = new JSONObject(JSONUtiles.leer("hotel.json"));

            JSONObject jHotel = json.getJSONObject("hotel");

            JSONArray jHabitaciones = jHotel.getJSONArray("habitacion");

            for (int i = 0; i < jHabitaciones.length(); i++) {

                JSONObject jHabitacion = jHabitaciones.getJSONObject(i);

                Habitacion habitacion = new Habitacion();

                habitacion.setNumHabitacion(jHabitacion.getInt("numHabitacion"));
                habitacion.setValorPorNoche(jHabitacion.getDouble("valorPorNoche"));
                habitacion.setCantPersonas(jHabitacion.getInt("cantPersonas"));

                // Convertir los valores de cadena a los enums correspondientes
                Tipo_Habitacion tipoHabitacion = Tipo_Habitacion.valueOf(jHabitacion.getString("tipoHabitacion").trim().toUpperCase());
                Tipo_Cama tipoCama = Tipo_Cama.valueOf(jHabitacion.getString("tipoCama").trim().replace(" ", "_").toUpperCase());
                Estado_Habitacion estadoHabitacion = Estado_Habitacion.valueOf(jHabitacion.getString("estadoHabitacion").trim().toUpperCase());

                // Asignar los enums a la instancia de Habitacion
                habitacion.setTipoHabitacion(tipoHabitacion);
                habitacion.setTipoCama(tipoCama);
                habitacion.setEstadoHabitacion(estadoHabitacion);


                habitaciones.add(habitacion);
            }

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return habitaciones;
    }


    // Método para convertir un objeto Habitacion a JSON

    public static void toJsonHabitacion(JSONArray jhabit, int numero, double valor, int cantPersonas, String tipo, String cama, String estado) {

        JSONObject jhabitacion = new JSONObject();

        try {
            jhabitacion.put("numeroHabitacion", numero);
            jhabitacion.put("valorPorNoche", valor);
            jhabitacion.put("cantPersonas", cantPersonas);
            jhabitacion.put("tipoHabitacion", tipo.toLowerCase());
            jhabitacion.put("tipoCama", cama.toLowerCase().replace("_", " "));
            jhabitacion.put("estadoHabitacion", estado.toLowerCase());

            jhabit.put(jhabitacion);
            JSONUtiles.grabar(jhabit);

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

}
