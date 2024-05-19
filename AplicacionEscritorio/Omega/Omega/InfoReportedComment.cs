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
            txtUsuario.Text = (await controlador.ObtenerUsuarioPorIdAsync(comentario.IdReportante)).Alias;
            txtComentario.Text = (await controlador.ObtenerComentarioPorId(comentario.IdComentario)).comentario;
           
            if (comentario.Ofensivo != null)
            {
                string esOfensivo = comentarioReportado.Ofensivo.HasValue && comentarioReportado.Ofensivo.Value ? "SI" : "NO";
                cmbOfensivo.Text = esOfensivo;
            }
        }


        private void btnAceptar_Click(object sender, EventArgs e)
        {
            //Recogemos el valor del ComboBox 

            //Llamamos a la api para realizar la modificacion
            //y si es "SI" ponerle al usuario que ha realizado un comentario ofensivo

        }

        private void btnCancelar_Click(object sender, EventArgs e)
        {
            this.Close();
        }
    }
}
