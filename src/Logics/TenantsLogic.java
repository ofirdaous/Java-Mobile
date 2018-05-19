package Logics;

import java.util.ArrayList;
import java.util.Date;

import Abstracts.BaseController;
import Abstracts.IRepository;
import Entities.ApartmentModel;
import Entities.PaymentModel;
import Repositories.ApartmentRepository;
import Repositories.PaymentRepository;

public class TenantsLogic extends BaseController {
	// region Constructor
	public TenantsLogic() throws Exception {
		super();
	}
	// endregion

	// region Public Methods
	// Function takes apartment ID, payment amount, and date as specific format and
	// inserts the data to SQL.
	public void insertPaymentForApartment(int apartmentID, double payment, Date date) throws Exception {
		if (apartmentID < 1 || payment < 0 || date == null)
			throw new Exception("Inputs are not valid.");

		IRepository<ApartmentModel> apartmentRepository = new ApartmentRepository(settings);
		ApartmentModel apartment = apartmentRepository.getByID(apartmentID);

		if (apartment == null)
			throw new Exception("No apartment exists with given ID.");

		PaymentModel paymentModel = new PaymentModel();

		paymentModel.dateOfPayment = date;
		paymentModel.paymentAmount = payment;
		paymentModel.apartmentNumberID = apartmentID;

		IRepository<PaymentModel> paymentRepository = new PaymentRepository(settings);
		paymentRepository.create(paymentModel);
	}

	// Receives list of payments of specific apartment by given ID and display the
	// information.
	public ArrayList<PaymentModel> getPaymentPerMonthForApartment(int id) throws Exception {
		if (id < 1)
			throw new Exception("Inputs are not valid.");

		IRepository<ApartmentModel> apartmentRepository = new ApartmentRepository(settings);
		ApartmentModel apartment = apartmentRepository.getByID(id);

		if (apartment == null)
			throw new Exception("No apartment exists with given ID.");

		PaymentRepository pRepo = new PaymentRepository(settings);
		ArrayList<PaymentModel> paymentListModel = pRepo.getAllPaymentsByApartmentID(id);

		return paymentListModel;
	}

	// Receives payment of apartment by given ID and given date, will take only the
	// month.
	public double getPaymentByIDAndMonth(int id, Date date) throws Exception {
		if (id < 1 || date == null)
			throw new Exception("Inputs are not valid.");

		double paymentAmount = 0;

		PaymentRepository paymentRepository = new PaymentRepository(settings);
		ArrayList<PaymentModel> paymentListModel = paymentRepository.getAllPaymentsByApartmentID(id);

		for (PaymentModel item : paymentListModel) {
			if (item.dateOfPayment.getMonth() == date.getMonth())
				paymentAmount += item.paymentAmount;
		}

		return paymentAmount;
	}
	// endregion
}
