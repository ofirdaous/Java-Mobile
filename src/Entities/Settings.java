package Entities;

public class Settings {
	// region Members
	public String userName;
	public String password;
	public String sqlConnectionString;
	// endregion

	// region Constructor
	public Settings(String sqlConnectionString, String userName, String password) throws Exception {
		if (!isSettingsArgumentsValid(sqlConnectionString, userName, password))
			throw new Exception("Arguments for settings are not valid, aborting.");

		this.userName = userName;
		this.password = password;
		this.sqlConnectionString = sqlConnectionString;
	}
	// endregion

	// region Private Methods
	// Validates whether the received arguments are valid or not.
	private boolean isSettingsArgumentsValid(String sqlConnectionString, String userName, String password) {
		if (sqlConnectionString == null || userName == null || password == null)
			return false;
		else
			return true;
	}
	// endregion
}
