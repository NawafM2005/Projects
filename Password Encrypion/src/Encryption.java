import java.io.IOException;
import javax.swing.JOptionPane;

/**
 * 
 */

/**
 * @author Nawaf
 *Date: 2/24/2023
 *Description: This class takes in a character and outputs if its a letter or not. It also encodes and decodes characters depending on the key given.
 *Methods: 	public static boolean isNotLetter (char character) 
 *	        public static char encode (char letter, int key )
 *			public static char decode (char letter, int key) 
 */
public class Encryption {

	/*
	 * Method to check letter and return true if it is a letter and false if character is not a letter
	 */
	public static boolean isNotLetter (char character) {

		boolean isLetter= false; // Create a boolean for isLetter and set to false for now

		if (Character.isLetter(character)) { // If the character sent is a letter set isLetter to false
			isLetter= false;
		}

		else { // If the character sent not a letter set isLetter to true
			isLetter=true;
		}

		return isLetter; // return true or false depending on if the character sent was a letter or not
	}

	/*
	 * Method to encrypt a letter using the key
	 */
	public static char encode (char letter, int key ) { // Take in letter and key

		char ascii, encoded=0; // Declare variables for ascii of letter and encoded letter

		if (Character.isLowerCase(letter)) { // if the character is lowercase

			ascii= (char) ((letter + key));  // Convert letter to ascii code and add the key

			if (ascii < 97) { // If ascii goes under 97 add 26 to keep it from a to z. Ex. if a is send adding 26 takes it to z.

				ascii= (char) (ascii + 26);
			}

			else if (ascii > 122) { // If ascii goes above 122 subtract 26 to keep it from a to z. Ex. if z is send subtracting 26 takes it to a.

				ascii= (char) (ascii - 26);
			}

			encoded = (char) ascii;// save the new letter to encoded variable

		}

		else { // If character send is upper case (Non letter r already filtered out in the UI)

			ascii= (char) ((letter + key)); // Convert letter to ascii code and add the key

			if (ascii < 65) { // If letter goes lower than 65 (A) 

				ascii= (char) (ascii + 26); // Add 26 to take it to Z
			}

			else if (ascii > 90) { //If letter goes above 90(Z)

				ascii= (char) (ascii - 26); // Subtract 26 to take it to A
			}

			encoded = (char) ascii; // Convert ascii to letter
		}

		return encoded; // Return the encoded letter
	}


	/*
	 * Method to decrypt a letter using the key
	 */
	public static char decode (char letter, int key) {

		char decoded=0; // Variable for decoding

		int newKey= -key; // Convert the key to -ve since we are going back to original phrase

		decoded= encode(letter, newKey); // Send to encode method since the steps are identical but with different key

		return decoded; // Return the new letter
	}


	public static void main(String[] args) throws IOException { // Test methods

		// Declare variables
		boolean isLetter; 
		char encrypted;
		char decrypted;

		//Check scenarios that could break the code
		isLetter= isNotLetter('a');

		System.out.println("Is Character a letter: " + isLetter);

		isLetter= isNotLetter(' '); // Blank space

		System.out.println("Is Character a letter: " + isLetter);

		encrypted= encode('a', 3);

		decrypted= decode('a', -3);

		System.out.println("The Character encoded is: " + encrypted + "\nThe Character encoded is: " + decrypted);

		encrypted= encode('z', 3);

		decrypted= decode('z', -3);

		System.out.println("The Character encoded is: " + encrypted + "\nThe Character encoded is: " + decrypted);
		

		// Create a mini program to make sure the class does what its supposed to using switch case learned in class
		int choice;

		do {
			String input = JOptionPane.showInputDialog("Choose an option:\n1. Check if a character is a letter\n2. Encode a letter\n3. Decode a letter\n4. Exit"); //Prompt user
			choice = Integer.parseInt(input); // Save user input to choice
			
			switch (choice) { // Use switch case and cases to display results to every choice 
			case 1:
				String characterInput = JOptionPane.showInputDialog("Enter a character:");
				char c = characterInput.charAt(0);
				isLetter = isNotLetter(c);
				JOptionPane.showMessageDialog(null, "Is Character a letter: " + isLetter);
				break;
			case 2:
				String letterToEncodeInput = JOptionPane.showInputDialog("Enter a character to encode:");
				char letterToEncode = letterToEncodeInput.charAt(0);
				String keyToEncodeInput = JOptionPane.showInputDialog("Enter a key:");
				int keyToEncode = Integer.parseInt(keyToEncodeInput);
				char encoded = encode(letterToEncode, keyToEncode);
				JOptionPane.showMessageDialog(null, "The Character encoded is: " + encoded);
				break;
			case 3:
				String letterToDecodeInput = JOptionPane.showInputDialog("Enter a character to decode:");
				char letterToDecode = letterToDecodeInput.charAt(0);
				String keyToDecodeInput = JOptionPane.showInputDialog("Enter a key:");
				int keyToDecode = Integer.parseInt(keyToDecodeInput);
				char decoded = decode(letterToDecode, keyToDecode);
				JOptionPane.showMessageDialog(null, "The Character decoded is: " + decoded);
				break;
			case 4:
				JOptionPane.showMessageDialog(null, "Exiting...");
				break;
			default:
				JOptionPane.showMessageDialog(null, "Invalid choice, please try again.");
				break;
			}
		} while (choice != 4);
	}
}

