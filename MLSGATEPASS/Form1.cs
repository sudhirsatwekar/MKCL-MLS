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
using Webcam_Test;
using System.Data.SqlClient;
using System.Configuration;

namespace Pass_Vidhanparishad
{
	public class Form1 : Form
	{
		private Process p = new Process();

		private Connection cn = new Connection();

		private byte[] imageData = null;

		private int rawkind = 0;

		private string photoid = "0";

		private IContainer components = null;

		private Label label1;

		private TextBox txtkramank;

		private Label label2;

		private PictureBox pictureBox1;

		private Label label3;

		private Label label5;

		private Label label6;

		private Label label7;

		private Label label8;

		private Label label9;

		private TextBox txtname;

		private TextBox txtpadnam;

		private TextBox txtshipharas;

		private Label label10;

		private Button btnprint;

		private Button btnclose;

		private DateTimePicker dtp;

		private PictureBox img;

		private Button btn_WebStart;

		private Button btn_Browse;

		private Button button1;

		private TextBox txtadhiveshan;

		private TextBox txt_City;

		private Label label4;

		private TextBox txt_Year;

		private Label label11;

		private DateTimePicker DTP_Date;

		public string City
		{
			get;
			set;
		}

		public string date
		{
			get;
			set;
		}

		public string Desig
		{
			get;
			set;
		}

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

		public Image photo
		{
			get;
			set;
		}

		public string sDate
		{
			get;
			set;
		}

		public string Shipharas
		{
			get;
			set;
		}

		public string Year
		{
			get;
			set;
		}

		public Form1()
		{
			this.InitializeComponent();
		}

		private void btn_Browse_Click(object sender, EventArgs e)
		{
			if (this.txtkramank.Text == "")
			{
				MessageBox.Show("Please Enter your Number");
			}
			else
			{
				this.photoid = this.txtkramank.Text.Trim();
				OpenFileDialog openFileDialog = new OpenFileDialog()
				{
					Filter = "Image files (*.jpg, *.jpeg, *.jpe, *.jfif, *.png) | *.jpg; *.jpeg; *.jpe; *.jfif; *.png"
				};
				if (openFileDialog.ShowDialog() == System.Windows.Forms.DialogResult.OK)
				{
					Path.GetExtension(openFileDialog.FileName);
					Bitmap bitmap = new Bitmap(openFileDialog.FileName);
					this.img.Image = bitmap;
					this.imageData = (byte[])(new ImageConverter()).ConvertTo(this.img.Image, typeof(byte[]));
				}
			}
		}

		private void btn_WebStart_Click(object sender, EventArgs e)
		{
			if (this.txtkramank.Text == "")
			{
				MessageBox.Show("Please Enter your Number");
			}
			else
			{
				try
				{
					this.photoid = this.txtkramank.Text.Trim();
					this.img.Image = new Bitmap(string.Concat(Application.StartupPath, "\\Images\\Photo.jpg"));
					(new Webcam_Test.Form1()).ShowDialog();
					if (Webcam_Test.Form1.imageData != null)
					{
						MemoryStream memoryStream = new MemoryStream(Webcam_Test.Form1.imageData);
						this.img.Image = Image.FromStream(memoryStream);
						memoryStream.Close();
					}
					this.imageData = Webcam_Test.Form1.imageData;
				}
				catch (Exception exception)
				{
					MessageBox.Show(exception.Message);
				}
			}
		}

		private void btnclose_Click(object sender, EventArgs e)
		{
			base.Close();
		}

