package Clases;

import manejoJSON.GestorJson;

public class pruebas {
    public static void main(String[] args) {
        Hotel hotel = GestorJson.fromJsonHotel();

        System.out.println(hotel);
    }
}
