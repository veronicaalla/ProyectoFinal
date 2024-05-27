﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Omega.Model
{
    public class LibroErroneo
    {
        public int id { get; set; }
        public DateTime auditCreated { get; set; }
        public string auditCreator { get; set; }
        public DateTime auditUpdated { get; set; }

        public string auditUpdater { get; set; }
        public int idLibro { get; set; }
        public int idReportante { get; set; }
        public bool? resuelto { get; set; }
    }
}