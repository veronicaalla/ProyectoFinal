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
    /// Clase principal de la aplicación.
    /// </summary>
    public partial class MainActivity : Form
    {

        /// <summary>
        /// Constructor de la clase MainActivity.
        /// </summary>
        public MainActivity()
        {
            InitializeComponent();
        }


        /// <summary>
        /// Método invocado cuando se hace clic en el menú "Salir".
        /// </summary>
        private void tsmiSalir_Click(object sender, EventArgs e)
        {
            Application.Exit();
        }


        /// <summary>
        /// Método invocado cuando se hace clic en el menú "Usuarios".
        /// </summary>
        private void tsmiUsuarios_Click(object sender, EventArgs e)
        {
            //Vamos a comprobar que la ventana, no este ya activa
            foreach (Form form in Application.OpenForms)
            {
                if (typeof(ListOfUsers) == form.GetType())
                {
                    form.Activate(); //Nos muestra por formulario y lo superpone en caso de que este abierto
                    return;
                }
            }

            //Si no esta activo, lo instanciamos y mostramos
            ListOfUsers listaUsuarios = new ListOfUsers();
            listaUsuarios.MdiParent = this;
            listaUsuarios.Show();
        }


        /// <summary>
        /// Método invocado cuando se hace clic en el menú "Libros Reportados".
        /// </summary>
        private void tsmiLibrosR_Click(object sender, EventArgs e)
        {
            //Vamos a comprobar que la ventana, no este ya activa
            foreach (Form form in Application.OpenForms)
            {
                if (typeof(ListOfReportedBooks) == form.GetType())
                {
                    form.Activate(); //Nos muestra por formulario (nos lo superpone)
                    return;
                }
            }

            //Si no esta activo, lo instanciamos y mostramos
            ListOfReportedBooks listaLibros = new ListOfReportedBooks();
            listaLibros.MdiParent = this;
            listaLibros.Show();
        }


        /// <summary>
        /// Método invocado cuando se hace clic en el menú "Comentarios Reportados".
        /// </summary>
        private void tsmiComentariosR_Click(object sender, EventArgs e)
        {
            //Vamos a comprobar que la ventana, no este ya activa
            foreach (Form form in Application.OpenForms)
            {
                if (typeof(ListOfReportedCommets) == form.GetType())
                {
                    form.Activate(); //Nos muestra por formulario (nos lo superpone)
                    return;
                }
            }

            //Si no esta activo, lo instanciamos y mostramos
            ListOfReportedCommets listaComentarios = new ListOfReportedCommets();
            listaComentarios.MdiParent = this;
            listaComentarios.Show();
        }


        private void tsmiCascada_Click(object sender, EventArgs e)
        {
            this.LayoutMdi(MdiLayout.Cascade);
        }

        private void tsmiHorizontal_Click(object sender, EventArgs e)
        {
            this.LayoutMdi(MdiLayout.TileHorizontal);
        }

        private void tsmiVertical_Click(object sender, EventArgs e)
        {
            this.LayoutMdi(MdiLayout.TileVertical);
        }


        /// <summary>
        /// Método invocado cuando se cierra el formulario principal.
        /// </summary>
        private void MainActivity_FormClosing(object sender, FormClosingEventArgs e)
        {
            Application.Exit();
        }
    }
    }