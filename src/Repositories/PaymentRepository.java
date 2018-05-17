package Repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Abstracts.BaseRepository;
import Abstracts.IRepository;
import Entities.PaymentModel;
import Entities.Settings;

public class PaymentRepository extends BaseRepository implements IRepository<PaymentModel> {
	// region Constructor
	public PaymentRepository(Settings settings) {
		super(settings);
	}
	// endregion

	// region Public Methods
	@Override
	public void create(PaymentModel model) throws Exception {
		if (!IsModelValid(model))
			throw new Exception("Model is not valid.");

		try (Connection sqlConnection = this.getConnectionDrive()) {
			Statement queryStatement = sqlConnection.createStatement();

			queryStatement.executeUpdate("INSERT INTO payment (PaymentAmount, DateOfPayment, ApartmentNumber) "
					+ "VALUES (" + model.paymentAmount + ", '" + dateSourceFormat.format(model.dateOfPayment) + "', "
					+ model.apartmentNumber + ");");

			sqlConnection.close();

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public ArrayList<PaymentModel> getAll() throws Exception {
		ArrayList<PaymentModel> paymentList = new ArrayList<PaymentModel>();

		try (Connection sqlConnection = this.getConnectionDrive()) {
			Statement queryStatement = sqlConnection.createStatement();
			ResultSet resultSet = queryStatement.executeQuery("SELECT * FROM payment");

			while (resultSet.next()) {
				PaymentModel model = new PaymentModel();

				model.id = Integer.parseInt(resultSet.getString("ID"));
				model.paymentAmount = Double.parseDouble(resultSet.getString("PaymentAmount"));
				model.dateOfPayment = dateSourceFormat.parse(resultSet.getString("DateOfPayment"));
				model.apartmentNumber = Integer.parseInt(resultSet.getString("ApartmentNumber"));

				if (model != null)
					paymentList.add(model);
			}

			sqlConnection.close();

		} catch (SQLException ex) {
			throw new Exception(ex.getMessage());
		}

		return paymentList;
	}

	@Override
	public PaymentModel getByID(int id) throws Exception {
		if (id < 1)
			throw new Exception("ID cannot be less than 1.");

		PaymentModel model = null;

		String query = "SELECT * FROM payment WHERE ID = " + id + "";

		try (Connection sqlConnection = this.getConnectionDrive()) {
			Statement queryStatement = sqlConnection.createStatement();
			ResultSet resultSet = queryStatement.executeQuery(query);

			if (resultSet.next()) {
				model = new PaymentModel();

				model.id = Integer.parseInt(resultSet.getString("ID"));
				model.paymentAmount = Double.parseDouble(resultSet.getString("PaymentAmount"));
				model.dateOfPayment = dateSourceFormat.parse(resultSet.getString("DateOfPayment"));
				model.apartmentNumber = Integer.parseInt(resultSet.getString("ApartmentNumber"));

			}

			sqlConnection.close();

		} catch (SQLException ex) {
			throw new Exception(ex.getMessage());
		}

		return model;
	}

	@Override
	public void update(PaymentModel model) throws Exception {
		if (!IsModelValid(model))
			throw new Exception("Model is not valid.");

		try (Connection sqlConnection = this.getConnectionDrive()) {
			Statement queryStatement = sqlConnection.createStatement();

			queryStatement.executeUpdate("UPDATE payment " + "SET ApartmentNumber = " + model.apartmentNumber + ", "
					+ "PaymentAmount = " + model.paymentAmount + " " + "WHERE ID = " + model.id + "");

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

			queryStatement.executeUpdate("DELETE FROM payment WHERE ID = " + id + "");

			sqlConnection.close();

		} catch (SQLException ex) {
			throw new Exception(ex.getMessage());
		}
	}

	public ArrayList<PaymentModel> GetAllByID(int id) throws Exception {
		if (id < 1)
			throw new Exception("ID cannot be less than 1.");

		ArrayList<PaymentModel> modelList = new ArrayList<PaymentModel>();

		String query = "SELECT * FROM payment AS p INNER JOIN apartment AS a ON p.ApartmentNumber = a.ID WHERE a.ID = "
				+ id + "";

		try (Connection sqlConnection = this.getConnectionDrive()) {
			Statement queryStatement = sqlConnection.createStatement();
			ResultSet resultSet = queryStatement.executeQuery(query);

			while (resultSet.next()) {
				PaymentModel model = new PaymentModel();

				model.id = Integer.parseInt(resultSet.getString("ID"));
				model.paymentAmount = Double.parseDouble(resultSet.getString("PaymentAmount"));
				model.dateOfPayment = dateSourceFormat.parse(resultSet.getString("DateOfPayment"));
				model.apartmentNumber = Integer.parseInt(resultSet.getString("ApartmentNumber"));

				modelList.add(model);
			}

			sqlConnection.close();

		} catch (SQLException ex) {
			throw new Exception(ex.getMessage());
		}

		return modelList;
	}
	// endregion

	// region Private Methods
	// Validates whether the received model is valid or not.
	private boolean IsModelValid(PaymentModel model) {
		if (model == null || model.id < 0 || model.dateOfPayment == null || model.paymentAmount < 0
				|| model.apartmentNumber < 1)
			return false;
		else
			return true;
	}
	// endregion
}
