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
        public ListOfReportedCommets()
        {
            InitializeComponent();
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

        
        private void actualizarLista()
        {
            //Método api que devuelve el listado de usuarios
            //y se asigna a el ListView
        }

        private void verComentario()
        {
            //Buscamos cual es el elemento seleccionado
            foreach (ListViewItem item in lvwComentarios.SelectedItems)
            {
                int idItem = (int)item.Tag;

                //Comentario comentarioSeleccionado = método api q devuelva su informacion por id
                //InfoReportedComment infoComentario = new InfoUser(comentarioSeleccionado);
                //infoComentario.ShowDialog();

            }
        }

        private void btnFiltrado_Click(object sender, EventArgs e)
        {

        }

    }
}
