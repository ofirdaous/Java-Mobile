package Repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Abstracts.BaseRepository;
import Abstracts.IRepository;
import Entities.ApartmentModel;
import Entities.Settings;

public class ApartmentRepository extends BaseRepository implements IRepository<ApartmentModel> {
	//region Constructor
	public ApartmentRepository(Settings settings) {
		super(settings);
	}
	//endregion

	//region Public Methods
	@Override
	public void create(ApartmentModel model) throws Exception {
		if(!IsModelValid(model))
			throw new Exception("Model is not valid.");

		try (Connection sqlConnection = DriverManager.getConnection(settings.SqlConnectionString,settings.UserName,settings.Password)){
			Statement queryStatement = sqlConnection.createStatement();

			queryStatement.executeUpdate("INSERT INTO apartment (ApartmentNumber) " 
					+ "VALUES ("+model.ApartmentNumber+")");

			sqlConnection.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} 
	}

	@Override
	public ArrayList<ApartmentModel> getAll() throws Exception {
		ArrayList<ApartmentModel> apartmentList = new ArrayList<ApartmentModel>();

		try (Connection sqlConnection = DriverManager.getConnection(settings.SqlConnectionString,settings.UserName,settings.Password)){
			Statement queryStatement = sqlConnection.createStatement();
			ResultSet resultSet = queryStatement.executeQuery("SELECT * FROM apartment");

			while (resultSet.next()) {
				ApartmentModel model = new ApartmentModel();

				model.ID = Integer.parseInt(resultSet.getString("ID"));
				model.ApartmentNumber = Integer.parseInt(resultSet.getString("ApartmentNumber"));

				if(model != null)
					apartmentList.add(model);
			}

			sqlConnection.close();
		} catch (SQLException ex) {
			throw new Exception(ex.getMessage());
		} 

		return apartmentList;
	}

	@Override
	public ApartmentModel getByID(int id) throws Exception {
		if(id < 1)
			throw new Exception("ID cannot be less than 1.");

		ApartmentModel model = null;

		String query = "SELECT * FROM apartment WHERE ID = " + id + "";

		try (Connection sqlConnection = DriverManager.getConnection(settings.SqlConnectionString,settings.UserName,settings.Password)){
			Statement queryStatement = sqlConnection.createStatement();
			ResultSet resultSet = queryStatement.executeQuery(query);

			if (resultSet.next()) {
				model = new ApartmentModel();

				model.ID = Integer.parseInt(resultSet.getString("ID"));
				model.ApartmentNumber = Integer.parseInt(resultSet.getString("ApartmentNumber"));
			}

			sqlConnection.close();
		} catch (SQLException ex) {
			throw new Exception(ex.getMessage());
		} 

		return model;
	}

	@Override
	public void update(ApartmentModel model) throws Exception {
		if(!IsModelValid(model))
			throw new Exception("Model is not valid.");

		try (Connection sqlConnection = DriverManager.getConnection(settings.SqlConnectionString,settings.UserName,settings.Password)){
			Statement queryStatement = sqlConnection.createStatement();

			queryStatement.executeUpdate("UPDATE apartment "
					+ "SET ApartmentNumber = " + model.ApartmentNumber + ""
					+ " WHERE ID = " + model.ID + "");

			sqlConnection.close();
		} catch (SQLException ex) {
			throw new Exception(ex.getMessage());
		} 
	}

	@Override
	public void deleteByID(int id) throws Exception {
		if(id < 1)
			throw new Exception("ID cannot be less than 1.");

		try (Connection sqlConnection = DriverManager.getConnection(settings.SqlConnectionString,settings.UserName,settings.Password)){
			Statement queryStatement = sqlConnection.createStatement();

			queryStatement.executeUpdate("DELETE FROM apartment WHERE ID = " + id + "");

			sqlConnection.close();
		} catch (SQLException ex) {
			throw new Exception(ex.getMessage());
		} 
	}
	//endregion

	//region Private Methods
	// Validates whether the received model is valid or not.
	private boolean IsModelValid(ApartmentModel model) {
		if(model == null || model.ID < 0 || model.ApartmentNumber < 1)
			return false;
		else
			return true;
	}

	// Receives driver manager connection entity.
	private Connection GetConnectionDrive() throws SQLException {
		return DriverManager.getConnection(settings.SqlConnectionString,settings.UserName,settings.Password);
	}
	//endregion
}
