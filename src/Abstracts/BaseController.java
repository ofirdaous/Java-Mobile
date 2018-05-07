package Abstracts;

import Entities.Settings;

public class BaseController {
	protected Settings settings;

	public BaseController(){
		this.settings = GetSettings();
	}

	private Settings GetSettings(){
		String url = "jdbc:mysql://localhost:3306/project1";
		String user = "root";
		String password = "Mako123456";
		Settings settings = new Settings(url, user, password);

		return settings;
	}
}
