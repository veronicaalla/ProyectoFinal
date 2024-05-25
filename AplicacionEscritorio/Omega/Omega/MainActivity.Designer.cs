namespace Omega
{
    partial class MainActivity
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
            this.menuStrip1 = new System.Windows.Forms.MenuStrip();
            this.tsmiArchivo = new System.Windows.Forms.ToolStripMenuItem();
            this.tsmiSalir = new System.Windows.Forms.ToolStripMenuItem();
            this.tsmiMantenimiento = new System.Windows.Forms.ToolStripMenuItem();
            this.tsmiUsuarios = new System.Windows.Forms.ToolStripMenuItem();
            this.toolStripSeparator1 = new System.Windows.Forms.ToolStripSeparator();
            this.tsmiLibrosR = new System.Windows.Forms.ToolStripMenuItem();
            this.tsmiComentariosR = new System.Windows.Forms.ToolStripMenuItem();
            this.tsmiVentanas = new System.Windows.Forms.ToolStripMenuItem();
            this.tsmiCascada = new System.Windows.Forms.ToolStripMenuItem();
            this.tsmiHorizontal = new System.Windows.Forms.ToolStripMenuItem();
            this.tsmiVertical = new System.Windows.Forms.ToolStripMenuItem();
            this.menuStrip1.SuspendLayout();
            this.SuspendLayout();
            // 
            // menuStrip1
            // 
            this.menuStrip1.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.tsmiArchivo,
            this.tsmiMantenimiento,
            this.tsmiVentanas});
            this.menuStrip1.Location = new System.Drawing.Point(0, 0);
            this.menuStrip1.Name = "menuStrip1";
            this.menuStrip1.Size = new System.Drawing.Size(800, 24);
            this.menuStrip1.TabIndex = 1;
            this.menuStrip1.Text = "menuStrip1";
            // 
            // tsmiArchivo
            // 
            this.tsmiArchivo.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.tsmiSalir});
            this.tsmiArchivo.Name = "tsmiArchivo";
            this.tsmiArchivo.Size = new System.Drawing.Size(60, 20);
            this.tsmiArchivo.Text = "Archivo";
            // 
            // tsmiSalir
            // 
            this.tsmiSalir.Name = "tsmiSalir";
            this.tsmiSalir.Size = new System.Drawing.Size(96, 22);
            this.tsmiSalir.Text = "Salir";
            this.tsmiSalir.Click += new System.EventHandler(this.tsmiSalir_Click);
            // 
            // tsmiMantenimiento
            // 
            this.tsmiMantenimiento.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.tsmiUsuarios,
            this.toolStripSeparator1,
            this.tsmiLibrosR,
            this.tsmiComentariosR});
            this.tsmiMantenimiento.Name = "tsmiMantenimiento";
            this.tsmiMantenimiento.Size = new System.Drawing.Size(101, 20);
            this.tsmiMantenimiento.Text = "Mantenimiento";
            // 
            // tsmiUsuarios
            // 
            this.tsmiUsuarios.Name = "tsmiUsuarios";
            this.tsmiUsuarios.Size = new System.Drawing.Size(205, 22);
            this.tsmiUsuarios.Text = "Usuarios";
            this.tsmiUsuarios.Click += new System.EventHandler(this.tsmiUsuarios_Click);
            // 
            // toolStripSeparator1
            // 
            this.toolStripSeparator1.Name = "toolStripSeparator1";
            this.toolStripSeparator1.Size = new System.Drawing.Size(202, 6);
            // 
            // tsmiLibrosR
            // 
            this.tsmiLibrosR.Name = "tsmiLibrosR";
            this.tsmiLibrosR.Size = new System.Drawing.Size(205, 22);
            this.tsmiLibrosR.Text = "Libros Reportados";
            this.tsmiLibrosR.Click += new System.EventHandler(this.tsmiLibrosR_Click);
            // 
            // tsmiComentariosR
            // 
            this.tsmiComentariosR.Name = "tsmiComentariosR";
            this.tsmiComentariosR.Size = new System.Drawing.Size(205, 22);
            this.tsmiComentariosR.Text = "Comentarios Reportados";
            this.tsmiComentariosR.Click += new System.EventHandler(this.tsmiComentariosR_Click);
            // 
            // tsmiVentanas
            // 
            this.tsmiVentanas.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.tsmiCascada,
            this.tsmiHorizontal,
            this.tsmiVertical});
            this.tsmiVentanas.Name = "tsmiVentanas";
            this.tsmiVentanas.Size = new System.Drawing.Size(66, 20);
            this.tsmiVentanas.Text = "Ventanas";
            // 
            // tsmiCascada
            // 
            this.tsmiCascada.Name = "tsmiCascada";
            this.tsmiCascada.Size = new System.Drawing.Size(129, 22);
            this.tsmiCascada.Text = "Cascada";
            this.tsmiCascada.Click += new System.EventHandler(this.tsmiCascada_Click);
            // 
            // tsmiHorizontal
            // 
            this.tsmiHorizontal.Name = "tsmiHorizontal";
            this.tsmiHorizontal.Size = new System.Drawing.Size(129, 22);
            this.tsmiHorizontal.Text = "Horizontal";
            this.tsmiHorizontal.Click += new System.EventHandler(this.tsmiHorizontal_Click);
            // 
            // tsmiVertical
            // 
            this.tsmiVertical.Name = "tsmiVertical";
            this.tsmiVertical.Size = new System.Drawing.Size(129, 22);
            this.tsmiVertical.Text = "Vertical";
            this.tsmiVertical.Click += new System.EventHandler(this.tsmiVertical_Click);
            // 
            // MainActivity
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(800, 450);
            this.Controls.Add(this.menuStrip1);
            this.IsMdiContainer = true;
            this.MainMenuStrip = this.menuStrip1;
            this.Name = "MainActivity";
            this.Text = "OMEGA";
            this.FormClosing += new System.Windows.Forms.FormClosingEventHandler(this.MainActivity_FormClosing);
            this.menuStrip1.ResumeLayout(false);
            this.menuStrip1.PerformLayout();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.MenuStrip menuStrip1;
        private System.Windows.Forms.ToolStripMenuItem tsmiArchivo;
        private System.Windows.Forms.ToolStripMenuItem tsmiMantenimiento;
        private System.Windows.Forms.ToolStripMenuItem tsmiVentanas;
        private System.Windows.Forms.ToolStripMenuItem tsmiSalir;
        private System.Windows.Forms.ToolStripMenuItem tsmiUsuarios;
        private System.Windows.Forms.ToolStripSeparator toolStripSeparator1;
        private System.Windows.Forms.ToolStripMenuItem tsmiLibrosR;
        private System.Windows.Forms.ToolStripMenuItem tsmiComentariosR;
        private System.Windows.Forms.ToolStripMenuItem tsmiCascada;
        private System.Windows.Forms.ToolStripMenuItem tsmiHorizontal;
        private System.Windows.Forms.ToolStripMenuItem tsmiVertical;
    }
}