import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import Entities.PaymentModel;
import Task1.TenantsInfo;

public class Menu {
	// region Members
	public Scanner scanner;
	public TenantsInfo tenant;
	public SimpleDateFormat dateSourceFormat;
	// endregion

	// region Constructor
	public Menu() throws Exception {
		this.tenant = new TenantsInfo();
		this.scanner = new Scanner(System.in);
		this.dateSourceFormat = new SimpleDateFormat("dd-MM-yyyy");
	}
	// endregion

	// region Public Methods
	// Runs the main function that executes the whole system.
	public void executeMenu() throws Exception {
		displayMenu();

		int choice = scanner.nextInt();

		while (choice != -1) {
			try {
				switch (choice) {
				case 1:
					insertPaymentForApartment();
					break;
				case 2:
					getPaymentPerMonthForApartment();
					break;
				case 3:
					getPaymentByIDAndMonth();
					break;
				default:
					System.out.println("The choice was not correct, please try again.");
					break;
				}
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
			}

			System.out.println("Please make new choice: ( Check menu options )");
			choice = scanner.nextInt();
		}

		System.out.println("Thank you, have a wonderful day.");
	}

	// Display the menu at the console.
	public void displayMenu() {
		System.out.println("Welcome to the first program, here are the options for running the program.");
		System.out.println("1. Ability to pay for a certain tenant. (apartment number)");
		System.out.println("2. Ability to get months paid by a certain tenant.");
		System.out.println("3. Ability to issue a total payment for a certain month.");
		System.out.println("Type -1 to exit the program.");
	}

	// Function that inserts payment for apartment.
	// Function takes apartment ID, payment amount, and date as specific format and inserts the data to SQL.
	public void insertPaymentForApartment() throws Exception {
		System.out.print("Specify the ID of the apartment: ");
		int apartmentID = scanner.nextInt();

		System.out.print("Specify the amount for the payment for the apartment: ");
		double payment = scanner.nextDouble();

		System.out.print("Specify the date of the payment: ( Format: dd-MM-yyyy )");
		String date = scanner.next();

		Date convertedDate = null;

		try {
			convertedDate = dateSourceFormat.parse(date);
		} catch (ParseException e) {
			throw e;
		}

		// Todo: Make validation whether the apartment ID is exists.

		tenant.InsertPaymentForApartment(apartmentID, payment, convertedDate);
	}

	// Receives list of payments of specific apartment by given ID and display the
	// information.
	public void getPaymentPerMonthForApartment() throws Exception {
		System.out.print("Choose the ID of the apartment: ");
		int id = scanner.nextInt();

		ArrayList<PaymentModel> paymentList = tenant.GetPaymentPerMonthForApartment(id);

		for (PaymentModel payment : paymentList) {
			System.out.println("Month: " + payment.DateOfPayment + ", Amount: " + payment.PaymentAmount);
		}
	}

	// Receives payment of apartment by given ID and given date, will take only the
	// month.
	public void getPaymentByIDAndMonth() throws Exception {
		System.out.print("Specify the ID of the apartment: ");
		int apartmentID = scanner.nextInt();

		System.out.print("Specify the date of the payment: ( Format: dd-MM-yyyy )");
		String date = scanner.next();

		Date convertedDate = null;

		try {
			convertedDate = dateSourceFormat.parse(date);
		} catch (ParseException e) {
			throw e;
		}

		double payment = tenant.GetPaymentByIDAndMonth(apartmentID, convertedDate);
		System.out.println("The payment amount is " + payment);
	}
	// endregion
}
