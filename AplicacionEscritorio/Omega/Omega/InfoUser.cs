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
    public partial class InfoUser : Form
    {

        Usuario usuario;
        Controlador controlador;

        public InfoUser()
        {
            InitializeComponent();
            usuario = new Usuario();
            controlador = new Controlador();
        }


        public InfoUser(Usuario infoUsuario) : this()
        {
            this.usuario = infoUsuario;

            //Comprobamos si el usuario es nuevo o no
            if (usuario.Id == 0)
            {
                return;
            }

            //Le asignamos a los campos la informacion de usuario
            txtNombre.Text = usuario.Nombre;
            txtApellidos.Text = usuario.Apellidos;
            txtUsuario.Text = usuario.Alias;
            dtpFechaNac.Text = usuario.FechaNacimiento.ToString();
            txtCorreo.Text = usuario.Correo;
            txtTelefono.Text = usuario.Telefono;
            cmbTipoUsuario.Text = obtenerTipoUsuario(usuario.Tipo);

            //La fecha de nacimiento y el tipo de usuario no se pueden modificar
            cmbTipoUsuario.Enabled = false;
            dtpFechaNac.Enabled = false;

        }



        private async void btnAceptar_Click(object sender, EventArgs e)
        {
            if (validarDatos())
            {
                //asignamos los datos al usuario pasado por parametro
                this.usuario.Alias = txtUsuario.Text;
                this.usuario.Nombre = txtNombre.Text;
                this.usuario.Apellidos = txtApellidos.Text;
                this.usuario.Correo = txtCorreo.Text;
                this.txtTelefono.Text = txtTelefono.Text;

                //La fecha de nacimiento no se puede modificar por terminos legales a futuro.
                //El tipo de usuario tampoco, seria de forma automatica en un futuro caso. 
                if (usuario.Id == 0)
                {
                    //Se le debe asignar la fecha de nacimiento y su tipo de usuario
                    this.usuario.FechaNacimiento = dtpFechaNac.Value;
                    this.usuario.Tipo = tipoUsuario(cmbTipoUsuario.Text);

                    try
                    {
                        //Creamos usuario
                        string result = await controlador.CreateUsuarioAsync(this.usuario);
                        MessageBox.Show(result);
                    }
                    catch (Exception ex)
                    {
                        MessageBox.Show("Error: " + ex.Message);
                    }
                }

                // Llamar al método de la API para modificar el usuario
                bool resultado = await controlador.ModificarUsuarioAsync(usuario.Id, usuario);

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

        public void mensajeError(string mensaje)
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

