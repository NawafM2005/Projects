/**
 * 
 */

/**
 * @author Sandesh
 *
 */
public class HourlyEmployee extends Employee{

	/*
	 * Private instance data
	 */
	private int hoursWorked, maxHours, overtimeHours;
	private double hourlyPay, overtimePay, annualSalary;

	/**
	 * Default Constructor
	 */
	public HourlyEmployee() {
		// init instance data
		this.hoursWorked = 0; 
		this.maxHours = 0; 
		this.overtimeHours = 0; 
		this.hourlyPay = 0; 
		this.overtimePay = 0; 
		this.annualSalary = 0;
	}

	/**
	 * Overloaded Constructor
	 */
	public HourlyEmployee(Employee eRecord) {
		// init instance data
		this();
	}

	/* 
	 * Method to update earnings according to the hours worked. 
	 */
	public double calculateEarningsHourly() {
		// declare and init variables
		double updatedEarnings = 0; 
		int weeklyHours = 0;
		maxHours = 40;

		weeklyHours = hoursWorked/48; // calculate the hours worked in the week

		// check if the overtime hours are worked
		if (weeklyHours > maxHours) {
			overtimeHours = weeklyHours - maxHours;
			overtimePay = (hourlyPay * 1.5) * overtimeHours; 
		}
		
		// update the earnings
		updatedEarnings = annualSalary + overtimePay;
		
		return updatedEarnings;
	} 
	
	// toString method
    public String toString() {
        return "HourlyEmployee{" +
                "hoursWorked = " + hoursWorked +
                ", maxHours = " + maxHours +
                ", overtimeHours = " + overtimeHours +
                ", hourlyPay = " + hourlyPay +
                ", overtimePay = " + overtimePay +
                '}';
    }

    /*
     * Getters and Setters for HourlyEmployee
     */
	public int getHoursWorked() {
		return hoursWorked;
	}

	public void setHoursWorked(int hoursWorked) {
		this.hoursWorked = hoursWorked;
	}

	public int getMaxHours() {
		return maxHours;
	}

	public void setMaxHours(int maxHours) {
		this.maxHours = maxHours;
	}

	public double getHourlyPay() {
		return hourlyPay;
	}

	public void setHourlyPay(double hourlyPay) {
		this.hourlyPay = hourlyPay;
	}
	
	/**
	 * @return the annualSalary
	 */
	public double getAnnualSalary() {
		return annualSalary;
	}

	/**
	 * @param annualSalary the annualSalary to set
	 */
	public void setAnnualSalary(double annualSalary) {
		this.annualSalary = annualSalary;
	}

	/**
	 * Self-Testing Main Method
	 */
	public static void main(String[] args) {
		// create an HourlyEmployee object
	    HourlyEmployee employee1 = new HourlyEmployee();
	    
	    // set values for the variables
	    employee1.setHoursWorked(5000);
	    employee1.setHourlyPay(15.0);
	    employee1.setAnnualSalary(10000);
	    
	    // calculate and print the earnings
	    employee1.calculateEarnings(employee1.calculateEarningsHourly());
	    System.out.println("Employee 1 earnings: $" + employee1.getAnnualEarning());
	    
		// create an HourlyEmployee object using overloaded constructor
	    Employee employeeRecord = new Employee();
	    HourlyEmployee employee2 = new HourlyEmployee(employeeRecord);
	    
	    // set values for the variables
	    employee2.setHoursWorked(3500);
	    employee2.setHourlyPay(20.0);
	    employee2.setAnnualSalary(10000);
	    
	    // calculate and print the earnings
	    employee2.calculateEarnings(employee2.calculateEarningsHourly());
	    System.out.println("Employee 2 earnings: $" + employee2.getAnnualEarning());

	}

}
