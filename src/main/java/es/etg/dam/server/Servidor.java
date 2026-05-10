package es.etg.dam.server;

import es.etg.dam.calculo.parser.ParserOperacionImpl;
import es.etg.dam.calculo.parser.ParserOperacion;
import es.etg.dam.exception.ServidorException;
import es.etg.dam.util.LogUtil;

import java.util.logging.Logger;
import java.util.logging.Level;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    public static final String HOST = "localhost";
    public static final int PUERTO = 8080;

    private static final String FICHERO_LOG = "Servidor.log";
    private static final String MSG_PUERTO_ESCUCHA = "Servidor escuchando en el puerto %d";

    public static void main(String[] args) throws ServidorException {

        Logger logger = null;
        ParserOperacion parser = new ParserOperacionImpl();
        ContadorIP contadorIP = new ContadorIP();

        try (ServerSocket serverSocket = new ServerSocket(PUERTO)) {
            logger = LogUtil.crearLog(FICHERO_LOG);

            LogUtil.escribirLog(logger, Level.INFO, String.format(MSG_PUERTO_ESCUCHA, PUERTO));

            while (true) {
                Socket socket = serverSocket.accept();
                Thread gestionarCliente = new Thread(new GestionarCliente(socket, parser, contadorIP, logger));
                gestionarCliente.start();
            }

        } catch (Exception e) {
            LogUtil.escribirLog(logger, Level.SEVERE, e.getMessage(), e);
            throw new ServidorException(e.getMessage(), e);
        }
    }
}