using CrystalDecisions.CrystalReports.Engine;
using CrystalDecisions.Shared;
using Pass_Vidhanparishad.Properties;
using System;
using System.ComponentModel;
using System.Diagnostics;
using System.Drawing;
using System.IO;
using System.Reflection;
using System.Runtime.CompilerServices;
using System.Windows.Forms;


namespace Pass_Vidhanparishad
{
	public class Form2 : Form
	{
		private IContainer components = null;

		private Button btnclose;

		private Button btnprint;

		private TextBox txtname;

		private TextBox txtpapername;

		private Label label9;

		private Label label8;

		private Label label7;

		private Label label6;

		private PictureBox pictureBox1;

		private Label label2;

		private TextBox txtkramank;

		private Label label1;

		private TextBox txtvartahar;

		private Label label3;

		private DateTimePicker dtpdate;

		private Button button1;

		private Process p = new Process();

		private Connection cn = new Connection();

		private Label label4;

		private int rawkind = 0;

		public int ID
		{
			get;
			set;
		}

		public new string Name
		{
			get;
			set;
		}

		public string PaperName
		{
			get;
			set;
		}

		public string sDate
		{
			get;
			set;
		}

		public string vartahar
		{
			get;
			set;
		}

		public Form2()
		{
			this.InitializeComponent();
		}

		private void btnclose_Click(object sender, EventArgs e)
		{
			base.Close();
		}

		private void btnprint_Click(object sender, EventArgs e)
		{
			try
			{
                if ((this.txtkramank.Text == "" ? 0 : Convert.ToInt32(!(this.txtname.Text == ""))) == 0)
				{
					MessageBox.Show("Please enter all information");
					return;
				}
				else
				{
					this.ID = Convert.ToInt32(this.txtkramank.Text.Trim());
					this.Name = this.txtname.Text.Trim();
					this.PaperName = this.txtpapername.Text.Trim();
					this.vartahar = this.txtvartahar.Text.Trim();
					this.sDate = this.dtpdate.Value.ToString("dd/MMM/yyyy");
					this.cn.InsertVidhanSabha(this.ID, this.vartahar, this.sDate, this.PaperName, this.Name, "VidhanSabha");
                    string workpath = "";
                    ReportDocument vidhanSabha = new ReportDocument();
                    workpath = @"D:\R\VidhanSabha.rpt";

                    vidhanSabha.FileName = workpath;
					//VidhanSabha vidhanSabha = new VidhanSabha()
					{
                        vidhanSabha.RecordSelectionFormula = string.Concat("{VidhanSabha.Kramank}= ", this.ID);
					};
					string str = string.Concat(Path.GetDirectoryName(Assembly.GetExecutingAssembly().Location), "\\VidhanSabha.pdf");
					vidhanSabha.ExportToDisk(ExportFormatType.PortableDocFormat, str);
					vidhanSabha.ExportToDisk(ExportFormatType.PortableDocFormat, string.Format("D:\\Pass_PDF\\VS-{0}.pdf", this.ID));
					//vidhanSabha.PrintToPrinter(1, false, 0, 0);
				}
			}
			catch (Exception exception1)
			{
				Exception exception = exception1;
				MessageBox.Show(string.Concat(exception.ToString(), "\n\nPlease contact to support"));
			}
		}

