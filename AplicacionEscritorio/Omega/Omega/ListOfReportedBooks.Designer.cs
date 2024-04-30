﻿namespace Omega
{
    partial class ListOfReportedBooks
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
            this.btnFiltrado = new System.Windows.Forms.Button();
            this.txtFiltrado = new System.Windows.Forms.TextBox();
            this.lvwLibros = new System.Windows.Forms.ListView();
            this.chISBN = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.chTitulo = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.chAutor = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.chDescripcion = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.chGenero = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.menuStrip1 = new System.Windows.Forms.MenuStrip();
            this.tsmiLibros = new System.Windows.Forms.ToolStripMenuItem();
            this.tsmiNuevo = new System.Windows.Forms.ToolStripMenuItem();
            this.pictureBox1 = new System.Windows.Forms.PictureBox();
            this.cmsLibros = new System.Windows.Forms.ContextMenuStrip(this.components);
            this.cmsNuevo = new System.Windows.Forms.ToolStripMenuItem();
            this.cmsVer = new System.Windows.Forms.ToolStripMenuItem();
            this.menuStrip1.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox1)).BeginInit();
            this.cmsLibros.SuspendLayout();
            this.SuspendLayout();
            // 
            // btnFiltrado
            // 
            this.btnFiltrado.Location = new System.Drawing.Point(595, 38);
            this.btnFiltrado.Name = "btnFiltrado";
            this.btnFiltrado.Size = new System.Drawing.Size(137, 23);
            this.btnFiltrado.TabIndex = 15;
            this.btnFiltrado.Text = "Filtrado";
            this.btnFiltrado.UseVisualStyleBackColor = true;
            this.btnFiltrado.Click += new System.EventHandler(this.btnFiltrado_Click);
            // 
            // txtFiltrado
            // 
            this.txtFiltrado.Location = new System.Drawing.Point(111, 41);
            this.txtFiltrado.Name = "txtFiltrado";
            this.txtFiltrado.Size = new System.Drawing.Size(469, 20);
            this.txtFiltrado.TabIndex = 14;
            // 
            // lvwLibros
            // 
            this.lvwLibros.Columns.AddRange(new System.Windows.Forms.ColumnHeader[] {
            this.chISBN,
            this.chTitulo,
            this.chAutor,
            this.chDescripcion,
            this.chGenero});
            this.lvwLibros.Dock = System.Windows.Forms.DockStyle.Bottom;
            this.lvwLibros.FullRowSelect = true;
            this.lvwLibros.GridLines = true;
            this.lvwLibros.HideSelection = false;
            this.lvwLibros.Location = new System.Drawing.Point(0, 89);
            this.lvwLibros.MultiSelect = false;
            this.lvwLibros.Name = "lvwLibros";
            this.lvwLibros.Size = new System.Drawing.Size(973, 377);
            this.lvwLibros.TabIndex = 13;
            this.lvwLibros.UseCompatibleStateImageBehavior = false;
            this.lvwLibros.View = System.Windows.Forms.View.Details;
            this.lvwLibros.SelectedIndexChanged += new System.EventHandler(this.lvwLibros_SelectedIndexChanged);
            this.lvwLibros.DoubleClick += new System.EventHandler(this.lvwLibros_DoubleClick);
            // 
            // chISBN
            // 
            this.chISBN.Text = "ISBN";
            this.chISBN.Width = 189;
            // 
            // chTitulo
            // 
            this.chTitulo.Text = "Titulo";
            this.chTitulo.Width = 198;
            // 
            // chAutor
            // 
            this.chAutor.Text = "Autor";
            this.chAutor.Width = 157;
            // 
            // chDescripcion
            // 
            this.chDescripcion.Text = "Descripción";
            this.chDescripcion.Width = 233;
            // 
            // chGenero
            // 
            this.chGenero.Text = "Género";
            this.chGenero.Width = 141;
            // 
            // menuStrip1
            // 
            this.menuStrip1.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.tsmiLibros});
            this.menuStrip1.Location = new System.Drawing.Point(0, 0);
            this.menuStrip1.Name = "menuStrip1";
            this.menuStrip1.Size = new System.Drawing.Size(973, 24);
            this.menuStrip1.TabIndex = 12;
            this.menuStrip1.Text = "menuStrip1";
            // 
            // tsmiLibros
            // 
            this.tsmiLibros.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.tsmiNuevo});
            this.tsmiLibros.Name = "tsmiLibros";
            this.tsmiLibros.Size = new System.Drawing.Size(51, 20);
            this.tsmiLibros.Text = "Libros";
            // 
            // tsmiNuevo
            // 
            this.tsmiNuevo.Name = "tsmiNuevo";
            this.tsmiNuevo.Size = new System.Drawing.Size(180, 22);
            this.tsmiNuevo.Text = "Nuevo";
            this.tsmiNuevo.Click += new System.EventHandler(this.tsmiNuevo_Click);
            // 
            // pictureBox1
            // 
            this.pictureBox1.Image = global::Omega.Properties.Resources.Logo_Omega_PC;
            this.pictureBox1.Location = new System.Drawing.Point(850, 27);
            this.pictureBox1.Name = "pictureBox1";
            this.pictureBox1.Size = new System.Drawing.Size(123, 62);
            this.pictureBox1.SizeMode = System.Windows.Forms.PictureBoxSizeMode.Zoom;
            this.pictureBox1.TabIndex = 16;
            this.pictureBox1.TabStop = false;
            // 
            // cmsLibros
            // 
            this.cmsLibros.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.cmsNuevo,
            this.cmsVer});
            this.cmsLibros.Name = "cmsLibros";
            this.cmsLibros.Size = new System.Drawing.Size(110, 48);
            // 
            // cmsNuevo
            // 
            this.cmsNuevo.Name = "cmsNuevo";
            this.cmsNuevo.Size = new System.Drawing.Size(109, 22);
            this.cmsNuevo.Text = "Nuevo";
            this.cmsNuevo.Click += new System.EventHandler(this.cmsNuevo_Click);
            // 
            // cmsVer
            // 
            this.cmsVer.Name = "cmsVer";
            this.cmsVer.Size = new System.Drawing.Size(109, 22);
            this.cmsVer.Text = "Ver";
            this.cmsVer.Click += new System.EventHandler(this.cmsVer_Click);
            // 
            // ListOfReportedBooks
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(973, 466);
            this.Controls.Add(this.pictureBox1);
            this.Controls.Add(this.btnFiltrado);
            this.Controls.Add(this.txtFiltrado);
            this.Controls.Add(this.lvwLibros);
            this.Controls.Add(this.menuStrip1);
            this.Name = "ListOfReportedBooks";
            this.Text = "Mantenimiento de Libros Reportados";
            this.menuStrip1.ResumeLayout(false);
            this.menuStrip1.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox1)).EndInit();
            this.cmsLibros.ResumeLayout(false);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.PictureBox pictureBox1;
        private System.Windows.Forms.Button btnFiltrado;
        private System.Windows.Forms.TextBox txtFiltrado;
        private System.Windows.Forms.ListView lvwLibros;
        private System.Windows.Forms.MenuStrip menuStrip1;
        private System.Windows.Forms.ToolStripMenuItem tsmiLibros;
        private System.Windows.Forms.ToolStripMenuItem tsmiNuevo;
        private System.Windows.Forms.ColumnHeader chISBN;
        private System.Windows.Forms.ColumnHeader chTitulo;
        private System.Windows.Forms.ColumnHeader chAutor;
        private System.Windows.Forms.ColumnHeader chDescripcion;
        private System.Windows.Forms.ColumnHeader chGenero;
        private System.Windows.Forms.ContextMenuStrip cmsLibros;
        private System.Windows.Forms.ToolStripMenuItem cmsNuevo;
        private System.Windows.Forms.ToolStripMenuItem cmsVer;
    }
}