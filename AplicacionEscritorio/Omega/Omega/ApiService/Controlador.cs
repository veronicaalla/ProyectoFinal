using Omega.Model;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Text;
using System.Text.Json;
using System.Threading.Tasks;



namespace Omega.ApiService
{
    internal class Controlador
    {
        private  HttpClient client;
        private  string rutaBasica = "http://172.20.10.3:8080/omega/";

        public Controlador()
        {
            client = new HttpClient();
        }

        //region CLASE USUARIO
        public async Task<Usuario> LoginUsuario(string usuarioOEmail, string clave)
        {

            string url = rutaBasica + "usuarios/login";

            var content = new FormUrlEncodedContent(new[]
            {
            new KeyValuePair<string, string>("usuarioOEmail", usuarioOEmail),
            new KeyValuePair<string, string>("clave", clave)
            });

            HttpResponseMessage response = await client.PostAsync(url, content);

            if (response.IsSuccessStatusCode)
            {
                string jsonResponse = await response.Content.ReadAsStringAsync();
                Usuario usuario = JsonSerializer.Deserialize<Usuario>(jsonResponse, new JsonSerializerOptions { PropertyNameCaseInsensitive = true });
                return usuario;
            }
            else if (response.StatusCode == System.Net.HttpStatusCode.Unauthorized)
            {
                Console.WriteLine("Credenciales inválidas.");
            }
            else if (response.StatusCode == System.Net.HttpStatusCode.NotFound)
            {
                Console.WriteLine("Usuario no encontrado.");
            }

            return null;
        }

        //endregion
    }
}
