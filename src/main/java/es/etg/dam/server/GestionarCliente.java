package es.etg.dam.server;

import es.etg.dam.exception.GestionClienteException;
import es.etg.dam.calculo.parser.ParserOperacion;
import es.etg.dam.calculo.Operacion;
import es.etg.dam.util.LogUtil;
import es.etg.dam.common.Conexion;

import lombok.AllArgsConstructor;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.net.Socket;

@AllArgsConstructor
public class GestionarCliente implements Runnable {

    private static final String MSG_CLIENTE_CONECTADO = "Cliente conectado: %s";
    private static final String MSG_NUM_PETICIONES = "Número de peticiones desde %s: %d";
    private static final String MSG_RESULTADO = "Resultado de %s: %d";

    private final Socket socket;
    private final ParserOperacion parser;
    private final ContadorIP contadorIP;
    private final Logger logger;

    @Override
    public void run() {
        try {
            String ip = socket.getInetAddress().getHostAddress();
            contadorIP.incrementar(ip);

            LogUtil.escribirLog(logger, Level.INFO, String.format(MSG_CLIENTE_CONECTADO, socket));
            LogUtil.escribirLog(logger, Level.INFO, String.format(MSG_NUM_PETICIONES, ip, contadorIP.obtener(ip)));

            String mensaje = Conexion.recibirSeguro(socket);
            Operacion operacion = parser.parsear(mensaje);

            int resultado = operacion.calcular();
            String respuesta = String.format(MSG_RESULTADO, mensaje, resultado);

            Conexion.enviarSeguro(respuesta, socket);
            socket.close();

        } catch (Exception e) {
            throw new GestionClienteException(e.getMessage(), e);
        }
    }
}