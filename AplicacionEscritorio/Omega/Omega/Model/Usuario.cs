using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Omega.Model
{
    internal class Usuario
    {
        //Campos de auditoria
        public DateTime AuditCreated { get; set; }
        public string AuditCreator { get; set; }

        public DateTime AuditUpdated { get; set; }
        public string AuditUpdater {  get; set; }


        //Campos especificos del usuario
        public int Id { get; set; }
        public string Alias { get; set; }
        public string Nombre { get; set; }
        public string Apellidos { get; set; }
        public DateTime FechaNacimiento { get; set; }
        public string Correo {  get; set; }
        public string Clave { get; set; }
        public string Telefono { get; set; }
        public int Tipo { get; set; }
        public bool Publico { get; set; }

        public Usuario()
        {

        }


       
    }

    //Clase de respuesta 
    public class UsuarioResponse
    {
        public int Id { get; set; }
        public string Alias { get; set; }
        public string Nombre { get; set; }
        public string Apellidos { get; set; }
        public DateTime FechaNacimiento { get; set; }
        public string Correo { get; set; }
        public string Clave { get; set; }
        public string Telefono { get; set; }
        public int Tipo { get; set; }
        public bool Publico { get; set; }
    }
}
