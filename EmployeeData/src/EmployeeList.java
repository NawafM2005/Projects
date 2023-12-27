import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

/**
 * 
 */

/**
 * @author Gurbaaz.C
 * Date: May 17 2023
 * Description: Represents a list of employee objects.
 * 
 * Method List: 
 * 				// Default Constructor
 * 				public EmployeeList()
 * 
 * 				// Getters and Setter Methods
 * 				public int getMaxSize()
 * 				public void setMaxSize(int maxSize)
 * 				public int getSize()
 * 				public void setSize(int size)
 * 				public Employee[] geteList()
 * 
 * 				// The insert method
 * 				// Checks if the size is below the maxSize
 * 				// If so, it increases the size by one and
 * 				// adds the record to the location just below 
 * 				// size and return true if successfully inserts
 * 				public boolean insert (Employee eRecord)
 * 
 * 				// toString method to return the list
 * 				public String toString ()
 * 
 * 				// Method to increase size the size of the existing array to accommodate more records
 * 				// Saves old list to temp list and saves back with an increased size
 * 				public boolean incArray(int newMaxSize)
 * 
 * 				// The delete method
 * 				// Finds the record using binarySearch,
 * 				// then deletes the record and
 * 				// decreases the size by one 
 * 				// returns true if successful
 * 				// or false if unsuccessful 
 * 				public boolean delete (Employee eRecord)
 * 
 * 				// Change a record
 * 				// Deletes the old record
 * 				// Adds a new one 
 * 				// Returns true if successful
 * 				public boolean change (Employee oldR, Employee newR)
 * 
 * 				// Shell Sort Method
 * 				// Source: https://www.baeldung.com/java-shell-sort
 * 				public void shellSort()
 * 
 * 				// binarySearch method 
 * 				public int binarySearch (Employee eRecord)
 * 
 * 				// linearSearch method
 * 				public int linearSearch (Employee eRecord)
 * 
 * 				// Method to find the size of the file
 * 				public static int sizeOfFile (String filename) throws IOException
 * 
 *  			// Method to read information from the pre existing file
 *  			public static String[] fileInfo(String fileName) throws IOException
 *  
 *  			// Method to save user key and file info to new fixed textfile
 *  			public static FileWriter saveInfo(String nameOfFile, String info, boolean append) throws IOException
 *  
 *  			// Self-Testing Main Method
 *  			public static void main(String[] args) throws IOException
 *
 */
public class EmployeeList {

	/**
	 * Private instance data
	 */
	private Employee eList [];
	private int maxSize;
	private int size;
	
	/**
	 * Default Constructor 
	 */
	public EmployeeList() {
		// Initialize the instance data
		this.maxSize = 10;
		this.size = 0;
		this.eList = new Employee[maxSize];
	}

	/**
	 * @return the maxSize
	 */
	public int getMaxSize() {
		return maxSize;
	}

