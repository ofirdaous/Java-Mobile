package Entities;

import java.util.Date;

public class PaymentModel {
	public int id;
	public int apartmentNumberID; // Foreign key to table 'apartment' to column 'ID'.
	public double paymentAmount;
	public Date dateOfPayment;
}
