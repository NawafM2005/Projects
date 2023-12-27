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
 * Description: Show User their registration is complete
 */
public class RegistrationComplete extends JFrame implements ActionListener {

	/*
	 * Private Variables
	 */
	private JButton BackBtn, exitBtn;
	private JPanel loginPanel, btnPanel;
	private TextPicture registrationComplete, registrationComplete2;

	public RegistrationComplete() {

		// Create frame
		super("Registration Complete");
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

		//Create the registration Complete Text
		registrationComplete = new TextPicture("Registration Complete...", 50, 70);
		registrationComplete.setColor(Color.WHITE);
		registrationComplete.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		registrationComplete.setBounds(0, 0, 400, 175);
		loginPanel.add(registrationComplete);

		//Create the registration Complete Text
		registrationComplete2 = new TextPicture("Press Back & Login!", 50, 150);
		registrationComplete2.setColor(Color.WHITE);
		registrationComplete2.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		registrationComplete2.setBounds(0, 0, 400, 175);
		loginPanel.add(registrationComplete2);


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

		BackBtn.setBounds(40, 18, 70, 30);

		exitBtn.setBounds(270, 18, 70, 30);

		BackBtn.setBackground(Color.ORANGE);

		exitBtn.setBackground(Color.MAGENTA);



		// Add buttons to the button panel
		btnPanel.add(BackBtn);
		btnPanel.add(exitBtn);	

		// Add buttons as listeners
		BackBtn.addActionListener(this);
		exitBtn.addActionListener(this);

		setResizable(false); // Cannot be resized
		setVisible(true); // Make  visible
		setDefaultCloseOperation(EXIT_ON_CLOSE); // Closes when x is clicked

	}

	public void actionPerformed(ActionEvent e) {

		// Exit if user wants
		if (e.getSource()== exitBtn) {
			System.exit(0);
		}

		// Send user back to the login screen
		if (e.getSource()== BackBtn) {

			setVisible(false);

			new LoginScreen();	
		}

	}

	public static void main(String[] args) {

		new RegistrationComplete();

	}
}
