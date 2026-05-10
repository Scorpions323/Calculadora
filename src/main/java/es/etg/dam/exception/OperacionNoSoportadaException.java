package es.etg.dam.exception;

public class OperacionNoSoportadaException extends Exception {

    private static final String MSG = "Operación no soportada: %s";

    public OperacionNoSoportadaException(String operacion) {
        super(String.format(MSG, operacion));
    }

    public OperacionNoSoportadaException(String operacion, Throwable causa) {
        super(String.format(MSG, operacion), causa);
    }
}