using System;
using System.Text;
using System.Security.Cryptography;


namespace Omega.ApiService
{
    internal class Seguridad
    {
        private static readonly string Key = "Dcnpzt5dJqcYXu7X";

        /// <summary>
        /// Encripta una cadena utilizando una clave de cifrado especificada.
        /// </summary>
        /// <param name="strToEncrypt">La cadena a encriptar.</param>
        /// <param name="encryptKey">La clave de cifrado.</param>
        /// <returns>La cadena encriptada en formato Base64.</returns>
        public string Encrypt(string strToEncrypt, string encryptKey)
        {
            try
            {
                byte[] plainTextBytes = Encoding.UTF8.GetBytes(strToEncrypt);
                using (var aes = Aes.Create())
                {
                    aes.Mode = CipherMode.CBC;
                    aes.Padding = PaddingMode.PKCS7;
                    aes.Key = GenerateKey(encryptKey);

                    aes.GenerateIV();
                    byte[] iv = aes.IV;
                    using (var encryptor = aes.CreateEncryptor(aes.Key, iv))
                    {
                        byte[] cipherTextBytes = encryptor.TransformFinalBlock(plainTextBytes, 0, plainTextBytes.Length);
                        byte[] cipherTextWithIv = new byte[iv.Length + cipherTextBytes.Length];
                        Buffer.BlockCopy(iv, 0, cipherTextWithIv, 0, iv.Length);
                        Buffer.BlockCopy(cipherTextBytes, 0, cipherTextWithIv, iv.Length, cipherTextBytes.Length);
                        return Convert.ToBase64String(cipherTextWithIv);
                    }
                }
            }
            catch (Exception ex)
            {
                throw new InvalidOperationException("Error encrypting data", ex);
            }
        }

        /// <summary>
        /// Genera una clave hash SHA-256 a partir de una contraseña.
        /// </summary>
        /// <param name="password">La contraseña para generar la clave.</param>
        /// <returns>Un arreglo de bytes representando la clave hash.</returns>
        private byte[] GenerateKey(string password)
        {
            using (var sha256 = SHA256.Create())
            {
                return sha256.ComputeHash(Encoding.UTF8.GetBytes(password));
            }
        }

        /// <summary>
        /// Desencripta una cadena encriptada utilizando una clave de cifrado especificada.
        /// </summary>
        /// <param name="strToDecrypt">La cadena a desencriptar.</param>
        /// <param name="encryptKey">La clave de cifrado.</param>
        /// <returns>La cadena desencriptada.</returns>
        public string Decrypt(string strToDecrypt, string encryptKey)
        {
            try
            {
                byte[] cipherTextWithIv = Convert.FromBase64String(strToDecrypt);
                using (var aes = Aes.Create())
                {
                    aes.Mode = CipherMode.CBC;
                    aes.Padding = PaddingMode.PKCS7;
                    aes.Key = GenerateKey(encryptKey);

                    int ivSize = aes.BlockSize / 8;
                    byte[] iv = new byte[ivSize];
                    byte[] cipherTextBytes = new byte[cipherTextWithIv.Length - ivSize];
                    Buffer.BlockCopy(cipherTextWithIv, 0, iv, 0, ivSize);
                    Buffer.BlockCopy(cipherTextWithIv, ivSize, cipherTextBytes, 0, cipherTextBytes.Length);

                    using (var decryptor = aes.CreateDecryptor(aes.Key, iv))
                    {
                        byte[] plainTextBytes = decryptor.TransformFinalBlock(cipherTextBytes, 0, cipherTextBytes.Length);
                        return Encoding.UTF8.GetString(plainTextBytes);
                    }
                }
            }
            catch (Exception ex)
            {
                throw new InvalidOperationException("Error decrypting data", ex);
            }
        }

        /// <summary>
        /// Encripta una cadena utilizando una clave predefinida.
        /// </summary>
        /// <param name="payload">La cadena a encriptar.</param>
        /// <returns>La cadena encriptada en formato Base64.</returns>
        public string Encriptado(string payload)
        {
            return Encrypt(payload, Key);
        }

        /// <summary>
        /// Desencripta una cadena encriptada utilizando una clave predefinida.
        /// </summary>
        /// <param name="codigo">La cadena encriptada a desencriptar.</param>
        /// <returns>La cadena desencriptada.</returns>
        public string Desencriptado(string codigo)
        {
            return Decrypt(codigo, Key);
        }
    }

}