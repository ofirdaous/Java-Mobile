package Entities;

public class Settings {
	//region Members
	public String UserName;
	public String Password;
	public String SqlConnectionString;
	//endregion

	//region Constructor
	public Settings(String sqlConnectionString, String userName, String password){
		if(!IsArgumentsValid(sqlConnectionString, userName, password))
			throw new Exception("Arguments for settings are not valid, aborting.");

		this.UserName = userName;
		this.Password = password;
		this.SqlConnectionString = sqlConnectionString;
	}
	//endregion

	//region Private Methods
	// Validates whether the received arguments are valid or not.
	private boolean IsArgumentsValid(String sqlConnectionString, String userName, String password) {
		if(sqlConnectionString == null || userName == null || password == null)
			return false;
		else
			return true;
	}
	//endregion
}
