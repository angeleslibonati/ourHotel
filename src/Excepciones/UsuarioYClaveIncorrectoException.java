package Excepciones;

public class UsuarioYClaveIncorrectoException extends RuntimeException {
    public UsuarioYClaveIncorrectoException(String message) {
        super(message);
    }
}