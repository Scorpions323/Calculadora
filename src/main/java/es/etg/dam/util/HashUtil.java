package es.etg.dam.util;

import java.security.MessageDigest;

public class HashUtil {

    private static final String ALGORITMO = "SHA-256";
    private static final String CODIFICACION = "UTF-8";
    private static final int BYTE_A_HEX = 2;
    private static final int MASCARA_BYTE = 0xFF;
    private static final int UN_DIGITO_HEX = 1;
    private static final char CARACTER_RELLENO = '0';

    public static String convertirSHA256(String cadena) throws Exception {
        MessageDigest md = MessageDigest.getInstance(ALGORITMO);
        byte[] hash = md.digest(cadena.getBytes(CODIFICACION));
        return bytesToHex(hash);
    }

    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(BYTE_A_HEX * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(MASCARA_BYTE & hash[i]);
            if (hex.length() == UN_DIGITO_HEX) {
                hexString.append(CARACTER_RELLENO);
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}