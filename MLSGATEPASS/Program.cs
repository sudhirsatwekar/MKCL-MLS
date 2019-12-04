using System;
using System.IO;
using System.Windows.Forms;

namespace Pass_Vidhanparishad
{
	internal static class Program
	{
		[STAThread]
		private static void Main()
		{
			string str = "D:\\Pass_PDF";
			if (!Directory.Exists(str))
			{
				Directory.CreateDirectory(str);
			}
			Application.EnableVisualStyles();
			Application.SetCompatibleTextRenderingDefault(false);
			Application.Run(new Master());
		}
	}
}