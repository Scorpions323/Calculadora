package es.etg.dam.calculo.operaciones;

import es.etg.dam.calculo.Operacion;

public class Resta extends Operacion {

    public Resta(int num1, int num2) {
        super(num1, num2);
    }

    @Override
    public int calcular() {
        return num1 - num2;
    }
}