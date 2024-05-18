using Newtonsoft.Json;
using Omega.Model;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Security.Policy;
using System.Text;
using System.Text.Json;
using System.Threading.Tasks;



namespace Omega.ApiService
{
    internal class Controlador
    {
        private  HttpClient client;
        private  string rutaBasica = "http://172.20.10.3:8080/omega/";
        public Seguridad seguridad;

        public Controlador()
        {
            client = new HttpClient();
            seguridad = new Seguridad();
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
                Usuario usuario = System.Text.Json.JsonSerializer.Deserialize<Usuario>(jsonResponse, new JsonSerializerOptions { PropertyNameCaseInsensitive = true });
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


        public async Task<List<Usuario>> GetPerfiles()
        {
            string url = rutaBasica + "usuarios";

            HttpResponseMessage response = await client.GetAsync(url);
            response.EnsureSuccessStatusCode();

            string responseJson = await response.Content.ReadAsStringAsync();

            List<Usuario> usuarios = JsonConvert.DeserializeObject<List<Usuario>>(responseJson);
            return usuarios;

        }


        public async Task<Usuario> ObtenerUsuarioPorIdAsync(int id)
        {
            string url = rutaBasica + $"usuarios/usuario/{id}";

            HttpResponseMessage response = await client.GetAsync(url);

            if (response.IsSuccessStatusCode)
            {
                string jsonResponse = await response.Content.ReadAsStringAsync();
                Usuario usuario = JsonConvert.DeserializeObject<Usuario>(jsonResponse);
                return usuario;
            }
            else
            {
                // Manejar errores aquí, por ejemplo, lanzar una excepción o devolver null
                throw new Exception("Error al obtener el usuario: " + response.ReasonPhrase);
            }
        }


        public async Task<string> CreateUsuarioAsync(Usuario usuario)
        {
            string url = $"{rutaBasica}usuarios"; // Cambia esto a la URL correcta de tu API

            var jsonContent = JsonConvert.SerializeObject(usuario);
            var contentString = new StringContent(jsonContent, Encoding.UTF8, "application/json");

            HttpResponseMessage response = await client.PostAsync(url, contentString);

            if (response.IsSuccessStatusCode)
            {
                return await response.Content.ReadAsStringAsync();
            }
            else
            {
                // Manejar errores aquí, por ejemplo, lanzar una excepción o devolver un mensaje de error
                string responseContent = await response.Content.ReadAsStringAsync();
                throw new Exception($"Error al crear el usuario: {responseContent}");
            }
        }

        public async Task<bool> ModificarUsuarioAsync(int id, Usuario usuarioModificado)
        {
            string url = $"{rutaBasica}usuarios/modificar/{id}";

            var jsonContent = JsonConvert.SerializeObject(usuarioModificado);
            var contentString = new StringContent(jsonContent, Encoding.UTF8, "application/json");

            HttpResponseMessage response = await client.PutAsync(url, contentString);

            if (response.IsSuccessStatusCode)
            {
                return true;
            }

            // Puedes agregar lógica adicional para manejar errores
            string responseContent = await response.Content.ReadAsStringAsync();
            Console.WriteLine($"Error: {responseContent}");
            return false;
        }

        public async Task<string> LlamarMetodoEliminarUsuario(int usuarioId)
        {
            string url = $"{rutaBasica}usuario/{usuarioId}";

            try
            {
                HttpResponseMessage response = await client.DeleteAsync(url); 

                if (response.IsSuccessStatusCode)
                {
                    string responseBody = await response.Content.ReadAsStringAsync();
                   return $"Respuesta del servidor: {responseBody}";
                }
                else
                {
                    return $"Error: {response.StatusCode}";
                }
            }
            catch (Exception e)
            {
                return $"Error al realizar la solicitud: {e.Message}";
            }
        }

        //endregion
    }
}
