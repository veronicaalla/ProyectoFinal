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
    public partial class Login : Form
    {
        public Login()
        {
            InitializeComponent();
        }

        private async void btnIniciarSesion_Click(object sender, EventArgs e)
        {
            if (validarDatos())
            {

                //Recogemlos los datos del formulario
                var usuario = txtUsuario.Text;
                var password = txtPassword.Text;

                //Llamamos al método API
                Controlador controlador = new Controlador();

                Usuario usuarioAcceso = await controlador.LoginUsuario(usuario, password);

                if (usuarioAcceso != null)
                {
                    //Comprobamos que el usuario sea de tipo administrador 
                    if (usuarioAcceso.Tipo == 1 || usuarioAcceso.Tipo == 2) {
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
                    else
                    {
                        MessageBox.Show("El usuario no tiene acceso a esta aplicación", "INFORMACIÓN", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
                    }

                }
                else
                {
                    MessageBox.Show("El usuario introducido no existe", "ERROR", MessageBoxButtons.OK, MessageBoxIcon.Error);
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
