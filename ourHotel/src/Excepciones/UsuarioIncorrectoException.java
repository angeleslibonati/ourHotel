package Excepciones;

public class UsuarioIncorrectoException extends RuntimeException {
    public UsuarioIncorrectoException(String message) {
        super(message);
    }
}
