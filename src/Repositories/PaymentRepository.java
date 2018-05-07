package Repositories;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import javax.swing.text.html.parser.ParserDelegator;

import Abstracts.BaseRepository;
import Abstracts.IRepository;
import Entities.ApartmentModel;
import Entities.PaymentModel;
import Entities.Settings;

public class PaymentRepository extends BaseRepository implements IRepository<PaymentModel> {

	SimpleDateFormat sourceFormat = new SimpleDateFormat("dd-MM-yyyy");

	public PaymentRepository(Settings settings) {
		super(settings);
	}

	@Override
	public void Create(PaymentModel model) throws Exception {
		if(!IsModelValid(model))
			throw new Exception("Model is not valid.");

		try (Connection sqlConnection = DriverManager.getConnection(settings.SqlConnectionString,settings.UserName,settings.Password)){
			Statement queryStatement = sqlConnection.createStatement();

			queryStatement.executeUpdate("INSERT INTO payment (PaymentAmount, DateOfPayment, ApartmentNumber) "
					+ "VALUES (" + model.PaymentAmount + ", '" + sourceFormat.format(model.DateOfPayment) + "', " + model.ApartmentNumber + ");");

			sqlConnection.close();

		} catch (SQLException ex) {
			ex.printStackTrace();
		} 
	}

	@Override
	public ArrayList<PaymentModel> GetAll() throws Exception {
		ArrayList<PaymentModel> paymentList = new ArrayList<PaymentModel>();

		try (Connection sqlConnection = DriverManager.getConnection(settings.SqlConnectionString,settings.UserName,settings.Password)){
			Statement queryStatement = sqlConnection.createStatement();
			ResultSet resultSet = queryStatement.executeQuery("SELECT * FROM payment");

			while (resultSet.next()) {
				PaymentModel model = new PaymentModel();

				model.ID = Integer.parseInt(resultSet.getString("ID"));
				model.PaymentAmount = Double.parseDouble(resultSet.getString("PaymentAmount"));
				model.DateOfPayment = sourceFormat.parse(resultSet.getString("DateOfPayment"));
				model.ApartmentNumber = Integer.parseInt(resultSet.getString("ApartmentNumber"));

				if(model != null)
					paymentList.add(model);
			}

			sqlConnection.close();

		} catch (SQLException ex) {
			throw new Exception(ex.getMessage());
		} 

		return paymentList;
	}

	@Override
	public PaymentModel GetByID(int id) throws Exception {
		if(id < 1)
			throw new Exception("ID cannot be less than 1.");

		PaymentModel model = null;

		String query = "SELECT * FROM payment WHERE ID = " + id + "";

		try (Connection sqlConnection = DriverManager.getConnection(settings.SqlConnectionString,settings.UserName,settings.Password)){
			Statement queryStatement = sqlConnection.createStatement();
			ResultSet resultSet = queryStatement.executeQuery(query);

			if (resultSet.next()) {
				model = new PaymentModel();

				model.ID = Integer.parseInt(resultSet.getString("ID"));
				model.PaymentAmount = Double.parseDouble(resultSet.getString("PaymentAmount"));
				model.DateOfPayment = sourceFormat.parse(resultSet.getString("DateOfPayment"));
				model.ApartmentNumber = Integer.parseInt(resultSet.getString("ApartmentNumber"));

			}

			sqlConnection.close();

		} catch (SQLException ex) {
			throw new Exception(ex.getMessage());
		} 

		return model;
	}

	@Override
	public void Update(PaymentModel model) throws Exception {
		if(!IsModelValid(model))
			throw new Exception("Model is not valid.");

		try (Connection sqlConnection = DriverManager.getConnection(settings.SqlConnectionString,settings.UserName,settings.Password)){
			Statement queryStatement = sqlConnection.createStatement();

			queryStatement.executeUpdate("UPDATE payment "
					+ "SET ApartmentNumber = " + model.ApartmentNumber + ", "
					+ "PaymentAmount = " + model.PaymentAmount + " "
					+ "WHERE ID = " + model.ID + "");

			sqlConnection.close();

		} catch (SQLException ex) {
			throw new Exception(ex.getMessage());
		} 
	}

	@Override
	public void DeleteByID(int id) throws Exception {
		if(id < 1)
			throw new Exception("ID cannot be less than 1.");

		try (Connection sqlConnection = DriverManager.getConnection(settings.SqlConnectionString,settings.UserName,settings.Password)){
			Statement queryStatement = sqlConnection.createStatement();

			queryStatement.executeUpdate("DELETE FROM payment WHERE ID = " + id + "");

			sqlConnection.close();

		} catch (SQLException ex) {
			throw new Exception(ex.getMessage());
		} 
	}

	public ArrayList<PaymentModel> GetAllByID(int id) throws Exception{
		if(id < 1)
			throw new Exception("ID cannot be less than 1.");

		ArrayList<PaymentModel> modelList = new ArrayList<PaymentModel>();

		String query = "SELECT * FROM payment AS p INNER JOIN apartment AS a ON p.ApartmentNumber = a.ID WHERE a.ID = "+id+"";

		try (Connection sqlConnection = DriverManager.getConnection(settings.SqlConnectionString,settings.UserName,settings.Password)){
			Statement queryStatement = sqlConnection.createStatement();
			ResultSet resultSet = queryStatement.executeQuery(query);

			while (resultSet.next()) {
				PaymentModel model = new PaymentModel();

				model.ID = Integer.parseInt(resultSet.getString("ID"));
				model.PaymentAmount = Double.parseDouble(resultSet.getString("PaymentAmount"));
				model.DateOfPayment = sourceFormat.parse(resultSet.getString("DateOfPayment"));
				model.ApartmentNumber = Integer.parseInt(resultSet.getString("ApartmentNumber"));

				modelList.add(model);
			}

			sqlConnection.close();

		} catch (SQLException ex) {
			throw new Exception(ex.getMessage());
		} 

		return modelList;
	}
	
	private boolean IsModelValid(PaymentModel model){
		if(model == null
				|| model.ID < 0
				|| model.DateOfPayment == null
				|| model.PaymentAmount < 0
				|| model.ApartmentNumber < 1)
			return false;
		else
			return true;
	}
}
