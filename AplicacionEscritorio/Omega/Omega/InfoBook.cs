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
    public partial class InfoBook : Form
    {

        Libro libro;
		Controlador controlador;
		
        public InfoBook()
        {
            InitializeComponent();
			controlador = new Controlador();
            cargarGeneros();
            libro = new Libro();
        }
		
		public InfoBook(Libro libroNuevo):this()
        {
            this.libro = libroNuevo;
			
			//Como el libro siempre va a existir, no hace falta verificar
			txtISBN.Text = libro.ISBN;
            txtTitulo.Text = libro.Titulo;
            txtAutor.Text = libro.Autor;
            txtDescripcion.Text = libro.Descripcion;

            //Debemos obtener el genero
            AsignamosGenero();


            dtpFechaPublicacion.Text = libro.FechaPublicacion.Value.ToString();
            txtPaginas.Text = libro.Paginas.ToString();
        }
		
        public async void AsignamosGenero()
        {
            string genero = (await controlador.ObtenerGeneroPorId(libro.Genero)).Nombre;
            cmbGenero.Text = genero;
        }

        private void btnAceptar_Click(object sender, EventArgs e)
        {

        }

        private void btnCancelar_Click(object sender, EventArgs e)
        {
			this.Close();
        }
		
		
		//Métodos auxiliares

        public bool validarDatos()
        {


            return true;
        }
		 public void mensajeError(string mensaje)
        {
            MessageBox.Show(mensaje, "ERROR", MessageBoxButtons.OK, MessageBoxIcon.Error);
        }

        public async void cargarGeneros()
        {
            List<Genero> generos = await controlador.ObtenerGeneros();

            //Recorremos la lista
            foreach(Genero g in  generos)
            {
                //Le asignamos a el cmb el genero
                cmbGenero.Items.Add(g.Nombre);
            }
        }
       
    }
}
