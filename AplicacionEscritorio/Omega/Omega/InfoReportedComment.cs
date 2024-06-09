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
    /// Formulario para mostrar y modificar un comentario reportado.
    /// </summary>
    public partial class InfoReportedComment : Form
    {

        ComentarioReportado comentarioReportado;
        Controlador controlador;

        /// <summary>
        /// Constructor por defecto de la clase InfoReportedComment.
        /// </summary>
        public InfoReportedComment()
        {
            InitializeComponent();
            controlador = new Controlador();
            //los campos no se pueden modificar, solo visualizar
            txtUsuario.Enabled = false;
            txtComentario.Enabled = false;

            //Inicializamos el comentario
            comentarioReportado = new ComentarioReportado();
        }

        /// <summary>
        /// Constructor sobrecargado de la clase InfoReportedComment que recibe un comentario reportado para mostrar su información.
        /// </summary>
        /// <param name="comentario">El comentario reportado a mostrar.</param>
        public InfoReportedComment(ComentarioReportado comentario) : this()
        {
            asignamosValores(comentario);
        }

        /// <summary>
        /// Asigna los valores del comentario reportado a los controles del formulario.
        /// </summary>
        /// <param name="comentario">El comentario reportado.</param>
        public async void asignamosValores(ComentarioReportado comentario)
        {
            this.comentarioReportado = comentario;

            txtUsuario.Text = (await controlador.ObtenerUsuarioPorIdAsync(comentario.idReportante)).alias;
            txtComentario.Text = (await controlador.ObtenerComentarioPorId(comentario.idComentario)).comentario;

            if (comentario.ofensivo != null)
            {
                if ((bool)comentario.ofensivo)
                {
                    cmbOfensivo.Text = "SI";
                }
                else
                {
                    cmbOfensivo.Text = "NO";
                }
            }
        }

        /// <summary>
        /// Evento que se dispara al hacer clic en el botón "Aceptar". Actualiza el comentario reportado.
        /// </summary>
        private void btnAceptar_Click(object sender, EventArgs e)
        {
            //Recogemos el valor del ComboBox (lo unico a modificar)
            if (cmbOfensivo.Text == "SI")
            {
                comentarioReportado.ofensivo = true;
            }
            else
            {
                comentarioReportado.ofensivo = false;
            }

            //Le debemos asignar el id del usuario que se ha logueado 
            //comentarioReportado.AuditUpdater = 

            actualizarComentario();
            this.Close();

        }

        /// <summary>
        /// Actualiza el listView donde se ven los comentarios reportados almacenados en la base de datos.
        /// </summary>
        private async void actualizarComentario()
        {
            int idUsuario = guardarUsuario.numeroGuardado;
            var cambioEfectuado = await controlador.EditarComentarioReportadoAsync(idUsuario, comentarioReportado);

            MessageBox.Show(cambioEfectuado, "INFORMACIÓN", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);

        }

        /// <summary>
        /// Evento que se dispara al hacer clic en el botón "Cancelar". Cierra el formulario.
        /// </summary>
        private void btnCancelar_Click(object sender, EventArgs e)
        {
            this.Close();
        }
    }
}
