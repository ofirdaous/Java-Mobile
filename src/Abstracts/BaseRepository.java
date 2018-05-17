package Abstracts;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import Entities.Settings;

public class BaseRepository {
	// region Members
	protected Settings settings;
	protected SimpleDateFormat dateSourceFormat;
	// endregion

	// region Constructor
	public BaseRepository(Settings settings) {
		this.settings = settings;
		this.dateSourceFormat = new SimpleDateFormat("dd-MM-yyyy");
	}
	// endregion

	// region Protected Methods
	// Receives driver manager connection entity.
	protected Connection getConnectionDrive() throws SQLException {
		return DriverManager.getConnection(settings.sqlConnectionString, settings.userName, settings.password);
	}
	// endregion
}
