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

    /// <summary>
    /// Formulario que muestra información de un libro reportado como erróneo.
    /// </summary>
    public partial class InfoReportedBook : Form
    {
		
		LibroErroneo libro;
		Controlador controlador;

  	/// <summary>
        /// Constructor vacío.
        /// </summary>
        public InfoReportedBook()
        {
            InitializeComponent();
			libro = new LibroErroneo();
			controlador = new Controlador();
        }

  /// <summary>
        /// Constructor que recibe un libro erróneo.
        /// </summary>
        /// <param name="libroErroneo">El libro erróneo a mostrar.</param>
        
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
  /// <summary>
        /// Muestra un mensaje de error.
        /// </summary>
        /// <param name="mensaje">El mensaje de error a mostrar.</param>
		 public void mensajeError(string mensaje)
        {
            MessageBox.Show(mensaje, "ERROR", MessageBoxButtons.OK, MessageBoxIcon.Error);
        }

       
    }
}
