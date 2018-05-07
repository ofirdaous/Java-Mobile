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

	public static void RepositoryTests() throws Exception{
		// ApartmentTester aTester = new ApartmentTester();
		// aTester.ExecuteTests();

		//PaymentTester pTester = new PaymentTester();
		//pTester.ExecuteTests();
	}

	public static void Task1Tests() throws Exception{
		//Task1Tester tTester = new Task1Tester();
		//tTester.ExecuteTests();
	}
}
