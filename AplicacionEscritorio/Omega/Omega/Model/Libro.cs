using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Omega.Model
{
    public class Libro
    {
        public int id { get; set; }
        public DateTime auditCreated { get; set; }
        public string auditCreator { get; set; }
        public DateTime auditUpdated { get; set; }
        public string auditUpdater { get; set; }
        public string isbn { get; set; }
        public string titulo { get; set; }
        public string autor { get; set; }
        public string descripcion { get; set; }
        public int genero { get; set; }
        public DateTime? fechaPublicacion { get; set; }
        public int paginas { get; set; }
    }
}
