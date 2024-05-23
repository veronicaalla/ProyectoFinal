using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Omega.Model
{
    public class Libro
    {
        public int Id { get; set; }
        public DateTime AuditCreated { get; set; }
        public string AuditCreator { get; set; }
        public DateTime AuditUpdated { get; set; }
        public string AuditUpdater { get; set; }
        public string ISBN { get; set; }
        public string Titulo { get; set; }
        public string Autor { get; set; }
        public string Descripcion { get; set; }
        public int Genero { get; set; }
        public DateTime? FechaPublicacion { get; set; }
        public int Paginas { get; set; }
    }
}
