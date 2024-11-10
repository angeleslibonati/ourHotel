package manejoJSON;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import Clases.*;
import Enum.*;
import static Enum.Tipo_Usuario.*;
import static manejoJSON.JSONUtiles.grabar;

public class GestorJson {


    public static Hotel fromJsonHotel() {

        Hotel hotel = new Hotel();

        try {

            JSONObject json = new JSONObject(JSONUtiles.leer("hotel.json"));
            System.out.println(json.toString(2));
            JSONObject jHotel = json.getJSONObject("hotel");

            JSONArray jHabitaciones = jHotel.getJSONArray("habitacion");
            List<Habitacion> habitaciones = new ArrayList<>();

            for (int i = 0; i < jHabitaciones.length(); i++) {

                JSONObject jHabitacion = jHabitaciones.getJSONObject(i);
                Habitacion habitacion = new Habitacion();

                habitacion.setNumHabitacion(jHabitacion.getInt("numeronumHabitacion"));
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

                JSONArray jServicios = jHabitacion.getJSONArray("servicios");
                ArrayList<Servicio_Habitacion> servicios = new ArrayList<>();
                for (int j = 0; j < jServicios.length(); j++) {
                    servicios.add (Servicio_Habitacion.valueOf(jServicios.getString(j).trim().toUpperCase()));
                }
                habitacion.setServicios(servicios);


                hotel.getHabitaciones().add(habitacion);

            }


            // Procesar pasajeros
            JSONArray jPasajeros = jHotel.getJSONObject("persona").getJSONArray("pasajero");
            for (int i = 0; i < jPasajeros.length(); i++) {
                JSONObject jPasajero = jPasajeros.getJSONObject(i);
                Pasajero pasajero = new Pasajero();

                asignarDatosPersona(pasajero, jPasajero);
                Tipo_Usuario rolPasajero = Tipo_Usuario.valueOf(jPasajero.getString("rol").trim().toUpperCase());
                pasajero.setRol(rolPasajero);

                JSONObject jDireccion = jPasajero.getJSONObject("direccion");
                Direccion direccion = new Direccion();
                direccion.setCalle(jDireccion.getString("calle"));
                direccion.setAltura(jDireccion.getInt("altura"));
                direccion.setCiudad(jDireccion.getString("ciudad"));
                pasajero.setDireccion(direccion);

                hotel.getPasajeros().add(pasajero);  // Agrega el pasajero a la lista de pasajeros de hotel
            }

            // Procesar empleados
            JSONArray jEmpleados = jHotel.getJSONObject("persona").getJSONArray("empleado");
            for (int i = 0; i < jEmpleados.length(); i++) {
                JSONObject jEmpleado = jEmpleados.getJSONObject(i);
                Empleado empleado = new Empleado();

                asignarDatosPersona(empleado, jEmpleado);
                empleado.setDni(jEmpleado.getString ("dni"));
                empleado.setHorasTrabajadas(jEmpleado.getDouble ("horasTrabajadas"));
                Estado_Empleado estadoEmpleado = Estado_Empleado.valueOf(jEmpleado.getString("estadoEmpleado").trim().toUpperCase());
                empleado.setEstadoEmpleado(estadoEmpleado);
                Tipo_Usuario rolEmpleado = Tipo_Usuario.valueOf(jEmpleado.getString("rol").trim().toUpperCase());
                empleado.setRol(rolEmpleado);

                JSONObject jDomicilio = jEmpleado.getJSONObject("domicilio");
                Direccion direccion = new Direccion();
                direccion.setCalle(jDomicilio.getString("calle"));
                direccion.setAltura(jDomicilio.getInt("altura"));
                direccion.setCiudad(jDomicilio.getString("ciudad"));
                empleado.setDireccion(direccion);

                hotel.getEmpleados().add(empleado);  // Agrega el empleado a la lista de empleados de hotel
            }

        } catch (JSONException e) {
            throw new RuntimeException("Error al leer el JSON de hotel.json", e);
        }

        return hotel;
    }

    public static void asignarDatosPersona(Persona persona, JSONObject jPersona) {
        try {
            persona.setDni(jPersona.getString("dni"));
            persona.setNombre(jPersona.getString("nombre"));
            persona.setApellido(jPersona.getString("apellido"));
            persona.setTelefono(jPersona.getString("telefono"));
            persona.setEmail(jPersona.getString("email"));
            persona.setUsuario(jPersona.getString("usuario"));
            persona.setContrasenia(jPersona.getString("contrasenia"));
        } catch (JSONException o) {
            throw new RuntimeException("Error al leer el JSON de hotel.json", o);
        }

    }

