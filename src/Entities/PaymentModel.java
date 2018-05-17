package Entities;

import java.util.Date;

public class PaymentModel {
	public int id;
	public int apartmentNumber; // Foreign key to table Apartment to PK column 'ID'.
	public double paymentAmount;
	public Date dateOfPayment;
}
