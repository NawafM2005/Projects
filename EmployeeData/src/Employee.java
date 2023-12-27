import javax.swing.JOptionPane;

/**
 * 
 */

/**
 * @author Gurbaaz.C
 * Date: May 11 2023
 * Description: This class contains the generic employee information: name, address, phone number,
 * 				employee ID, employee type, date of hiring in the form of yyyy/mm/dd and annual earnings. 
 * 
 * Method List:
 * 				// Default Constructor
 * 				public Employee()
 * 
 * 				// Overloaded constructor 
 * 				public Employee(String record)
 * 
 * 				// Method to populate the data. Assumes the format
 * 				// First Name<Last Name<Address<Phone Number<Hiring Date<Employee Type
 * 				public void processRecord(String record)
 * 
 * 				// Method to populate all the data. Assumes the format:
 *				// First Name<Last Name<Address<Phone Number<Employee ID<Hiring Date<Employee Type<Annual Earnings
 *				public void processAllData(String record)
 * 
 * 				// toString Method
 * 				public String toString()
 * 
 * 				// Calculate earnings method (a setter method for annualEarning)
 * 				public void calculateEarnings (double annualEarning)
 * 
 * 				// Generates a 12 character employee ID
 * 					The ID contains 
 * 						7 randomly generated digits each from 0 to 9
 *  	  				The first 2 letters of their first name
 *      				The first 3 letters of their last name
 *      		public String employeeID ()
 *      		
 *      		// Getter and Setter Methods
 *      		public String getFirstName()
 *      		public void setFirstName(String firstName)
 *      		public String getLastName()
 *      		public void setLastName(String lastName)
 *      		public String getAddress()
 *      		public void setAddress(String address)
 *      		public String getPhoneNumber()
 *      		public void setPhoneNumber(String phoneNumber)
 *      		public String getEmployeeID()
 *      		public void setEmployeeID(String employeeID)
 *      		public char getEmployeeType()
 *      		public void setEmployeeType(char employeeType)
 *      		public String getHiringDate()
 *      		public void setHiringDate(String hiringDate)
 *      		public double getAnnualEarning()
 *      					
 *      		// Self-Testing Main Method
 *      		public static void main(String[] args)
 *
 */
public class Employee {

	/**
	 * Private Data 
	 * (first name, last name, address, phone number, employee ID, employee type, date of hiring in the form
	 * of yyyy/mm/dd and annual earnings)
	 */
	private String firstName, lastName;
	private String address;
	private String phoneNumber;
	private String employeeID;
	private char employeeType;
	private String hiringDate;
	private double annualEarning;

	/**
	 * Default Constructor
	 */
	public Employee() {
		// Initialize all private data
		this.firstName = "";
		this.lastName = "";
		this.address = "";
		this.phoneNumber = "";
		this.employeeID = "";
		this.employeeType = 0;
		this.hiringDate = "";
		this.annualEarning = 0;
	}

	/**
	 * Overloaded constructor 
	 */
	public Employee(String record) {
		// Initialize all private data
		this.firstName = "";
		this.lastName = "";
		this.address = "";
		this.phoneNumber = "";
		this.employeeID = "";
		this.employeeType = 0;
		this.hiringDate = "";
		this.annualEarning = 0;

		// Call the processRecord method to proccess the string 
		this.processRecord(record);
	}

	/**
	 * Method to populate the data. Assumes the format
	 * First Name<Last Name<Address<Phone Number<Hiring Date<Employee Type
	 */
	public void processRecord(String record) {
		// Splits input string using a slash 
		String word[];
		word = record.split("<"); 

		// Assigns the elements to the private data
		this.firstName = word [0]; 
		this.lastName = word [1];
		this.address = word[2];
		this.phoneNumber = word[3]; 
		this.hiringDate = word[4];
		this.employeeType = word[5].charAt(0);

		// Calls the employeeID method to generate the unique ID
		this.employeeID = employeeID();
	}
	
	/**
	 * Method to populate all the data. Assumes the format
	 * First Name<Last Name<Address<Phone Number<Employee ID<Employee Type<Hiring Date<Annual Earnings
	 */
	public void processAllData(String record) {
		// Splits input string using a slash 
		String word[];
		word = record.split("<"); 

		// Assigns the elements to the private data
		this.firstName = word [0]; 
		this.lastName = word [1];
		this.address = word[2];
		this.phoneNumber = word[3]; 
		this.employeeID = word [4];
		this.hiringDate = word[6];
		this.employeeType = word[5].charAt(0);
		this.annualEarning = Double.parseDouble(word[7]);
	}

