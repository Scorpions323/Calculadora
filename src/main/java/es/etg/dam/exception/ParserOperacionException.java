package es.etg.dam.exception;

public class ParserOperacionException extends RuntimeException {

    private static final String MSG = "Error parseando el mensaje: %s";

    public ParserOperacionException(String detalle) {
        super(String.format(MSG, detalle));
    }

    public ParserOperacionException(String detalle, Throwable causa) {
        super(String.format(MSG, detalle), causa);
    }
}