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
	// Function takes apartment number, payment amount, and date as specific format
	// and
	// inserts the data to SQL.
	public void insertPaymentForApartment(int apartmentNumber, double payment, Date date) throws Exception {
		if (apartmentNumber < 1 || payment < 0 || date == null)
			throw new Exception("Inputs are not valid.");

		ApartmentRepository apartmentRepository = new ApartmentRepository(settings);
		ApartmentModel apartment = apartmentRepository.getByApartmentNumber(apartmentNumber);

		if (apartment == null) {
			apartment = new ApartmentModel();

			apartment.apartmentNumber = apartmentNumber;
			apartmentRepository.create(apartment);

			apartment = apartmentRepository.getByApartmentNumber(apartmentNumber);
		}

		PaymentModel paymentModel = new PaymentModel();

		paymentModel.dateOfPayment = date;
		paymentModel.paymentAmount = payment;
		paymentModel.apartmentNumberID = apartment.id;

		IRepository<PaymentModel> paymentRepository = new PaymentRepository(settings);
		paymentRepository.create(paymentModel);
	}

	// Receives list of payments of specific apartment by given apartment number and
	// display the
	// information.
	public ArrayList<PaymentModel> getPaymentPerMonthForApartment(int apartmentNumber) throws Exception {
		if (apartmentNumber < 1)
			throw new Exception("Inputs are not valid.");

		ApartmentRepository apartmentRepository = new ApartmentRepository(settings);
		ApartmentModel apartment = apartmentRepository.getByApartmentNumber(apartmentNumber);

		if (apartment == null)
			throw new Exception("No apartment exists with given apartment number.");

		PaymentRepository pRepo = new PaymentRepository(settings);
		return pRepo.getAllPaymentsByApartmentID(apartment.id);
	}

	// Receives payment of apartment by given apartment number and given date, will
	// take only the month.
	public double getPaymentByApartmentNumberAndMonth(int apartmentNumber, Date date) throws Exception {
		if (apartmentNumber < 1 || date == null)
			throw new Exception("Inputs are not valid.");

		ApartmentRepository apartmentRepository = new ApartmentRepository(settings);
		ApartmentModel apartment = apartmentRepository.getByApartmentNumber(apartmentNumber);

		if (apartment == null)
			throw new Exception("No apartment exists with given apartment number.");

		double calculatedPaymentAmount = 0;

		PaymentRepository paymentRepository = new PaymentRepository(settings);
		ArrayList<PaymentModel> payments = paymentRepository.getAllPaymentsByApartmentID(apartment.id);

		for (PaymentModel payment : payments)
			if (payment.dateOfPayment.getMonth() == date.getMonth() && payment.dateOfPayment.getYear() == date.getYear())
				calculatedPaymentAmount += payment.paymentAmount;

		return calculatedPaymentAmount;
	}
	// endregion
}
