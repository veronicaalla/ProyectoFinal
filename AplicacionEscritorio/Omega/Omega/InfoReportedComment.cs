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
    public partial class InfoReportedComment : Form
    {

        ComentarioReportado comentarioReportado;
        Controlador controlador;
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

        public InfoReportedComment(ComentarioReportado comentario) : this()
        {
            asignamosValores(comentario);
        }


        public async void asignamosValores(ComentarioReportado comentario)
        {
            this.comentarioReportado = comentario;

            txtUsuario.Text = (await controlador.ObtenerUsuarioPorIdAsync(comentario.IdReportante)).Alias;
            txtComentario.Text = (await controlador.ObtenerComentarioPorId(comentario.IdComentario)).comentario;

            if (comentario.Ofensivo != null)
            {
                if ((bool)comentario.Ofensivo)
                {
                    cmbOfensivo.Text = "SI";
                }
                else
                {
                    cmbOfensivo.Text = "NO";
                }
            }
        }


        private void btnAceptar_Click(object sender, EventArgs e)
        {
            //Recogemos el valor del ComboBox (lo unico a modificar)
            if (cmbOfensivo.Text == "SI")
            {
                comentarioReportado.Ofensivo = true;
            }
            else
            {
                comentarioReportado.Ofensivo = false;
            }

            //Le debemos asignar el id del usuario que se ha logueado 
            //comentarioReportado.AuditUpdater = 

            actualizarComentario();

        }

        private async void actualizarComentario()
        {
            var cambioEfectuado = await controlador.EditarComentarioReportado(comentarioReportado.Id, comentarioReportado);

            if (cambioEfectuado)
            {
                MessageBox.Show("Se ha modificado correctamente", "INFORMACIÓN", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
            }
            else
            {
                MessageBox.Show("No se ha podido realizar la modificación", "INFORMACIÓN", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
            }
        }

        private void btnCancelar_Click(object sender, EventArgs e)
        {
            this.Close();
        }
    }
}
