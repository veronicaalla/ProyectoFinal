using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Omega.Model
{
    public class Comentario
    {
        public int Id { get; set; }

        public int IdLibro { get; set; }

        public int IdUsuario { get; set; }

        public string comentario { get; set; } // Cambié el nombre de la propiedad para que sea más descriptivo

        public DateTime Fecha { get; set; } // DateTime en lugar de LocalDateTime para C#

    }
}
