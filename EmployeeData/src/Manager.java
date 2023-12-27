/**
 * 
 */

/**
 * @author Sandesh
 *
 */
public class Manager extends Employee {

	/*
	 * Private instance data
	 */
	private double annualSalary;
	private double annualBonus;
	private double performanceRating;

	/**
	 * Default Constructor
	 */
	public Manager() {
		annualSalary = 0.0;
		annualBonus = 0.0;
		performanceRating = 0.0;
	}

	/**
	 * Overloaded Constructor
	 */
	public Manager(Employee employee) {
		super();
		annualSalary = 0.0;
		annualBonus = 0.0;
		performanceRating = 0.0;
	}

	/* 
	 * Method to update earnings according to the hours worked. 
	 */
	public double calculateEarningsManager() {
		double bonusPercentage = 0.0;
		double bonus = 0; 
		double totalEarnings = 0; 
		
		if (performanceRating == 1.0) {
			bonusPercentage = 0.015;
		} else if (performanceRating == 2.0) {
			bonusPercentage = 0.03;
		} else if (performanceRating == 3.0) {
			bonusPercentage = 0.1;
		}

		bonus = annualSalary * bonusPercentage;
		totalEarnings = annualSalary + bonus;

		return totalEarnings;
	}

	// getters and setters
	public double getAnnualSalary() {
		return annualSalary;
	}

	public void setAnnualSalary(double annualSalary) {
		this.annualSalary = annualSalary;
	}

	public double getAnnualBonus() {
		return annualBonus;
	}

	public void setAnnualBonus(double annualBonus) {
		this.annualBonus = annualBonus;
	}

	public double getPerformanceRating() {
		return performanceRating;
	}

	public void setPerformanceRating(double performanceRating) {
		this.performanceRating = performanceRating;
	}

	// toString method
	public String toString() {
		return super.toString() +
				"Annual Salary: " + annualSalary + "\n" +
				"Annual Bonus: " + annualBonus + "\n" +
				"Performance Rating: " + performanceRating + "\n";
	}

	/**
	 * Self-Testing Main Method
	 */
	public static void main(String[] args) {
		// create a Manager object
		Manager manager = new Manager();

		// set the employee information
		manager.setAnnualSalary(60000.0);
		manager.setAnnualBonus(3000.0);
		manager.setPerformanceRating(2.0);

		// calculate and update earnings
		manager.calculateEarnings(manager.calculateEarningsManager());

		// print the manager information
		System.out.println("The Manager's earnings this year are: $" + manager.getAnnualEarning());
	}
}
