import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 * 
 */

/**
 * @author Nawaf
 * Sub: Gurbaaz.C
 * Sub: Sandesh. M
 * Date: 5/24/2023
 * Description: Main Screen of the program that allows user to make employee delete and much more
 * Method List: 
 * public void deleteRow(String ID, int rowCount) {
 * public void shellSortTable() {
 * public int compareRows(String[] row1, int row2Index) {
 * public void insertFormat() {
 * public void findFormat() {
 * public void deleteFormat() {
 * public void changeFormat() {
 * 
 */
public class MainScreen extends JFrame implements ActionListener {

	/**
	 * Private Variables
	 */
	private JPanel sidePanel, infoPanel, titlePanel, btnInfoPanel, enterData;
	private JButton switchAccountsBtn, exitBtn;
	private TextPicture titleText, dataText;
	private JTextField firstNameField, lastNameField, addressField , phoneField, typeField, dateField, userIDField, salaryField, hoursWorkedField,
	hourlyPayField, annualSalesField, annualBonusField, performanceRatingField;
	private JButton insertBtn, deleteBtn ,changeBtn, findBtn, sortBtn, saveToFile, readFromFile;
	private JButton submitInsertBtn, submitDeleteBtn, submitChangeBtn, nextChangeBtn, nextInsertBtn, submitFindBtn;
	private DefaultTableModel model;
	private String username;
	private Employee employee;
	private Manager manager;
	private SalesEmployee sales;
	private HourlyEmployee hourly;
	private EmployeeList eList;
	private JTable table;
	private String employeeData;


