import Tests.ApartmentTester;

public class Main {
	/*
	 * Moshe Binieli - 311800668, Ofir Daous - 203796156
	 */
	public static void main(String[] args) {
		try {
			
			// Menu menu = new Menu();
			// menu.executeMenu();
			executeTests();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void executeTests() throws Exception {
		ApartmentTester apartmentTester = new ApartmentTester();
		apartmentTester.ExecuteTests();
	}
}

/*
 * Create apartment table *
 * 
 * CREATE TABLE `apartment`
 * (
 * `ID` INT(11) NOT NULL AUTO_INCREMENT,
 * `ApartmentNumber` INT(11) NOT NULL,
 * `FirstName` varchar(255) DEFAULT NULL,
 * `LastName` varchar(255) DEFAULT NULL,
 * `IdentityNumber` varchar(11) DEFAULT NULL,
 * `YearsSeniority` int(4) DEFAULT NULL,
 * `TenantType` int(1) NOT NULL,
 * PRIMARY KEY (`ID`)
 * )
 * 
 * 
 * Create payment table *
 * 
 * CREATE TABLE `payment`
 * (
 * `ID` INT(11) NOT NULL AUTO_INCREMENT,
 * `PaymentAmount` DOUBLE NOT NULL,
 * `DateOfPayment` VARCHAR(255) NOT NULL,
 * `ApartmentNumberID` INT(11) NOT NULL,
 * PRIMARY KEY (`ID`),
 * FOREIGN KEY (ApartmentNumberID) REFERENCES apartment (ID)
 * )
 * 
 */