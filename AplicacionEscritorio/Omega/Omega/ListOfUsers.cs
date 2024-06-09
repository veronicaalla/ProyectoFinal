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
    /// Formulario para mostrar la lista de usuarios y realizar operaciones CRUD sobre ellos.
    /// </summary>
    public partial class ListOfUsers : Form
    {

        Controlador controlador;

        /// <summary>
        /// Constructor por defecto de la clase ListOfUsers.
        /// </summary>
        public ListOfUsers()
        {
            InitializeComponent();
            controlador = new Controlador();
            actualizarLista();
        }


        /// <summary>
        /// Evento que se dispara al hacer clic en el elemento del menú contextual "Nuevo". Abre el formulario para crear un nuevo usuario.
        /// </summary>
        private void tsmiNuevo_Click(object sender, EventArgs e)
        {
            crearUsuario();
        }


        /// <summary>
        /// Evento que se dispara cuando se selecciona un usuario en la lista. Habilita las opciones de ver, editar y eliminar mediante el menú contextual.
        /// </summary>
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


        /// <summary>
        /// Evento que se dispara al hacer doble clic en un usuario en la lista. Muestra información detallada del usuario seleccionado.
        /// </summary>
        private void lvwUsuarios_DoubleClick(object sender, EventArgs e)
        {
            if (lvwUsuarios.SelectedIndices.Count > 0)
            {
                verUsuario();
            }

        }


        /// <summary>
        /// Evento que se dispara al hacer clic en el elemento del menú contextual "Nuevo". Abre el formulario para crear un nuevo usuario.
        /// </summary>
        private void cmsNuevo_Click(object sender, EventArgs e)
        {
            crearUsuario();
        }


        /// <summary>
        /// Evento que se dispara al hacer clic en el elemento del menú contextual "Ver". Muestra información detallada del usuario seleccionado.
        /// </summary>
        private void cmsVer_Click(object sender, EventArgs e)
        {
            verUsuario();
        }


        /// <summary>
        /// Evento que se dispara al hacer clic en el elemento del menú contextual "Eliminar". Elimina el usuario seleccionado de la lista.
        /// </summary>
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

        
            

        // ---------------- Métodos auxiliares ------------

        /// <summary>
        /// Método para abrir el formulario para crear un nuevo usuario.
        /// </summary>
        private void crearUsuario()
        {
            InfoUser newUser = new InfoUser();
            newUser.ShowDialog();

            //Actualizamos la lista 
            actualizarLista();
        }


        /// <summary>
        /// Método para mostrar información detallada de un usuario seleccionado.
        /// </summary>
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
            actualizarLista();
        }


        /// <summary>
        /// Método para actualizar la lista de usuarios.
        /// </summary>
        private async void actualizarLista()
        {
            List<Usuario> usuarios = await controlador.GetUsuarios();

            if (usuarios != null)
            {
                lvwUsuarios.Items.Clear();

                //Recorremos la lista
                foreach (Usuario u in usuarios)
                {
                    ListViewItem nuevoItem = new ListViewItem();
                    nuevoItem = lvwUsuarios.Items.Add(u.alias);
                    nuevoItem.SubItems.Add(u.nombre);
                    nuevoItem.SubItems.Add(u.apellidos);
                    nuevoItem.SubItems.Add(u.correo);
                    nuevoItem.SubItems.Add(u.telefono);
                    nuevoItem.SubItems.Add(obtenerTipoUsuario(u.tipo));
                    nuevoItem.Tag = u.id;
                }
            }
            else
            {
                MessageBox.Show("No hay ningun usuario", "ADVERTENCIA", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
            }

        }

        /// <summary>
        /// Método para obtener el tipo de usuario según el valor numérico.
        /// </summary>
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


    }
}
