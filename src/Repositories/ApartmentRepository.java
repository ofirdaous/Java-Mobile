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
	// region Constructor
	public ApartmentRepository(Settings settings) {
		super(settings);
	}
	// endregion
	
	// region Public Methods
	@Override
	public void create(ApartmentModel model) throws Exception {
		if (!IsModelValid(model))
			throw new Exception("Model is not valid.");

		try (Connection sqlConnection = this.getConnectionDrive()) {
			Statement queryStatement = sqlConnection.createStatement();

			queryStatement.executeUpdate(
					"INSERT INTO apartment (ApartmentNumber) " + "VALUES (" + model.apartmentNumber + ")");

			sqlConnection.close();
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public ArrayList<ApartmentModel> getAll() throws Exception {
		ArrayList<ApartmentModel> apartmentList = new ArrayList<ApartmentModel>();

		try (Connection sqlConnection = this.getConnectionDrive()) {
			Statement queryStatement = sqlConnection.createStatement();
			ResultSet resultSet = queryStatement.executeQuery("SELECT * FROM apartment");

			while (resultSet.next()) {
				ApartmentModel model = new ApartmentModel();

				model.id = Integer.parseInt(resultSet.getString("ID"));
				model.apartmentNumber = Integer.parseInt(resultSet.getString("ApartmentNumber"));

				if (model != null)
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
		if (id < 1)
			throw new Exception("ID cannot be less than 1.");

		ApartmentModel model = null;

		String query = "SELECT * FROM apartment WHERE ID = " + id + "";

		try (Connection sqlConnection = this.getConnectionDrive()) {
			Statement queryStatement = sqlConnection.createStatement();
			ResultSet resultSet = queryStatement.executeQuery(query);

			if (resultSet.next()) {
				model = new ApartmentModel();

				model.id = Integer.parseInt(resultSet.getString("ID"));
				model.apartmentNumber = Integer.parseInt(resultSet.getString("ApartmentNumber"));
			}

			sqlConnection.close();
			
		} catch (SQLException ex) {
			throw new Exception(ex.getMessage());
		}

		return model;
	}

	@Override
	public void update(ApartmentModel model) throws Exception {
		if (!IsModelValid(model))
			throw new Exception("Model is not valid.");

		try (Connection sqlConnection = this.getConnectionDrive()) {
			Statement queryStatement = sqlConnection.createStatement();

			queryStatement.executeUpdate("UPDATE apartment " + "SET ApartmentNumber = " + model.apartmentNumber + ""
					+ " WHERE ID = " + model.id + "");

			sqlConnection.close();
			
		} catch (SQLException ex) {
			throw new Exception(ex.getMessage());
		}
	}

	@Override
	public void deleteByID(int id) throws Exception {
		if (id < 1)
			throw new Exception("ID cannot be less than 1.");

		try (Connection sqlConnection = this.getConnectionDrive()) {
			Statement queryStatement = sqlConnection.createStatement();

			queryStatement.executeUpdate("DELETE FROM apartment WHERE ID = " + id + "");

			sqlConnection.close();
			
		} catch (SQLException ex) {
			throw new Exception(ex.getMessage());
		}
	}
	// endregion

	// region Private Methods
	// Validates whether the received model is valid or not.
	private boolean IsModelValid(ApartmentModel model) {
		if (model == null || model.id < 0 || model.apartmentNumber < 1)
			return false;
		else
			return true;
	}
	// endregion
}
