package es.etg.dam.util;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;

public class CifradoUtil {

    private static final String ALGORITMO = "AES";
    private static final String MODO = "AES/ECB/PKCS5Padding";
    private static final String CODIFICACION = "UTF-8";
    private static final int LONGITUD_CLAVE = 16;
    private static final int BITS_AES = 128;
    public static final String PASS = "1234567890123456";

    public static String cifrar(String mensaje, String pass) throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITMO);
        keyGenerator.init(BITS_AES);
        Key key = keyGenerator.generateKey();
        key = new SecretKeySpec(pass.getBytes(CODIFICACION), 0, LONGITUD_CLAVE, ALGORITMO);

        Cipher aes = Cipher.getInstance(MODO);
        aes.init(Cipher.ENCRYPT_MODE, key);

        byte[] cifrado = aes.doFinal(mensaje.getBytes(CODIFICACION));
        return Base64.getEncoder().encodeToString(cifrado);
    }

    public static String descifrar(String mensaje, String pass) throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITMO);
        keyGenerator.init(BITS_AES);
        Key key = keyGenerator.generateKey();
        key = new SecretKeySpec(pass.getBytes(), 0, LONGITUD_CLAVE, ALGORITMO);

        Cipher aes = Cipher.getInstance(MODO);
        aes.init(Cipher.DECRYPT_MODE, key);

        byte[] cifrado = Base64.getDecoder().decode(mensaje);
        byte[] descifrado = aes.doFinal(cifrado);

        return new String(descifrado, CODIFICACION);
    }
}