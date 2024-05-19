using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Omega.Model
{
    public class ComentarioReportado
    {
        public int Id { get; set; }

        public DateTime AuditCreated { get; set; }

        public string AuditCreator { get; set; }

        public DateTime AuditUpdated { get; set; }

        public string AuditUpdater { get; set; }

        public int IdComentario { get; set; }

        public int IdReportante { get; set; }

        public bool? Ofensivo { get; set; }

    }
}
