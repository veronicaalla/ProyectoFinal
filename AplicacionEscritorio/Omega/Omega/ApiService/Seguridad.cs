using System;
using System.Text;
using System.Security.Cryptography;


namespace Omega.ApiService
{
    internal class Seguridad
    {
        private static readonly string Key = "Dcnpzt5dJqcYXu7X";

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

        private byte[] GenerateKey(string password)
        {
            using (var sha256 = SHA256.Create())
            {
                return sha256.ComputeHash(Encoding.UTF8.GetBytes(password));
            }
        }

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

        public string Encriptado(string payload)
        {
            return Encrypt(payload, Key);
        }

        public string Desencriptado(string codigo)
        {
            return Decrypt(codigo, Key);
        }
    }

}