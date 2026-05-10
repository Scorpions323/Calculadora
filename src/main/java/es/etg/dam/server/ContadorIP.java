package es.etg.dam.server;

import java.util.HashMap;
import java.util.Map;

public class ContadorIP {

    private final Map<String, Integer> peticionesPorIp = new HashMap<>();

    private static final int VALOR_INICIAL = 0;
    private static final int INCREMENTO = 1;

    public void incrementar(String ip) {
        peticionesPorIp.merge(ip, INCREMENTO, Integer::sum);
    }

    public int obtener(String ip) {
        Integer valor = peticionesPorIp.get(ip);
        return (valor == null) ? VALOR_INICIAL : valor;
    }
}