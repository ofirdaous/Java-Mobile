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

	public TenantsInfo(){
		super();
	}

	public void InsertPaymentForApartment(int apartmentID, double payment, Date date) throws Exception{
		if(apartmentID < 1 || payment < 0 || date == null)
			throw new Exception("Inputs are not valid.");
		
		IRepository<ApartmentModel> aRepo = new ApartmentRepository(settings);
		ApartmentModel apartment = aRepo.GetByID(apartmentID);
		
		if(apartment == null)
			throw new Exception("No apartment exists with given ID, aborting.");
		
		IRepository<PaymentModel> pRepo = new PaymentRepository(settings);
		PaymentModel paymentModel = new PaymentModel();

		paymentModel.PaymentAmount = payment;
		paymentModel.DateOfPayment = date;
		paymentModel.ApartmentNumber = apartmentID;

		pRepo.Create(paymentModel);
	}
	
	public ArrayList<PaymentModel> GetPaymentPerMonthForApartment(int id) throws Exception{
		if(id < 1)
			throw new Exception("Inputs are not valid.");
		
		IRepository<ApartmentModel> aRepo = new ApartmentRepository(settings);
		ApartmentModel apartment = aRepo.GetByID(id);
		
		if(apartment == null)
			throw new Exception("No apartment exists with given ID, aborting.");
		
		PaymentRepository pRepo = new PaymentRepository(settings);
		ArrayList<PaymentModel> paymentListModel = pRepo.GetAllByID(id);
		
		return paymentListModel;
	}
	
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
}