		private void btnprint_Click(object sender, EventArgs e)
		{
			try
			{
				if (!(this.txtkramank.Text == "" || this.txtname.Text == "" ? false : !(this.txt_City.Text == "")))
				{
					MessageBox.Show("Please enter all information");
					return;
				}
				else if (this.imageData == null)
				{
					MessageBox.Show("Please select photo");
					return;
				}
				else
				{
					this.ID = Convert.ToInt32(this.txtkramank.Text.Trim());
					this.Year = this.txt_Year.Text.Trim();
					this.City = this.txt_City.Text.Trim();
					this.Name = this.txtname.Text.Trim();
					this.Desig = this.txtpadnam.Text.Trim();
					this.Shipharas = this.txtshipharas.Text.Trim();
					this.sDate = this.dtp.Value.ToString("dd/MMM/yyyy");
					this.cn.InsertVidhanBhavan(this.ID, this.Name, this.Desig, this.Shipharas, this.sDate, this.imageData, "VidhanBhavan");
					//VidhanBhavan vidhanBhavan = new VidhanBhavan();
                    string workpath = "";
                    ReportDocument vidhanBhavan = new ReportDocument();
                    workpath = @"D:\R\vidhanBhavan.rpt";
                    vidhanBhavan.FileName = workpath;
                    SqlConnection con= new SqlConnection(ConfigurationManager.AppSettings["ConnectionString"]);
                   
					vidhanBhavan.DataDefinition.FormulaFields["Adhiveshan"].Text = string.Concat("'", this.txtadhiveshan.Text, "'");
					vidhanBhavan.DataDefinition.FormulaFields["City"].Text = string.Concat("'", this.txt_City.Text, "'");
					vidhanBhavan.DataDefinition.FormulaFields["Year"].Text = string.Concat("'", this.txt_Year.Text, "'");
					vidhanBhavan.DataDefinition.FormulaFields["date"].Text = string.Concat("'", this.DTP_Date.Text, "'");
					vidhanBhavan.RecordSelectionFormula = string.Concat("{VidhanBhavan.Kramank}= ", this.ID);
					string str = string.Concat(Path.GetDirectoryName(Assembly.GetExecutingAssembly().Location), "\\VidhanBhavan.pdf");
					vidhanBhavan.ExportToDisk(ExportFormatType.PortableDocFormat, str);
					vidhanBhavan.ExportToDisk(ExportFormatType.PortableDocFormat, string.Format("D:\\Pass_PDF\\VB-{0}.pdf", this.ID));
					//vidhanBhavan.PrintToPrinter(1, false, 0, 0);
					MessageBox.Show(string.Format("File saved to D:\\Pass_PDF\\VB-{0}.pdf", this.ID));
				}
			}
			catch (Exception exception1)
			{
				Exception exception = exception1;
				MessageBox.Show(string.Concat(exception.ToString(), "Please contact to support"));
			}
		}

