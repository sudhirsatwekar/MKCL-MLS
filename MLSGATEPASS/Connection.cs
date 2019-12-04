using System;
using System.Configuration;
using System.Data;
using System.Data.Common;
using System.Data.SqlClient;
using System.Windows.Forms;

namespace Pass_Vidhanparishad
{
	internal class Connection
	{
		public static string connString;

		private SqlConnection con = new SqlConnection(Connection.connString);

		private SqlCommand cmd = new SqlCommand();

		public static int rowsaffected;

		private DataSet ds = new DataSet();

		private SqlDataReader dr;

		private int rawkind = 0;

		static Connection()
		{
			Connection.connString = ConfigurationManager.ConnectionStrings["ProjectConn"].ConnectionString;
		}

		public Connection()
		{
		}

		public void AllowNumber(KeyPressEventArgs e)
		{
			if ((char.IsNumber(e.KeyChar) || char.IsWhiteSpace(e.KeyChar) ? 0 : Convert.ToInt32(!char.IsControl(e.KeyChar))) == 0)
			{
				e.Handled = false;
			}
			else
			{
				e.Handled = true;
			}
		}

		public void InsertVidhanBhavan(int Id, string name, string Desig, string shipharas, string date, byte[] Photo, string Tablename)
		{
			try
			{
				try
				{
					this.cmd.Connection = this.con;
					this.cmd.CommandType = CommandType.StoredProcedure;
					this.cmd.CommandText = "InsertPassInfo";
					this.cmd.Parameters.Clear();
					this.cmd.Parameters.AddWithValue("@Id", SqlDbType.Int).Value = Id;
					this.cmd.Parameters.AddWithValue("@Name", SqlDbType.NVarChar).Value = name;
					this.cmd.Parameters.AddWithValue("@Padnaav", SqlDbType.NVarChar).Value = Desig;
					this.cmd.Parameters.AddWithValue("@Shipharas", SqlDbType.NVarChar).Value = shipharas;
					this.cmd.Parameters.AddWithValue("@Date", SqlDbType.Date).Value = date;
					this.cmd.Parameters.AddWithValue("@photo", SqlDbType.Image).Value = Photo;
					this.cmd.Parameters.AddWithValue("@Vartahar", SqlDbType.Image).Value = "";
					this.cmd.Parameters.AddWithValue("@Papername", SqlDbType.Image).Value = "";
					this.cmd.Parameters.AddWithValue("@TableName", SqlDbType.NVarChar).Value = Tablename;
					if (this.con.State == ConnectionState.Closed)
					{
						this.con.Open();
					}
					this.cmd.ExecuteNonQuery();
				}
				catch (Exception exception)
				{
					MessageBox.Show(exception.Message.ToString());
				}
			}
			finally
			{
				this.con.Close();
			}
		}

		public void InsertVidhanParishad(int Id, string vartahar, DateTime date, string papername, string name, byte[] Photo, string Tablename)
		{
			try
			{
				try
				{
					this.cmd.Connection = this.con;
					this.cmd.CommandType = CommandType.StoredProcedure;
					this.cmd.CommandText = "InsertPassInfoVidhan";
					this.cmd.Parameters.Clear();
					this.cmd.Parameters.AddWithValue("@Id", SqlDbType.Int).Value = Id;
					this.cmd.Parameters.AddWithValue("@Name", SqlDbType.NVarChar).Value = name;
					this.cmd.Parameters.AddWithValue("@Padnaav", SqlDbType.NVarChar).Value = "";
					this.cmd.Parameters.AddWithValue("@Shipharas", SqlDbType.NVarChar).Value = "";
					this.cmd.Parameters.AddWithValue("@Date", SqlDbType.Date).Value = date;
					this.cmd.Parameters.AddWithValue("@Vartahar", SqlDbType.Image).Value = vartahar;
					this.cmd.Parameters.AddWithValue("@Papername", SqlDbType.Image).Value = papername;
					this.cmd.Parameters.AddWithValue("@TableName", SqlDbType.NVarChar).Value = Tablename;
					if (this.con.State == ConnectionState.Closed)
					{
						this.con.Open();
					}
					this.cmd.ExecuteNonQuery();
				}
				catch (Exception exception)
				{
					MessageBox.Show(exception.Message.ToString());
				}
			}
			finally
			{
				this.con.Close();
			}
		}

		public void InsertVidhanSabha(int Id, string vartahar, string date, string papername, string name, string Tablename)
		{
			try
			{
				try
				{
					this.cmd.Connection = this.con;
					this.cmd.CommandType = CommandType.StoredProcedure;
					this.cmd.CommandText = "InsertPassInfoVidhan";
					this.cmd.Parameters.Clear();
					this.cmd.Parameters.AddWithValue("@Id", SqlDbType.Int).Value = Id;
					this.cmd.Parameters.AddWithValue("@Name", SqlDbType.NVarChar).Value = name;
					this.cmd.Parameters.AddWithValue("@Padnaav", SqlDbType.NVarChar).Value = "";
					this.cmd.Parameters.AddWithValue("@Shipharas", SqlDbType.NVarChar).Value = "";
					this.cmd.Parameters.AddWithValue("@Date", SqlDbType.Date).Value = date;
					this.cmd.Parameters.AddWithValue("@Vartahar", SqlDbType.Image).Value = vartahar;
					this.cmd.Parameters.AddWithValue("@Papername", SqlDbType.Image).Value = papername;
					this.cmd.Parameters.AddWithValue("@TableName", SqlDbType.NVarChar).Value = Tablename;
					if (this.con.State == ConnectionState.Closed)
					{
						this.con.Open();
					}
					this.cmd.ExecuteNonQuery();
				}
				catch (Exception exception)
				{
					MessageBox.Show(exception.Message.ToString());
				}
			}
			finally
			{
				this.con.Close();
			}
		}

		public DataTable RetrivePassRecord(int Id, string Tablename)
		{
			DataTable datatable = new DataTable();
			try
			{
				this.cmd.Connection = this.con;
				this.cmd.CommandType = CommandType.StoredProcedure;
				this.cmd.CommandText = "sp_getPassRecord";
				this.cmd.Parameters.Clear();
				this.cmd.Parameters.AddWithValue("@Id", SqlDbType.Int).Value = Id;
				this.cmd.Parameters.AddWithValue("@TableName", SqlDbType.NVarChar).Value = Tablename;
				if (this.con.State == ConnectionState.Closed)
				{
					this.con.Open();
				}
				SqlDataAdapter da = new SqlDataAdapter()
				{
					SelectCommand = this.cmd
				};
				da.Fill(datatable);
				this.con.Close();
			}
			catch (Exception exception)
			{
				MessageBox.Show(exception.Message.ToString());
			}
			return datatable;
		}
	}
}