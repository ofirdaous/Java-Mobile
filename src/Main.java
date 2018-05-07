public class Main {
/*
 * Moshe Binieli - 311800668
 * Ofir Daous - 203796156
 */
	public static void main(String[] args)
	{
		try
		{
			Menu menu = new Menu();
			menu.ExecuteMenu();
		}
		catch (Exception e) 
		{
			System.out.println(e.getMessage());
		}
	}
}
