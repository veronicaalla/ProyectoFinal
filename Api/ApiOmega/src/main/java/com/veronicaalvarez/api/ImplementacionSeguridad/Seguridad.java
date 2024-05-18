package com.veronicaalvarez.api.ImplementacionSeguridad;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.util.Base64;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

public class Seguridad {
    private static final String key = "Dcnpzt5dJqcYXu7X";

    /*
   private static String readPrivateKeyFromFile() throws IOException {
   Path currentPath = Paths.get("").toAbsolutePath();
   Path path = Paths.get(currentPath+"\\src\\main\\java\\com\\diego\\fernandez\\proyectofinal\\Contrasenia_privada.txt");
   if (!Files.exists(path) || !Files.isRegularFile(path)) {
   throw new IllegalArgumentException("El archivo especificado no existe.");
   }
    try (BufferedReader reader = new BufferedReader(new FileReader(path.toFile()))) { return reader.readLine();
    // Se espera que la clave privada esté en la primera línea del archivo } }
    // Método para obtener la clave privada a partir de una cadena Base64

    private static PrivateKey getPrivateKeyFromString(String privateKeyStr) throws NoSuchAlgorithmException, InvalidKeySpecException
    { byte[] privateKeyBytes = Base64.getDecoder().decode(privateKeyStr);
    PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
    return keyFactory.generatePrivate(keySpec); }

    private static byte[] serializeObject(Object obj) throws IOException
    {
    try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
    ObjectOutputStream oos = new ObjectOutputStream(baos))
    { oos.writeObject(obj);
    return baos.toByteArray(); } }

    // Método para cifrar datos utilizando una clave privada RSA
    public static String encryptData(Object data)
    {
    try {
        String privateKeyStr = readPrivateKeyFromFile();
        PrivateKey privateKey = getPrivateKeyFromString(privateKeyStr);
        // Inicializar el cifrado RSA Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding"); c
        ipher.init(Cipher.ENCRYPT_MODE, privateKey);
        // Serializar el objeto a bytes
        byte[] objectBytes = serializeObject(data);
        // Cifrar los bytes del objeto
        byte[] encryptedBytes = cipher.doFinal(objectBytes);
        // Devolver los bytes cifrados como una cadena Base64
        return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (IOException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException | InvalidKeySpecException e) {
            throw new RuntimeException("Error al cifrar los datos", e);
        }
    }
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

    public String encriptado (Map<String, Object> map){
        return encrypt( map, key);
    }
}
