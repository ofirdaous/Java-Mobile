package Entities;

public class Settings {
	public String SqlConnectionString;
	public String UserName;
	public String Password;

	public Settings(String sqlConnectionString, String userName, String password){
		this.SqlConnectionString = sqlConnectionString;
		this.UserName = userName;
		this.Password = password;
	}
}
