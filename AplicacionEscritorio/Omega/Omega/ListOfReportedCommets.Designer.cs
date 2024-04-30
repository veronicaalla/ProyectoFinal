﻿namespace Omega
{
    partial class ListOfReportedCommets
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
            this.lvwComentarios = new System.Windows.Forms.ListView();
            this.chUsuario = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.chComentario = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.chOfensivo = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.pictureBox1 = new System.Windows.Forms.PictureBox();
            this.cmsComentarios = new System.Windows.Forms.ContextMenuStrip(this.components);
            this.cmsVer = new System.Windows.Forms.ToolStripMenuItem();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox1)).BeginInit();
            this.cmsComentarios.SuspendLayout();
            this.SuspendLayout();
            // 
            // btnFiltrado
            // 
            this.btnFiltrado.Location = new System.Drawing.Point(630, 21);
            this.btnFiltrado.Name = "btnFiltrado";
            this.btnFiltrado.Size = new System.Drawing.Size(137, 23);
            this.btnFiltrado.TabIndex = 10;
            this.btnFiltrado.Text = "Filtrado";
            this.btnFiltrado.UseVisualStyleBackColor = true;
            this.btnFiltrado.Click += new System.EventHandler(this.btnFiltrado_Click);
            // 
            // txtFiltrado
            // 
            this.txtFiltrado.Location = new System.Drawing.Point(85, 24);
            this.txtFiltrado.Name = "txtFiltrado";
            this.txtFiltrado.Size = new System.Drawing.Size(530, 20);
            this.txtFiltrado.TabIndex = 9;
            // 
            // lvwComentarios
            // 
            this.lvwComentarios.Columns.AddRange(new System.Windows.Forms.ColumnHeader[] {
            this.chUsuario,
            this.chComentario,
            this.chOfensivo});
            this.lvwComentarios.ContextMenuStrip = this.cmsComentarios;
            this.lvwComentarios.Dock = System.Windows.Forms.DockStyle.Bottom;
            this.lvwComentarios.FullRowSelect = true;
            this.lvwComentarios.GridLines = true;
            this.lvwComentarios.HideSelection = false;
            this.lvwComentarios.Location = new System.Drawing.Point(0, 63);
            this.lvwComentarios.MultiSelect = false;
            this.lvwComentarios.Name = "lvwComentarios";
            this.lvwComentarios.Size = new System.Drawing.Size(973, 403);
            this.lvwComentarios.TabIndex = 8;
            this.lvwComentarios.UseCompatibleStateImageBehavior = false;
            this.lvwComentarios.View = System.Windows.Forms.View.Details;
            this.lvwComentarios.SelectedIndexChanged += new System.EventHandler(this.lvwComentarios_SelectedIndexChanged);
            this.lvwComentarios.DoubleClick += new System.EventHandler(this.lvwComentarios_DoubleClick);
            // 
            // chUsuario
            // 
            this.chUsuario.Text = "Usuario reportado";
            this.chUsuario.Width = 200;
            // 
            // chComentario
            // 
            this.chComentario.Text = "Comentario Reportado";
            this.chComentario.Width = 477;
            // 
            // chOfensivo
            // 
            this.chOfensivo.Text = "Ofensivo";
            this.chOfensivo.Width = 191;
            // 
            // pictureBox1
            // 
            this.pictureBox1.Image = global::Omega.Properties.Resources.Logo_Omega_PC;
            this.pictureBox1.Location = new System.Drawing.Point(850, 2);
            this.pictureBox1.Name = "pictureBox1";
            this.pictureBox1.Size = new System.Drawing.Size(123, 62);
            this.pictureBox1.SizeMode = System.Windows.Forms.PictureBoxSizeMode.Zoom;
            this.pictureBox1.TabIndex = 11;
            this.pictureBox1.TabStop = false;
            // 
            // cmsComentarios
            // 
            this.cmsComentarios.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.cmsVer});
            this.cmsComentarios.Name = "cmsComentarios";
            this.cmsComentarios.Size = new System.Drawing.Size(181, 48);
            // 
            // cmsVer
            // 
            this.cmsVer.Name = "cmsVer";
            this.cmsVer.Size = new System.Drawing.Size(180, 22);
            this.cmsVer.Text = "Ver";
            this.cmsVer.Click += new System.EventHandler(this.cmsVer_Click);
            // 
            // ListOfReportedCommets
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(973, 466);
            this.Controls.Add(this.pictureBox1);
            this.Controls.Add(this.btnFiltrado);
            this.Controls.Add(this.txtFiltrado);
            this.Controls.Add(this.lvwComentarios);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedSingle;
            this.Name = "ListOfReportedCommets";
            this.Text = "Mantenimiento de Comentarios Reportados";
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox1)).EndInit();
            this.cmsComentarios.ResumeLayout(false);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Button btnFiltrado;
        private System.Windows.Forms.TextBox txtFiltrado;
        private System.Windows.Forms.ListView lvwComentarios;
        private System.Windows.Forms.ColumnHeader chUsuario;
        private System.Windows.Forms.ColumnHeader chComentario;
        private System.Windows.Forms.ColumnHeader chOfensivo;
        private System.Windows.Forms.PictureBox pictureBox1;
        private System.Windows.Forms.ContextMenuStrip cmsComentarios;
        private System.Windows.Forms.ToolStripMenuItem cmsVer;
    }
}