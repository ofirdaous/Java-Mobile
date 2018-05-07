package Entities;

import java.util.Date;

public class PaymentModel {
	public int ID;

	public double PaymentAmount;

	public Date DateOfPayment;

	// Foreign key to table Apartment to PK column 'ID'.
	public int ApartmentNumber;
}
