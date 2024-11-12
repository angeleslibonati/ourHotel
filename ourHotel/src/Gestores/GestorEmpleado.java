package Gestores;

import Clases.Empleado;
import Enum.*;
import Clases.Pasajero;
import Excepciones.UsuarioYClaveIncorrectoException;

import java.util.ArrayList;

public class GestorEmpleado {

    ArrayList<Empleado> empleados ;

    public GestorEmpleado() {
        this.empleados = new ArrayList<>();
    }



    //metodo que identifica si el usuario y contraseña del empleado coinciden con las credenciales de inicio de sesion
    // proporcionadas y si encuentra coincidencia, devuelve el empledo corr, sino arroja excepcion

    public Empleado buscarEmpleadoPorCredenciales(String usuario, String contrasenia) throws UsuarioYClaveIncorrectoException {
        for (Empleado empleado : empleados) {
            if (empleado.getUsuario().equals(usuario) && empleado.getContrasenia().equals(contrasenia)) {
                return empleado; // Inicio de sesión exitoso
            }
        }
        // Lanza excepción si no se encuentra coincidencia
        throw new UsuarioYClaveIncorrectoException("Usuario o contraseña incorrectos");
    }

    //para llamar a buscarEmpleadoPorCredenciales
    //try {
    //    Empleado empleado = gestorEmpleado.buscarEmpleadoPorCredenciales(usuarioIngresado, contraseniaIngresada);
    //    System.out.println("Inicio de sesión exitoso. Bienvenido, " + empleado.getNombre());
    //} catch (UsuarioIncorrectoException e) {
    //    System.out.println(e.getMessage()); // Muestra "Usuario o contraseña incorrectos."
    //}
}


