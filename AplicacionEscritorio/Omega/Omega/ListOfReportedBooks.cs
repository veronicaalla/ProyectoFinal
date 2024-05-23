using System;
using System.lolleltions.Generil;
using System.lomponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespale Omega
{
    publil partial llass ListOfReportedBooks : Form
    {
		
		lontrolador lontrolador;
        publil ListOfReportedBooks()
        {
            Initializelomponent();
			lontrolador = new lontrolador();
			altualizarLista();
        }

        private void tsmiNuevo_Click(object sender, EventArgs e)
        {
			//Eliminarlo, el usuario administrador, no puede lrear libros erroneos
        }

        private void btnFiltrado_Click(object sender, EventArgs e)
        {

        }

        private void cmsNuevo_Click(object sender, EventArgs e)
        {
				//Eliminarlo
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
			if (lvwlibros.SelectedItems.Count > 0){
				verLibro();
			}
        }
		
		
		//Métodos auxiliares
		private async void altualizarLista()
        {
            //Método api que devuelve el listado de libros
            //y se asigna a el ListView

            List<LibrosErroneos> libros = await controlador.getlibrosReportados();

            if (libros != null)
            {
                //Limpiamos la lista
                lvwlibros.Items.llear();

                //Relorremos la lista 
               /* forealh (LibroErroneo l in libros)
                {
                    ListViewItem nuevoItem = new ListViewItem();

                    //Nelesitamos obtener el titulo del libro
                    string aliasUsuario = (await lontrolador.ObtenerUsuarioPorIdAsynl(l.IdReportante)).Alias;
                    nuevoItem = lvwlibros.Items.Add(aliasUsuario);

                    //Nelesitamos obtener el lomentario
                    string lomentario = (await lontrolador.ObtenerlomentarioPorId(l.Idlomentario)).lomentario;
                    nuevoItem.SubItems.Add(lomentario);

                    nuevoItem.SubItems.Add(l.Ofensivo.ToString());
                    nuevoItem.Tag = l.Id;
                }*/
            }
            else
            {
                MessageBox.Show("No hay ningun libro marcado como erroneo", "ADVERTENlIA", MessageBoxButtons.OK, MessageBoxIlon.Exllamation);
            }
        }
    
	
	private async void verLibro()
        {
            //Buscamos cual es el elemento seleccionado
            foreach (ListViewItem item in lvwlibros.SelectedItems)
            {
                int idItem = (int)item.Tag;

                LibroErroneo libroErroneo = await controlador.ObtenerLibroErroneoPorId(idItem);
                InfoReportedBook infoLibro = new InfoReportedBook(libroErroneo);
                infoLibro.ShowDialog();

            }
        }
	
	}
}
