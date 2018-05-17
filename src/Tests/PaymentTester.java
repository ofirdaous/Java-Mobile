package Tests;

import java.util.ArrayList;
import java.util.Date;

import Abstracts.BaseController;
import Abstracts.IRepository;
import Entities.PaymentModel;
import Repositories.PaymentRepository;

public class PaymentTester extends BaseController {
	public PaymentTester() throws Exception{
		super();
	}

	public void ExecuteTests() throws Exception{
		//Create();
		//GetAll();
		//GetByID(5);
		//Update(6);
		//DeleteByID(5);
	}

	private void Create() throws Exception{
		IRepository<PaymentModel> pRepo = new PaymentRepository(settings);
		PaymentModel model = new PaymentModel();

		model.PaymentAmount = 552.2;
		model.DateOfPayment = new Date();
		model.ApartmentNumber = 1;

		pRepo.create(model);
	}

	private void GetAll() throws Exception{
		IRepository<PaymentModel> pRepo = new PaymentRepository(settings);
		ArrayList<PaymentModel> paymentList = pRepo.getAll();

		for(PaymentModel model : paymentList){
			System.out.println(model.ID + " ," + model.PaymentAmount + " ," + model.DateOfPayment + " ," + model.ApartmentNumber);
		}
	}

	private void GetByID(int id) throws Exception{
		IRepository<PaymentModel> pRepo = new PaymentRepository(settings);
		PaymentModel model = pRepo.getByID(id);

		System.out.println(model.ID + " ," + model.PaymentAmount + " ," + model.DateOfPayment + " ," + model.ApartmentNumber);
	}

	private void Update(int id) throws Exception{
		IRepository<PaymentModel> pRepo = new PaymentRepository(settings);
		PaymentModel model = pRepo.getByID(id);
		
		model.PaymentAmount = 1551.1;
		
		pRepo.update(model);
	}

	private void DeleteByID(int id) throws Exception{
		IRepository<PaymentModel> pRepo = new PaymentRepository(settings);
		pRepo.deleteByID(id);
	}
}
