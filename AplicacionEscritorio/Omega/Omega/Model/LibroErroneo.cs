using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Omega.Model
{
    public class LibroErroneo
    {
        public int Id { get; set; }
        public DateTime AuditCreated { get; set; }
        public string AuditCreator { get; set; }
        public DateTime AuditUpdated { get; set; }

        public string AuditUpdater { get; set; }
        public int IdLibro { get; set; }
        public int IdReportante { get; set; }
        public bool? Resuelto { get; set; }
    }
}
