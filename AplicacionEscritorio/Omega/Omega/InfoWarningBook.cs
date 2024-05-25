using Omega.ApiService;
using Omega.Model;
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
    /// Formulario para mostrar información sobre un libro marcado como erróneo.
    /// </summary>
    public partial class InfoWarningBook : Form
    {
        LibroErroneo libroErroneo;
        Controlador controlador;

        /// <summary>
        /// Constructor por defecto de la clase InfoWarningBook.
        /// </summary>
        public InfoWarningBook()
        {
            InitializeComponent();
            libroErroneo = new LibroErroneo();
            controlador = new Controlador();
        }


        /// <summary>
        /// Constructor sobrecargado de la clase InfoWarningBook que recibe información sobre un libro erróneo.
        /// </summary>
        /// <param name="libro">El libro erróneo cuya información se va a mostrar.</param>
        public InfoWarningBook(LibroErroneo libro) : this()
        {
            this.libroErroneo = libro;
            asignarDatos();
        }

        /// <summary>
        /// Asigna los datos del libro erróneo a los campos del formulario.
        /// </summary>
        public async void asignarDatos()
        {
            //Le asignamos los datos 
            //Obtenemos el titulo del libro
            string titulo = (await controlador.ObtenerLibroPorId(libroErroneo.idLibro)).titulo;
            txtTitulo.Text = titulo;

            //Obtenemos el usuario
            string aliasUsuario = (await controlador.ObtenerUsuarioPorIdAsync(libroErroneo.idReportante)).alias;
            txtUsuario.Text = aliasUsuario;

            if (libroErroneo.resuelto != null)
            {
                if (libroErroneo.resuelto.HasValue == true)
                {
                    cmbResuelto.Text = "SI";
                }
                else
                {
                    cmbResuelto.Text = "NO";
                }
            }
        }

        /// <summary>
        /// Evento que se dispara al hacer clic en el botón de Aceptar. 
        /// Y realiza una modificación
        /// </summary>
        private async void btnAceptar_Click(object sender, EventArgs e)
        {
            string valorcmb = cmbResuelto.Text;
            if (valorcmb.Equals("SI"))
            {
                this.libroErroneo.resuelto = true;
            }else {
                this.libroErroneo.resuelto = false;
            }

            //Llamamos a el método de modificar
            string result = await controlador.EditarLibroErroneoAsync(libroErroneo, 2);
            MessageBox.Show(result, "INFORMACIÓN", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);

            this.Close();
        }


        /// <summary>
        /// Evento que se dispara al hacer clic en el botón "Cancelar". Cierra el formulario.
        /// </summary>
        private void btnCancelar_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        /// <summary>
        /// Evento que se dispara al hacer clic en el botón "Ver Libro". Muestra información detallada del libro erróneo.
        /// </summary>
        private async void btnLibro_Click(object sender, EventArgs e)
        {
            //Obtenemos el libro que esta indicado como erroneo
            Libro libro = await controlador.ObtenerLibroPorId(libroErroneo.idLibro);

            //Se lo pasamos a el formulario
            InfoBook infoLibro = new InfoBook(libro);
            infoLibro.ShowDialog();
        }
    }
}
