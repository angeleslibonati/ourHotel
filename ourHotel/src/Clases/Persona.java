package Clases;

public abstract class Persona {


    protected String dni;
    protected String nombre;
    protected String apellido;
    protected String telefono;
    protected String email;
    protected Direccion direccion;
    protected String usuario;
    protected String contrasenia;



    public String getUsuario() {
        return usuario;
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

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    @Override
    public String toString() {
        return "Persona:" +
                "\ndni=" + dni +
                "\nnombre='" + nombre +
                "\napellido='" + apellido +
                "\ntelefono='" + telefono +
                "\nemail='" + email  +
                "\ndireccion=" + direccion +
                "\nusuario='" + usuario +
                "\ncontrasenia='" + contrasenia;
    }
}