		private void button1_Click(object sender, EventArgs e)
		{
			this.txtname.Text = "";
			this.txtpadnam.Text = "";
			this.txtshipharas.Text = "";
			this.txtkramank.Text = "";
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

		private void Form1_Load(object sender, EventArgs e)
		{
		}

		private void InitializeComponent()
		{
            this.label1 = new System.Windows.Forms.Label();
            this.txtkramank = new System.Windows.Forms.TextBox();
            this.label2 = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.label5 = new System.Windows.Forms.Label();
            this.label6 = new System.Windows.Forms.Label();
            this.label7 = new System.Windows.Forms.Label();
            this.label8 = new System.Windows.Forms.Label();
            this.label9 = new System.Windows.Forms.Label();
            this.txtname = new System.Windows.Forms.TextBox();
            this.txtpadnam = new System.Windows.Forms.TextBox();
            this.txtshipharas = new System.Windows.Forms.TextBox();
            this.label10 = new System.Windows.Forms.Label();
            this.btnprint = new System.Windows.Forms.Button();
            this.btnclose = new System.Windows.Forms.Button();
            this.dtp = new System.Windows.Forms.DateTimePicker();
            this.btn_WebStart = new System.Windows.Forms.Button();
            this.btn_Browse = new System.Windows.Forms.Button();
            this.img = new System.Windows.Forms.PictureBox();
            this.pictureBox1 = new System.Windows.Forms.PictureBox();
            this.button1 = new System.Windows.Forms.Button();
            this.txtadhiveshan = new System.Windows.Forms.TextBox();
            this.txt_City = new System.Windows.Forms.TextBox();
            this.label4 = new System.Windows.Forms.Label();
            this.txt_Year = new System.Windows.Forms.TextBox();
            this.label11 = new System.Windows.Forms.Label();
            this.DTP_Date = new System.Windows.Forms.DateTimePicker();
            ((System.ComponentModel.ISupportInitialize)(this.img)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox1)).BeginInit();
            this.SuspendLayout();
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Font = new System.Drawing.Font("Microsoft Sans Serif", 14.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label1.Location = new System.Drawing.Point(481, 57);
            this.label1.Margin = new System.Windows.Forms.Padding(6, 0, 6, 0);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(46, 24);
            this.label1.TabIndex = 0;
            this.label1.Text = "क्रमांक";
            // 
            // txtkramank
            // 
            this.txtkramank.Font = new System.Drawing.Font("Sakal Marathi", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.txtkramank.Location = new System.Drawing.Point(539, 57);
            this.txtkramank.Margin = new System.Windows.Forms.Padding(6);
            this.txtkramank.MaxLength = 2147483647;
            this.txtkramank.Name = "txtkramank";
            this.txtkramank.Size = new System.Drawing.Size(171, 34);
            this.txtkramank.TabIndex = 0;
            this.txtkramank.KeyPress += new System.Windows.Forms.KeyPressEventHandler(this.txtkramank_KeyPress);
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Font = new System.Drawing.Font("Microsoft Sans Serif", 14.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label2.Location = new System.Drawing.Point(285, 231);
            this.label2.Margin = new System.Windows.Forms.Padding(6, 0, 6, 0);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(76, 24);
            this.label2.TabIndex = 2;
            this.label2.Text = "विधानभवन,";
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Font = new System.Drawing.Font("Microsoft Sans Serif", 14.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label3.Location = new System.Drawing.Point(325, 254);
            this.label3.Margin = new System.Windows.Forms.Padding(6, 0, 6, 0);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(157, 24);
            this.label3.TabIndex = 4;
            this.label3.Text = "विधानपरिषद / विधानसभा";
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Font = new System.Drawing.Font("Microsoft Sans Serif", 14.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label5.Location = new System.Drawing.Point(292, 298);
            this.label5.Margin = new System.Windows.Forms.Padding(6, 0, 6, 0);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(264, 24);
            this.label5.TabIndex = 6;
            this.label5.Text = "वृत्तपत्र / दूरचित्रवाहिनी प्रतिनिधी प्रवेशपत्रिका";
            // 
            // label6
            // 
            this.label6.AutoSize = true;
            this.label6.Font = new System.Drawing.Font("Microsoft Sans Serif", 15.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label6.Location = new System.Drawing.Point(77, 349);
            this.label6.Margin = new System.Windows.Forms.Padding(6, 0, 6, 0);
            this.label6.Name = "label6";
            this.label6.Size = new System.Drawing.Size(35, 25);
            this.label6.TabIndex = 2;
            this.label6.Text = "नाव";
            // 
            // label7
            // 
            this.label7.AutoSize = true;
            this.label7.Font = new System.Drawing.Font("Microsoft Sans Serif", 15.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label7.Location = new System.Drawing.Point(77, 382);
            this.label7.Margin = new System.Windows.Forms.Padding(6, 0, 6, 0);
            this.label7.Name = "label7";
            this.label7.Size = new System.Drawing.Size(55, 25);
            this.label7.TabIndex = 4;
            this.label7.Text = "पदनाम";
            // 
            // label8
            // 
            this.label8.AutoSize = true;
            this.label8.Font = new System.Drawing.Font("Microsoft Sans Serif", 15.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label8.Location = new System.Drawing.Point(77, 419);
            this.label8.Margin = new System.Windows.Forms.Padding(6, 0, 6, 0);
            this.label8.Name = "label8";
            this.label8.Size = new System.Drawing.Size(62, 25);
            this.label8.TabIndex = 6;
            this.label8.Text = "शिफारस";
            // 
            // label9
            // 
            this.label9.AutoSize = true;
            this.label9.Font = new System.Drawing.Font("Microsoft Sans Serif", 15.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label9.Location = new System.Drawing.Point(297, 788);
            this.label9.Margin = new System.Windows.Forms.Padding(6, 0, 6, 0);
            this.label9.Name = "label9";
            this.label9.Size = new System.Drawing.Size(101, 25);
            this.label9.TabIndex = 8;
            this.label9.Text = "idnaaMk";
            // 
            // txtname
            // 
            this.txtname.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.txtname.Location = new System.Drawing.Point(150, 350);
            this.txtname.Margin = new System.Windows.Forms.Padding(6);
            this.txtname.Multiline = true;
            this.txtname.Name = "txtname";
            this.txtname.Size = new System.Drawing.Size(251, 28);
            this.txtname.TabIndex = 5;
            // 
            // txtpadnam
            // 
            this.txtpadnam.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.txtpadnam.Location = new System.Drawing.Point(150, 384);
            this.txtpadnam.Margin = new System.Windows.Forms.Padding(6);
            this.txtpadnam.Multiline = true;
            this.txtpadnam.Name = "txtpadnam";
            this.txtpadnam.Size = new System.Drawing.Size(251, 28);
            this.txtpadnam.TabIndex = 6;
            // 
            // txtshipharas
            // 
            this.txtshipharas.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.txtshipharas.Location = new System.Drawing.Point(150, 418);
            this.txtshipharas.Margin = new System.Windows.Forms.Padding(6);
            this.txtshipharas.Multiline = true;
            this.txtshipharas.Name = "txtshipharas";
            this.txtshipharas.Size = new System.Drawing.Size(251, 28);
            this.txtshipharas.TabIndex = 7;
            this.txtshipharas.TextChanged += new System.EventHandler(this.txtshipharas_TextChanged);
            // 
            // label10
            // 
            this.label10.AutoSize = true;
            this.label10.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label10.Location = new System.Drawing.Point(992, 932);
            this.label10.Margin = new System.Windows.Forms.Padding(6, 0, 6, 0);
            this.label10.Name = "label10";
            this.label10.Size = new System.Drawing.Size(308, 60);
            this.label10.TabIndex = 15;
            this.label10.Text = "            Da AnaMt kLsao\r\n              `pQaana sicava\r\nmaharaYT ivaQaanamaMDL," +
                " saicavaalaya";
            // 
            // btnprint
            // 
            this.btnprint.Font = new System.Drawing.Font("Microsoft Sans Serif", 15.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnprint.Location = new System.Drawing.Point(429, 348);
            this.btnprint.Margin = new System.Windows.Forms.Padding(6);
            this.btnprint.Name = "btnprint";
            this.btnprint.Size = new System.Drawing.Size(85, 30);
            this.btnprint.TabIndex = 11;
            this.btnprint.Text = "प्रिंट";
            this.btnprint.UseVisualStyleBackColor = true;
            this.btnprint.Click += new System.EventHandler(this.btnprint_Click);
            // 
            // btnclose
            // 
            this.btnclose.Font = new System.Drawing.Font("Microsoft Sans Serif", 15.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnclose.Location = new System.Drawing.Point(429, 381);
            this.btnclose.Margin = new System.Windows.Forms.Padding(6);
            this.btnclose.Name = "btnclose";
            this.btnclose.Size = new System.Drawing.Size(85, 30);
            this.btnclose.TabIndex = 12;
            this.btnclose.Text = "बंद";
            this.btnclose.UseVisualStyleBackColor = true;
            this.btnclose.Click += new System.EventHandler(this.btnclose_Click);
            // 
            // dtp
            // 
            this.dtp.CustomFormat = "dd/MMM/yyyy";
            this.dtp.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.dtp.Format = System.Windows.Forms.DateTimePickerFormat.Short;
            this.dtp.Location = new System.Drawing.Point(444, 792);
            this.dtp.Margin = new System.Windows.Forms.Padding(6);
            this.dtp.Name = "dtp";
            this.dtp.Size = new System.Drawing.Size(457, 26);
            this.dtp.TabIndex = 9;
            // 
            // btn_WebStart
            // 
            this.btn_WebStart.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btn_WebStart.Location = new System.Drawing.Point(101, 215);
            this.btn_WebStart.Margin = new System.Windows.Forms.Padding(6);
            this.btn_WebStart.Name = "btn_WebStart";
            this.btn_WebStart.Size = new System.Drawing.Size(75, 28);
            this.btn_WebStart.TabIndex = 9;
            this.btn_WebStart.Text = "Web Cam";
            this.btn_WebStart.UseVisualStyleBackColor = true;
            this.btn_WebStart.Click += new System.EventHandler(this.btn_WebStart_Click);
            // 
            // btn_Browse
            // 
            this.btn_Browse.Location = new System.Drawing.Point(191, 216);
            this.btn_Browse.Margin = new System.Windows.Forms.Padding(6);
            this.btn_Browse.Name = "btn_Browse";
            this.btn_Browse.Size = new System.Drawing.Size(60, 28);
            this.btn_Browse.TabIndex = 10;
            this.btn_Browse.Text = "...";
            this.btn_Browse.UseVisualStyleBackColor = true;
            this.btn_Browse.Click += new System.EventHandler(this.btn_Browse_Click);
            // 
            // img
            // 
            this.img.Image = global::Pass_Vidhanparishad.Properties.Resources.Photo;
            this.img.Location = new System.Drawing.Point(102, 48);
            this.img.Margin = new System.Windows.Forms.Padding(6);
            this.img.Name = "img";
            this.img.Size = new System.Drawing.Size(150, 160);
            this.img.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.img.TabIndex = 19;
            this.img.TabStop = false;
            // 
            // pictureBox1
            // 
            this.pictureBox1.Image = global::Pass_Vidhanparishad.Properties.Resources.satyameva_jayate1_1_;
            this.pictureBox1.Location = new System.Drawing.Point(328, 20);
            this.pictureBox1.Margin = new System.Windows.Forms.Padding(6);
            this.pictureBox1.Name = "pictureBox1";
            this.pictureBox1.Size = new System.Drawing.Size(120, 202);
            this.pictureBox1.TabIndex = 3;
            this.pictureBox1.TabStop = false;
            // 
            // button1
            // 
            this.button1.Font = new System.Drawing.Font("Microsoft Sans Serif", 15.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.button1.Location = new System.Drawing.Point(429, 414);
            this.button1.Margin = new System.Windows.Forms.Padding(6);
            this.button1.Name = "button1";
            this.button1.Size = new System.Drawing.Size(85, 30);
            this.button1.TabIndex = 13;
            this.button1.Text = "नविन";
            this.button1.UseVisualStyleBackColor = true;
            this.button1.Click += new System.EventHandler(this.button1_Click);
            // 
            // txtadhiveshan
            // 
            this.txtadhiveshan.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Bold);
            this.txtadhiveshan.Location = new System.Drawing.Point(273, 276);
            this.txtadhiveshan.Margin = new System.Windows.Forms.Padding(6);
            this.txtadhiveshan.Name = "txtadhiveshan";
            this.txtadhiveshan.Size = new System.Drawing.Size(224, 26);
            this.txtadhiveshan.TabIndex = 3;
            this.txtadhiveshan.Text = "हिवाळी अधिवेशन";
            // 
            // txt_City
            // 
            this.txt_City.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.txt_City.Location = new System.Drawing.Point(373, 229);
            this.txt_City.Margin = new System.Windows.Forms.Padding(6);
            this.txt_City.Name = "txt_City";
            this.txt_City.Size = new System.Drawing.Size(146, 26);
            this.txt_City.TabIndex = 1;
            this.txt_City.Text = "नागपूर";
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Font = new System.Drawing.Font("Microsoft Sans Serif", 15.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label4.Location = new System.Drawing.Point(536, 423);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(209, 75);
            this.label4.TabIndex = 25;
            this.label4.Text = "    (श्री.राजेंद्र भागवत)\r\n     सचिव (कार्यभार)\r\nमहाराष्ट्र विधानमंडळ सचिवालय";
            // 
            // txt_Year
            // 
            this.txt_Year.Font = new System.Drawing.Font("Sakal Marathi", 8.999999F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.txt_Year.Location = new System.Drawing.Point(498, 276);
            this.txt_Year.Margin = new System.Windows.Forms.Padding(6);
            this.txt_Year.Name = "txt_Year";
            this.txt_Year.Size = new System.Drawing.Size(71, 27);
            this.txt_Year.TabIndex = 4;
            this.txt_Year.Text = "२०१९";
            // 
            // label11
            // 
            this.label11.AutoSize = true;
            this.label11.Font = new System.Drawing.Font("Microsoft Sans Serif", 15.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label11.Location = new System.Drawing.Point(78, 451);
            this.label11.Name = "label11";
            this.label11.Size = new System.Drawing.Size(52, 25);
            this.label11.TabIndex = 27;
            this.label11.Text = "दिनांक";
            // 
            // DTP_Date
            // 
            this.DTP_Date.AllowDrop = true;
            this.DTP_Date.CustomFormat = "dd-MM-yyyy";
            this.DTP_Date.Font = new System.Drawing.Font("Sakal Marathi", 9.749999F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.DTP_Date.Format = System.Windows.Forms.DateTimePickerFormat.Custom;
            this.DTP_Date.Location = new System.Drawing.Point(150, 452);
            this.DTP_Date.Name = "DTP_Date";
            this.DTP_Date.Size = new System.Drawing.Size(251, 29);
            this.DTP_Date.TabIndex = 8;
            this.DTP_Date.Value = new System.DateTime(2019, 12, 4, 15, 37, 27, 0);
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(11F, 24F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(750, 572);
            this.Controls.Add(this.DTP_Date);
            this.Controls.Add(this.label11);
            this.Controls.Add(this.txt_Year);
            this.Controls.Add(this.label4);
            this.Controls.Add(this.txt_City);
            this.Controls.Add(this.txtadhiveshan);
            this.Controls.Add(this.button1);
            this.Controls.Add(this.btn_Browse);
            this.Controls.Add(this.btn_WebStart);
            this.Controls.Add(this.img);
            this.Controls.Add(this.dtp);
            this.Controls.Add(this.btnclose);
            this.Controls.Add(this.btnprint);
            this.Controls.Add(this.label10);
            this.Controls.Add(this.txtshipharas);
            this.Controls.Add(this.txtpadnam);
            this.Controls.Add(this.txtname);
            this.Controls.Add(this.label9);
            this.Controls.Add(this.label8);
            this.Controls.Add(this.label7);
            this.Controls.Add(this.label6);
            this.Controls.Add(this.label5);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.pictureBox1);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.txtkramank);
            this.Controls.Add(this.label1);
            this.Font = new System.Drawing.Font("Microsoft Sans Serif", 14.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.None;
            this.Margin = new System.Windows.Forms.Padding(6);
            this.Name = "Form1";
            this.ShowInTaskbar = false;
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "Vidhan Bhavan";
            this.Load += new System.EventHandler(this.Form1_Load);
            ((System.ComponentModel.ISupportInitialize)(this.img)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox1)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

		}

		public byte[] ReadImageFile(string imageLocation)
		{
			long length = (new FileInfo(imageLocation)).Length;
			FileStream fileStream = new FileStream(imageLocation, FileMode.Open, FileAccess.Read);
			this.imageData = (new BinaryReader(fileStream)).ReadBytes((int)length);
			return this.imageData;
		}

		private void txtkramank_KeyPress(object sender, KeyPressEventArgs e)
		{
			this.cn.AllowNumber(e);
		}

		private void txtshipharas_TextChanged(object sender, EventArgs e)
		{
		}
	}
}