	public MainScreen() {

		// Create frame
		super("Main Menu");
		setSize(1000, 700);
		setLocationRelativeTo(null); // Default location to middle of screen
		setLayout(null);

		// Initialize Objects
		employee = new Employee();
		eList = new EmployeeList();
		manager = new Manager ();
		sales = new SalesEmployee ();
		hourly = new HourlyEmployee ();

		// Initalize data
		employeeData = "";

		//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Create and place the Side Panel
		sidePanel= new JPanel();

		//Set Bounds
		sidePanel.setBounds(0, 0, 200, 800);

		//Set Color
		sidePanel.setBackground(Color.BLACK);

		// Set its layout to null
		sidePanel.setLayout(null);

		// Add to frame
		add(sidePanel);

		// Create and place the Title Panel
		titlePanel = new JPanel() {

			// Research from: https://stackoverflow.com/questions/19125707/simplest-way-to-set-image-as-jpanel-background

			// Override the paintComponent method to paint the background image
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				ImageIcon backgroundImage = new ImageIcon("water.jpg");
				g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), null);
			}
		};

		//Set Bounds
		titlePanel.setBounds(200, 0, 800, 100);

		// Make the panel non-opaque
		titlePanel.setOpaque(false);

		// Set its layout to null
		titlePanel.setLayout(null);

		// Add to frame
		add(titlePanel);

		//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Create and place the Info Panel
		infoPanel= new JPanel();

		//Set Bounds
		infoPanel.setBounds(200, 100, 800, 480);

		//Set Color
		infoPanel.setBackground(Color.RED);

		// Set its layout to null
		infoPanel.setLayout(null);

		// Add to frame
		add(infoPanel);

		//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Create and place the Btn Side Panel
		btnInfoPanel= new JPanel();

		//Set Bounds
		btnInfoPanel.setBounds(200, 580, 800, 250);

		//Set Color
		btnInfoPanel.setBackground(Color.WHITE);

		// Set its layout to FlowLayout
		btnInfoPanel.setLayout(null);

		// Add to frame
		add(btnInfoPanel);

		// Add Logo Image to Top Left
		ImageIcon logoImage = new ImageIcon("LockImg.png");
		JLabel logoLabel = new JLabel(logoImage);
		logoLabel.setBounds(0, 0, logoImage.getIconWidth(), logoImage.getIconHeight());
		sidePanel.add(logoLabel);


		//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Create and place the enter Data Panel
		enterData= new JPanel();

		//Set Bounds
		enterData.setBounds(0, 100, 200, 430);

		//Set Color
		enterData.setBackground(Color.WHITE);

		// Set its layout to null
		enterData.setLayout(null);

		// Add to frame
		sidePanel.add(enterData);

		enterData.setVisible(false);

		//--------------------------------------------------> text Boxes and Text
		//Create the username Text
		dataText = new TextPicture("Insert", 70, 25);
		dataText.setColor(Color.BLACK);
		dataText.setFont(new Font("Times New Roman", Font.BOLD, 20));
		dataText.setBounds(0, 0, 200, 430);
		enterData.add(dataText);

		//Create firstName text field
		firstNameField = new JTextField("First Name");
		firstNameField.setBounds(30, 45, 150, 25);
		firstNameField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		enterData.add (firstNameField);

		//Create lastName text field
		lastNameField = new JTextField("Last Name");
		lastNameField.setBounds(30, 85, 150, 25);
		lastNameField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		enterData.add (lastNameField);

		//Create address text field
		addressField = new JTextField("Address");
		addressField.setBounds(30, 125, 150, 25);
		addressField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		enterData.add (addressField);

		//Create phone text field
		phoneField = new JTextField("Phone #");
		phoneField.setBounds(30, 165, 150, 25);
		phoneField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		enterData.add (phoneField);

		//Create type text field
		typeField = new JTextField("Type");
		typeField.setBounds(30,205, 150, 25);
		typeField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		enterData.add (typeField);

		//Create date text field
		dateField = new JTextField("Hiring Date");
		dateField.setBounds(30, 245, 150, 25);
		dateField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		enterData.add (dateField);

		//Create ID text field
		userIDField = new JTextField("ID");
		userIDField.setBounds(30, 285, 150, 25);
		userIDField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		enterData.add (userIDField);

		//Create salary text field
		salaryField = new JTextField("Salary");
		salaryField.setBounds(30, 325, 150, 25);
		salaryField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		enterData.add (salaryField);

		//Create hoursWorkedField text field
		hoursWorkedField = new JTextField("Hours Worked");
		hoursWorkedField.setBounds(30, 45, 150, 25);
		hoursWorkedField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		enterData.add (hoursWorkedField);

		//Create hourlyPayField text field
		hourlyPayField = new JTextField("Hourly Pay");
		hourlyPayField.setBounds(30, 125, 150, 25);
		hourlyPayField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		enterData.add (hourlyPayField);

		//Create annualSalesField text field
		annualSalesField = new JTextField("Annual Sales");
		annualSalesField.setBounds(30, 205, 150, 25);
		annualSalesField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		enterData.add (annualSalesField);

		//Create annualBonusField text field
		annualBonusField = new JTextField("Annual Bonus");
		annualBonusField.setBounds(30, 205, 150, 25);
		annualBonusField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		enterData.add (annualBonusField);

		//Create performanceRatingField text field
		performanceRatingField = new JTextField("Performance Rating");
		performanceRatingField.setBounds(30, 125, 150, 25);
		performanceRatingField.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		enterData.add (performanceRatingField);

		//Create the username Text
		titleText = new TextPicture("Welcome " + getUsername(), 60, 60);
		titleText.setColor(Color.WHITE);
		titleText.setFont(new Font("Times New Roman", Font.BOLD, 50));
		titleText.setBounds(0, 0, 800, 300);
		titlePanel.add(titleText);


		//------------------------------------------------------> Create Buttons on Enter Data Panel
		submitInsertBtn= new JButton("Submit");

		submitInsertBtn.setBounds(30, 365, 130, 35);

		submitInsertBtn.setBackground(Color.CYAN);

		enterData.add(submitInsertBtn);

		submitDeleteBtn= new JButton("Submit");

		submitDeleteBtn.setBounds(30, 365, 130, 35);

		submitDeleteBtn.setBackground(Color.CYAN);

		enterData.add(submitDeleteBtn);

		submitChangeBtn= new JButton("Submit");

		submitChangeBtn.setBounds(30, 365, 130, 35);

		submitChangeBtn.setBackground(Color.CYAN);

		enterData.add(submitChangeBtn);

		nextChangeBtn= new JButton("Next");

		nextChangeBtn.setBounds(30, 365, 130, 35);

		nextChangeBtn.setBackground(Color.CYAN);

		enterData.add(nextChangeBtn);

		nextInsertBtn= new JButton("Next");

		nextInsertBtn.setBounds(30, 365, 130, 35);

		nextInsertBtn.setBackground(Color.CYAN);

		enterData.add(nextInsertBtn);

		submitFindBtn= new JButton("Find");

		submitFindBtn.setBounds(30, 365, 130, 35);

		submitFindBtn.setBackground(Color.CYAN);

		enterData.add(submitFindBtn);

		//---------------------------------------------------------->  Create Buttons for Side Panel

		switchAccountsBtn= new JButton("Switch Account");

		switchAccountsBtn.setBounds(35, 550, 130, 35);

		switchAccountsBtn.setBackground(Color.ORANGE);

		exitBtn= new JButton("Exit");

		exitBtn.setBounds(45, 600, 110, 35);

		exitBtn.setForeground(Color.WHITE);

		exitBtn.setBackground(Color.RED);

		//----------------------------------------------> Info Panel Buttons
		// InfoSideButtons
		insertBtn= new JButton("Insert");
		insertBtn.setBounds(15, 25, 90, 35);
		insertBtn.setBackground(Color.ORANGE);

		deleteBtn= new JButton("Delete");
		deleteBtn.setBounds(125, 25, 90, 35);
		deleteBtn.setBackground(Color.ORANGE);

		changeBtn= new JButton("Change");
		changeBtn.setBounds(235, 25, 90, 35);
		changeBtn.setBackground(Color.ORANGE);

		findBtn= new JButton("Find");
		findBtn.setBounds(345, 25, 90, 35);
		findBtn.setBackground(Color.ORANGE);

		sortBtn= new JButton("Sort");
		sortBtn.setBounds(455, 25, 90, 35);
		sortBtn.setBackground(Color.ORANGE);

		saveToFile= new JButton("Save");
		saveToFile.setBounds(565, 25, 90, 35);
		saveToFile.setBackground(Color.ORANGE);

		readFromFile= new JButton("Read");
		readFromFile.setBounds(675, 25, 90, 35);
		readFromFile.setBackground(Color.ORANGE);

		// Add buttons to the side panel
		sidePanel.add(switchAccountsBtn);
		sidePanel.add(exitBtn);


		// InfoSideButtons
		btnInfoPanel.add(insertBtn);
		btnInfoPanel.add(deleteBtn);
		btnInfoPanel.add(changeBtn);
		btnInfoPanel.add(findBtn);
		btnInfoPanel.add(sortBtn);
		btnInfoPanel.add(saveToFile);
		btnInfoPanel.add(readFromFile);

		// Add buttons as listeners
		switchAccountsBtn.addActionListener(this);
		insertBtn.addActionListener(this);
		deleteBtn.addActionListener(this);
		changeBtn.addActionListener(this);
		findBtn.addActionListener(this);
		sortBtn.addActionListener(this);
		exitBtn.addActionListener(this);
		saveToFile.addActionListener(this);
		readFromFile.addActionListener(this);
		submitInsertBtn.addActionListener(this);
		submitDeleteBtn.addActionListener(this);
		submitChangeBtn.addActionListener(this);
		nextChangeBtn.addActionListener(this);
		nextInsertBtn.addActionListener(this);
		submitFindBtn.addActionListener(this);


		// Research from: https://docs.oracle.com/javase/tutorial/uiswing/components/table.html and more

		// Create the table
		String[] columnNames = {"First Name","Last Name", "Address", "Phone#", "ID", "Type", "Hiring Date", "Annual Earnings"};

		Object[][] data = null;

		// Create a DefaultTableModel with the data and column names
		model = new DefaultTableModel(data, columnNames);

		// Create the JTable using the DefaultTableModel
		table = new JTable(model);

		// All from google but Tutorialspoint.com
		table.setEnabled(false);
		table.getTableHeader().setResizingAllowed(false);
		table.getTableHeader().setReorderingAllowed(false);

		// Create a scroll pane for the table
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 10, 750, 450); // set the bounds for the scroll pane	

		// Add the scroll pane to the info panel
		infoPanel.add(scrollPane);

		setResizable(false); // Cannot be resized
		setVisible(true); // Make  visible
		setDefaultCloseOperation(EXIT_ON_CLOSE); // Closes when x is clicked
	}

	/*
	 * Getters and Setters
	 */
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {

		this.username = username;

		titleText.setText("Welcome " + username);
	}

	public String[] getColumnNames() {
		return new String[]{"First Name", "Last Name", "Address", "Phone#", "ID", "Type", "Hiring Date", "Annual Earnings"};
	}

	/*
	 * Method to Delete Row when trying to delete an employee
	 */
	public void deleteRow(String ID, int rowCount) {

		// Vairiables
		String searchValue = ID;
		String infoFound="";

		int row=-1;

		// Loop through and find the designated Row
		for (int i=0; i < rowCount; i++ ) {

			infoFound = (String) model.getValueAt(i, 4);

			if (infoFound.equals(searchValue)) {
				row = i;
				break;
			}
		}// End of For Loop

		// if Row is found delete it
		if (row != -1) {
			model.removeRow(row);
		} else {
			JOptionPane.showMessageDialog(null, "Value not found in the table.");
		}
	}

	/*
	 * Method to shell Sort Table
	 */
	public void shellSortTable() {

		// Variables
		int n = model.getRowCount();
		int[] gaps = {701, 301, 132, 57, 23, 10, 4, 1};

		// Save Process as the normal Shell Sort but with rows and table
		for (int gap=0; gap<gaps.length; gap++) {

			for (int i = gap; i < n; i++) {
				String[] currentRow = new String[model.getColumnCount()];
				for (int j = 0; j < model.getColumnCount(); j++) {
					currentRow[j] = (String) model.getValueAt(i, j);
				}

				int j = i;
				while (j >= gap && compareRows(currentRow, j - gap) < 0) {
					for (int k = 0; k < model.getColumnCount(); k++) {
						model.setValueAt(model.getValueAt(j - gap, k), j, k);
					}
					j -= gap;
				}

				for (int k = 0; k < model.getColumnCount(); k++) {
					model.setValueAt(currentRow[k], j, k);
				}
			}
		}
	}

	/*
	 * Method to be used in the Table Shell To Compare 2 rows
	 */
	public int compareRows(String[] row1, int row2Index) {
		String id1 = row1[4]; // Assuming "ID" column is at index 4
		String id2 = (String) model.getValueAt(row2Index, 4);
		return id1.compareTo(id2);
	}

	/*
	 * Fix repeated Code
	 */
	public void insertFormat() {
		submitDeleteBtn.setVisible(false);
		submitInsertBtn.setVisible(false);
		submitChangeBtn.setVisible(false);
		nextInsertBtn.setVisible(true);
		nextChangeBtn.setVisible(false);
		firstNameField.setVisible(true);
		lastNameField.setVisible(true);
		addressField.setVisible(true);
		phoneField.setVisible(true);
		dateField.setVisible(true);
		typeField.setVisible(true);
		userIDField.setVisible(false);
		enterData.setVisible(true);
		hoursWorkedField.setVisible(false);
		hourlyPayField.setVisible(false);
		annualSalesField.setVisible(false);
		annualBonusField.setVisible(false);
		performanceRatingField.setVisible(false);
		salaryField.setVisible(false);
	}

	public void findFormat() {
		submitDeleteBtn.setVisible(false);
		submitInsertBtn.setVisible(false);
		submitChangeBtn.setVisible(false);
		submitFindBtn.setVisible(true);
		nextInsertBtn.setVisible(false);
		nextChangeBtn.setVisible(false);
		firstNameField.setVisible(true);
		lastNameField.setVisible(true);
		addressField.setVisible(true);
		phoneField.setVisible(true);
		dateField.setVisible(true);
		typeField.setVisible(true);
		userIDField.setVisible(true);
		enterData.setVisible(true);
		hoursWorkedField.setVisible(false);
		hourlyPayField.setVisible(false);
		annualSalesField.setVisible(false);
		annualBonusField.setVisible(false);
		performanceRatingField.setVisible(false);
		salaryField.setVisible(true);
	}

	public void deleteFormat() {

		enterData.setVisible(false);
		submitInsertBtn.setVisible(false);
		submitDeleteBtn.setVisible(true);
		firstNameField.setVisible(false);
		lastNameField.setVisible(false);
		addressField.setVisible(false);
		phoneField.setVisible(false);
		dateField.setVisible(false);
		typeField.setVisible(false);
		salaryField.setVisible(false);
		hoursWorkedField.setVisible(false);
		hourlyPayField.setVisible(false);
		annualBonusField.setVisible(false);
		performanceRatingField.setVisible(false);
		annualSalesField.setVisible(false);
		userIDField.setVisible(true);
		dataText.setText("Delete");
		enterData.setVisible(true);
	}

	public void changeFormat() {

		dataText.setText("Employee To Replace");
		dataText.setxPos(10);

		nextChangeBtn.setVisible(true);

		submitDeleteBtn.setVisible(false);
		submitInsertBtn.setVisible(false);
		submitChangeBtn.setVisible(false);
		firstNameField.setVisible(false);
		lastNameField.setVisible(false);
		addressField.setVisible(false);
		phoneField.setVisible(false);
		dateField.setVisible(false);
		typeField.setVisible(false);
		hoursWorkedField.setVisible(false);
		hourlyPayField.setVisible(false);
		annualSalesField.setVisible(false);
		annualBonusField.setVisible(false);
		performanceRatingField.setVisible(false);
		salaryField.setVisible(false);
		userIDField.setVisible(true);
		enterData.setVisible(true);
	}
	public void actionPerformed(ActionEvent e) {

		// If user wants to exit 
		if (e.getSource()==exitBtn) {
			System.exit(0);
		}

		// If user wants to Switch Accounts
		if (e.getSource()==switchAccountsBtn) {
			setVisible(false);
			new LoginScreen();	
		}

		// If user wants to insert show the side Panel 
		if (e.getSource()==insertBtn) {

			insertFormat();

		}

		// Once user presses next ask them for info dependant on their Input (Manager, Hourly, Sales)
		if (e.getSource() == nextInsertBtn) {
			submitDeleteBtn.setVisible(false);
			submitInsertBtn.setVisible(true);
			submitChangeBtn.setVisible(false);
			nextInsertBtn.setVisible(false);
			nextChangeBtn.setVisible(false);
			firstNameField.setVisible(false);
			lastNameField.setVisible(false);
			addressField.setVisible(false);
			phoneField.setVisible(false);
			dateField.setVisible(false);
			typeField.setVisible(false);
			userIDField.setVisible(false);
			salaryField.setVisible(true);

			if (typeField.getText().equalsIgnoreCase("H")) {
				hoursWorkedField.setVisible(true);
				hourlyPayField.setVisible(true);
				enterData.setVisible(true);
			}
			else if (typeField.getText().equalsIgnoreCase("S")) {
				annualSalesField.setVisible(true);
			}
			else if (typeField.getText().equalsIgnoreCase("M")) {
				annualBonusField.setVisible(true);
				performanceRatingField.setVisible(true);
			}
			else {
				JOptionPane.showMessageDialog(null, "Please enter the correct employee type.");
				insertFormat();
			}
		}

		// Once user submits enter it to the eList and also the Table
		if (e.getSource() == submitInsertBtn) {
			if (typeField.getText().equalsIgnoreCase("H")) {
				hourly.setAnnualSalary(Double.parseDouble(salaryField.getText()));
				hourly.setHourlyPay(Double.parseDouble(hourlyPayField.getText()));
				hourly.setHoursWorked(Integer.parseInt(hoursWorkedField.getText()));

				// Save into
				employeeData = firstNameField.getText() + "<" +  lastNameField.getText() + "<" + addressField.getText() + "<" + phoneField.getText() + "<" + dateField.getText() + "<" + typeField.getText();

				// Process Info
				employee.processRecord(employeeData);

				//Calculate sallary
				employee.calculateEarnings(hourly.calculateEarningsHourly());

				// Insert into the eList
				eList.insert(employee);

				// Add to the table
				model.addRow(employee.toString().split("<"));	

				// Reset objects
				hourly = new HourlyEmployee();
				employee = new Employee();

			}

			// Repeat but with Sales
			else if (typeField.getText().equalsIgnoreCase("S")) {
				sales.setAnnualSalary(Double.parseDouble(salaryField.getText()));
				sales.setAnnualSales(Double.parseDouble(annualSalesField.getText()));

				employeeData = firstNameField.getText() + "<" +  lastNameField.getText() + "<" + addressField.getText() + "<" + phoneField.getText() + "<" + dateField.getText() + "<" + typeField.getText();

				employee.processRecord(employeeData);

				employee.calculateEarnings(sales.calculateEarningsSales());

				eList.insert(employee);

				model.addRow(employee.toString().split("<"));	

				sales = new SalesEmployee();
				employee = new Employee();
			}
			// Repeat but with Manager
			else if (typeField.getText().equalsIgnoreCase("M")) {
				manager.setAnnualSalary(Double.parseDouble(salaryField.getText()));
				manager.setAnnualBonus(Double.parseDouble(annualBonusField.getText()));
				manager.setPerformanceRating(Double.parseDouble(performanceRatingField.getText()));

				employeeData = firstNameField.getText() + "<" +  lastNameField.getText() + "<" + addressField.getText() + "<" + phoneField.getText() + "<" + dateField.getText() + "<" + typeField.getText();

				employee.processRecord(employeeData);

				employee.calculateEarnings(manager.calculateEarningsManager());

				eList.insert(employee);

				model.addRow(employee.toString().split("<"));	

				manager = new Manager();
				employee = new Employee();
			}

			// Change panel to allow more inserts
			insertFormat();
		}

		// If user wants to delete format the panel
		if (e.getSource()==deleteBtn) {

			deleteFormat();
		}

		// If user wants to delete
		if (e.getSource() == submitDeleteBtn) {

			employee = new Employee();

			// Save user id to variable
			String employeeData = userIDField.getText();

			// Set employee id to user Input
			employee.setEmployeeID(employeeData);

			// linear search for row Number
			int rowNumber = eList.linearSearchID(employee);

			// If id doesn't match show error
			if (rowNumber == -1) {
				JOptionPane.showMessageDialog(null, "ID Not Found!");
			}

			// If matches delete the Employee from eList
			eList.delete(eList.geteList()[rowNumber]);

			// If matches delete the row
			deleteRow(employeeData, model.getRowCount());
		}

		// if user wants to change an employee
		if (e.getSource() == changeBtn) {

			// Format the panel
			changeFormat();
		}

		// Delete the old Employee
		if (e.getSource() == nextChangeBtn) {

			employee = new Employee();

			// Same as delete

			String employeeData = userIDField.getText();

			employee.setEmployeeID(employeeData);

			int rowNumber = eList.linearSearchID(employee);

			if (rowNumber == -1) {
				JOptionPane.showMessageDialog(null, "ID Not Found!");
			}

			eList.delete(eList.geteList()[rowNumber]);

			deleteRow(employeeData, model.getRowCount());


			// Format the Enter Data Panel
			dataText.setText("New Employee");
			dataText.setxPos(40);

			insertFormat();
			nextInsertBtn.setVisible(false);
			submitInsertBtn.setVisible(false);
			submitChangeBtn.setVisible(true);
			enterData.setVisible(true);
		}

		// Create new Employee
		if (e.getSource() == submitChangeBtn) {

			employee.setEmployeeID("");

			submitDeleteBtn.setVisible(false);
			submitInsertBtn.setVisible(true);
			submitChangeBtn.setVisible(false);
			nextInsertBtn.setVisible(false);
			nextChangeBtn.setVisible(false);
			firstNameField.setVisible(false);
			lastNameField.setVisible(false);
			addressField.setVisible(false);
			phoneField.setVisible(false);
			dateField.setVisible(false);
			typeField.setVisible(false);
			userIDField.setVisible(false);
			salaryField.setVisible(true);

			if (typeField.getText().equalsIgnoreCase("H")) {
				hoursWorkedField.setVisible(true);
				hourlyPayField.setVisible(true);
				enterData.setVisible(true);
			}
			else if (typeField.getText().equalsIgnoreCase("S")) {
				annualSalesField.setVisible(true);
			}
			else if (typeField.getText().equalsIgnoreCase("M")) {
				annualBonusField.setVisible(true);
				performanceRatingField.setVisible(true);
			}
			else {
				JOptionPane.showMessageDialog(null, "Please enter the correct employee type.");
				insertFormat();
			}

			if (typeField.getText().equalsIgnoreCase("H")) {
				hourly.setAnnualSalary(Double.parseDouble(salaryField.getText()));
				hourly.setHourlyPay(Double.parseDouble(hourlyPayField.getText()));
				hourly.setHoursWorked(Integer.parseInt(hoursWorkedField.getText()));

				// Save into
				employeeData = firstNameField.getText() + "<" +  lastNameField.getText() + "<" + addressField.getText() + "<" + phoneField.getText() + "<" + dateField.getText() + "<" + typeField.getText();

				// Process Info
				employee.processRecord(employeeData);

				//Calculate sallary
				employee.calculateEarnings(hourly.calculateEarningsHourly());

				// Insert into the eList
				eList.insert(employee);

				// Add to the table
				model.addRow(employee.toString().split("<"));	

				// Reset objects
				hourly = new HourlyEmployee();
				employee = new Employee();



				JOptionPane.showMessageDialog(null, eList.geteList());

			}

			// Repeat but with Sales
			else if (typeField.getText().equalsIgnoreCase("S")) {
				sales.setAnnualSalary(Double.parseDouble(salaryField.getText()));
				sales.setAnnualSales(Double.parseDouble(annualSalesField.getText()));

				employeeData = firstNameField.getText() + "<" +  lastNameField.getText() + "<" + addressField.getText() + "<" + phoneField.getText() + "<" + dateField.getText() + "<" + typeField.getText();

				employee.processRecord(employeeData);

				employee.calculateEarnings(sales.calculateEarningsSales());

				eList.insert(employee);

				model.addRow(employee.toString().split("<"));	

				sales = new SalesEmployee();
				employee = new Employee();
			}
			// Repeat but with Manager
			else if (typeField.getText().equalsIgnoreCase("M")) {
				manager.setAnnualSalary(Double.parseDouble(salaryField.getText()));
				manager.setAnnualBonus(Double.parseDouble(annualBonusField.getText()));
				manager.setPerformanceRating(Double.parseDouble(performanceRatingField.getText()));

				employeeData = firstNameField.getText() + "<" +  lastNameField.getText() + "<" + addressField.getText() + "<" + phoneField.getText() + "<" + dateField.getText() + "<" + typeField.getText();

				employee.processRecord(employeeData);

				employee.calculateEarnings(manager.calculateEarningsManager());

				eList.insert(employee);

				model.addRow(employee.toString().split("<"));	

				manager = new Manager();
				employee = new Employee();
			}

			// Change panel to allow more inserts
			insertFormat();
		}

		// If user wants to find employee
		if (e.getSource() == findBtn) {

			// Format side panel
			dataText.setText("Find Employee");
			dataText.setxPos(40);

			findFormat();
			
		}

		// If user submits to Find
		if (e.getSource() == submitFindBtn) {

			// Save info to a String
			String searchData = firstNameField.getText() + "<" +  lastNameField.getText() + "<" + addressField.getText() + "<" + phoneField.getText() + "<" + userIDField.getText() + "<"
					+  typeField.getText() + "<" + dateField.getText() + "<" + salaryField.getText();

			// Process Info
			Employee recordToFind = new Employee();
			recordToFind.processAllData(searchData);

			// Call and store the return value from the linearSearch method 
			int location= eList.linearSearchFull(recordToFind);

			// If found show message or else show Employee Not Found
			if (location>0) {
				JOptionPane.showMessageDialog(null, eList.geteList()[location]);
			}
			else {
				JOptionPane.showMessageDialog(null, "Employee Not Found");
			}
		}

		// If user wants to save to a file
		if (e.getSource() == saveToFile) {

			// Ask for File Name
			String fileName = JOptionPane.showInputDialog(null, "Enter File To Save To", "NewFile.txt");

			// Save info to file
			try {
				FileClass.saveInfo(fileName, eList.toString(), false);
				JOptionPane.showMessageDialog(null, "File Saved");
			} catch (IOException e3) {
				// Handle IOException
				e3.printStackTrace();
			} catch (Exception e2) {
				// Handle any other exception
				System.err.println("An error occurred: " + e2.getMessage());
				e2.printStackTrace();
			}
		}

		// If user wants to read from File
		if (e.getSource() == readFromFile) {

			// Variables
			String fileInfo[] = null;
			String fileName = "";

			// Prompt for fileName and save 
			try {

				// Prompt for fileName
				fileName = JOptionPane.showInputDialog(null, "Enter File To Read From", "SaveFile.txt");

				// Get File Info
				fileInfo = FileClass.fileInfo(fileName);

				// Get size of File
				int size = FileClass.sizeOfFile(fileName);

				// Save info to eList and also add to table
				for (int i = 0; i < size; i++) {
					model.addRow(fileInfo[i].split("<"));
					employee.processAllData(fileInfo[i]);
					eList.insert(employee);
					employee = new Employee();
				}

				// Catch any errors
			} catch (IOException e1) {
				// Handle IOException
				e1.printStackTrace();
			} catch (Exception e2) {
				// Handle any other exception
				System.err.println("An error occurred: " + e2.getMessage());
				e2.printStackTrace();
			}
		}

		// If user wants to sort
		if (e.getSource() == sortBtn) {
			// Sort the eList
			eList.shellSort();

			// Call method to sort the table
			shellSortTable();
		}
	}

	public static void main(String[] args) {

		new MainScreen();
	}

}