	/**
	 * @param maxSize the maxSize to set
	 */
	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
	}

	/**
	 * @return the size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * @param size the size to set
	 */
	public void setSize(int size) {
		this.size = size;
	}

	/**
	 * @return the eList
	 */
	public Employee[] geteList() {
		return eList;
	}

	/**
	 * The insert method
	 * Checks if the size is below the maxSize
	 * If so, it increases the size by one and
	 * adds the record to the location just below 
	 * size and return true if successfully inserts
	 */
	public boolean insert (Employee eRecord) {
		// If size is below maxSize
		if (size < maxSize) {
			size++; // Increase by 1
			eList[size-1] = eRecord; // add the record
			return true;
		}
		return false; // Record could not be inserted
	}

	/**
	 * toString method to return the list
	 */
	public String toString () {
		// Declare and initialize a string variable
		String theList = "";

		// Use a for loop to convert the whole list in to a string
		for (int i = 0; i< size; i++) {
			theList = theList + eList[i].toString() + "\n";
		}
		
		// Return the list
		return theList;
	}

	/*
	 * Method to increase size the size of the existing array to accommodate more records
	 * Saves old list to temp list and saves back with an increased size
	 */
	public boolean incArray(int newMaxSize) {
		// Run if the new size is more than original size or else keep old list
		if (newMaxSize>maxSize) {

			// Create temporary array to store the old data
			Employee tempArray[]= new Employee[newMaxSize];

			// Copy old list to the tempArray
			for (int i=0; i<size;i++) {
				tempArray[i]=this.eList[i];
			}	
			// Save info from tempArray to list
			this.eList=tempArray;

			// Change size to the new size
			this.maxSize= newMaxSize;
			return true; // Return true
		}

		return false; // If newMaxSize is less than or equal to the old maxSize return false
	}

	/**
	 * The delete method
	 * Finds the record using binarySearch,
	 * then deletes the record and
	 * decreases the size by one 
	 * returns true if successful
	 * or false if unsuccessful 
	 */
	public boolean delete (Employee eRecord) {
		// Find the record to delete using employeeID and linearSearch
		int location= this.linearSearchID(eRecord);

		// If the location is valid, then delete the record at that location
		if (location>=0) {
			eList[location] = eList[size-1];
			size--; // Decrease the size
			return true; // Record was deleted
		}
		// Sort the list
		this.shellSort();

		return false; // Record does not exist
	}

	/**
	 * Change a record
	 * Deletes the old record
	 * Adds a new one 
	 * Returns true if successful
	 */
	public boolean change (Employee oldR, Employee newR) {
		// Delete the old record
		if (delete(oldR)) { // Checks if it can delete it
			insert(newR);
			return true; // was able to delete and insert
		}
		// Sort the list again
		this.shellSort();

		return false; // Change was unsuccessful 
	}

	/**
	 * Shell Sort Method
	 * Source: https://www.baeldung.com/java-shell-sort
	 */
	public void shellSort() {
		// Create and initialize int variable to size
		int n = size;

		// Use a for loop to reduce gap 
		for (int gap = n / 2; gap > 0; gap /= 2) {

			// Do a insertion sort for the reduced gap using for loop
			for (int i = gap; i < n; i++) {
				// Create Employee and initialize it to list at i
				Employee temp = eList[i];
				// Create a int and initialize it to i
				int j = i;

				// A while loop to sort the record, checks if the record by converting to string
				// then the records are compared to each other and if less than 0 (thus lower in the alpha),
				// the records are rearranged 
				while (j >= gap && eList[j - gap].toString().compareToIgnoreCase(temp.toString()) > 0) {
					eList[j] = eList[j - gap];
					j -= gap;
				}
				eList[j] = temp;
			}
		}
	}

	/**
	 * binarySearch method 
	 */
	public int binarySearch (Employee eRecord) {
		// Declare and initialize variable for low, high and middle
		int low = 0;
		int high = size-1;
		int middle;

		// While the low end of my list is below the high end
		while (low <= high) {
			middle = (low + high)/2; // Find the middle
			// 	Check if middle is the record 
			if (eRecord.toString().compareToIgnoreCase(eList[middle].toString())==0) {
				return middle; // Found it
			}
			// Check if record is lower in the alpha
			else if (eRecord.toString().compareToIgnoreCase(eList[middle].toString()) < 0) {
				// My record is lower in the alpha
				high = middle - 1;
			}
			// Record is higher in the alpha
			else {
				// My record is higher in the alpha
				low = middle + 1;
			}
		}
		return -1; // If not found, return -1
	}
	
	/**
	 * Linear Search for ID
	 */
	public int linearSearchID (Employee eRecord) {
		// Loop through the valid list
		for (int i = 0; i < size; i++) {
			if (eList[i].getEmployeeID().equalsIgnoreCase(eRecord.getEmployeeID())) {
				return i; // Return the location of the name
			}
		}
		return -1; // Not found
	}
	
	/**
	 * Linear Search for Phone
	 */
	public int linearSearchFull (Employee eRecord) {
		// Loop through the valid list
		for (int i = 0; i < size; i++) {
			if (eList[i].toString().equalsIgnoreCase(eRecord.toString())) {
				return i; // Return the location of the name
			}
		}
		return -1; // Not found
	}
	
	/**
	 * Method to find the size of the file
	 */
	public static int sizeOfFile (String filename) throws IOException {
		// open the file to read from
		FileReader fr = new FileReader(filename);
		BufferedReader input = new BufferedReader(fr);

		// Create a sizeCounter variable 
		int sizeCounter =0;

		// Check for the size of the data being received using while loop and sizeCounter
		while (input.readLine() != null) {
			sizeCounter++;
		}
		return sizeCounter; // Return the sizeCounter
	}

	/**
	 * Method to read information from the pre existing file
	 */
	public static String[] fileInfo(String fileName) throws IOException { 
		// Set up fileReader and BufferedReader to read a file
		FileReader file = new FileReader (fileName);
		BufferedReader input= new BufferedReader (file);

		// Read the size of file
		int size= sizeOfFile(fileName);

		// Create a string for file information
		String fileInfo[]= new String [size];

		// Loop through and save info
		for (int i=0; i<size; i ++) { 
			fileInfo[i]=input.readLine();
		}

		// Close file stream
		input.close();

		return fileInfo; // Return the information from file as string
	}

	/**
	 * Method to save user key and file info to new fixed textfile
	 */
	public static FileWriter saveInfo(String nameOfFile, String info, boolean append) throws IOException{

		// Open file to write
		FileWriter fileK = new FileWriter("PasswordFile.txt", append);
		PrintWriter inputSave = new PrintWriter(fileK);

		if (append == true) {
			inputSave.print("\n" + info); // Save the key and phrase to 2nd line
			}
			else {
		        fileK.write(""); // Erase content of the file
				inputSave.print(info); // Save the key and phrase to 2nd line
			}
			// Close the file
			fileK.close();

			return fileK; // Return the create file
	}

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// <<< Testing for insert, change, delete, sort, linear search, and binary search methods >>>
		
		// Create a Employee List object
		EmployeeList testList = new EmployeeList();

		// Create a int variable for user input and counter
		int yesNo = 0, counter = 0; 

		// Infinite loop for testing
		while(true) {
			char command = JOptionPane.showInputDialog(null,
					"i - insert\n" + 
							"p - print\n" +
							"c - change\n" +
							"d - delete\n" +
							"R - sort by record\n" +
							"s - Linear Search for employeeID\n" +
							"B - Binary Search for record\n" +
							"q - quit", "i").charAt(0);
			if (command == 'q') {
				break;
			}

			switch(command) {
			case 'i': {
				// Prompt for record
				String record = JOptionPane.showInputDialog(null, "Enter [First Name<Last Name<Address<Phone Number<Hiring Date<Employee Type]", 
						"Nawaf<Monky<48 William Road<333-333-3333<2005/10/15<M");
				// Create the eList record
				Employee eInfo = new Employee();
				eInfo.processRecord(record);
				if (testList.insert(eInfo)) {
					JOptionPane.showMessageDialog(null, "Insert successful");
					counter++;
				}
				else if (counter == testList.maxSize) {
					JOptionPane.showMessageDialog(null, "List is full!"); // Inform the user that the insert failed 

					// Ask the user if they wish to add more records to the list
					yesNo = JOptionPane.showConfirmDialog(null, "Would you like to add more records?"
							, "?", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);

					// If user enters yes
					if (yesNo == JOptionPane.YES_OPTION) {
						// Prompt user for size
						int newSize=Integer.parseInt(JOptionPane.showInputDialog(null, "What Size Of Record?"));

						// If the size of the array is increased then display message informing the user 
						if(testList.incArray(newSize)) {
							JOptionPane.showMessageDialog(null, "Size Increased!");
						}
						// Otherwise inform the user that the array was not increases
						else {
							JOptionPane.showMessageDialog(null, "Failed! Size was not changed!");
						}
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Insert failed!"); // Inform the user that the insert failed 

				}
				break;
			}
			case 'c': {
				// Prompt for old record
				String oldRecord = JOptionPane.showInputDialog(null, "Enter [First Name<Last Name<Address<Phone Number<Employee ID<Hiring Date<Employee Type<Annual Earnings]", 
						"Nawaf<Monky<48 William Road<333-333-3333<1234567RaDom<2005/10/15<M<0.0");
				// Create a employee object and process 
				Employee oldInfo = new Employee();
				oldInfo.processRecord(oldRecord);

				// Prompt for new record
				String newRecord = JOptionPane.showInputDialog(null, "Enter [First Name<Last Name<Address<Phone Number<Hiring Date<Employee Type]", 
						"Nawaf<Monky<48 William Road<333-333-3333<2005/10/15<M");
				// Create a employee object and process
				Employee newInfo = new Employee();
				newInfo.processRecord(newRecord);

				// Check if change was successful or not and display a message to inform the user
				if (testList.change(oldInfo, newInfo)) {
					JOptionPane.showMessageDialog(null,  "Change Successful");
				}
				else {
					JOptionPane.showMessageDialog(null,  "Change Failed");
				}
				break;
			}
			case 'B': {
				// Prompt user for a whole record
				String record = JOptionPane.showInputDialog(null, "Enter [First Name<Last Name<Address<Phone Number<Employee ID<Hiring Date<Employee Type<Annual Earnings]", 
						"Nawaf<Monky<48 William Road<333-333-3333<1234567RaDom<2005/10/15<M<0.0");
				// Create a Employee to process the entered record
				Employee recordToFind = new Employee();
				recordToFind.processAllData(record);

				// Call and store the return value from the binarySearch method (overloaded)
				int location= testList.binarySearch(recordToFind);

				// Check if search was successful or not and display a message to inform the user
				if (location>=0) {
					JOptionPane.showMessageDialog(null, testList.geteList()[location] + " Found!");
				}
				else {
					JOptionPane.showMessageDialog(null, record + " Not Found!");

				}
				break;
			}
			case 's': {
				// Prompt user for a whole record
				String record = JOptionPane.showInputDialog(null, "Enter [First Name<Last Name<Address<Phone Number<Employee ID<Hiring Date<Employee Type<Annual Earnings]", 
						"Nawaf<Monky<48 William Road<333-333-3333<1234567RaDom<2005/10/15<M<0.0");
				// Create a Employee to process the entered record
				Employee recordToFind = new Employee();
				recordToFind.processAllData(record);

				// Call and store the return value from the linearSearch method 
				int location= testList.linearSearchID(recordToFind);

				// Check if search was successful or not and display a message to inform the user
				if (location>=0) {
					JOptionPane.showMessageDialog(null, testList.geteList()[location] + " Found!");
				}
				else {
					JOptionPane.showMessageDialog(null, recordToFind + " Not Found!");

				}
				break;
			}
			case 'R': {
				// Call the shell sort method
				testList.shellSort();
				break;
			}
			case 'p':{
				// Call the toString method and print the records
				JOptionPane.showMessageDialog(null, testList.toString());
				break;
			}
			case 'd':{
				// Prompt for record
				String record = JOptionPane.showInputDialog(null, "Enter [First Name<Last Name<Address<Phone Number<Employee ID<Hiring Date<Employee Type<Annual Earnings]", 
						"Nawaf<Monky<48 William Road<333-333-3333<1234567RaDom<2005/10/15<M<0.0");
				// Create the eList record and process it
				Employee eInfo = new Employee();
				eInfo.processAllData(record);

				// Check if delete was successful or not and display a message to inform the user
				if (testList.delete(eInfo)) {
					JOptionPane.showMessageDialog(null, "Delete successful");
					counter--;
				}
				else {
					JOptionPane.showMessageDialog(null, "Delete failed");
				}
				break;
			}

			}
		}
		
		// <<<<< Testing save and read file methods >>>>>
		
		// Create a string array
		String fileInfo[];

		// Open the file and read the info using fileInfo method
		fileInfo=fileInfo("PasswordFile.txt");
		
		// Use a for loop to display the info from the file
		for (int i=0; i<fileInfo.length; i++) { 
			System.out.println(fileInfo[i]);
		}

		// Save data into the PasswordFile.txt using saveInfo method (without rewriting it)
		saveInfo("PasswordFile.txt", "Campos/123", true);

		// Call the sizeOfFile method and store the value returned into size
		int size = sizeOfFile("PasswordFile.txt");

		// Print the size of the file
		System.out.println(size);

	}

}
