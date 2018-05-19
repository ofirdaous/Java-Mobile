import java.util.Date;
import java.util.Scanner;
import java.util.ArrayList;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import Logics.TenantsLogic;
import Entities.PaymentModel;

public class Menu {
	// region Members
	public Scanner scanner;
	public TenantsLogic tenant;
	public SimpleDateFormat dateSourceFormat;
	// endregion

	// region Constructor
	public Menu() throws Exception {
		this.tenant = new TenantsLogic();
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
					getPaymentByApartmentNumberAndMonth();
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
		System.out.println("1. Insert payment for apartment.");
		System.out.println("2. Get payments for apartment.");
		System.out.println("3. Get total payment for apartment by given month.");
		System.out.println("Type -1 to exit the program.");
	}

	// Function that inserts payment for apartment.
	// Function takes apartment number, payment amount, and date as specific format
	// and inserts the data to SQL.
	public void insertPaymentForApartment() throws Exception {
		System.out.print("What is the apartment number you want to insert payment for ( Must be above than 0 ): ");
		int apartmentNumber = scanner.nextInt();

		if (apartmentNumber < 1)
			throw new Exception("Inserted apartment number cannot be less than 1.");

		System.out.print("What is the payment amount you want to insert: ");
		double payment = scanner.nextDouble();

		System.out.print("What is the date that the payment has been paid ( Format: dd-MM-yyyy ): ");
		String date = scanner.next();

		Date convertedDate = null;

		try {
			convertedDate = dateSourceFormat.parse(date);
		} catch (ParseException e) {
			throw e;
		}

		tenant.insertPaymentForApartment(apartmentNumber, payment, convertedDate);
		System.out.println("New payment has been created for apartment number " + apartmentNumber + ".");
	}

	// Receives list of payments of specific apartment by given number and display
	// the
	// information.
	public void getPaymentPerMonthForApartment() throws Exception {
		System.out.print("What is the apartment number: ");
		int apartmentNumber = scanner.nextInt();

		if (apartmentNumber < 1)
			throw new Exception("Inserted apartment number cannot be less than 1.");

		ArrayList<PaymentModel> paymentList = tenant.getPaymentPerMonthForApartment(apartmentNumber);

		if (paymentList == null || paymentList.size() == 0)
			System.out.println("No payments has been found for this apartment " + apartmentNumber + ".");

		if (paymentList != null && paymentList.size() > 0)
			for (PaymentModel payment : paymentList)
				System.out.println("Month: " + payment.dateOfPayment + ", Amount: " + payment.paymentAmount);
	}

	// Receives payment of apartment by given ID and given date, will take only the
	// month.
	public void getPaymentByApartmentNumberAndMonth() throws Exception {
		System.out.print("What is the apartment number: ");
		int apartmentNumber = scanner.nextInt();

		System.out.print("Specify the month and year of the payment ( Format: MM-yyyy ): ");
		String date = scanner.next();

		Date convertedDate = null;

		try {
			convertedDate = dateSourceFormat.parse("01-" + date);
		} catch (ParseException e) {
			throw e;
		}

		double payment = tenant.getPaymentByApartmentNumberAndMonth(apartmentNumber, convertedDate);
		System.out.println("The payment amount for month " + date + " is " + payment + ".");
	}
	// endregion
}