    public static void toJsonHotel(Hotel hotel) {
        JSONObject jHotel = new JSONObject();
        JSONArray jHabitaciones = new JSONArray();
        JSONArray jPasajeros = new JSONArray();
        JSONArray jEmpleados = new JSONArray();

        try {

            // Convertir habitaciones a JSON
            for (Habitacion habitacion : hotel.getHabitaciones()) {
                JSONObject jHabitacion = new JSONObject();
                jHabitacion.put("numeronumHabitacion", habitacion.getNumHabitacion());
                jHabitacion.put("valorPorNoche", habitacion.getValorPorNoche());
                jHabitacion.put("cantPersonas", habitacion.getCantPersonas());
                jHabitacion.put("tipoHabitacion", habitacion.getTipoHabitacion().toString().toLowerCase());
                jHabitacion.put("tipoCama", habitacion.getTipoCama().toString().replace("_", " ").toLowerCase());
                jHabitacion.put("estadoHabitacion", habitacion.getEstadoHabitacion().toString().toLowerCase());

                JSONArray jServicios = new JSONArray();
                for (Servicio_Habitacion servicio : habitacion.getServicios()) {
                    jServicios.put(servicio.name());  // Usar el nombre del enum tal cual para consistencia con el JSON de entrada
                }
                jHabitacion.put("servicios", jServicios);

                jHabitaciones.put(jHabitacion);
            }
            jHotel.put("habitacion", jHabitaciones);

            // Convertir pasajeros a JSON

            for (Pasajero pasajero : hotel.getPasajeros()) {
                JSONObject jPasajero = new JSONObject();
                jPasajero.put("dni", pasajero.getDni());
                jPasajero.put("nombre", pasajero.getNombre());
                jPasajero.put("apellido", pasajero.getApellido());
                jPasajero.put("telefono", pasajero.getTelefono());
                jPasajero.put("email", pasajero.getEmail());
                jPasajero.put("usuario", pasajero.getUsuario());
                jPasajero.put("contrasenia", pasajero.getContrasenia());
                jPasajero.put("rol", "Pasajero");

                JSONObject jDireccion = new JSONObject();
                jDireccion.put("calle", pasajero.getDireccion().getCalle());
                jDireccion.put("altura", pasajero.getDireccion().getAltura());
                jDireccion.put("ciudad", pasajero.getDireccion().getCiudad());
                jPasajero.put("direccion", jDireccion);

                jPasajeros.put(jPasajero);
            }


            // Convertir empleados a JSON
            for (Empleado empleado : hotel.getEmpleados()) {
                JSONObject jEmpleado = new JSONObject();

                jEmpleado.put("dni", empleado.getDni());
                jEmpleado.put("nombre", empleado.getNombre());
                jEmpleado.put("apellido", empleado.getApellido());
                jEmpleado.put("telefono", empleado.getTelefono());
                jEmpleado.put("email", empleado.getEmail());
                jEmpleado.put("usuario", empleado.getUsuario());
                jEmpleado.put("contrasenia", empleado.getContrasenia());
                jEmpleado.put("rol", empleado.getRol().toString().toLowerCase());
                jEmpleado.put("estadoEmpleado", empleado.getEstadoEmpleado());

                JSONObject jDomicilio = new JSONObject();
                jDomicilio.put("calle", empleado.getDireccion().getCalle());
                jDomicilio.put("altura", empleado.getDireccion().getAltura());
                jDomicilio.put("ciudad", empleado.getDireccion().getCiudad());
                jEmpleado.put("domicilio", jDomicilio);

                jEmpleado.put("horasTrabajadas", empleado.getHorasTrabajadas());

                jEmpleados.put(jEmpleado);
            }


            // Agrupar pasajeros y empleados bajo "persona"
            JSONObject jPersona = new JSONObject();
            jPersona.put("pasajero", jPasajeros);
            jPersona.put("empleado", jEmpleados);

            // Completar el JSON final de hotel
            jHotel.put("persona", jPersona);

            JSONUtiles.grabarObjeto(jHotel);  // Usa el método grabarObjeto para escribir directamente el objeto hotel


        } catch (JSONException l) {
            throw new RuntimeException("Error al cargar los datos al Hotel");
        }

    }


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
                Date fechaInicio = (Date) format.parse(OReserva.getString("fechaInicio"));
                miReserva.setFechaInicio(fechaInicio);

                Date fechaFin = (Date) format.parse(OReserva.getString("fechaFin"));
                miReserva.setFechaFin(fechaFin);

                // Convertir los valores de cadena a los enums correspondientes

                miReserva.setEstadoReserva(Estado_Reserva.fromString(OReserva.getString("estadoReserva")));

                JSONObject OEmpleado = OReserva.getJSONObject("empleado");

                Empleado miEmpleado = new Empleado();

                //miEmpleado.setId(OEmpleado.getInt("id"));

                miReserva.setEmpleado(miEmpleado);

                misReservas.add(miReserva);
            }

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return misReservas;
    }


}




