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
    public partial class ListOfReportedBooks : Form
    {
        Controlador controlador;

        public ListOfReportedBooks()
        {
            InitializeComponent();
            controlador = new Controlador();
            actualizarLista();
        }



        private void btnFiltrado_Click(object sender, EventArgs e)
        {

        }


        private void cmsVer_Click(object sender, EventArgs e)
        {
            verLibro();
        }

        private void lvwLibros_SelectedIndexChanged(object sender, EventArgs e)
        {
            cmsVer.Enabled = true;
        }

        private void lvwLibros_DoubleClick(object sender, EventArgs e)
        {
            if (lvwLibros.SelectedIndices.Count > 0)
            {
                verLibro();
            }
        }

        private async void verLibro()
        {
            //Buscamos cual es el elemento seleccionado
            foreach (ListViewItem item in lvwLibros.SelectedItems)
            {
                int idItem = (int)item.Tag;

                LibroErroneo libroErroneo = await controlador.ObtenerLibroErroneoPorId(idItem);

                InfoWarningBook infoLibro = new InfoWarningBook(libroErroneo);
                infoLibro.ShowDialog();

            }
        }

        private async void actualizarLista()
        {
            List<LibroErroneo> librosErroneos = await controlador.ObtenerLibrosErroneos();
            if (librosErroneos != null)
            {
                //Limpiamos la lista
                lvwLibros.Items.Clear();

                //Recorremos la lista
                foreach(LibroErroneo e in librosErroneos)
                {
                    ListViewItem nuevoItem = new ListViewItem();

                    //Obtenemos el titulo del libro
                    string titulo = (await controlador.ObtenerLibroPorId(e.IdLibro)).Titulo;
                    nuevoItem = lvwLibros.Items.Add(titulo);

                    //Obtenemos el usuario
                    string aliasUsuario = (await controlador.ObtenerUsuarioPorIdAsync(e.IdReportante)).Alias;
                    nuevoItem.SubItems.Add(aliasUsuario);

                    if (e.Resuelto != null)
                    {
                        nuevoItem.SubItems.Add(estaResuelto(e.Resuelto.Value));
                    }

                    nuevoItem.Tag = e.Id;

                   
                }
            }
            else
            {
                MessageBox.Show("No hay libros erroneos", "INFORMACIÓN", MessageBoxButtons.OK, MessageBoxIcon.Hand);
            }
        }

        private string estaResuelto(bool corregido)
        {
            if (corregido)
            {
                return "SI";
            }
            return "NO";
        }
    }
}