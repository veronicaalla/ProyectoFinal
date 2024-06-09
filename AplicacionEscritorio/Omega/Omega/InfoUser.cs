using Omega.ApiService;
using Omega.Model;
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
    /// <summary>
    /// Formulario para mostrar y modificar la información de un usuario.
    /// </summary>
    public partial class InfoUser : Form
    {

        Usuario usuario;
        Controlador controlador;

        /// <summary>
        /// Constructor por defecto de la clase InfoUser.
        /// </summary>
        public InfoUser()
        {
            InitializeComponent();
            usuario = new Usuario();
            controlador = new Controlador();
        }


        /// <summary>
        /// Constructor sobrecargado de la clase InfoUser que recibe la información de un usuario para mostrarla.
        /// </summary>
        /// <param name="infoUsuario">El usuario cuya información se va a mostrar.</param>
        public InfoUser(Usuario infoUsuario) : this()
        {
            this.usuario = infoUsuario;

            //Comprobamos si el usuario es nuevo o no
            if (usuario.id == 0)
            {
                btnAceptar.Text = "Agregar";
                return;
            }

            //Le asignamos a los campos la informacion de usuario
            txtNombre.Text = usuario.nombre;
            txtApellidos.Text = usuario.apellidos;
            txtUsuario.Text = usuario.alias;
            txtCorreo.Text = usuario.correo;
            txtTelefono.Text = usuario.telefono;
            cmbTipoUsuario.Text = obtenerTipoUsuario(usuario.tipo);

            //El campo tipo usuario no se puede modificar
            cmbTipoUsuario.Enabled = false;

            btnAceptar.Text = "Modificar";

        }


        /// <summary>
        /// Evento que se dispara al hacer clic en el botón "Aceptar". Agrega o modifica la información del usuario.
        /// </summary>
        private async void btnAceptar_Click(object sender, EventArgs e)
        {
            if (validarDatos())
            {
                //asignamos los datos al usuario pasado por parametro
                this.usuario.alias = txtUsuario.Text;
                this.usuario.nombre = txtNombre.Text;
                this.usuario.apellidos = txtApellidos.Text;
                this.usuario.correo = txtCorreo.Text;
                this.usuario.telefono= txtTelefono.Text;

                
                if (usuario.id == 0)
                {
                 
                    this.usuario.clave = "1234567890";
                    this.usuario.tipo = tipoUsuario(cmbTipoUsuario.Text);

                    try
                    {
                        //Creamos usuario
                        string result = await controlador.CreateUsuarioAsync(this.usuario);
                        MessageBox.Show("Usuario creado exitosamente, su contraseña es 1234567890");
                    }
                    catch (Exception ex)
                    {
                        MessageBox.Show("Error: " + ex.Message);
                    }
                    this.Close();
                    return;
                }

               
                //recuperamos el id del usuario que ha accedido
                int idUsuario = guardarUsuario.numeroGuardado;

                // Llamar al método de la API para modificar el usuario
                bool resultado = await controlador.ModificarUsuarioAsync(idUsuario, usuario);

                if (resultado)
                {
                    MessageBox.Show("Usuario modificado exitosamente");
                }
                else
                {
                    MessageBox.Show("Error al modificar el usuario");
                }

                this.Close();
            }
        }


        /// <summary>
        /// Evento que se dispara al hacer clic en el botón "Cancelar". Cierra el formulario.
        /// </summary>
        private void btnCancelar_Click(object sender, EventArgs e)
        {
            this.Close();
        }


        //------------------------------ Métodos auxiliares ------------------------

        /// <summary>
        /// Valida los datos ingresados por el usuario en los campos del formulario.
        /// </summary>
        /// <returns>True si los datos son válidos, False en caso contrario.</returns>
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

            return true;
        }


        /// <summary>
        /// Valida que una cadena de texto no contenga números.
        /// </summary>
        /// <param name="cad">La cadena de texto a validar.</param>
        /// <returns>True si la cadena no contiene números, False en caso contrario.</returns>
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


        /// <summary>
        /// Valida el formato de una dirección de correo electrónico.
        /// </summary>
        /// <returns>True si el formato del correo es válido, False en caso contrario.</returns>
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


        /// <summary>
        /// Muestra un mensaje de error en una ventana emergente.
        /// </summary>
        /// <param name="mensaje">El mensaje de error a mostrar.</param>
        public void mensajeError(string mensaje)
        {
            MessageBox.Show(mensaje, "ERROR", MessageBoxButtons.OK, MessageBoxIcon.Error);
        }


        /// <summary>
        /// Evento que se dispara al presionar una tecla en el campo de teléfono. Permite solo la entrada de dígitos.
        /// </summary>
        private void txtTelefono_KeyPress(object sender, KeyPressEventArgs e)
        {
            if (!char.IsControl(e.KeyChar) && !char.IsDigit(e.KeyChar))
            {
                e.Handled = true;
            }
        }


        /// <summary>
        /// Obtiene el tipo de usuario en formato de texto a partir de su valor numérico.
        /// </summary>
        /// <param name="tipo">El valor numérico del tipo de usuario.</param>
        /// <returns>El tipo de usuario en formato de texto.</returns>
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


        /// <summary>
        /// Obtiene el valor numérico del tipo de usuario a partir de su descripción en texto.
        /// </summary>
        /// <param name="tipo">El tipo de usuario en texto.</param>
        /// <returns>El valor numérico del tipo de usuario.</returns>
        private int tipoUsuario(string tipo)
        {
            switch (tipo)
            {
                case "SuperAdmi": return 1;
                case "Administrador": return 2;
                case "Usuario": return 3;
                case "Escritor": return 4;
            }
            return 0;
        }
    }
}

