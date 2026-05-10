package es.etg.dam.client;

import es.etg.dam.exception.ClienteException;
import es.etg.dam.common.Conexion;
import es.etg.dam.server.Servidor;
import es.etg.dam.util.LogUtil;

import java.util.logging.Logger;
import java.util.logging.Level;
import java.net.Socket;

public class Cliente {

    private static final String SEPARADOR = " ";
    private static final int NUM_1 = 0;
    private static final int OPERADOR = 1;
    private static final int NUM_2 = 2;
    private static final String FICHERO_LOG = "Cliente.log";

    public static void main(String[] args) throws ClienteException {

        Logger logger = null;

        try (Socket socket = new Socket(Servidor.HOST, Servidor.PUERTO)) {
            logger = LogUtil.crearLog(FICHERO_LOG);

            String mensaje = args[NUM_1] + SEPARADOR + args[OPERADOR] + SEPARADOR + args[NUM_2];

            Conexion.enviarSeguro(mensaje, socket);

            String respuesta = Conexion.recibirSeguro(socket);
            System.out.println(respuesta);

        } catch (Exception e) {
            LogUtil.escribirLog(logger, Level.SEVERE, e.getMessage(), e);
            throw new ClienteException(e.getMessage(), e);
        }
    }
}