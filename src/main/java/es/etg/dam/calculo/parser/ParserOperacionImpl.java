package es.etg.dam.calculo.parser;

import es.etg.dam.exception.ParserOperacionException;
import es.etg.dam.calculo.OperacionFactory;
import es.etg.dam.calculo.Operacion;

public class ParserOperacionImpl implements ParserOperacion {

    private static final String SEPARADOR = " ";
    private static final int NUM_1 = 0;
    private static final int OPERADOR = 1;
    private static final int NUM_2 = 2;

    @Override
    public Operacion parsear(String mensaje) throws ParserOperacionException {

        try {
            String[] partes = mensaje.split(SEPARADOR);

            int num1 = Integer.parseInt(partes[NUM_1]);
            String operador = partes[OPERADOR];
            int num2 = Integer.parseInt(partes[NUM_2]);

            return OperacionFactory.crearOperacion(operador, num1, num2);

        } catch (Exception e) {
            throw new ParserOperacionException(e.getMessage(), e);
        }
    }
}