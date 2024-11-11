package Clases;

public class Direccion {

    protected String calle;
    protected int altura;
    protected String ciudad;

    //Constructor
    public Direccion (){

    }
    public Direccion(String calle, int altura, String ciudad) {
        this.calle = calle;
        this.altura = altura;
        this.ciudad = ciudad;
    }

    public String getCalle() {
        return calle;
    }

    public int getAltura() {
        return altura;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }


    public void mostrarDireccion(){
        Menu.centradoOpciones("Direccion: " + this.calle);
        Menu.centradoOpciones("Altura: " + this.altura);
        Menu.centradoOpciones("Ciudad: " + this.ciudad);
    }
}
