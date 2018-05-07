package Tests;

import java.util.ArrayList;
import java.util.Date;

import Entities.PaymentModel;
import Task1.TenantsInfo;

public class Task1Tester {

	public void ExecuteTests() throws Exception{
		//InsertPaymentForApartment();
		//GetPaymentPerMonthForApartment(1);
		//GetPaymentByIDAndMonth(1, new Date());
	}

	private void InsertPaymentForApartment() throws Exception{
		TenantsInfo repo = new TenantsInfo();
		repo.InsertPaymentForApartment(1, 420, new Date());
	}

	private void GetPaymentPerMonthForApartment(int id) throws Exception{
		TenantsInfo repo = new TenantsInfo();

		ArrayList<PaymentModel> paymentList = repo.GetPaymentPerMonthForApartment(id);

		for(PaymentModel model : paymentList){
			System.out.println(model.ID + " ," + model.PaymentAmount + " ," + model.DateOfPayment + " ," + model.ApartmentNumber);
		}
	}

	private void GetPaymentByIDAndMonth(int id, Date date) throws Exception{
		TenantsInfo repo = new TenantsInfo();
		double paymentAmouont = repo.GetPaymentByIDAndMonth(id, date);
		System.out.println("Payment amount: " + paymentAmouont);
	}
}
