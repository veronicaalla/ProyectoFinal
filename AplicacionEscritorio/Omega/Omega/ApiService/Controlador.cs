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
        private HttpClient client;
        private string rutaBasica = "http://10.0.2.2:8080/omega/";
        public Seguridad seguridad;

        public Controlador()
        {
            client = new HttpClient();
            seguridad = new Seguridad();
        }

        /// <summary>
        /// Autentica un usuario mediante su nombre de usuario o correo electrónico y su clave.
        /// </summary>
        /// <param name="usuarioOEmail">El nombre de usuario o correo electrónico.</param>
        /// <param name="clave">La clave del usuario.</param>
        /// <returns>Un objeto Usuario si la autenticación es exitosa; null en caso contrario.</returns>
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

        /// <summary>
        /// Obtiene una lista de todos los usuarios.
        /// </summary>
        /// <returns>Una lista de objetos Usuario.</returns>
        public async Task<List<Usuario>> GetUsuarios()
        {
            string url = rutaBasica + "usuarios";

            HttpResponseMessage response = await client.GetAsync(url);
            response.EnsureSuccessStatusCode();

            string responseJson = await response.Content.ReadAsStringAsync();

            List<Usuario> usuarios = JsonConvert.DeserializeObject<List<Usuario>>(responseJson);
            return usuarios;

        }

        /// <summary>
        /// Obtiene un usuario por su ID.
        /// </summary>
        /// <param name="id">El ID del usuario.</param>
        /// <returns>Un objeto Usuario si se encuentra; lanza una excepción en caso de error.</returns>
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


        /// <summary>
        /// Crea un nuevo usuario.
        /// </summary>
        /// <param name="usuario">El objeto Usuario a crear.</param>
        /// <returns>El contenido de la respuesta de la API si la creación es exitosa; lanza una excepción en caso de error.</returns>
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

        /// <summary>
        /// Modifica un usuario existente.
        /// </summary>
        /// <param name="id">El ID del usuario a modificar.</param>
        /// <param name="usuarioModificado">El objeto Usuario con los datos modificados.</param>
        /// <returns>true si la modificación es exitosa; false en caso contrario.</returns>
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


        /// <summary>
        /// Elimina un usuario por su ID.
        /// </summary>
        /// <param name="usuarioId">El ID del usuario a eliminar.</param>
        /// <returns>El contenido de la respuesta de la API si la eliminación es exitosa; un mensaje de error en caso contrario.</returns>
        public async Task<string> EliminarUsuario(int usuarioId)
        {
            string url = $"{rutaBasica}usuarios/usuario/{usuarioId}";

            HttpResponseMessage response = await client.DeleteAsync(url);

            if (response.IsSuccessStatusCode)
            {
                string responseBody = await response.Content.ReadAsStringAsync();
                return $"{responseBody}";
            }
            else
            {
                return $"Error: {response.StatusCode}";
            }
        }

        /// <summary>
        /// Obtiene una lista de todos los comentarios reportados.
        /// </summary>
        /// <returns>Una lista de objetos ComentarioReportado.</returns>
        public async Task<List<ComentarioReportado>> getComentariosReportados()
        {
            string url = $"{rutaBasica}comentarioreportado";

            HttpResponseMessage response = await client.GetAsync(url);

            if (response.IsSuccessStatusCode)
            {
                string jsonResponse = await response.Content.ReadAsStringAsync();
                List<ComentarioReportado> comentariosReportados = JsonConvert.DeserializeObject<List<ComentarioReportado>>(jsonResponse);
                return comentariosReportados;
            }
            else if (response.StatusCode == System.Net.HttpStatusCode.NoContent)
            {
                Console.WriteLine("No hay comentarios reportados.");
            }
            else
            {
                Console.WriteLine($"Error al obtener comentarios reportados: {response.StatusCode}");
            }

            return null;
        }

        /// <summary>
        /// Obtiene un comentario por su ID.
        /// </summary>
        /// <param name="id">El ID del comentario.</param>
        /// <returns>Un objeto Comentario si se encuentra; null en caso contrario.</returns>
        public async Task<Comentario> ObtenerComentarioPorId(int id)
        {
            string url = $"{rutaBasica}comentarios/comentario/{id}";

            HttpResponseMessage response = await client.GetAsync(url);

            if (response.IsSuccessStatusCode)
            {
                string jsonResponse = await response.Content.ReadAsStringAsync();
                Comentario comentario = JsonConvert.DeserializeObject<Comentario>(jsonResponse);
                return comentario;
            }
            else if (response.StatusCode == System.Net.HttpStatusCode.NotFound)
            {
                Console.WriteLine("Comentario no encontrado.");
            }
            else
            {
                Console.WriteLine($"Error al obtener el comentario: {response.StatusCode}");
            }

            return null;
        }


        /// <summary>
        /// Obtiene un comentario reportado por su ID.
        /// </summary>
        /// <param name="id">El ID del comentario reportado.</param>
        /// <returns>Un objeto ComentarioReportado si se encuentra; null en caso contrario.</returns>
        public async Task<ComentarioReportado> ObtenerComentarioReportadoPorId(int id)
        {
            string url = $"{rutaBasica}comentarioreportado/comentario/{id}";

            HttpResponseMessage response = await client.GetAsync(url);

            if (response.IsSuccessStatusCode)
            {
                string jsonResponse = await response.Content.ReadAsStringAsync();
                ComentarioReportado comentarioReportado = JsonConvert.DeserializeObject<ComentarioReportado>(jsonResponse);
                return comentarioReportado;
            }
            else if (response.StatusCode == System.Net.HttpStatusCode.NotFound)
            {
                Console.WriteLine("Comentario reportado no encontrado.");
            }
            else
            {
                Console.WriteLine($"Error al obtener el comentario reportado: {response.StatusCode}");
            }

            return null;
        }

        /// <summary>
        /// Edita un comentario reportado en la API.
        /// </summary>
        /// <param name="idUsuario">El ID del usuario que reportó el comentario.</param>
        /// <param name="comentarioReportadoNuevo">El nuevo objeto ComentarioReportado con los datos actualizados.</param>
        /// <returns>Una cadena que representa la respuesta JSON de la API después de editar el comentario reportado.</returns>
        /// <exception cref="ArgumentNullException">Si el parámetro <paramref name="comentarioReportadoNuevo"/> es nulo.</exception>
        /// <exception cref="HttpRequestException">Si ocurre un error al enviar la solicitud HTTP.</exception>
        public  async Task<string> EditarComentarioReportadoAsync(int idUsuario, ComentarioReportado comentarioReportadoNuevo)
        {

            string url = $"{rutaBasica}comentarioreportado/editar/{idUsuario}";

            // Convertir el objeto a JSON
            string jsonBody = JsonConvert.SerializeObject(comentarioReportadoNuevo);

           
            // Crear la solicitud HTTP
            var request = new HttpRequestMessage
            {
                Method = HttpMethod.Put,
                RequestUri = new Uri(url),
                Content = new StringContent(jsonBody, Encoding.UTF8, "application/json")
            };

            // Enviar la solicitud y obtener la respuesta
            var response = await client.SendAsync(request);

            // Leer y devolver la respuesta como cadena
            return await response.Content.ReadAsStringAsync();
        }


        /// <summary>
        /// Obtiene una lista de todos los libros erróneos.
        /// </summary>
        /// <returns>Una lista de objetos LibroErroneo.</returns>
        public async Task<List<LibroErroneo>> ObtenerLibrosErroneos()
        {
            var url = $"{rutaBasica}libroerroneno";
            HttpResponseMessage response = await client.GetAsync(url);
            response.EnsureSuccessStatusCode();

            string responseBody = await response.Content.ReadAsStringAsync();
            List<LibroErroneo> librosErroneos = JsonConvert.DeserializeObject<List<LibroErroneo>>(responseBody);

            return librosErroneos;
        }

        /// <summary>
        /// Obtiene un libro erróneo por su ID.
        /// </summary>
        /// <param name="id">El ID del libro erróneo.</param>
        /// <returns>Un objeto LibroErroneo si se encuentra; null en caso contrario.</returns>
        public async Task<LibroErroneo> ObtenerLibroErroneoPorId(int id)
        {
            var url = $"{rutaBasica}libroerroneno/id/{id}";
            HttpResponseMessage response = await client.GetAsync(url);
            if (response.StatusCode == System.Net.HttpStatusCode.NotFound)
            {
                return null;
            }
            response.EnsureSuccessStatusCode();

            string responseBody = await response.Content.ReadAsStringAsync();
            LibroErroneo libroErroneo = JsonConvert.DeserializeObject<LibroErroneo>(responseBody);

            return libroErroneo;
        }

        /// <summary>
        /// Obtiene un libro por su ID.
        /// </summary>
        /// <param name="id">El ID del libro.</param>
        /// <returns>Un objeto Libro si se encuentra; null en caso contrario.</returns>
        public async Task<Libro> ObtenerLibroPorId(int id)
        {
            var url = $"{rutaBasica}libros/id/{id}";
            HttpResponseMessage response = await client.GetAsync(url);
            if (response.StatusCode == System.Net.HttpStatusCode.NotFound)
            {
                return null;
            }
            response.EnsureSuccessStatusCode();

            string responseBody = await response.Content.ReadAsStringAsync();
            Libro libro = JsonConvert.DeserializeObject<Libro>(responseBody);

            return libro;
        }

        /// <summary>
        /// Obtiene una lista de todos los géneros.
        /// </summary>
        /// <returns>Una lista de objetos Genero.</returns>
        public async Task<List<Genero>> ObtenerGeneros()
        {
            var url = $"{rutaBasica}generos";
            HttpResponseMessage response = await client.GetAsync(url);
            response.EnsureSuccessStatusCode();

            string responseBody = await response.Content.ReadAsStringAsync();
            List<Genero> generos = JsonConvert.DeserializeObject<List<Genero>>(responseBody);

            return generos;
        }

        /// <summary>
        /// Obtiene un género por su ID.
        /// </summary>
        /// <param name="id">El ID del género.</param>
        /// <returns>Un objeto Genero si se encuentra; null en caso contrario.</returns>
        public async Task<Genero> ObtenerGeneroPorId(int id)
        {
            var url = $"{rutaBasica}generos/id/{id}";
            HttpResponseMessage response = await client.GetAsync(url);
            if (response.StatusCode == System.Net.HttpStatusCode.NotFound)
            {
                return null;
            }
            response.EnsureSuccessStatusCode();

            string responseBody = await response.Content.ReadAsStringAsync();
            Genero genero = JsonConvert.DeserializeObject<Genero>(responseBody);

            return genero;
        }


        /// <summary>
        /// Obtiene el ID de género por su nombre.
        /// </summary>
        /// <param name="nombre">El nombre del género.</param>
        /// <returns>El ID del género como un entero opcional. Si el género no se encuentra, se devuelve null.</returns>
        public async Task<int?> ObtenerIdGeneroPorNombreAsync(string nombre)
        {
            try
            {
                var url = $"{rutaBasica}generos/nombre/{nombre}";

                var response = await client.GetAsync(url);

                if (response.IsSuccessStatusCode)
                {
                    string result = await response.Content.ReadAsStringAsync();
                    int idGenero = JsonConvert.DeserializeObject<int>(result);
                    return idGenero;
                }
                else if (response.StatusCode == System.Net.HttpStatusCode.NotFound)
                {
                    Console.WriteLine($"Genero con nombre '{nombre}' no encontrado.");
                    return null;
                }
                else
                {
                    Console.WriteLine($"Error: {response.StatusCode}");
                    return null;
                }
            }
            catch (Exception ex)
            {
                Console.WriteLine($"Exception: {ex.Message}");
                return null;
            }
        }


        /// <summary>
        /// Edita un libro.
        /// </summary>
        /// <param name="userId">El ID del usuario que edita el libro.</param>
        /// <param name="libroActualizado">El objeto Libro con los datos actualizados.</param>
        /// <returns>Un mensaje indicando el resultado de la operación.</returns>
        public async Task<string> EditarLibroAsync(int userId, Libro libroActualizado)
        {
            try
            {
                var url = $"{rutaBasica}libros/{userId}";
                // Serializar el objeto libro a JSON
                var json = JsonConvert.SerializeObject(libroActualizado);
                var content = new StringContent(json, Encoding.UTF8, "application/json");

                // Realizar la solicitud PUT
                HttpResponseMessage response = await client.PutAsync(url, content);

                // Verificar la respuesta
                if (response.IsSuccessStatusCode)
                {
                    return "El libro se ha modificado correctamente";
                }
                else
                {
                    return $"Error: {response.StatusCode}";
                }
            }
            catch (Exception ex)
            {
               return $"Excepción: {ex.Message}";
            }
        }


        /// <summary>
        /// Edita un libro erróneo en la base de datos.
        /// </summary>
        /// <param name="libroErroneo">El objeto libro erróneo con los nuevos datos.</param>
        /// <param name="idUsuario">El ID del usuario que está editando el libro erróneo.</param>
        /// <returns>Un mensaje de éxito o error como una cadena.</returns>
        public async Task<string> EditarLibroErroneoAsync(LibroErroneo libroErroneo, int idUsuario)
        {
            try
            {
                var url = $"{rutaBasica}libroerroneno/editar/{libroErroneo.id}?idUsuario={idUsuario}";

                var jsonContent = JsonConvert.SerializeObject(libroErroneo);
                var contentString = new StringContent(jsonContent, Encoding.UTF8, "application/json");

                var response = await client.PutAsync(url, contentString);

                if (response.IsSuccessStatusCode)
                {
                    string result = await response.Content.ReadAsStringAsync();
                    return "Resultado: " + result;
                }
                else if (response.StatusCode == System.Net.HttpStatusCode.NotFound)
                {
                    string result = await response.Content.ReadAsStringAsync();
                    return $"Error: {response.StatusCode}, {result}";
                }
                else
                {
                    return $"Error: {response.StatusCode}";
                }
            }
            catch (Exception ex)
            {
                return $"Exception: {ex.Message}";
            }
        }
    }

}