	/**
	 * toString Method
	 */
	public String toString() {
		// Create a string variable for type and initialize it
		String type = "";

		// A switch case to convert the short form to full form for the types 
		switch (this.employeeType) {
		case 'M': {
			type = "Manager";
			break;
		}
		case 'H': {
			type = "Hourly";
			break;
		}
		case 'S': {
			type = "Sales";
			break;
		}
		default: {
			type = "Invaild";
		}
		}

		// Return the data as a string using the following format:
		// (firstName<lastName<address<phoneNumber<employeeID<type<hiringDate<annualEarning<)
		return firstName + "<" + lastName + "<" + address + "<" 
		+ phoneNumber + "<" + employeeID + "<" + type 
		+ "<" + hiringDate + "<" + annualEarning + "<";
	}

	/**
	 * Calculate earnings method (a setter method for annualEarning)
	 */
	public void calculateEarnings (double annualEarning) {
		this.annualEarning = annualEarning;
	}

	/**
	 * Generates a 12 character employee ID
	 * The ID contains 
	 * 		7 randomly generated digits each from 0 to 9
	 *      The first 2 letters of their first name
	 *      The first 3 letters of their last name
	 */
	public String employeeID () {
		// Generate 7 random digits each from 0 to 9 using for loop
		for (int i = 0; i < 7; i++) {
			int randomDigit = (int)(Math.random() * 10);
			employeeID += Integer.toString(randomDigit);
		}

		// Add a part of first name and last name
		employeeID += firstName.substring(0,2);
		employeeID += lastName.substring(0,3);

		// Return the final generated ID
		return employeeID;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the employeeID
	 */
	public String getEmployeeID() {
		return employeeID;
	}

	/**
	 * @param employeeID the employeeID to set
	 */
	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}

	/**
	 * @return the employeeType
	 */
	public char getEmployeeType() {
		return employeeType;
	}

	/**
	 * @param employeeType the employeeType to set
	 */
	public void setEmployeeType(char employeeType) {
		this.employeeType = employeeType;
	}

	/**
	 * @return the hiringDate
	 */
	public String getHiringDate() {
		return hiringDate;
	}

	/**
	 * @param hiringDate the hiringDate to set
	 */
	public void setHiringDate(String hiringDate) {
		this.hiringDate = hiringDate;
	}

	/**
	 * @return the annualEarning
	 */
	public double getAnnualEarning() {
		return annualEarning;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Declare a string with a record
		String myRecord = "Nawaf<Mahmood<123 Unknown Road<647-560-9092<2005/10/15<M<";

		// Create object of employee
		Employee eInfo = new Employee();

		// Test the processor method + employeeID method
		eInfo.processRecord(myRecord);

		// Display the object by using the toString() method 
		System.out.println(eInfo.toString() +"\n\n");

		// Create a new employee
		Employee eInfo2 = new Employee ();

		// Test all other setter methods
		eInfo2.setAddress("48 Davidslem Rd");
		eInfo2.setEmployeeType('S');
		eInfo2.setFirstName("Gurbaaz");
		eInfo2.setLastName("Cheema");
		eInfo2.setHiringDate("2005/11/21");
		eInfo2.setPhoneNumber("416-999-6666");
		eInfo2.calculateEarnings(5000);

		// Call the employeeID method and setter
		eInfo2.setEmployeeID(eInfo2.employeeID());

		// Test all getter methods
		System.out.println(eInfo2.getEmployeeID());
		System.out.println(eInfo2.getAddress());
		System.out.println(eInfo2.getEmployeeType());
		System.out.println(eInfo2.getFirstName());
		System.out.println(eInfo2.getLastName());
		System.out.println(eInfo2.getHiringDate());
		System.out.println(eInfo2.getPhoneNumber());
		System.out.println(eInfo2.getAnnualEarning() + "\n\n");

		// Display the object
		System.out.println(eInfo2.toString() + "\n\n");

		// Declare a string with a record
		String myRecord2 = "Nawaf<Mahmood<123 Unknown Road<647-560-9092<2005/10/15<M<";

		// Call the overloaded employee constructor
		Employee eInfo3 = new Employee(myRecord2);

		// Display the object
		System.out.println(eInfo3.toString() + "\n\n");

	}

}
