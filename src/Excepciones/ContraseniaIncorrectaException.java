package Excepciones;

public class ContraseniaIncorrectaException extends RuntimeException {
    public ContraseniaIncorrectaException(String message) {
        super(message);
    }
}
