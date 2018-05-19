import Tests.PaymentTester;
import Tests.ApartmentTester;

public class Main {
	/*
	 * Moshe Binieli - 311800668, Ofir Daous - 203796156
	 */
	public static void main(String[] args) {
		try {
			executeProgram();
			// executeApartmentTests();
			// executePaymentTests();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void executeProgram() throws Exception {
		Menu menu = new Menu();
		menu.executeMenu();
	}

	private static void executeApartmentTests() throws Exception {
		ApartmentTester apartmentTester = new ApartmentTester();
		apartmentTester.ExecuteTests();
	}

	private static void executePaymentTests() throws Exception {
		PaymentTester paymentTester = new PaymentTester();
		paymentTester.ExecuteTests();
	}
}

/*
 * Create apartment table *
 * 
 * CREATE TABLE `apartment` ( `ID` INT(11) NOT NULL AUTO_INCREMENT,
 * `ApartmentNumber` INT(11) NOT NULL, `FirstName` varchar(255) DEFAULT NULL,
 * `LastName` varchar(255) DEFAULT NULL, `IdentityNumber` varchar(11) DEFAULT
 * NULL, `YearsSeniority` int(3) NOT NULL, `TenantType` int(1) NOT NULL, PRIMARY
 * KEY (`ID`) )
 * 
 * 
 * Create payment table *
 * 
 * CREATE TABLE `payment` ( `ID` INT(11) NOT NULL AUTO_INCREMENT,
 * `PaymentAmount` DOUBLE NOT NULL, `DateOfPayment` VARCHAR(255) NOT NULL,
 * `ApartmentNumberID` INT(11) NOT NULL, PRIMARY KEY (`ID`), FOREIGN KEY
 * (ApartmentNumberID) REFERENCES apartment (ID) )
 * 
 */