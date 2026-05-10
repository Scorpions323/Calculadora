package es.etg.dam.calculo;

import es.etg.dam.exception.OperacionNoSoportadaException;
import es.etg.dam.calculo.operaciones.Resta;
import es.etg.dam.calculo.operaciones.Suma;

public class OperacionFactory {

    private static final String SUMA = "+";
    private static final String RESTA = "-";

    public static Operacion crearOperacion(String operador, int num1, int num2) throws OperacionNoSoportadaException {

        return switch (operador) {
            case SUMA -> new Suma(num1, num2);
            case RESTA -> new Resta(num1, num2);
            default -> throw new OperacionNoSoportadaException(operador);
        };
    }
}