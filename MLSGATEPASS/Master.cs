using System;
using System.ComponentModel;
using System.Drawing;
using System.Windows.Forms;

namespace Pass_Vidhanparishad
{
	public class Master : Form
	{
		private IContainer components = null;

		private Button button2;

		private Button button3;

		private Button button1;

		private Panel panel1;

		private Label label1;

		private Label label3;

		private Label label2;

		private Label label4;

		public Master()
		{
			this.InitializeComponent();
		}

		private void button1_Click(object sender, EventArgs e)
		{
			(new Form1()).ShowDialog(this);
		}

		private void button2_Click(object sender, EventArgs e)
		{
			(new Form2()).ShowDialog(this);
		}

		private void button3_Click(object sender, EventArgs e)
		{
			(new Form3()).ShowDialog(this);
		}

		protected override void Dispose(bool disposing)
		{
            if ((!disposing ? 0 : Convert.ToInt32(this.components != null)) != 0)
			{
				this.components.Dispose();
			}
			base.Dispose(disposing);
		}

		private void InitializeComponent()
		{
			this.button2 = new Button();
			this.button3 = new Button();
			this.button1 = new Button();
			this.panel1 = new Panel();
			this.label1 = new Label();
			this.label2 = new Label();
			this.label3 = new Label();
			this.label4 = new Label();
			this.panel1.SuspendLayout();
			base.SuspendLayout();
			this.button2.FlatStyle = FlatStyle.Flat;
			this.button2.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25f, FontStyle.Bold, GraphicsUnit.Point, 0);
			this.button2.ForeColor = SystemColors.ActiveCaptionText;
			this.button2.Location = new Point(139, 12);
			this.button2.Name = "button2";
			this.button2.Size = new System.Drawing.Size(110, 23);
			this.button2.TabIndex = 1;
			this.button2.Text = "Vidhan Sabha";
			this.button2.UseVisualStyleBackColor = true;
			this.button2.Click += new EventHandler(this.button2_Click);
			this.button3.FlatStyle = FlatStyle.Flat;
			this.button3.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25f, FontStyle.Bold, GraphicsUnit.Point, 0);
			this.button3.ForeColor = SystemColors.ActiveCaptionText;
			this.button3.Location = new Point(255, 12);
			this.button3.Name = "button3";
			this.button3.Size = new System.Drawing.Size(132, 23);
			this.button3.TabIndex = 2;
			this.button3.Text = "Vidhan Parishad";
			this.button3.UseVisualStyleBackColor = true;
			this.button3.Click += new EventHandler(this.button3_Click);
			this.button1.FlatStyle = FlatStyle.Flat;
			this.button1.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25f, FontStyle.Bold, GraphicsUnit.Point, 0);
			this.button1.ForeColor = SystemColors.ActiveCaptionText;
			this.button1.Location = new Point(12, 12);
			this.button1.Name = "button1";
			this.button1.Size = new System.Drawing.Size(121, 23);
			this.button1.TabIndex = 3;
			this.button1.Text = "Vidhan Bhavan";
			this.button1.UseVisualStyleBackColor = true;
			this.button1.Click += new EventHandler(this.button1_Click);
			this.panel1.Controls.Add(this.label4);
			this.panel1.Controls.Add(this.label3);
			this.panel1.Controls.Add(this.label2);
			this.panel1.Controls.Add(this.label1);
			this.panel1.Dock = DockStyle.Bottom;
			this.panel1.Location = new Point(0, 250);
			this.panel1.Name = "panel1";
			this.panel1.Size = new System.Drawing.Size(505, 81);
			this.panel1.TabIndex = 4;
			this.label1.Anchor = AnchorStyles.Right;
			this.label1.AutoSize = true;
			this.label1.Font = new System.Drawing.Font("Microsoft Sans Serif", 9.75f, FontStyle.Bold, GraphicsUnit.Point, 0);
			this.label1.ForeColor = Color.White;
			this.label1.Location = new Point(299, 24);
			this.label1.Name = "label1";
			this.label1.Size = new System.Drawing.Size(201, 16);
			this.label1.TabIndex = 0;
			this.label1.Text = "Krishna Technologies, Pune";
			this.label2.Anchor = AnchorStyles.Right;
			this.label2.AutoSize = true;
			this.label2.Font = new System.Drawing.Font("Microsoft Sans Serif", 9.75f, FontStyle.Bold, GraphicsUnit.Point, 0);
			this.label2.ForeColor = Color.White;
			this.label2.Location = new Point(299, 10);
			this.label2.Name = "label2";
			this.label2.Size = new System.Drawing.Size(107, 16);
			this.label2.TabIndex = 1;
			this.label2.Text = "Developed By";
			this.label3.Anchor = AnchorStyles.Right;
			this.label3.AutoSize = true;
			this.label3.Font = new System.Drawing.Font("Microsoft Sans Serif", 9.75f, FontStyle.Bold, GraphicsUnit.Point, 0);
			this.label3.ForeColor = Color.White;
			this.label3.Location = new Point(299, 40);
			this.label3.Name = "label3";
			this.label3.Size = new System.Drawing.Size(152, 16);
			this.label3.TabIndex = 2;
			this.label3.Text = "Contact : 9373440410";
			this.label4.Anchor = AnchorStyles.Right;
			this.label4.AutoSize = true;
			this.label4.Font = new System.Drawing.Font("Microsoft Sans Serif", 9.75f, FontStyle.Bold, GraphicsUnit.Point, 0);
			this.label4.ForeColor = Color.White;
			this.label4.Location = new Point(299, 56);
			this.label4.Name = "label4";
			this.label4.Size = new System.Drawing.Size(203, 16);
			this.label4.TabIndex = 3;
			this.label4.Text = "Email : kiran@krishnatech.in";
			base.AutoScaleDimensions = new SizeF(6f, 13f);
			base.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
			this.BackColor = Color.DarkGray;
			base.ClientSize = new System.Drawing.Size(505, 331);
			base.Controls.Add(this.panel1);
			base.Controls.Add(this.button1);
			base.Controls.Add(this.button3);
			base.Controls.Add(this.button2);
			base.Name = "Master";
			this.Text = "Pass";
			base.WindowState = FormWindowState.Maximized;
			this.panel1.ResumeLayout(false);
			this.panel1.PerformLayout();
			base.ResumeLayout(false);
		}
	}
}