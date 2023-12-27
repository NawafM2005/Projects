import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * 
 */

/**
 * @author Nawaf
 * Date:5/11/2023
 * Description: The Password Screen that reads from a file and checks if the user Password is correct and also lets user go to register screen if they please
 */
public class LoginScreen extends JFrame implements ActionListener {

	/*
	 * Private Variables
	 */
	private JButton loginBtn, exitBtn, registerBtn;
	private JPanel loginPanel, btnPanel;
	private JTextField usernameField;
	private TextPicture usernameText, passwordText, loginText;
	private JPasswordField passwordField;
	
	public LoginScreen() {

		// Create frame
		super("Login");
		setSize(400, 300);
		setLocationRelativeTo(null); // Default location to middle of screen
		setLayout(null); // No Layout
		
		// Create and place the login Panel
		loginPanel = new JPanel() {
			
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
		loginPanel.setBounds(0, 0, 400, 200);

		// Make the panel non-opaque
		loginPanel.setOpaque(false);

		// Set its layout to null
		loginPanel.setLayout(null);

		// Add to frame
		add(loginPanel);

		//Create the loginText Text
		loginText = new TextPicture("Login", 150, 45);
		loginText.setColor(Color.BLACK);
		loginText.setFont(new Font("Times New Roman", Font.ITALIC, 35));
		loginText.setBounds(0, 0, 400, 175);
		loginPanel.add(loginText);

		//Create the usernameText Text
		usernameText = new TextPicture("Username: ", 70, 100);
		usernameText.setColor(Color.RED);
		usernameText.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		usernameText.setBounds(0, 0, 400, 175);
		loginPanel.add(usernameText);

		//Create userName text field
		usernameField = new JTextField();
		usernameField.setBounds(170, 80, 150, 25);
		usernameField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		loginPanel.add (usernameField);

		//Create the password Text
		passwordText = new TextPicture("Password: ", 70, 160);
		passwordText.setColor(Color.RED);
		passwordText.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		passwordText.setBounds(0, 0, 400, 175);
		loginPanel.add(passwordText);

		//Create password text field
		passwordField = new JPasswordField();		
		passwordField.setBounds(170, 140, 150, 25);
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
		loginBtn= new JButton("Login");
		exitBtn= new JButton("Exit");
		registerBtn= new JButton("Register");
		
		// Set button Bounds
		loginBtn.setBounds(40, 18, 70, 30);
		registerBtn.setBounds(145, 18, 90, 30);
		exitBtn.setBounds(270, 18, 70, 30);

		loginBtn.setBackground(Color.ORANGE);
		exitBtn.setBackground(Color.MAGENTA);



		// Add buttons to the button panel
		btnPanel.add(loginBtn);
		btnPanel.add(exitBtn);	
		btnPanel.add(registerBtn);	

		// Add buttons as listeners
		loginBtn.addActionListener(this);
		exitBtn.addActionListener(this);
		registerBtn.addActionListener(this);

		setResizable(false); // Cannot be resized
		setVisible(true); // Make  visible
		setDefaultCloseOperation(EXIT_ON_CLOSE); // Closes when x is clicked

	}

	public void actionPerformed(ActionEvent e){

		// If user wants to exit exit the program
		if (e.getSource()== exitBtn) {
			System.exit(0);
		}

		// Open register screen
		if (e.getSource()== registerBtn) {

			setVisible(false);

			new RegisterScreen();	
		}

		// Allow user to login
		if (e.getSource() == loginBtn) {

			String fileInfo[] = null;

			// Read from the password File
			try {
				fileInfo=FileClass.fileInfo("PasswordFile.txt");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			// Check if Info Matches
			for (int i=0; i< fileInfo.length; i++) { // Loop through

				if ((usernameField.getText() + "/" + passwordField.getText()).equals(fileInfo[i])) {
					
					// If credentials correct let user enter main screen
					setVisible(false);

					MainScreen menu = new MainScreen();	
					
					menu.setUsername(usernameField.getText());
				}
			}
		}
	}

	public static void main(String[] args) {

		new LoginScreen();
	}

}
