using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading.Tasks;
using System.Windows.Forms;
using static System.Windows.Forms.VisualStyles.VisualStyleElement;

namespace Omega
{
    public partial class InfoUser : Form
    {

        //Usuario _usuario 
        public InfoUser()
        {
            InitializeComponent();
            //_usuario = Usuario();
        }

        /*
        
        public InfoUser(Usuario usuario):this()
        {
            //Comprobamos si el usuario es nuevo o no

            if (usuario.id == 0){
                return
            }

            //Le asignamos a los campos la informacion de usuario
        }
        
         */

        private void btnAceptar_Click(object sender, EventArgs e)
        {
            if (validarDatos())
            {
                //asignamos los datos al usuario pasado por parametro

                //llamamos a la ai para crear o actualizar datos 

                this.Close();
            }
        }

        

        private void btnCancelar_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        //Métodos auxiliares
        private bool validarDatos()
        {
            if (string.IsNullOrEmpty(txtNombre.Text))
            {
                mensajeError("Introduce el nombre");
                txtNombre.Focus();
                return false;
            }

            if (!noNumeros(txtNombre.Text))
            {
                mensajeError("El nombre no puede contener numeros");
                txtNombre.Focus();
                return false;
            }


            if (string.IsNullOrEmpty(txtApellidos.Text))
            {
                mensajeError("Introduce los apellidos");
                txtApellidos.Focus();
                return false;
            }

            if (!noNumeros(txtApellidos.Text))
            {
                mensajeError("Los apellidos no pueden contener numeros");
                txtApellidos.Focus();
                return false;
            }

            if (string.IsNullOrEmpty(txtUsuario.Text))
            {
                mensajeError("Introduce el nombre de usuario");
                txtUsuario.Focus();
                return false;
            }

            if (string.IsNullOrEmpty(txtCorreo.Text))
            {
                mensajeError("Introduce el correo");
                txtCorreo.Focus();
                return false;
            }
            //Comprobacion de si el correo introducido es valido o no 

            if (!correoValido())
            {
                mensajeError("Introduce un correo valido");
                txtCorreo.Focus();
                return false;
            }
            

            if (string.IsNullOrEmpty(txtTelefono.Text))
            {
                mensajeError("Introduce el telefono");
                txtTelefono.Focus();
                return false;
            }

            //Comprobar que al menos el usuarios tiene 15 años


            return true;
        }


        private bool noNumeros(string cad)
        {
            string pattern = @"^[a-zA-ZÀ-ÿ\s]+$";
            Match match = Regex.Match(cad, pattern);

            if (!match.Success)
            {
                return false;
            }
            else
            {
                return true;
            }
        }


        private bool correoValido()
        {
            string pattern = @"^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.(com|es)$";
            Match match = Regex.Match(txtCorreo.Text, pattern);

            if (match.Success)
            {
                return true;
            }
            else
            {
                return false;
            }
        }

        public void mensajeError (string mensaje)
        {
            MessageBox.Show(mensaje, "ERROR", MessageBoxButtons.OK, MessageBoxIcon.Error);
        }

        private void txtTelefono_KeyPress(object sender, KeyPressEventArgs e)
        {
            if (!char.IsControl(e.KeyChar) && !char.IsDigit(e.KeyChar))
            {
                e.Handled = true;
            }
        }
    }
}
