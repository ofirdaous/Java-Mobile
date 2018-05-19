import Abstracts.IController;
import Controllers.TenantController;
import Controllers.HouseCommitteeController;

public class Menu {
	// region Public Methods
	public void executeMenu() throws Exception {
		IController controller = null;
		System.out.println("Choose who you are: ");
		int choice = 1;

		if (choice == 1)
			controller = new HouseCommitteeController();
		else if (choice == 2)
			controller = new TenantController();

		if (controller != null)
			controller.execute();
	}
	// endregion
}
