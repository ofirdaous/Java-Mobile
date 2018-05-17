package Entities;

import java.util.Date;

public class PaymentModel {
	public int ID;
	public int ApartmentNumber; // Foreign key to table Apartment to PK column 'ID'.
	public double PaymentAmount;
	public Date DateOfPayment;
}
