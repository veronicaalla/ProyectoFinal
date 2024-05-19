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
    public partial class ListOfUsers : Form
    {

        //Llamamos al método API
        Controlador controlador;

        public ListOfUsers()
        {
            InitializeComponent();
            controlador = new Controlador();
            actualizarLista();
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

        private async void cmsEliminar_Click(object sender, EventArgs e)
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
                    string result = await controlador.EliminarUsuario(idItem);
                    MessageBox.Show(result, "ELIMMINAR", MessageBoxButtons.OK);
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

        private async void verUsuario()
        {
            //Buscamos cual es el elemento seleccionado
            foreach (ListViewItem item in lvwUsuarios.SelectedItems)
            {
               
                int idItem = (int)item.Tag;

                Usuario usuarioSeleccionado =  await controlador.ObtenerUsuarioPorIdAsync(idItem);
                InfoUser infoUsuario = new InfoUser(usuarioSeleccionado);
                infoUsuario.ShowDialog();

            }
        }


        private async void actualizarLista()
        {
            List<Usuario> usuarios = await controlador.GetPerfiles();

            if (usuarios != null)
            {
                lvwUsuarios.Items.Clear();

                //Recorremos la lista
                foreach (Usuario u in usuarios)
                {
                    ListViewItem nuevoItem = new ListViewItem();
                    nuevoItem = lvwUsuarios.Items.Add(u.Alias);
                    nuevoItem.SubItems.Add(u.Nombre);
                    nuevoItem.SubItems.Add(u.Apellidos);
                    nuevoItem.SubItems.Add(formatoFecha(u.FechaNacimiento.ToString()));
                    nuevoItem.SubItems.Add(u.Correo);
                    nuevoItem.SubItems.Add(u.Telefono);
                    nuevoItem.SubItems.Add(obtenerTipoUsuario(u.Tipo));
                    nuevoItem.Tag = u.Id;
                }
            }
            else
            {
                MessageBox.Show("No hay ningun usuario", "ADVERTENCIA", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
            }

        }

        private string obtenerTipoUsuario(int tipo)
        {
            switch (tipo)
            {
                case 1: return "superadmi";
                case 2: return "administrador";
                case 3: return "usuario";
                case 4: return "escritor";
            }
            return "";
        }

        private string formatoFecha (string fecha)
        {
            bool exito = DateTime.TryParse(fecha, out DateTime fechaFormateada);

            if (exito)
            {
                string fechaFinal = fechaFormateada.ToString("dd/MM/yyyy");
                return fechaFinal;
            }
            return null;
        }


    }
}
