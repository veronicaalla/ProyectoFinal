package es.veronica.alvarez.omega

import android.os.Build
import java.security.MessageDigest
import java.util.Base64
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

class Seguridad {

    val key = "Dcnpzt5dJqcYXu7X"


    fun encrypt(strToEncrypt: String, encryptKey : String): String {
        val plainText = strToEncrypt.toByteArray(Charsets.UTF_8)
        val key = generateKey(encryptKey)
        val cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING")
        cipher.init(Cipher.ENCRYPT_MODE, key)
        val cipherText = cipher.iv + cipher.doFinal(plainText) // get IV and concatenate with ciphertext
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Base64.getEncoder().encodeToString(cipherText)
        } else {
            TODO("VERSION.SDK_INT < O")
        }  // Base64 encode data
    }

    fun generateKey(password: String): SecretKeySpec {
        val digest: MessageDigest = MessageDigest.getInstance("SHA-256")
        val bytes = password.toByteArray()
        digest.update(bytes, 0, bytes.size)
        val key = digest.digest()
        val secretKeySpec = SecretKeySpec(key, "AES")
        return secretKeySpec
    }


    fun decrypt(strToDecrypt: String, encryptKey: String): String {
        val cipherText = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Base64.getDecoder().decode(strToDecrypt)
        } else {
            TODO("VERSION.SDK_INT < O")
        }
        val key = generateKey(encryptKey)
        val cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING")

        // Separar IV del texto cifrado
        val ivSize = 16 // tamaño del IV en bytes
        val iv = ByteArray(ivSize)
        val ct = ByteArray(cipherText.size - ivSize)
        System.arraycopy(cipherText, 0, iv, 0, iv.size)
        System.arraycopy(cipherText, iv.size, ct, 0, ct.size)
        val ivParams = IvParameterSpec(iv)

        cipher.init(Cipher.DECRYPT_MODE, key, ivParams)
        val plainText = cipher.doFinal(ct)
        return String(plainText, Charsets.UTF_8)
    }



    fun desencriptado (codigo:String):String{
        return  decrypt(codigo, key)
    }
    fun estructura(error: String, resultado: String): String {
        val valor = "{Response= {$resultado } Error= { $error }}"
        return encrypt(valor, key)
    }

}