		private void button1_Click(object sender, EventArgs e)
		{
			this.txtkramank.Text = "";
			this.txtname.Text = "";
			this.txtpapername.Text = "";
			this.txtvartahar.Text = "";
			this.txtkramank.Select();
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
            this.btnclose = new System.Windows.Forms.Button();
            this.btnprint = new System.Windows.Forms.Button();
            this.txtname = new System.Windows.Forms.TextBox();
            this.txtpapername = new System.Windows.Forms.TextBox();
            this.label9 = new System.Windows.Forms.Label();
            this.label8 = new System.Windows.Forms.Label();
            this.label7 = new System.Windows.Forms.Label();
            this.label6 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.txtkramank = new System.Windows.Forms.TextBox();
            this.label1 = new System.Windows.Forms.Label();
            this.txtvartahar = new System.Windows.Forms.TextBox();
            this.label3 = new System.Windows.Forms.Label();
            this.dtpdate = new System.Windows.Forms.DateTimePicker();
            this.pictureBox1 = new System.Windows.Forms.PictureBox();
            this.button1 = new System.Windows.Forms.Button();
            this.label4 = new System.Windows.Forms.Label();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox1)).BeginInit();
            this.SuspendLayout();
            // 
            // btnclose
            // 
            this.btnclose.Font = new System.Drawing.Font("Microsoft Sans Serif", 15.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnclose.Location = new System.Drawing.Point(283, 495);
            this.btnclose.Name = "btnclose";
            this.btnclose.Size = new System.Drawing.Size(104, 33);
            this.btnclose.TabIndex = 8;
            this.btnclose.Text = "बंद";
            this.btnclose.UseVisualStyleBackColor = true;
            this.btnclose.Click += new System.EventHandler(this.btnclose_Click);
            // 
            // btnprint
            // 
            this.btnprint.Font = new System.Drawing.Font("Microsoft Sans Serif", 15.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnprint.Location = new System.Drawing.Point(173, 495);
            this.btnprint.Name = "btnprint";
            this.btnprint.Size = new System.Drawing.Size(104, 33);
            this.btnprint.TabIndex = 7;
            this.btnprint.Text = "प्रिंट";
            this.btnprint.UseVisualStyleBackColor = true;
            this.btnprint.Click += new System.EventHandler(this.btnprint_Click);
            // 
            // txtname
            // 
            this.txtname.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.txtname.Location = new System.Drawing.Point(173, 417);
            this.txtname.Multiline = true;
            this.txtname.Name = "txtname";
            this.txtname.Size = new System.Drawing.Size(251, 28);
            this.txtname.TabIndex = 6;
            this.txtname.TextChanged += new System.EventHandler(this.textBox4_TextChanged);
            // 
            // txtpapername
            // 
            this.txtpapername.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.txtpapername.Location = new System.Drawing.Point(55, 359);
            this.txtpapername.Multiline = true;
            this.txtpapername.Name = "txtpapername";
            this.txtpapername.Size = new System.Drawing.Size(251, 28);
            this.txtpapername.TabIndex = 5;
            // 
            // label9
            // 
            this.label9.AutoSize = true;
            this.label9.Font = new System.Drawing.Font("Microsoft Sans Serif", 15.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label9.Location = new System.Drawing.Point(451, 413);
            this.label9.Name = "label9";
            this.label9.Size = new System.Drawing.Size(142, 25);
            this.label9.TabIndex = 28;
            this.label9.Text = "यांनी वापरायची आहे.";
            // 
            // label8
            // 
            this.label8.AutoSize = true;
            this.label8.Font = new System.Drawing.Font("Microsoft Sans Serif", 15.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label8.Location = new System.Drawing.Point(51, 413);
            this.label8.Name = "label8";
            this.label8.Size = new System.Drawing.Size(101, 25);
            this.label8.TabIndex = 27;
            this.label8.Text = "हि प्रवेशपत्रिका";
            // 
            // label7
            // 
            this.label7.AutoSize = true;
            this.label7.Font = new System.Drawing.Font("Microsoft Sans Serif", 15.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label7.Location = new System.Drawing.Point(325, 355);
            this.label7.Name = "label7";
            this.label7.Size = new System.Drawing.Size(379, 25);
            this.label7.TabIndex = 26;
            this.label7.Text = "वृत्तपत्र संस्थेच्या प्रतिनिधीला वार्ताहर गॅलरीत प्रवेश दयावा.";
            // 
            // label6
            // 
            this.label6.AutoSize = true;
            this.label6.Font = new System.Drawing.Font("Microsoft Sans Serif", 15.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label6.Location = new System.Drawing.Point(236, 303);
            this.label6.Name = "label6";
            this.label6.Size = new System.Drawing.Size(378, 25);
            this.label6.TabIndex = 25;
            this.label6.Text = "रोजी सुरु होणाऱ्या विधानसभेच्या अधिवेशनाच्या कालावधीत";
            this.label6.Click += new System.EventHandler(this.label6_Click);
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Font = new System.Drawing.Font("Microsoft Sans Serif", 14.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label2.Location = new System.Drawing.Point(289, 224);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(119, 24);
            this.label2.TabIndex = 20;
            this.label2.Text = "महराष्ट्र विधानसभा";
            // 
            // txtkramank
            // 
            this.txtkramank.Font = new System.Drawing.Font("Sakal Marathi", 12F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.txtkramank.Location = new System.Drawing.Point(521, 30);
            this.txtkramank.MaxLength = 2147483647;
            this.txtkramank.Multiline = true;
            this.txtkramank.Name = "txtkramank";
            this.txtkramank.Size = new System.Drawing.Size(156, 28);
            this.txtkramank.TabIndex = 1;
            this.txtkramank.KeyPress += new System.Windows.Forms.KeyPressEventHandler(this.txtkramank_KeyPress);
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label1.Location = new System.Drawing.Point(467, 31);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(39, 20);
            this.label1.TabIndex = 0;
            this.label1.Text = "क्रमांक";
            // 
            // txtvartahar
            // 
            this.txtvartahar.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.txtvartahar.Location = new System.Drawing.Point(520, 79);
            this.txtvartahar.Multiline = true;
            this.txtvartahar.Name = "txtvartahar";
            this.txtvartahar.Size = new System.Drawing.Size(156, 28);
            this.txtvartahar.TabIndex = 3;
            this.txtvartahar.TextChanged += new System.EventHandler(this.textBox5_TextChanged);
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label3.Location = new System.Drawing.Point(466, 80);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(43, 20);
            this.label3.TabIndex = 2;
            this.label3.Text = "वार्ताहर";
            this.label3.Click += new System.EventHandler(this.label3_Click);
            // 
            // dtpdate
            // 
            this.dtpdate.Font = new System.Drawing.Font("Sakal Marathi", 12F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.dtpdate.Format = System.Windows.Forms.DateTimePickerFormat.Short;
            this.dtpdate.Location = new System.Drawing.Point(55, 303);
            this.dtpdate.Name = "dtpdate";
            this.dtpdate.Size = new System.Drawing.Size(172, 34);
            this.dtpdate.TabIndex = 4;
            // 
            // pictureBox1
            // 
            this.pictureBox1.Image = global::Pass_Vidhanparishad.Properties.Resources.satyameva_jayate1_1_;
            this.pictureBox1.Location = new System.Drawing.Point(293, 11);
            this.pictureBox1.Name = "pictureBox1";
            this.pictureBox1.Size = new System.Drawing.Size(119, 206);
            this.pictureBox1.TabIndex = 21;
            this.pictureBox1.TabStop = false;
            // 
            // button1
            // 
            this.button1.Font = new System.Drawing.Font("Microsoft Sans Serif", 15.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.button1.Location = new System.Drawing.Point(393, 495);
            this.button1.Name = "button1";
            this.button1.Size = new System.Drawing.Size(104, 33);
            this.button1.TabIndex = 34;
            this.button1.Text = "नविन";
            this.button1.UseVisualStyleBackColor = true;
            this.button1.Click += new System.EventHandler(this.button1_Click);
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Font = new System.Drawing.Font("Microsoft Sans Serif", 15.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label4.Location = new System.Drawing.Point(529, 488);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(209, 75);
            this.label4.TabIndex = 35;
            this.label4.Text = "     (श्री.राजेंद्र भागवत)\r\n     सचिव (कार्यभार)\r\nमहाराष्ट्र विधानमंडळ सचिवालय";
            // 
            // Form2
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(750, 572);
            this.Controls.Add(this.label4);
            this.Controls.Add(this.button1);
            this.Controls.Add(this.dtpdate);
            this.Controls.Add(this.txtvartahar);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.btnclose);
            this.Controls.Add(this.btnprint);
            this.Controls.Add(this.txtname);
            this.Controls.Add(this.txtpapername);
            this.Controls.Add(this.label9);
            this.Controls.Add(this.label8);
            this.Controls.Add(this.label7);
            this.Controls.Add(this.label6);
            this.Controls.Add(this.pictureBox1);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.txtkramank);
            this.Controls.Add(this.label1);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.None;
            this.Name = "Form2";
            this.ShowInTaskbar = false;
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "Vidhan Sabha";
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox1)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

		}

		private void label3_Click(object sender, EventArgs e)
		{
		}

		private void label6_Click(object sender, EventArgs e)
		{
		}

		private void textBox2_TextChanged(object sender, EventArgs e)
		{
		}

		private void textBox4_TextChanged(object sender, EventArgs e)
		{
		}

		private void textBox5_TextChanged(object sender, EventArgs e)
		{
		}

		private void txtkramank_KeyPress(object sender, KeyPressEventArgs e)
		{
			this.cn.AllowNumber(e);
		}
	}
}