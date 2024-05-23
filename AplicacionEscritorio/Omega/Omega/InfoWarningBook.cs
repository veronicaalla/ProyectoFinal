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
    public partial class InfoWarningBook : Form
    {
        LibroErroneo libroErroneo;
        Controlador controlador;

        public InfoWarningBook()
        {
            InitializeComponent();
            libroErroneo = new LibroErroneo();
            controlador = new Controlador();
        }

        public InfoWarningBook(LibroErroneo libro) : this()
        {
            this.libroErroneo = libro;
            asignarDatos();
        }

        public async void asignarDatos()
        {
            //Le asignamos los datos 
            //Obtenemos el titulo del libro
            string titulo = (await controlador.ObtenerLibroPorId(libroErroneo.IdLibro)).Titulo;
            txtTitulo.Text = titulo;

            //Obtenemos el usuario
            string aliasUsuario = (await controlador.ObtenerUsuarioPorIdAsync(libroErroneo.IdReportante)).Alias;
            txtUsuario.Text = aliasUsuario;

            if (libroErroneo.Resuelto != null)
            {
                if (libroErroneo.Resuelto.HasValue == true)
                {
                    cmbResuelto.Text = "SI";
                }
                else
                {
                    cmbResuelto.Text = "NO";
                }
            }
        }

        private void btnAceptar_Click(object sender, EventArgs e)
        {

        }

        private void btnCancelar_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        private async void btnLibro_Click(object sender, EventArgs e)
        {
            //Obtenemos el libro que esta indicado como erroneo
            Libro libro = await controlador.ObtenerLibroPorId(libroErroneo.IdLibro);

            //Se lo pasamos a el formulario
            InfoBook infoLibro = new InfoBook(libro);
            infoLibro.ShowDialog();
        }
    }
}
