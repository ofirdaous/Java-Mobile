package Tests;

import java.util.ArrayList;
import java.util.Date;

import Entities.PaymentModel;
import Task1.TenantsInfo;

public class TestExecute {
	public void executeTests() throws Exception {
		// insertPaymentForApartment();
		// getPaymentPerMonthForApartment(1);
		// getPaymentByIDAndMonth(1, new Date());
	}

	private void insertPaymentForApartment() throws Exception {
		TenantsInfo tenantsInfo = new TenantsInfo();
		tenantsInfo.insertPaymentForApartment(1, 420, new Date());
	}

	private void getPaymentPerMonthForApartment(int id) throws Exception {
		TenantsInfo tenantsInfo = new TenantsInfo();

		ArrayList<PaymentModel> paymentList = tenantsInfo.getPaymentPerMonthForApartment(id);

		for (PaymentModel model : paymentList) {
			System.out.println(
					model.id + " ," + model.paymentAmount + " ," + model.dateOfPayment + " ," + model.apartmentNumber);
		}
	}

	private void getPaymentByIDAndMonth(int id, Date date) throws Exception {
		TenantsInfo tenantsInfo = new TenantsInfo();
		double paymentAmouont = tenantsInfo.getPaymentByIDAndMonth(id, date);
		System.out.println("Payment amount: " + paymentAmouont);
	}
}
