namespace Omega
{
    partial class ListOfUsers
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.components = new System.ComponentModel.Container();
            this.menuStrip1 = new System.Windows.Forms.MenuStrip();
            this.tsmiUsuarios = new System.Windows.Forms.ToolStripMenuItem();
            this.tsmiNuevo = new System.Windows.Forms.ToolStripMenuItem();
            this.lvwUsuarios = new System.Windows.Forms.ListView();
            this.chUsuario = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.chNombre = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.chApellidos = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.chFechaNac = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.chCorreo = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.chTelefono = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.chTipoUsuario = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.cmsUsuarios = new System.Windows.Forms.ContextMenuStrip(this.components);
            this.cmsNuevo = new System.Windows.Forms.ToolStripMenuItem();
            this.cmsVer = new System.Windows.Forms.ToolStripMenuItem();
            this.cmsEliminar = new System.Windows.Forms.ToolStripMenuItem();
            this.txtFiltrado = new System.Windows.Forms.TextBox();
            this.btnFiltrado = new System.Windows.Forms.Button();
            this.pictureBox2 = new System.Windows.Forms.PictureBox();
            this.menuStrip1.SuspendLayout();
            this.cmsUsuarios.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox2)).BeginInit();
            this.SuspendLayout();
            // 
            // menuStrip1
            // 
            this.menuStrip1.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.tsmiUsuarios});
            this.menuStrip1.Location = new System.Drawing.Point(0, 0);
            this.menuStrip1.Name = "menuStrip1";
            this.menuStrip1.Size = new System.Drawing.Size(962, 24);
            this.menuStrip1.TabIndex = 2;
            this.menuStrip1.Text = "menuStrip1";
            // 
            // tsmiUsuarios
            // 
            this.tsmiUsuarios.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.tsmiNuevo});
            this.tsmiUsuarios.Name = "tsmiUsuarios";
            this.tsmiUsuarios.Size = new System.Drawing.Size(64, 20);
            this.tsmiUsuarios.Text = "Usuarios";
            // 
            // tsmiNuevo
            // 
            this.tsmiNuevo.Name = "tsmiNuevo";
            this.tsmiNuevo.Size = new System.Drawing.Size(109, 22);
            this.tsmiNuevo.Text = "Nuevo";
            this.tsmiNuevo.Click += new System.EventHandler(this.tsmiNuevo_Click);
            // 
            // lvwUsuarios
            // 
            this.lvwUsuarios.Columns.AddRange(new System.Windows.Forms.ColumnHeader[] {
            this.chUsuario,
            this.chNombre,
            this.chApellidos,
            this.chFechaNac,
            this.chCorreo,
            this.chTelefono,
            this.chTipoUsuario});
            this.lvwUsuarios.ContextMenuStrip = this.cmsUsuarios;
            this.lvwUsuarios.Dock = System.Windows.Forms.DockStyle.Bottom;
            this.lvwUsuarios.FullRowSelect = true;
            this.lvwUsuarios.GridLines = true;
            this.lvwUsuarios.HideSelection = false;
            this.lvwUsuarios.Location = new System.Drawing.Point(0, 86);
            this.lvwUsuarios.MultiSelect = false;
            this.lvwUsuarios.Name = "lvwUsuarios";
            this.lvwUsuarios.Size = new System.Drawing.Size(962, 380);
            this.lvwUsuarios.TabIndex = 4;
            this.lvwUsuarios.UseCompatibleStateImageBehavior = false;
            this.lvwUsuarios.View = System.Windows.Forms.View.Details;
            this.lvwUsuarios.SelectedIndexChanged += new System.EventHandler(this.lvwUsuarios_SelectedIndexChanged);
            this.lvwUsuarios.DoubleClick += new System.EventHandler(this.lvwUsuarios_DoubleClick);
            // 
            // chUsuario
            // 
            this.chUsuario.Text = "Usuario";
            this.chUsuario.Width = 123;
            // 
            // chNombre
            // 
            this.chNombre.Text = "Nombre";
            this.chNombre.Width = 127;
            // 
            // chApellidos
            // 
            this.chApellidos.Text = "Apellidos";
            this.chApellidos.Width = 147;
            // 
            // chFechaNac
            // 
            this.chFechaNac.Text = "Fecha Nacimiento";
            this.chFechaNac.Width = 128;
            // 
            // chCorreo
            // 
            this.chCorreo.Text = "Correo";
            this.chCorreo.Width = 180;
            // 
            // chTelefono
            // 
            this.chTelefono.Text = "Telefono";
            this.chTelefono.Width = 125;
            // 
            // chTipoUsuario
            // 
            this.chTipoUsuario.Text = "Tipo Usuario";
            this.chTipoUsuario.Width = 102;
            // 
            // cmsUsuarios
            // 
            this.cmsUsuarios.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.cmsNuevo,
            this.cmsVer,
            this.cmsEliminar});
            this.cmsUsuarios.Name = "cmsUsuarios";
            this.cmsUsuarios.Size = new System.Drawing.Size(118, 70);
            // 
            // cmsNuevo
            // 
            this.cmsNuevo.Name = "cmsNuevo";
            this.cmsNuevo.Size = new System.Drawing.Size(117, 22);
            this.cmsNuevo.Text = "Nuevo";
            this.cmsNuevo.Click += new System.EventHandler(this.cmsNuevo_Click);
            // 
            // cmsVer
            // 
            this.cmsVer.Name = "cmsVer";
            this.cmsVer.Size = new System.Drawing.Size(117, 22);
            this.cmsVer.Text = "Ver";
            this.cmsVer.Click += new System.EventHandler(this.cmsVer_Click);
            // 
            // cmsEliminar
            // 
            this.cmsEliminar.Name = "cmsEliminar";
            this.cmsEliminar.Size = new System.Drawing.Size(117, 22);
            this.cmsEliminar.Text = "Eliminar";
            this.cmsEliminar.Click += new System.EventHandler(this.cmsEliminar_Click);
            // 
            // txtFiltrado
            // 
            this.txtFiltrado.Location = new System.Drawing.Point(56, 44);
            this.txtFiltrado.Name = "txtFiltrado";
            this.txtFiltrado.Size = new System.Drawing.Size(530, 20);
            this.txtFiltrado.TabIndex = 5;
            // 
            // btnFiltrado
            // 
            this.btnFiltrado.Location = new System.Drawing.Point(592, 44);
            this.btnFiltrado.Name = "btnFiltrado";
            this.btnFiltrado.Size = new System.Drawing.Size(137, 23);
            this.btnFiltrado.TabIndex = 6;
            this.btnFiltrado.Text = "Filtrado";
            this.btnFiltrado.UseVisualStyleBackColor = true;
            this.btnFiltrado.Click += new System.EventHandler(this.btnFiltrado_Click);
            // 
            // pictureBox2
            // 
            this.pictureBox2.Image = global::Omega.Properties.Resources.Logo_Omega_PC;
            this.pictureBox2.Location = new System.Drawing.Point(850, 27);
            this.pictureBox2.Name = "pictureBox2";
            this.pictureBox2.Size = new System.Drawing.Size(123, 62);
            this.pictureBox2.SizeMode = System.Windows.Forms.PictureBoxSizeMode.Zoom;
            this.pictureBox2.TabIndex = 12;
            this.pictureBox2.TabStop = false;
            // 
            // ListOfUsers
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(962, 466);
            this.Controls.Add(this.pictureBox2);
            this.Controls.Add(this.btnFiltrado);
            this.Controls.Add(this.txtFiltrado);
            this.Controls.Add(this.lvwUsuarios);
            this.Controls.Add(this.menuStrip1);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedSingle;
            this.Name = "ListOfUsers";
            this.Text = "Mantenimiento de Usuarios";
            this.menuStrip1.ResumeLayout(false);
            this.menuStrip1.PerformLayout();
            this.cmsUsuarios.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox2)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion
        private System.Windows.Forms.MenuStrip menuStrip1;
        private System.Windows.Forms.ToolStripMenuItem tsmiUsuarios;
        private System.Windows.Forms.ToolStripMenuItem tsmiNuevo;
        private System.Windows.Forms.ListView lvwUsuarios;
        private System.Windows.Forms.ColumnHeader chUsuario;
        private System.Windows.Forms.ColumnHeader chNombre;
        private System.Windows.Forms.ColumnHeader chApellidos;
        private System.Windows.Forms.ColumnHeader chFechaNac;
        private System.Windows.Forms.ColumnHeader chCorreo;
        private System.Windows.Forms.ColumnHeader chTelefono;
        private System.Windows.Forms.ColumnHeader chTipoUsuario;
        private System.Windows.Forms.TextBox txtFiltrado;
        private System.Windows.Forms.Button btnFiltrado;
        private System.Windows.Forms.ContextMenuStrip cmsUsuarios;
        private System.Windows.Forms.ToolStripMenuItem cmsNuevo;
        private System.Windows.Forms.ToolStripMenuItem cmsVer;
        private System.Windows.Forms.ToolStripMenuItem cmsEliminar;
        private System.Windows.Forms.PictureBox pictureBox2;
    }
}