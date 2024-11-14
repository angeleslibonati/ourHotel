package Interfaces;

import Gestores.GestorHotel;

import java.util.Scanner;

public interface I_ABM {
    void alta(Scanner scan, GestorHotel miHotel);

    void baja(Scanner scan, GestorHotel miHotel);

    void modificacion(Scanner scan, GestorHotel miHotel);
}
