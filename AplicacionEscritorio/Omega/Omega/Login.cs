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
    public partial class Login : Form
    {
        public Login()
        {
            InitializeComponent();
        }

        private void btnIniciarSesion_Click(object sender, EventArgs e)
        {
            if (validarDatos())
            {
                //Realizamos la llamada a la api

                //Realizamos prueba 
                if (txtUsuario.Text.Equals("veronica") && txtPassword.Text.Equals("Va@12345"))
                {
                    //Vamos a comprobar que la ventana, no este ya activa
                    foreach (Form form in Application.OpenForms)
                    {
                        if (typeof(MainActivity) == form.GetType())
                        {
                            form.Activate(); //Nos muestra por formulario (nos lo superpone)
                            return;
                        }
                    }

                    //Si no esta activo, lo instanciamos y mostramos
                    MainActivity mainActivity = new MainActivity();
                    this.Hide(); //Ocultamos la ventana del login 
                    mainActivity.Show();
                }
            }
        }


        private bool validarDatos()
        {
            if (string.IsNullOrEmpty(txtUsuario.Text))
            {
                MessageBox.Show("Introduce el usuario", "ERROR", MessageBoxButtons.OK, MessageBoxIcon.Error);
                txtUsuario.Focus();
                return false;
            }

            if (string.IsNullOrEmpty(txtPassword.Text))
            {
                MessageBox.Show("Introduce la contraseña", "ERROR", MessageBoxButtons.OK, MessageBoxIcon.Error);
                txtPassword.Focus();
                return false;
            }
            return true;
        }
    }
}
