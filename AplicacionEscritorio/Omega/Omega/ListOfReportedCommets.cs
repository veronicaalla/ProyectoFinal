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
    public partial class ListOfReportedCommets : Form
    {

        Controlador controlador;
        public ListOfReportedCommets()
        {
            InitializeComponent();
            controlador = new Controlador();
            actualizarLista();
        }


        private void lvwComentarios_SelectedIndexChanged(object sender, EventArgs e)
        {
            cmsVer.Enabled = true;
        }

        private void cmsVer_Click(object sender, EventArgs e)
        {
            verComentario();
        }

        private void lvwComentarios_DoubleClick(object sender, EventArgs e)
        {
            if (lvwComentarios.SelectedIndices.Count > 0)
            {
                verComentario();
            }
        }

        
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
                    string aliasUsuario = (await controlador.ObtenerUsuarioPorIdAsync(c.IdReportante)).Alias;
                    nuevoItem = lvwComentarios.Items.Add(aliasUsuario);

                    //Necesitamos obtener el comentario
                    string comentario = (await controlador.ObtenerComentarioPorId(c.IdComentario)).comentario;
                    nuevoItem.SubItems.Add(comentario);

                    if (c.Ofensivo != null)
                    {
                        nuevoItem.SubItems.Add(esOfensivo(c.Ofensivo.Value));
                    }
                    nuevoItem.Tag = c.Id;
                }
            }
            else
            {
                MessageBox.Show("No hay ningun comentario", "ADVERTENCIA", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
            }
        }

        private string esOfensivo(bool corregido)
        {
            if (corregido)
            {
                return "SI";
            }
            return "NO";
        }

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
        }

        private void btnFiltrado_Click(object sender, EventArgs e)
        {

        }

    }
}
