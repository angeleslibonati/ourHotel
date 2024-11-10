package Excepciones;

public class ReservaInvalidaException extends RuntimeException {
    public ReservaInvalidaException(String message) {
        super(message);
    }
}
