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
    /// Formulario para el inicio de sesión.
    /// </summary>
    public partial class Login : Form
    {

        /// <summary>
        /// Constructor de la clase Login.
        /// </summary>
        public Login()
        {
            InitializeComponent();
        }


        /// <summary>
        /// Método llamado cuando se hace clic en el botón de iniciar sesión.
        /// </summary>
        /// <param name="sender">Objeto que desencadenó el evento.</param>
        /// <param name="e">Argumentos del evento.</param>
        private async void btnIniciarSesion_Click(object sender, EventArgs e)
        {
            if (validarDatos())
            {

                //Recogemlos los datos del formulario
                var usuario = txtUsuario.Text;
                var password = txtPassword.Text;

                Controlador controlador = new Controlador();
                Usuario usuarioAcceso = await controlador.LoginUsuario(usuario, password);

                if (usuarioAcceso != null)
                {
                    //Comprobamos que el usuario sea de tipo administrador 
                    if (usuarioAcceso.tipo == 1 || usuarioAcceso.tipo == 2) {
                        // Crear una instancia de la clase que almacena el id del usuario que esta registrado

                        // Asignar un valor al entero guardado
                        guardarUsuario.numeroGuardado = usuarioAcceso.id;

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


        /// <summary>
        /// Validar si los campos de usuario y contraseña están llenos.
        /// </summary>
        /// <returns>Verdadero si los campos están llenos; de lo contrario, falso.</returns>
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
