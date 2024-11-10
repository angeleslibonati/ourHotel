package Enum;

public enum Servicio_Habitacion {

    MASAJE("Masaje", 1500),
    SPA("Spa", 2500),
    SAUNA("Sauna", 3500),
    HIDROMASAJE("Hidromasaje", 1000),
    DESAYUNO("Desayuno", 5500),
    ALMUERZO_CENA("Comida", 10200),
    SERVICIO_BRINDIS("Brindis", 15000), //champagnea,
    BEBIDA_SIN_ALCOHOL("Bebida", 2500);


    private String nombre;
    private double costo;


    Servicio_Habitacion(String nombre, double costo) {
        this.nombre = nombre;
        this.costo = costo;
    }

    public String getNombre() {
        return nombre;
    }

    public double getCosto() {
        return costo;
    }
}