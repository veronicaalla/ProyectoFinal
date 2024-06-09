using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Omega.Model
{
    public class Usuario
    {
        //Campos de auditoria
        public DateTime auditCreated { get; set; }
        public string auditCreator { get; set; }

        public DateTime auditUpdated { get; set; }
        public string auditUpdater {  get; set; }


        //Campos especificos del usuario
        public int id { get; set; }
        public string alias { get; set; }
        public string nombre { get; set; }
        public string apellidos { get; set; }
        //public DateTime fechaNacimiento { get; set; }
        public string correo {  get; set; }
        public string clave { get; set; }
        public string telefono { get; set; }
        public int tipo { get; set; }
        public bool publico { get; set; }

        public Usuario()
        {

        }

    }

  
}
