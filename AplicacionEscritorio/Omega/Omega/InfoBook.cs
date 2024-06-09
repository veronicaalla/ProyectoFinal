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
    /// Formulario para mostrar y editar la información de un libro.
    /// </summary>
    public partial class InfoBook : Form
    {

        Libro libro;
		Controlador controlador;

        /// <summary>
        /// Constructor por defecto de la clase InfoBook.
        /// </summary>
        public InfoBook()
        {
            InitializeComponent();
			controlador = new Controlador();
            cargarGeneros();
            libro = new Libro();
        }

        /// <summary>
        /// Constructor sobrecargado de la clase InfoBook que recibe un libro para mostrar su información.
        /// </summary>
        /// <param name="libroNuevo">El libro a mostrar.</param>
        public InfoBook(Libro libroNuevo):this()
        {
            this.libro = libroNuevo;
			
			//Como el libro siempre va a existir, no hace falta verificar
			txtISBN.Text = libro.isbn;
            txtTitulo.Text = libro.titulo;
            txtAutor.Text = libro.autor;
            txtDescripcion.Text = libro.descripcion;

            //Debemos obtener el genero
            AsignamosGenero();


            dtpFechaPublicacion.Text = libro.fechaPublicacion.Value.ToString();
            txtPaginas.Text = libro.paginas.ToString();
        }

        /// <summary>
        /// Método para asignar el género del libro al ComboBox de género.
        /// </summary>
        public async void AsignamosGenero()
        {
            string genero = (await controlador.ObtenerGeneroPorId(libro.genero)).nombre;
            cmbGenero.Text = genero;
        }

        /// <summary>
        /// Manejador de eventos para el botón "Aceptar". Valida los datos y edita el libro.
        /// </summary>
        private async void btnAceptar_Click(object sender, EventArgs e)
        {
            if (validarDatos())
            {
                //Asignamos los datos 
                this.libro.titulo = txtTitulo.Text;
                this.libro.isbn = txtISBN.Text;
                this.libro.autor = txtAutor.Text;
                this.libro.descripcion = txtDescripcion.Text;
                this.libro.genero = (int)await controlador.ObtenerIdGeneroPorNombreAsync(cmbGenero.Text);
                this.libro.fechaPublicacion = dtpFechaPublicacion.Value;
                this.libro.paginas = int.Parse(txtPaginas.Text);

                int idUsuario = guardarUsuario.numeroGuardado;
                var resultado = await controlador.EditarLibroAsync(idUsuario, libro);
                MessageBox.Show(resultado, "INFORMACIÓN", MessageBoxButtons.OK, MessageBoxIcon.Information);
                this.Close();
            }
        }

        /// <summary>
        /// Manejador de eventos para el botón "Cancelar". Cierra el formulario.
        /// </summary>
        private void btnCancelar_Click(object sender, EventArgs e)
        {
			this.Close();
        }


        //Métodos auxiliares


        /// <summary>
        /// Valida los datos del formulario.
        /// </summary>
        /// <returns>True si los datos son válidos, False en caso contrario.</returns>
        public bool validarDatos()
        {

            if (string.IsNullOrEmpty(txtTitulo.Text))
            {
                mensajeError("Debe introducir el titulo");
                return false;
            }

            if (string.IsNullOrEmpty(txtISBN.Text))
            {
                mensajeError("Debe indicar el ISBN");
                return false;
            }

            if (string.IsNullOrEmpty(txtAutor.Text))
            {
                mensajeError("Debe indicar el autor del libro");
                return false;
            }

            if (string.IsNullOrEmpty(txtDescripcion.Text))
            {
                mensajeError("Introduce la sinopsis");
                return false;
            }

            if (string.IsNullOrEmpty(txtPaginas.Text))
            {
                mensajeError("Debe introducir el numero de paginas que contiene el libro");
                return false;
            }

            return true;
        }

        /// <summary>
        /// Muestra un mensaje de error.
        /// </summary>
        /// <param name="mensaje">El mensaje de error a mostrar.</param>
        public void mensajeError(string mensaje)
        {
            MessageBox.Show(mensaje, "ERROR", MessageBoxButtons.OK, MessageBoxIcon.Error);
        }

        /// <summary>
        /// Carga los géneros disponibles en el ComboBox de géneros.
        /// </summary>
        public async void cargarGeneros()
        {
            List<Genero> generos = await controlador.ObtenerGeneros();

            //Recorremos la lista
            foreach(Genero g in  generos)
            {
                //Le asignamos a el cmb el genero
                cmbGenero.Items.Add(g.nombre);
            }
        }

        /// <summary>
        /// Evento que se dispara al presionar una tecla en el campo de texto ISBN. Solo permite dígitos.
        /// </summary>
        private void txtISBN_KeyPress(object sender, KeyPressEventArgs e)
        {
            if (!char.IsControl(e.KeyChar) && !char.IsDigit(e.KeyChar))
            {
                e.Handled = true;
            }
        }

        /// <summary>
        /// Evento que se dispara al presionar una tecla en el campo de texto Páginas. Solo permite dígitos.
        /// </summary>
        private void txtPaginas_KeyPress(object sender, KeyPressEventArgs e)
        {
            if (!char.IsControl(e.KeyChar) && !char.IsDigit(e.KeyChar))
            {
                e.Handled = true;
            }
        }
    }
}
