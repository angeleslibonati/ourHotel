package Excepciones;

public class HabitacionNoDisponibleException extends RuntimeException {
    public HabitacionNoDisponibleException(String message) {
        super(message);
    }
}