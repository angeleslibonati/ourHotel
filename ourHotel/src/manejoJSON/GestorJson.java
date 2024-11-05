package manejoJSON;
import Clases.Empleado;
import Clases.Habitacion;
import Clases.Pasajero;
import Clases.Reserva;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import Enum.Estado_Reserva;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class GestorJson {

    public static ArrayList<Reserva> mapeoReserva () throws ParseException {

        ArrayList <Reserva> misReservas = new ArrayList<>();
        try {
            JSONObject reserva = new JSONObject(JSONUtiles.leer("reserva.json"));

            JSONArray JReserva = reserva.getJSONArray("reserva");


            for (int i = 0; i < JReserva.length(); i++){
                JSONObject OReserva = JReserva.getJSONObject(i);
                Reserva miReserva = new Reserva();

                JSONObject OHabitacion = OReserva.getJSONObject("habitacion");
                Habitacion miHabitacion = new Habitacion();

                miHabitacion.setNumHabitacion(OHabitacion.getInt("numHabitacion"));

                JSONObject OPasajero = OReserva.getJSONObject("pasajero");
                Pasajero miPasajero = new Pasajero();

                miPasajero.setDni(OPasajero.getString("dni"));

                miReserva.setHabitacion(miHabitacion);
                miReserva.setPasajero(miPasajero);
                miReserva.setIdReserva(OReserva.getInt("idReserva"));

                //Convertir un String a un valor tipo Date
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date fechaInicio = format.parse(OReserva.getString("fechaInicio"));
                miReserva.setFechaInicio(fechaInicio);

                Date fechaFin = format.parse(OReserva.getString("fechaFin"));
                miReserva.setFechaFin(fechaFin);

                // Convertir los valores de cadena a los enums correspondientes
                //Estado_Reserva estadoReserva = Estado_Reserva.valueOf(OReserva.getString("estadoReserva").trim().toUpperCase());

                miReserva.setEstadoReserva(Estado_Reserva.fromString(OReserva.getString("estadoReserva")));

                JSONObject OEmpleado = OReserva.getJSONObject("empleado");

                Empleado miEmpleado = new Empleado();

                miEmpleado.setId(OEmpleado.getInt("id"));

                miReserva.setEmpleado(miEmpleado);

                misReservas.add(miReserva);
            }

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return misReservas;
    }
}
