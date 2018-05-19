package Controllers;

import Abstracts.IController;

public class TenantController implements IController {

	@Override
	public void execute() throws Exception {
		System.out.println("Good here");
	}
}
