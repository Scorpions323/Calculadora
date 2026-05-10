package es.etg.dam.calculo;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class Operacion {

    protected int num1;
    protected int num2;

    public abstract int calcular();
}