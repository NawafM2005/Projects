import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * 
 */

/**
 * @author Nawaf
 * Date: May 12 2023
 * Description: User can register a new username and password
 */
public class RegisterScreen extends JFrame implements ActionListener {

	/*
	 * Private Variables
	 */
	private JButton BackBtn, exitBtn, registerBtn;
	private JPanel loginPanel, btnPanel;
	private JTextField usernameField, passwordField;
	private TextPicture usernameText, passwordText, RegistrationText;

	public RegisterScreen() {

		// Create frame
		super("Register");
		setSize(400, 300);
		setLocationRelativeTo(null); // Default location to middle of screen
		setLayout(null);

		//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Create and place the Login Panel
		loginPanel= new JPanel();

		//Set Bounds
		loginPanel.setBounds(0, 0, 400, 200);

		//Set Color
		loginPanel.setBackground(new Color(0x0C1D3C));

		// Set its layout to null
		loginPanel.setLayout(null);

		// Add to frame
		add(loginPanel);

		//Create the username Text
		RegistrationText = new TextPicture("Register", 150, 45);
		RegistrationText.setColor(Color.WHITE);
		RegistrationText.setFont(new Font("Times New Roman", Font.ITALIC, 35));
		RegistrationText.setBounds(0, 0, 400, 175);
		loginPanel.add(RegistrationText);

		//Create the username Text
		usernameText = new TextPicture("New Username: ", 40, 100);
		usernameText.setColor(Color.RED);
		usernameText.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		usernameText.setBounds(0, 0, 400, 175);
		loginPanel.add(usernameText);

		//Create userName text field
		usernameField = new JTextField();
		usernameField.setBounds(180, 80, 150, 25);
		usernameField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		loginPanel.add (usernameField);

		//Create the password Text
		passwordText = new TextPicture("New Password: ", 40, 160);
		passwordText.setColor(Color.RED);
		passwordText.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		passwordText.setBounds(0, 0, 400, 175);
		loginPanel.add(passwordText);

		//Create password text field
		passwordField = new JTextField();
		passwordField.setBounds(180, 140, 150, 25);
		passwordField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		loginPanel.add (passwordField);

		//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Create and place the Button Panel
		btnPanel= new JPanel();

		// Place it
		btnPanel.setBounds(0, 200, 400, 100);
		// leave the layout as the default FlowLayout

		//Set Color
		btnPanel.setBackground(Color.BLACK);

		// Set its layout to null
		btnPanel.setLayout(null);

		// Add to Frame
		add(btnPanel);


		// Create Buttons
		BackBtn= new JButton("Back");
		exitBtn= new JButton("Exit");
		registerBtn= new JButton("Register");

		// Set Button Bounds
		BackBtn.setBounds(40, 18, 70, 30);

		registerBtn.setBounds(145, 18, 90, 30);

		exitBtn.setBounds(270, 18, 70, 30);

		BackBtn.setBackground(Color.ORANGE);

		exitBtn.setBackground(Color.MAGENTA);



		// Add buttons to the button panel
		btnPanel.add(BackBtn);
		btnPanel.add(exitBtn);	
		btnPanel.add(registerBtn);	

		// Add buttons as listeners
		BackBtn.addActionListener(this);
		exitBtn.addActionListener(this);
		registerBtn.addActionListener(this);

		setResizable(false); // Cannot be resized
		setVisible(true); // Make  visible
		setDefaultCloseOperation(EXIT_ON_CLOSE); // Closes when x is clicked

	}

	public void actionPerformed(ActionEvent e) {

		// Exit if user wants to leave
		if (e.getSource()== exitBtn) {
			System.exit(0);
		}

		// Go Back to Login Screen if user wants
		if (e.getSource()== BackBtn) {

			setVisible(false);

			new LoginScreen();	
		}

		// If user wants to register
		if (e.getSource()==registerBtn) {

			// Save into to the password file
			
			String newInfo= usernameField.getText() + "/" + passwordField.getText();

			if (usernameField.getText().equals("") || (passwordField.getText().equals(""))) {

				setVisible(false);

				new RegisterScreen();	
			}

			else {

				try {
					FileClass.saveInfo("PasswordFile.txt", newInfo, true);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				// Show Registration Complete Screen
				setVisible(false);

				new RegistrationComplete();
			}
		}
	}

	public static void main(String[] args) {

		new RegisterScreen();

	}
}
