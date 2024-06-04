package com.veronicaalvarez.api.ImplementacionSeguridad;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.util.Base64;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

/**
 * Clase que proporciona métodos para encriptar y desencriptar datos utilizando el algoritmo AES.
 */
public class Seguridad {

    /**
     * Clave secreta utilizada para encriptar y desencriptar los datos.
     */
    private static final String key = "Dcnpzt5dJqcYXu7X";

  /**
     * Encripta un mapa de datos en una cadena encriptada utilizando la clave proporcionada.
     * 
     * @param data    Mapa de datos a encriptar
     * @param encryptKey Clave utilizada para encriptar los datos
     * @return Cadena encriptada
     */
    public String encrypt(Map<String, Object> data, String encryptKey) {
        try {
            StringBuilder objeto = new StringBuilder();
            for (Map.Entry<String, Object> entry : data.entrySet()) {
                Object value = entry.getValue();
                Field[] fields = value.getClass().getDeclaredFields();
                for (Field field : fields) {
                    field.setAccessible(true);
                    String fieldName = field.getName();
                    Object fieldValue = field.get(value);
                    objeto.append("\"").append(fieldName).append("\"").append(":").append("\"").append(fieldValue).append("\"").append(", ");
                }
            }
            // Remove the last comma and add curly braces
            objeto.setLength(objeto.length() - 2);
            objeto.insert(0, "{");
            objeto.append("}");

            String strToEncrypt = objeto.toString();

            byte[] plainText = strToEncrypt.getBytes("UTF-8");
            SecretKeySpec key = generateKey(encryptKey);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] iv = cipher.getIV();
            byte[] cipherText = cipher.doFinal(plainText);
            byte[] cipherTextWithIv = new byte[iv.length + cipherText.length];
            System.arraycopy(iv, 0, cipherTextWithIv, 0, iv.length);
            System.arraycopy(cipherText, 0, cipherTextWithIv, iv.length, cipherText.length);
            return Base64.getEncoder().encodeToString(cipherTextWithIv);
        } catch (Exception e) {
            throw new RuntimeException("Error encrypting data", e);
        }
    }

    /**
     * Genera una clave secreta a partir de una contraseña utilizando el algoritmo SHA-256.
     * 
     * @param password Contraseña utilizada para generar la clave secreta
     * @return Clave secreta generada
     * @throws NoSuchAlgorithmException Si no se encuentra el algoritmo SHA-256
     */
    private SecretKeySpec generateKey(String password) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] bytes = password.getBytes();
        digest.update(bytes, 0, bytes.length);
        byte[] key = digest.digest();
        return new SecretKeySpec(key, "AES");
    }


    /**
     * Desencripta una cadena encriptada utilizando la clave proporcionada.
     * 
     * @param strToDecrypt Cadena encriptada a desencriptar
     * @param encryptKey Clave utilizada para desencriptar los datos
     * @return Cadena desencriptada
     */
    public String decrypt(String strToDecrypt, String encryptKey) {
        try {
            byte[] cipherText = Base64.getDecoder().decode(strToDecrypt);
            SecretKeySpec key = generateKey(encryptKey);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");

            // Separar IV del texto cifrado
            int ivSize = 16;
            byte[] iv = new byte[ivSize];
            byte[] ct = new byte[cipherText.length - ivSize];
            System.arraycopy(cipherText, 0, iv, 0, ivSize);
            System.arraycopy(cipherText, ivSize, ct, 0, ct.length);
            IvParameterSpec ivParams = new IvParameterSpec(iv);

            cipher.init(Cipher.DECRYPT_MODE, key, ivParams);
            byte[] plainText = cipher.doFinal(ct);
            return new String(plainText, "UTF-8");
        } catch (Exception e) {
            throw new RuntimeException("Error decrypting data", e);
        }
    }


    /**
     * Desencripta una cadena encriptada utilizando la clave secreta predeterminada.
     * 
     * @param codigo Cadena encriptada a desencriptar
     * @return Cadena desencriptada
     */
    public String desencriptado(String codigo) {
        return decrypt(codigo, key);
    }

    /**
     * Encripta un mapa de datos en una cadena encriptada utilizando la clave secreta predeterminada.
     * 
     * @param map Mapa de datos a encriptar
     * @return Cadena encriptada
     */
    public String encriptado (Map<String, Object> map){
        return encrypt( map, key);
    }
}
