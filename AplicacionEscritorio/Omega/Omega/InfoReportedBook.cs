using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Omega
{
    public partial class InfoReportedBook : Form
    {
		
		LibroErroneo libro;
		Controlador controlador;
		
        public InfoReportedBook()
        {
            InitializeComponent();
			libro = new LibroErroneo();
			controlador = new Controlador();
        }
		
		public InfoReportedBook( LibroErroneo libroErroneo):this()
        {
            this.libro = libroErroneo;
			
			//Como el libro siempre va a existir, no hace falta verificar
			
        }
		

        private void btnAceptar_Click(object sender, EventArgs e)
        {

        }

        private void btnCancelar_Click(object sender, EventArgs e)
        {
			this.Close();
        }
		
		
		//Métodos auxiliares
		 public void mensajeError(string mensaje)
        {
            MessageBox.Show(mensaje, "ERROR", MessageBoxButtons.OK, MessageBoxIcon.Error);
        }

       
    }
}
