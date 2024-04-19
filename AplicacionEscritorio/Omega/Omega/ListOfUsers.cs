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
    public partial class ListOfUsers : Form
    {
        public ListOfUsers()
        {
            InitializeComponent();
            //actualizarLista();
        }

        private void tsmiNuevo_Click(object sender, EventArgs e)
        {
            crearUsuario();
             
        }



        private void lvwUsuarios_SelectedIndexChanged(object sender, EventArgs e)
        {
            if (lvwUsuarios.SelectedItems.Count != 0)
            {
                cmsNuevo.Enabled = true;
                cmsVer.Enabled = true;
                cmsEliminar.Enabled = true;
            }
            else
            {
                cmsVer.Enabled = false;
                cmsEliminar.Enabled = false;
            }
        }

        private void lvwUsuarios_DoubleClick(object sender, EventArgs e)
        {
            if (lvwUsuarios.SelectedIndices.Count > 0)
            {
                verUsuario();
            }
            
        }

        

        private void cmsNuevo_Click(object sender, EventArgs e)
        {
            crearUsuario();
        }

        private void cmsVer_Click(object sender, EventArgs e)
        {
            verUsuario();
        }

        private void cmsEliminar_Click(object sender, EventArgs e)
        {
            //Buscamos cual es el elemento seleccinado
            foreach (ListViewItem item in lvwUsuarios.SelectedItems)
            {
                DialogResult dR = MessageBox.Show("¿Seguro que desea eliminar el usuario?", "IMPORTANTE", MessageBoxButtons.OKCancel, MessageBoxIcon.Warning);
                if (dR == DialogResult.OK)
                {
                    //Eliminamos el elemento
                    int idItem = (int)item.Tag;
                    
                    //llamamos al método api que elimina el usuario
                }
            }

            actualizarLista();
        }

        private void btnFiltrado_Click(object sender, EventArgs e)
        {

        }


        // ---------------- Métodos auxiliares ------------
        private void crearUsuario()
        {
            //Creamos un usuario vacio y se lo pasamos como parametro
            //Usuario usuario = new Usuario();

            InfoUser newUser = new InfoUser();
            newUser.ShowDialog();

            //Actualizamos la lista 
            actualizarLista();

        }

        private void verUsuario()
        {
             //Buscamos cual es el elemento seleccionado
            foreach (ListViewItem item in lvwUsuarios.SelectedItems)
            {
                int idItem = (int)item.Tag;

                //Usuario usuarioSeleccionado = método api q devuelva su informacion por id
                //InfoUser infoUsuario = new InfoUser(usuarioSeleccionado);
                //infoUsuario.ShowDialog();

            }

        }

        private void actualizarLista()
        {
            //Método api que devuelve el listado de usuarios
            //y se asigna a el ListView
        }
    }
}
