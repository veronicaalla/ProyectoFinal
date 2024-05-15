package com.veronicaalvarez.api.ImplementacionSeguridad;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.util.Base64;
import java.security.NoSuchAlgorithmException;

public class Seguridad {
    private static final String key = "Dcnpzt5dJqcYXu7X";

    public String encrypt(String strToEncrypt, String encryptKey) {
        try {
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

    private SecretKeySpec generateKey(String password) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] bytes = password.getBytes();
        digest.update(bytes, 0, bytes.length);
        byte[] key = digest.digest();
        return new SecretKeySpec(key, "AES");
    }

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


    public String desencriptado(String codigo) {
        return decrypt(codigo, key);
    }

    public String estructura (String error, String resultado){
        String valor = "{Response= {" + resultado + " } Error= { " +error+ " }}";
        return encrypt(valor, key);
    }
}
