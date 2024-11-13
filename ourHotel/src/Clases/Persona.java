package Clases;
import Enum.Tipo_Usuario;

import java.util.ArrayList;

public abstract class Persona {


    protected String dni;
    protected String nombre;
    protected String apellido;
    protected String telefono;
    protected String email;
    protected Direccion direccion;
    protected Tipo_Usuario rol;
    protected String usuario;
    protected String contrasenia;



    public String getUsuario() {
        return usuario;
    }

    public Tipo_Usuario getRol() {
        return rol;
    }

    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getEmail() {
        return email;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setRol(Tipo_Usuario rol) {
        this.rol = rol;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }


    public void mostarPersona (){

        Menu.dibujarTerminacion();
        Menu.encabezadoMenu("Datos Personales");
        Menu.centradoOpciones("Nombre: " + this.nombre);
        Menu.centradoOpciones("Apellido: " + this.apellido);
        Menu.centradoOpciones("Dni: " + this.dni);
        this.direccion.mostrarDireccion();
        Menu.centradoOpciones("Telefono: " + this.telefono);
        Menu.centradoOpciones("Email: " + this.email);
        Menu.centradoOpciones("Rol: " + this.rol);
        Menu.centradoOpciones("Usuario: " + this.usuario);
        Menu.dibujarTerminacion();
    }

    public static int buscarPorDni(String dni, ArrayList<Persona> personas) {
        int index = -1;
        int cont = 0;

        while (cont < (personas.size()-1) && index == -1) {

            if (dni.equalsIgnoreCase(personas.get(cont).getDni())) {
                index = cont;
            }
            cont ++;
        }
        return index;
    }
}
