package Repositories;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;

import java.util.ArrayList;

import Abstracts.IRepository;
import Abstracts.BaseRepository;
import Entities.Settings;
import Entities.ApartmentModel;
import Entities.PaymentModel;

public class PaymentRepository extends BaseRepository implements IRepository<PaymentModel> {
	// region Constructor
	public PaymentRepository(Settings settings) {
		super(settings);
	}
	// endregion

	// region Public Methods
	@Override
	public void create(PaymentModel model) throws Exception {
		if (!isModelValid(model))
			throw new Exception("Model is not valid.");

		IRepository<ApartmentModel> apartmentRepository = new ApartmentRepository(settings);
		ApartmentModel apartment = apartmentRepository.getByID(model.apartmentNumberID);

		if (apartment == null)
			throw new Exception("Attempts to create payment for apartment that doesn't exist.");

		try (Connection sqlConnection = this.getConnectionDrive()) {
			Statement queryStatement = sqlConnection.createStatement();

			queryStatement.executeUpdate("INSERT INTO payment (PaymentAmount, DateOfPayment, ApartmentNumberID) "
					+ "VALUES (" + model.paymentAmount + ", '" + dateSourceFormat.format(model.dateOfPayment) + "', "
					+ model.apartmentNumberID + ");");

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
				model.apartmentNumberID = Integer.parseInt(resultSet.getString("ApartmentNumberID"));

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
				model.apartmentNumberID = Integer.parseInt(resultSet.getString("ApartmentNumberID"));
			}

			sqlConnection.close();

		} catch (SQLException ex) {
			throw new Exception(ex.getMessage());
		}

		return model;
	}

	@Override
	public void update(PaymentModel model) throws Exception {
		if (!isModelValid(model))
			throw new Exception("Model is not valid.");

		PaymentModel modelFromDB = getByID(model.id);

		if (modelFromDB == null)
			throw new Exception("Attempt to update model that doesn't exist at database, aborting.");

		try (Connection sqlConnection = this.getConnectionDrive()) {
			Statement queryStatement = sqlConnection.createStatement();

			queryStatement.executeUpdate("UPDATE payment " + "SET ApartmentNumberID = " + model.apartmentNumberID + ", "
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

		PaymentModel modelFromDB = getByID(id);

		if (modelFromDB == null)
			throw new Exception("Attempt to delete model that doesn't exist at database, aborting.");

		try (Connection sqlConnection = this.getConnectionDrive()) {
			Statement queryStatement = sqlConnection.createStatement();

			queryStatement.executeUpdate("DELETE FROM payment WHERE ID = " + id + "");

			sqlConnection.close();

		} catch (SQLException ex) {
			throw new Exception(ex.getMessage());
		}
	}

	// Receives all payments by given apartment ID with inner join.
	public ArrayList<PaymentModel> getAllPaymentsByApartmentID(int apartmentID) throws Exception {
		if (apartmentID < 1)
			throw new Exception("ID cannot be less than 1.");

		IRepository<ApartmentModel> apartmentRepository = new ApartmentRepository(settings);
		ApartmentModel apartment = apartmentRepository.getByID(apartmentID);

		if (apartment == null)
			throw new Exception("Cannot get payments since the given ID for apartment doesn't exist.");

		ArrayList<PaymentModel> modelList = new ArrayList<PaymentModel>();

		String query = "SELECT * FROM payment AS p INNER JOIN apartment AS a ON p.ApartmentNumberID = a.ID WHERE a.ID = "
				+ apartmentID + "";

		try (Connection sqlConnection = this.getConnectionDrive()) {
			Statement queryStatement = sqlConnection.createStatement();
			ResultSet resultSet = queryStatement.executeQuery(query);

			while (resultSet.next()) {
				PaymentModel model = new PaymentModel();

				model.id = Integer.parseInt(resultSet.getString("ID"));
				model.paymentAmount = Double.parseDouble(resultSet.getString("PaymentAmount"));
				model.dateOfPayment = dateSourceFormat.parse(resultSet.getString("DateOfPayment"));
				model.apartmentNumberID = Integer.parseInt(resultSet.getString("ApartmentNumberID"));

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
	private boolean isModelValid(PaymentModel model) {
		if (model == null || model.id < 0 || model.dateOfPayment == null || model.paymentAmount < 0
				|| model.apartmentNumberID < 1)
			return false;
		else
			return true;
	}
	// endregion
}
