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
    /// Formulario para mostrar la lista de libros reportados como erróneos.
    /// </summary>
    public partial class ListOfReportedBooks : Form
    {
        Controlador controlador;

        /// <summary>
        /// Constructor por defecto de la clase ListOfReportedBooks.
        /// </summary>
        public ListOfReportedBooks()
        {
            InitializeComponent();
            controlador = new Controlador();
            actualizarLista();
        }


        /// <summary>
        /// Evento que se dispara al hacer clic en el botón "Filtrado". Realiza una acción de filtrado, pero el método aún no está implementado.
        /// </summary>
        private void btnFiltrado_Click(object sender, EventArgs e)
        {

        }

        /// <summary>
        /// Evento que se dispara al hacer clic en el elemento del menú contextual "Ver". Muestra información detallada del libro reportado seleccionado.
        /// </summary>
        private void cmsVer_Click(object sender, EventArgs e)
        {
            verLibro();
        }

        /// <summary>
        /// Evento que se dispara cuando se selecciona un elemento en la lista de libros reportados. Habilita la opción de ver detalles del libro mediante el menú contextual.
        /// </summary>
        private void lvwLibros_SelectedIndexChanged(object sender, EventArgs e)
        {
            cmsVer.Enabled = true;
        }

        /// <summary>
        /// Evento que se dispara al hacer doble clic en un elemento de la lista de libros reportados. Muestra información detallada del libro reportado seleccionado.
        /// </summary>
        private void lvwLibros_DoubleClick(object sender, EventArgs e)
        {
            if (lvwLibros.SelectedIndices.Count > 0)
            {
                verLibro();
            }
        }

        /// <summary>
        /// Método para mostrar información detallada de un libro reportado seleccionado.
        /// </summary>
        private async void verLibro()
        {
            //Buscamos cual es el elemento seleccionado
            foreach (ListViewItem item in lvwLibros.SelectedItems)
            {
                int idItem = (int)item.Tag;

                LibroErroneo libroErroneo = await controlador.ObtenerLibroErroneoPorId(idItem);

                InfoWarningBook infoLibro = new InfoWarningBook(libroErroneo);
                infoLibro.ShowDialog();

            }
        }

        /// <summary>
        /// Método para actualizar la lista de libros reportados.
        /// </summary>
        private async void actualizarLista()
        {
            List<LibroErroneo> librosErroneos = await controlador.ObtenerLibrosErroneos();
            if (librosErroneos != null)
            {
                //Limpiamos la lista
                lvwLibros.Items.Clear();

                //Recorremos la lista
                foreach(LibroErroneo e in librosErroneos)
                {
                    ListViewItem nuevoItem = new ListViewItem();

                    //Obtenemos el titulo del libro
                    string titulo = (await controlador.ObtenerLibroPorId(e.idLibro)).titulo;
                    nuevoItem = lvwLibros.Items.Add(titulo);

                    //Obtenemos el usuario
                    string aliasUsuario = (await controlador.ObtenerUsuarioPorIdAsync(e.idReportante)).alias;
                    nuevoItem.SubItems.Add(aliasUsuario);

                    if (e.resuelto != null)
                    {
                        nuevoItem.SubItems.Add(estaResuelto(e.resuelto.Value));
                    }

                    nuevoItem.Tag = e.id;

                   
                }
            }
            else
            {
                MessageBox.Show("No hay libros erroneos", "INFORMACIÓN", MessageBoxButtons.OK, MessageBoxIcon.Hand);
            }
        }

        /// <summary>
        /// Determina si un libro reportado está resuelto o no.
        /// </summary>
        /// <param name="corregido">Booleano que indica si el libro está resuelto o no.</param>
        /// <returns>Una cadena que indica si el libro está resuelto ("SI") o no ("NO").</returns>
        private string estaResuelto(bool corregido)
        {
            return corregido ? "SI" : "NO";
        }
    }
}