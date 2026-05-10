package es.etg.dam.calculo.parser;

import es.etg.dam.exception.OperacionNoSoportadaException;
import es.etg.dam.calculo.Operacion;

public interface ParserOperacion {
    Operacion parsear(String mensaje) throws OperacionNoSoportadaException;
}