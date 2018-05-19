public class Main {
	/*
	 * Moshe Binieli - 311800668, Ofir Daous - 203796156
	 */
	public static void main(String[] args) {
		try {
			Menu menu = new Menu();
			menu.executeMenu();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}

/*
	* Create apartment table *
	
	CREATE TABLE `apartment` (
	    `ID` INT(11) NOT NULL AUTO_INCREMENT,
	    `ApartmentNumber` INT(11) NOT NULL,
	    PRIMARY KEY (`ID`)
	)

	* Create payment table * 
	
	CREATE TABLE `payment` (
    `ID` INT(11) NOT NULL AUTO_INCREMENT,
    `PaymentAmount` DOUBLE NOT NULL,
    `DateOfPayment` VARCHAR(255) NOT NULL,
    `ApartmentNumberID` INT(11) NOT NULL,
    PRIMARY KEY (`ID`),
    FOREIGN KEY (ApartmentNumberID)
        REFERENCES apartment (ID))

*/