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

import Utils.Utils;

public class ApartmentRepository extends BaseRepository implements IRepository<ApartmentModel> {
	// region Members
	private final String CONST_ID = "ID";
	private final String CONST_APARTMENT_NUMBER = "ApartmentNumber";
	private final String CONST_YEARS_SENIORITY = "YearsSeniority";
	private final String CONST_FIRST_NAME = "FirstName";
	private final String CONST_LAST_NAME = "LastName";
	private final String CONST_IDENTITY_NUMBER = "IdentityNumber";
	private final String CONST_TENANT_TYPE = "TenantType";
	// endregion

	// region Constructor
	public ApartmentRepository(Settings settings) {
		super(settings);
	}
	// endregion

	// region Public Methods
	@Override
	public void create(ApartmentModel model) throws Exception {
		if (!isModelValid(model))
			throw new Exception("Model is not valid.");

		ApartmentModel modelFromDB = getByApartmentNumber(model.apartmentNumber);

		if (modelFromDB != null)
			throw new Exception("Attempt to create apartment that already exist, aborting.");

		try (Connection sqlConnection = this.getConnectionDrive()) {
			Statement queryStatement = sqlConnection.createStatement();

			queryStatement.executeUpdate("INSERT INTO apartment (" + CONST_APARTMENT_NUMBER + ", "
					+ CONST_YEARS_SENIORITY + ", " + CONST_FIRST_NAME + ", " + CONST_LAST_NAME + ", "
					+ CONST_IDENTITY_NUMBER + ", " + CONST_TENANT_TYPE + ") " + "VALUES (" + model.apartmentNumber
					+ ", " + model.yearsSeniority + ", '" + model.firstName + "', '" + model.lastName + "', '"
					+ model.identityNumber + "', " + Utils.parseTenanTypetInteger(model.tenantType) + ")");

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
				ApartmentModel model = castDatabaseDetailsToModel(resultSet);

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

		String query = "SELECT * FROM apartment WHERE " + CONST_ID + " = " + id + "";

		try (Connection sqlConnection = this.getConnectionDrive()) {
			Statement queryStatement = sqlConnection.createStatement();
			ResultSet resultSet = queryStatement.executeQuery(query);

			if (resultSet.next()) {
				model = castDatabaseDetailsToModel(resultSet);
			}

			sqlConnection.close();

		} catch (SQLException ex) {
			throw new Exception(ex.getMessage());
		}

		return model;
	}

	public ApartmentModel getByApartmentNumber(int apartmentNumber) throws Exception {
		if (apartmentNumber < 1)
			throw new Exception("Apartment number cannot be less than 1.");

		ApartmentModel model = null;

		String query = "SELECT * FROM apartment WHERE " + CONST_APARTMENT_NUMBER + " = " + apartmentNumber + "";

		try (Connection sqlConnection = this.getConnectionDrive()) {
			Statement queryStatement = sqlConnection.createStatement();
			ResultSet resultSet = queryStatement.executeQuery(query);

			if (resultSet.next()) {
				model = castDatabaseDetailsToModel(resultSet);
			}

			sqlConnection.close();

		} catch (SQLException ex) {
			throw new Exception(ex.getMessage());
		}

		return model;
	}

	@Override
	public void update(ApartmentModel model) throws Exception {
		if (!isModelValid(model))
			throw new Exception("Model is not valid.");

		ApartmentModel modelFromDB = getByID(model.id);

		if (modelFromDB == null)
			throw new Exception("Attempt to update model that doesn't exist at database, aborting.");

		try (Connection sqlConnection = this.getConnectionDrive()) {
			Statement queryStatement = sqlConnection.createStatement();

			queryStatement.executeUpdate("UPDATE apartment " + "SET " + CONST_APARTMENT_NUMBER + " = "
					+ model.apartmentNumber + ", " + CONST_YEARS_SENIORITY + " = " + model.yearsSeniority + ", "
					+ CONST_FIRST_NAME + " = '" + model.firstName + "', " + CONST_LAST_NAME + " = '" + model.lastName
					+ "', " + CONST_IDENTITY_NUMBER + " = '" + model.identityNumber + "', " + CONST_TENANT_TYPE + " = "
					+ Utils.parseTenanTypetInteger(model.tenantType) + " WHERE " + CONST_ID + " = " + model.id + "");

			sqlConnection.close();

		} catch (SQLException ex) {
			throw new Exception(ex.getMessage());
		}
	}

	@Override
	public void deleteByID(int id) throws Exception {
		if (id < 1)
			throw new Exception("ID cannot be less than 1.");

		ApartmentModel modelFromDB = getByID(id);

		// Validates whether the given model exist in database.
		if (modelFromDB == null)
			throw new Exception("Attempt to delete model that doesn't exist at database, aborting.");

		// Get all payments for given apartment.
		PaymentRepository paymentRepository = new PaymentRepository(settings);
		ArrayList<PaymentModel> payments = paymentRepository.getAllPaymentsByApartmentID(id);

		// Remove all payments for apartment.
		for (PaymentModel payment : payments)
			paymentRepository.deleteByID(payment.id);

		try (Connection sqlConnection = this.getConnectionDrive()) {
			Statement queryStatement = sqlConnection.createStatement();

			queryStatement.executeUpdate("DELETE FROM apartment WHERE " + CONST_ID + " = " + id + "");

			sqlConnection.close();

		} catch (SQLException ex) {
			throw new Exception(ex.getMessage());
		}
	}
	// endregion

	// region Private Methods
	// Validates whether the received model is valid or not.
	private boolean isModelValid(ApartmentModel model) {
		if (model == null || model.id < 0 || model.apartmentNumber < 1)
			return false;
		else
			return true;
	}

	// Casts details from result set from database to model of the repository.
	private ApartmentModel castDatabaseDetailsToModel(ResultSet resultSet) throws NumberFormatException, SQLException {
		if (resultSet == null)
			return null;

		ApartmentModel model = new ApartmentModel();

		model.id = Integer.parseInt(resultSet.getString(CONST_ID));
		model.apartmentNumber = Integer.parseInt(resultSet.getString(CONST_APARTMENT_NUMBER));
		model.yearsSeniority = Integer.parseInt(resultSet.getString(CONST_YEARS_SENIORITY));
		model.firstName = resultSet.getString(CONST_FIRST_NAME);
		model.lastName = resultSet.getString(CONST_LAST_NAME);
		model.identityNumber = resultSet.getString(CONST_IDENTITY_NUMBER);
		model.tenantType = Utils.parseTenanTypetEnum(Integer.parseInt(resultSet.getString(CONST_TENANT_TYPE)));

		return model;
	}
	// endregion
}
