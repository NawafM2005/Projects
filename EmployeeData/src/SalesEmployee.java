/**
 * 
 */

/**
 * @author Sandesh
 *
 */
public class SalesEmployee extends Employee{

	// Private Data
	private double percentCommision, annualSalary, annualSales; 

	/**
	 * Default Constructor
	 */
	public SalesEmployee() {
		// init all data
		this.annualSales = 0; 
		this.percentCommision = 0;
		this.annualSalary = 0; 
	}

	/**
	 * Overloaded Constructor
	 */
	public SalesEmployee(Employee employee) {
		// init all data
		this();
	}

	/* 
	 * Method to update earnings according to the sales. 
	 */
	public double calculateEarningsSales() {
		// declare and init variables
		double updatedEarnings = 0; 
		double commision = 0;
		percentCommision = 0.06;

		commision = annualSales * percentCommision; // calculate the hours worked in the week

		// update the earnings
		updatedEarnings = annualSalary + commision;

		return updatedEarnings;
	}

	public String toString() {
		return "SalesEmployee{" +
				"annualSales = " + annualSales +
				", percentCommision = " + percentCommision +
				", annualSalary = " + annualSalary +
				'}';
	}

	public double getPercentCommision() {
		return percentCommision;
	}

	public void changeCommision(double percentCommision) {
		this.percentCommision = percentCommision;
	}

	public double getAnnualSalary() {
		return annualSalary;
	}

	public void setAnnualSalary(double annualSalary) {
		this.annualSalary = annualSalary;
	}

	public double getAnnualSales() {
		return annualSales;
	}

	public void setAnnualSales(double annualSales) {
		this.annualSales = annualSales;
	}

	/**
	 * Self-Testing Main Method
	 */
	public static void main(String[] args) {
		// create an SalesEmployee object
		SalesEmployee employee1 = new SalesEmployee();

		// set values for the variables
		employee1.setAnnualSales(100000.0);
		employee1.setAnnualSalary(50000.0);

		// calculate and print the earnings
		employee1.calculateEarnings(employee1.calculateEarningsSales());
		System.out.println("Employee 1 earnings: $" + employee1.getAnnualEarning());

		// create an SalesEmployee object using overloaded constructor
		Employee employeeRecord = new Employee();
		SalesEmployee employee2 = new SalesEmployee(employeeRecord);

		// set values for the variables
		employee2.setAnnualSales(150000.0);
		employee2.setAnnualSalary(60000.0);

		// calculate and print the earnings
		employee2.calculateEarnings(employee2.calculateEarningsSales());
		System.out.println("Employee 2 earnings: $" + employee2.getAnnualEarning());

	}

}
