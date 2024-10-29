package Clases;

public abstract class Persona {

    private final int id = ++Persona.contador;
    private static int contador = 0;
    protected String dni;
    protected String nombre;
    protected String apellido;
    protected String telefono;
    protected String email;
    protected Direccion direccion;

    public int getId() {
        return id;
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
}
