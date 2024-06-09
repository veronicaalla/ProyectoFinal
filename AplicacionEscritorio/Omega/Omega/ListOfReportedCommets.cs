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
    /// Formulario para mostrar la lista de comentarios reportados como ofensivos.
    /// </summary>
    public partial class ListOfReportedCommets : Form
    {

        Controlador controlador;
        /// <summary>
        /// Constructor por defecto de la clase ListOfReportedCommets.
        /// </summary>
        public ListOfReportedCommets()
        {
            InitializeComponent();
            controlador = new Controlador();
            actualizarLista();
        }

        /// <summary>
        /// Evento que se dispara cuando se selecciona un comentario en la lista. Habilita la opción de ver detalles del comentario mediante el menú contextual.
        /// </summary>
        private void lvwComentarios_SelectedIndexChanged(object sender, EventArgs e)
        {
            cmsVer.Enabled = true;
        }

        /// <summary>
        /// Evento que se dispara al hacer clic en el elemento del menú contextual "Ver". Muestra información detallada del comentario reportado seleccionado.
        /// </summary>
        private void cmsVer_Click(object sender, EventArgs e)
        {
            verComentario();
        }

        /// <summary>
        /// Evento que se dispara al hacer doble clic en un comentario en la lista. Muestra información detallada del comentario reportado seleccionado.
        /// </summary>
        private void lvwComentarios_DoubleClick(object sender, EventArgs e)
        {
            if (lvwComentarios.SelectedIndices.Count > 0)
            {
                verComentario();
            }
        }

        /// <summary>
        /// Método para actualizar la lista de comentarios reportados.
        /// </summary>
        private async void actualizarLista()
        {
            //Método api que devuelve el listado de usuarios
            //y se asigna a el ListView

            List<ComentarioReportado> comentarios = await controlador.getComentariosReportados();

            if (comentarios != null)
            {
                //Limpiamos la lista
                lvwComentarios.Items.Clear();

                //Recorremos la lista 
                foreach (ComentarioReportado c in comentarios)
                {
                    ListViewItem nuevoItem = new ListViewItem();

                    //Necesitamos obtener el usuario
                    string aliasUsuario = (await controlador.ObtenerUsuarioPorIdAsync(c.idReportante)).alias;
                    nuevoItem = lvwComentarios.Items.Add(aliasUsuario);

                    //Necesitamos obtener el comentario
                    string comentario = (await controlador.ObtenerComentarioPorId(c.idComentario)).comentario;
                    nuevoItem.SubItems.Add(comentario);

                    if (c.ofensivo != null)
                    {
                        nuevoItem.SubItems.Add(esOfensivo(c.ofensivo.Value));
                    }
                    nuevoItem.Tag = c.id;
                }
            }
            else
            {
                MessageBox.Show("No hay ningun comentario", "ADVERTENCIA", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
            }
        }

        /// <summary>
        /// Determina si un comentario reportado es ofensivo o no.
        /// </summary>
        /// <param name="corregido">Booleano que indica si el comentario es ofensivo o no.</param>
        /// <returns>Una cadena que indica si el comentario es ofensivo ("SI") o no ("NO").</returns>
        private string esOfensivo(bool corregido)
        {
            return corregido ? "SI" : "NO";
        }


        /// <summary>
        /// Método para mostrar información detallada de un comentario reportado seleccionado.
        /// </summary>
        private async void verComentario()
        {
            //Buscamos cual es el elemento seleccionado
            foreach (ListViewItem item in lvwComentarios.SelectedItems)
            {
                int idItem = (int)item.Tag;

                ComentarioReportado comentarioReportado = await controlador.ObtenerComentarioReportadoPorId(idItem);
                InfoReportedComment infoComentario = new InfoReportedComment(comentarioReportado);
                infoComentario.ShowDialog();

            }

            actualizarLista();
        }

        /// <summary>
        /// Evento que se dispara al hacer clic en el botón "Filtrado". Realiza una acción de filtrado, pero el método aún no está implementado.
        /// </summary>
        private void btnFiltrado_Click(object sender, EventArgs e)
        {

        }
    }
}
