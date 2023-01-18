namespace JavaVersionManager
{
    partial class Form1
    {
        /// <summary>
        /// Variable nécessaire au concepteur.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Nettoyage des ressources utilisées.
        /// </summary>
        /// <param name="disposing">true si les ressources managées doivent être supprimées ; sinon, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Code généré par le Concepteur Windows Form

        /// <summary>
        /// Méthode requise pour la prise en charge du concepteur - ne modifiez pas
        /// le contenu de cette méthode avec l'éditeur de code.
        /// </summary>
        private void InitializeComponent()
        {
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(Form1));
            this.parameters = new System.Windows.Forms.Button();
            this.title = new System.Windows.Forms.Label();
            this.label1 = new System.Windows.Forms.Label();
            this.reportBug = new System.Windows.Forms.Button();
            this.SuspendLayout();
            // 
            // parameters
            // 
            this.parameters.BackColor = System.Drawing.Color.Transparent;
            this.parameters.Location = new System.Drawing.Point(119, 874);
            this.parameters.Name = "parameters";
            this.parameters.Size = new System.Drawing.Size(131, 36);
            this.parameters.TabIndex = 0;
            this.parameters.Text = "Paramètres";
            this.parameters.UseVisualStyleBackColor = false;
            this.parameters.Click += new System.EventHandler(this.button1_Click);
            // 
            // title
            // 
            this.title.AutoSize = true;
            this.title.BackColor = System.Drawing.Color.Transparent;
            this.title.Font = new System.Drawing.Font("Sans Serif Collection", 36F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.title.ForeColor = System.Drawing.Color.DarkSlateGray;
            this.title.Location = new System.Drawing.Point(-8, 0);
            this.title.Name = "title";
            this.title.Size = new System.Drawing.Size(1081, 118);
            this.title.TabIndex = 2;
            this.title.Text = "JavaVersion Manager V1.0";
            this.title.Click += new System.EventHandler(this.label1_Click_1);
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.BackColor = System.Drawing.Color.Transparent;
            this.label1.Font = new System.Drawing.Font("Sitka Banner", 15.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label1.Location = new System.Drawing.Point(12, 105);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(128, 30);
            this.label1.TabIndex = 3;
            this.label1.Text = "No design yet.";
            this.label1.Click += new System.EventHandler(this.label1_Click_2);
            // 
            // reportBug
            // 
            this.reportBug.Location = new System.Drawing.Point(11, 874);
            this.reportBug.Name = "reportBug";
            this.reportBug.Size = new System.Drawing.Size(102, 36);
            this.reportBug.TabIndex = 4;
            this.reportBug.Text = "Report Bug";
            this.reportBug.UseVisualStyleBackColor = true;
            this.reportBug.Click += new System.EventHandler(this.button1_Click_1);
            // 
            // Form1
            // 
            this.AccessibleName = "JavaVersionManager";
            this.AutoScaleDimensions = new System.Drawing.SizeF(96F, 96F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Dpi;
            this.BackgroundImage = ((System.Drawing.Image)(resources.GetObject("$this.BackgroundImage")));
            this.ClientSize = new System.Drawing.Size(1709, 919);
            this.Controls.Add(this.reportBug);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.title);
            this.Controls.Add(this.parameters);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedToolWindow;
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.Name = "Form1";
            this.Text = "JavaVersionManager";
            this.FormClosing += new System.Windows.Forms.FormClosingEventHandler(this.Form1_FormClosing);
            this.Load += new System.EventHandler(this.Form1_Load);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Button parameters;
        private System.Windows.Forms.Label title;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Button reportBug;
    }
}

