package Enum;

public enum Estado_Reserva {

    RESERVADO,
    CONFIRMADO,
    FINALIZADO,
    CANCELADO;

    public static Estado_Reserva fromString(String value) {
        try {
            return Estado_Reserva.valueOf(value.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            // Manejar el caso donde no se encuentra el valor,
            // por ejemplo, lanzando una excepción personalizada o retornando
            // un valor por defecto throw new IllegalArgumentException("Estado de reserva desconocido: " + value);
        }
        return null;
    }
}
