package Tests;

import java.util.ArrayList;
import java.util.Date;

import Entities.PaymentModel;
import Logics.TenantsLogic;

public class TenantTester {
	public void executeTests() throws Exception {
		// insertPaymentForApartment();
		// getPaymentPerMonthForApartment(1);
		// getPaymentByIDAndMonth(1, new Date());
	}

	private void insertPaymentForApartment() throws Exception {
		TenantsLogic tenantsInfo = new TenantsLogic();
		tenantsInfo.insertPaymentForApartment(1, 420, new Date());
	}

	private void getPaymentPerMonthForApartment(int id) throws Exception {
		TenantsLogic tenantsInfo = new TenantsLogic();

		ArrayList<PaymentModel> paymentList = tenantsInfo.getPaymentPerMonthForApartment(id);

		for (PaymentModel model : paymentList) {
			System.out.println(
					model.id + " ," + model.paymentAmount + " ," + model.dateOfPayment + " ," + model.apartmentNumberID);
		}
	}

	private void getPaymentByIDAndMonth(int id, Date date) throws Exception {
		TenantsLogic tenantsInfo = new TenantsLogic();
		double paymentAmouont = tenantsInfo.getPaymentByIDAndMonth(id, date);
		System.out.println("Payment amount: " + paymentAmouont);
	}
}
