package Tests;

import java.util.ArrayList;
import java.util.Date;

import Abstracts.BaseController;
import Abstracts.IRepository;
import Entities.PaymentModel;
import Repositories.PaymentRepository;

public class PaymentTester extends BaseController {
	public PaymentTester() throws Exception {
		super();
	}

	public void ExecuteTests() throws Exception {
		// create();
		// getAll();
		// getByID(5);
		// update(6);
		// deleteByID(5);
	}

	private void create() throws Exception {
		IRepository<PaymentModel> pRepo = new PaymentRepository(settings);
		PaymentModel model = new PaymentModel();

		model.paymentAmount = 552.2;
		model.dateOfPayment = new Date();
		model.apartmentNumberID = 1;

		pRepo.create(model);
	}

	private void getAll() throws Exception {
		IRepository<PaymentModel> pRepo = new PaymentRepository(settings);
		ArrayList<PaymentModel> paymentList = pRepo.getAll();

		for (PaymentModel model : paymentList) {
			System.out.println(
					model.id + " ," + model.paymentAmount + " ," + model.dateOfPayment + " ," + model.apartmentNumberID);
		}
	}

	private void getByID(int id) throws Exception {
		IRepository<PaymentModel> pRepo = new PaymentRepository(settings);
		PaymentModel model = pRepo.getByID(id);

		System.out.println(
				model.id + " ," + model.paymentAmount + " ," + model.dateOfPayment + " ," + model.apartmentNumberID);
	}

	private void update(int id) throws Exception {
		IRepository<PaymentModel> pRepo = new PaymentRepository(settings);
		PaymentModel model = pRepo.getByID(id);

		model.paymentAmount = 1551.1;

		pRepo.update(model);
	}

	private void deleteByID(int id) throws Exception {
		IRepository<PaymentModel> paymentRepository = new PaymentRepository(settings);
		paymentRepository.deleteByID(id);
	}
}
