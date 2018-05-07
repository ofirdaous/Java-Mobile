package Abstracts;

import Entities.Settings;

public class BaseController {
	//region Members
	protected Settings settings;
	//endregion

	//region Constructor
	public BaseController() {
		this.settings = GetSettings();
	}
	//endregion

	//region Public Methods
	// Recives settings of the project and initialize it to global variable.
	private Settings GetSettings(){
		return new Settings("jdbc:mysql://localhost:3306/project1", "root", "Mako123456");
	}
	//endregion
}
