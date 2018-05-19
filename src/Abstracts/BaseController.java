package Abstracts;

import Entities.Settings;

public class BaseController {
	// region Members
	protected Settings settings;
	// endregion

	// region Constructor
	public BaseController() throws Exception {
		this.settings = getSettings();
	}
	// endregion

	// region Public Methods
	// Recives settings of the project and initialize it to global variable.
	private Settings getSettings() throws Exception {
		// return new Settings("jdbc:mysql://localhost:3306/project1?autoReconnect=true&useSSL=false", "root", "Mako123456");
		return new Settings("jdbc:mysql://localhost:3306/tenants?autoReconnect=true&useSSL=false", "root", "Ab456123@@");
	}
	// endregion
}
