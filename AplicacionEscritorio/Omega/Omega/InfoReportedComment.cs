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

        //Comentario _comentario;
        public InfoReportedComment()
        {
            InitializeComponent();

            //los campos no se pueden modificar, solo visualizar
            txtUsuario.Enabled = false;
            txtComentario.Enabled = false;

            //Inicializamos el comentario
            //_comentario = new Comentario();
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
