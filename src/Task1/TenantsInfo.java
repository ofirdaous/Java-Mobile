package Task1;

import java.util.ArrayList;
import java.util.Date;

import Abstracts.BaseController;
import Abstracts.IRepository;
import Entities.ApartmentModel;
import Entities.PaymentModel;
import Repositories.ApartmentRepository;
import Repositories.PaymentRepository;

public class TenantsInfo extends BaseController {
	//region Constructor
	public TenantsInfo() throws Exception{
		super();
	}
	//endregion

	//region Public Methods
	// Function takes apartment ID, payment amount, and date as specific format and inserts the data to SQL.
	public void InsertPaymentForApartment(int apartmentID, double payment, Date date) throws Exception{
		if(apartmentID < 1 || payment < 0 || date == null)
			throw new Exception("Inputs are not valid.");
		
		IRepository<ApartmentModel> aRepo = new ApartmentRepository(settings);
		ApartmentModel apartment = aRepo.getByID(apartmentID);
		
		if(apartment == null)
			throw new Exception("No apartment exists with given ID, aborting.");
		
		IRepository<PaymentModel> pRepo = new PaymentRepository(settings);
		PaymentModel paymentModel = new PaymentModel();

		paymentModel.PaymentAmount = payment;
		paymentModel.DateOfPayment = date;
		paymentModel.ApartmentNumber = apartmentID;

		pRepo.create(paymentModel);
	}
	
	// Receives list of payments of specific apartment by given ID and display the information.
	public ArrayList<PaymentModel> GetPaymentPerMonthForApartment(int id) throws Exception{
		if(id < 1)
			throw new Exception("Inputs are not valid.");
		
		IRepository<ApartmentModel> aRepo = new ApartmentRepository(settings);
		ApartmentModel apartment = aRepo.getByID(id);
		
		if(apartment == null)
			throw new Exception("No apartment exists with given ID, aborting.");
		
		PaymentRepository pRepo = new PaymentRepository(settings);
		ArrayList<PaymentModel> paymentListModel = pRepo.GetAllByID(id);
		
		return paymentListModel;
	}
	
	// Receives payment of apartment by given ID and given date, will take only the month.
	public double GetPaymentByIDAndMonth(int id, Date date) throws Exception{
		if(id < 1 || date == null)
			throw new Exception("Inputs are not valid.");
		
		double paymentAmount = 0;
		
		PaymentRepository pRepo = new PaymentRepository(settings);
		ArrayList<PaymentModel> paymentListModel = pRepo.GetAllByID(id);
		
		for(PaymentModel item : paymentListModel){
			if(item.DateOfPayment.getMonth() == date.getMonth())
				paymentAmount += item.PaymentAmount;
		}
		
		return paymentAmount;
	}
	//endregion
}
