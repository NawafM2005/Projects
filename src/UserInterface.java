import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * @author Minhaj 
 * Nawaf Helped
 * Date:3/01/2023
 * Description: The UI method that not only displays work from all classes but also formats user key, puts the key in range, finds key, checks key, formats text to be put into a file
 *Method List:	public static String formatUserkey (int key) 
 *				public static int putkeyInRange (int key)
 *				public static int getKey(String fileInfo) throws IOException
 *				public static boolean checkkey (int finalkey)
 *				public static String formatNewFileInfo (String text, int key) 
 *				public static JTextArea createTextArea(String display)
 *				publix static JPanel createKeyPanel() {
 */
public class UserInterface {

	/*
	 * Method to format the key user enters to a string that can be printed into the new file created with the fixed name "LiveModeFile.txt"
	 */
	public static String formatUserkey (int key) {

		String formattedkey=""; // Declare variable for the formatted key
		char PosOrNeg= '+';

		if (key < 0) { // If user entered a negative key

			PosOrNeg = '-';
			key = -key; // Convert to positive for padding

			// Pad the input string with leading zeros up to length 10 since the - sign takes up 1 character
			formattedkey = String.format("%010d", key);

		} 

		else { // If user entered a positive key

			// Pad the input string with leading zeros up to length 10
			formattedkey = String.format("%010d", key);
		}

		formattedkey = PosOrNeg + formattedkey; // Add the Positive or Negative sign

		return formattedkey; // Return the formatted key
	}

	public static int putkeyInRange (int key) { // Method to put the key in range of 0 to 26

		int encryptedkey = key % 26; // Calculate

		if (encryptedkey == 0) {

			if (key < 0) { // If the resulting key is negative
				encryptedkey = -26; // Subtract 26 
			} 
			else { // If resulting key is positive
				encryptedkey = 26; // Add 26
			}
		}

		return encryptedkey; // Return the new in range key
	}

	public static int getKey(String fileInfo) throws IOException { // Method to find key from text file

		// Declare variables
		int key=0;
		String initialKey ="";

		initialKey = fileInfo.substring(1,11); // Make a substring from 2nd letter to 12th

		key= Integer.parseInt(initialKey); // Turn into an integer removing extra 0s

		// find the sign
		if (fileInfo.charAt(0) == '-') { // If sign is negative

			key=key*-1; // if sign is -ve multiply key by -1/ Or else keep it the same (positive)
		}


		return key; // Return the key from file as int
	}

	public static boolean checkkey (int finalkey) { // Check if key is between the range of -2 Billion to + 2 Billion

		boolean validRange = false; // Declare valid range variable

		if (finalkey < -2000000000 || finalkey > +2000000000) { // If key is out of range return false
			validRange= false;
		}

		else { // If inrange return true

			validRange = true;
		}

		return validRange; // Return either true or false
	}

	public static String formatNewFileInfo (String text, int key) { // Method to format user key before it is send to be printed to a file

		String fullFileInfo=""; // Declare variable for all of the info formatted

		String newKey=formatUserkey(key); // Send key to be formatted from another method

		fullFileInfo= newKey + text; // Save new key with text

		return fullFileInfo; // Return the newly formatted info

	}

	public static JTextArea createTextArea(String display) { // Method to format Output

		JTextArea textArea = new JTextArea(display);
		textArea.setOpaque(false);
		textArea.setForeground(Color.BLUE);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setPreferredSize(new Dimension(600, 150));
		Font font = new Font("Times New Roman", Font.ROMAN_BASELINE, 18);
		textArea.setForeground(Color.BLACK);
		textArea.setFont(font);
		return textArea; // Return formatted
	}

	// Method to create button that when clicked generates a random key 
	public static JPanel createKeyPanel() {
		
	    JPanel panel = new JPanel(new GridLayout(5, 3)); // Create JPanel Grid
	    JLabel keyLabel = new JLabel("Enter Key"); // Prompt User
	    JTextField keyTextField = new JTextField(); // Create text field for key
	    JButton generateKeyButton = new JButton("Generate Key"); // Create a button that says "Generate Key"
	    generateKeyButton.addActionListener(new ActionListener() { // Add action listener to know when pressed / clicked
	    	
	        public void actionPerformed(ActionEvent e) { // When button is pressed
	            int min = -2000000000; // Min value generated
	            int max = +2000000000; // Max value generated
	            int generatedKey = (int) (Math.random() * (max - min + 1)); // Genenate value using math.random
	            
	            while (generatedKey % 26 == 0) { // Make sure the generated key is not divisible by 26 or is 0
	                generatedKey = (int) (Math.random() * (max - min + 1));
	            }
	            
	            keyTextField.setText(String.valueOf(generatedKey)); // Set the input box to value of key generated
	        }
	    });
	    panel.add(keyLabel); // Create panel to display
	    panel.add(keyTextField); // Add Prompt
	    panel.add(new JLabel());
	    panel.add(generateKeyButton); // Add button
	    return panel; // Return panel
	    
	    /*
	     * Research from these and other videos and stuff
	     * https://stackoverflow.com/questions/32983503/how-to-display-a-button-and-also-display-a-random-number-when-a-user-pressses-th
	     * https://javapointers.com/java/java-se/the-jbutton/
	     * https://www.educative.io/answers/how-to-use-the-mathrandom-method-in-java
	     */
	}

	public static void main(String[] args) throws IOException {

		// Declare variables
		String[] fileInfo = null;
		int key = 0, inRange = 0;
		String phrase="",encrypted="", decrypted="", text="", text2="",
				text3="",fileName="", display="", formatted="", inputPhrase="";
		boolean validRange;


		try { // Try catch to catch any errors

			String options[] = {"File", "Live"}; // Create options to ask user

			// Research from https://mkyong.com/swing/java-swing-joptionpane-showoptiondialog-example/
			int liveOrFile=JOptionPane.showOptionDialog(null, "File Mode or Live Mode", "Select Option", 
					JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]); // Ask user to select to Use Live Mode or File Mode

			if (liveOrFile== 0) { // If user picks File Mode

				fileName=JOptionPane.showInputDialog(null, "Please enter file name", "Phrases.txt"); // Prompt for file name

				String fileModeOptions[] = {"Encode", "Decode"}; // Create options to ask user

				// Research from https://mkyong.com/swing/java-swing-joptionpane-showoptiondialog-example/
				int encodeOrDecode=JOptionPane.showOptionDialog(null, "Encode File or Decode File", "Select Option", 
						JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, fileModeOptions, fileModeOptions[0]); // Ask to encode the file or decode it

				if (encodeOrDecode==0) { // If user wants to encode

					fileInfo=FileAccess.fileInfo(fileName); // Get information from file

					for (int i=0; i<fileInfo.length; i++) { // for loop

						key=getKey(fileInfo[i]); // get key for [i] line

						phrase= fileInfo[i].substring(11,fileInfo[i].length()); // get phrase by using a substring

						validRange=UserInterface.checkkey(key); // Check if key is in valid range

						if (validRange == false) { // If key is not display error message and exit program
							JOptionPane.showMessageDialog(null, "Please make sure key is in valid range (-2Billion <--> +2Billion");
							System.exit(0);
						}

						else {

							inRange = UserInterface.putkeyInRange(key); // Since key is between a valid range send to be  put in range to be encoded

							if (inRange == 0 || inRange == 26) { // If the key in range is 0 or 26 display error since the output would be the same
								JOptionPane.showMessageDialog(null, "key cannot be 0 or 26. Please Change in Text File!");
								System.exit(0);
							}

							char[] encryptedArray = new char[phrase.length()]; // create an array to store encrypted characters

							for (int j = 0; j < phrase.length(); j++) { // For loop to encode

								boolean isLetter = Encryption.isNotLetter(phrase.charAt(j)); // Call isNotLetter method
								char letter; // Declare char for letter

								if (isLetter == true) { // If character is not a letter keep it the same do not encode
									letter = phrase.charAt(j);
									encryptedArray[j] = letter; // Save to array
								} 

								else { // If character is a letter encode using encode method from encryption class
									char encoded = Encryption.encode(phrase.charAt(j), inRange);
									encryptedArray[j] = encoded; // Save to array
								}
							}

							encrypted = new String(encryptedArray); // convert the encrypted array back to a string

							String newKey=formatUserkey(key); // Format user key and save to newKey String

							formatted= newKey + encrypted; // Format to have key followed by encrypted phrase

							text3 = (text3.isEmpty()? "" : text3 + "\n") + formatted; // To make sure there are no unnessecerry blank lines 	
						} 

						text= text + encrypted.concat("\n"); // Save encrypted phrase to text variable
						encrypted=""; // Make sure encrypted is black to be repeated	

					}

					display="The Encoded Phrases are:\n" + text; // Save output to display

					JTextArea textArea = createTextArea(display); // JText to format

					JOptionPane.showMessageDialog(null, textArea, "Message", JOptionPane.PLAIN_MESSAGE); // Display the text

					String userFileName= JOptionPane.showInputDialog(null, "Enter File Name to save encrypted phrases to", "EncryptedPhrases.txt"); // Prompt user for encrypted fileName

					FileAccess.fileSaver(userFileName, text3, fileInfo.length); // Send information and name to fileSaver method to create file

					JOptionPane.showMessageDialog(null, "The file has been saved"); // Display that the file has been saved

				}
				
				if (encodeOrDecode == 1) { // If user picks decode the file

					fileInfo=FileAccess.fileInfo(fileName); // call fileInfo method to get the information

					for (int i=0; i<fileInfo.length; i++) { // For loop

						key=getKey(fileInfo[i]); // get key

						phrase= fileInfo[i].substring(11,fileInfo[i].length()); // get phrase using substring

						validRange=UserInterface.checkkey(key); // Check if key is in valid range

						if (validRange == false) { // If key is not display error message and exit program
							JOptionPane.showMessageDialog(null, "Please make sure key is in valid range (-2Billion <--> +2Billion");
							System.exit(0);
						}

						inRange = UserInterface.putkeyInRange(key); // Make sure key is in range

						if (inRange == 0 || inRange == 26) { // If key is 0 or 26 error since the phrase would stay the same
							JOptionPane.showMessageDialog(null, "key cannot be 0 or 26. Please Change in Text File!");
							System.exit(0);
						}

						char[] decryptedArray = new char[phrase.length()]; // create an array to store decrypted characters

						for (int j = 0; j < phrase.length(); j++) { // For loop to decode

							boolean isLetter = Encryption.isNotLetter(phrase.charAt(j)); // send character to isNotALetter method
							char letter; // Variable for letter

							if (isLetter == true) { // If character is not a letter
								letter = phrase.charAt(j);
								decryptedArray[j] = letter; // Save as it is no decoding
							} 

							else { // If character is a letter
								char decoded = Encryption.decode(phrase.charAt(j), inRange); // Decode using decode method from another class
								decryptedArray[j] = decoded; // Save to array
							}

						}

						decrypted = new String(decryptedArray); // convert the decrypted array back to a string

						text2= text2 + decrypted.concat("\n"); // Save decrypted phrase to text2 variable to be displayed
						decrypted=""; //Reset decrypted to black to repeat
					}

					display = "The Decoded Phrases are:\n" + text2; // Save message to display

					JTextArea textArea = createTextArea(display); // Format using JText area

					JOptionPane.showMessageDialog(null, textArea, "Message", JOptionPane.PLAIN_MESSAGE); // Display the text
				}
			}

			if (liveOrFile == 1) { // Is user chooses live mode

				String option[] = {"Encode", "Decode"}; // Options to ask user

				// Research from https://mkyong.com/swing/java-swing-joptionpane-showoptiondialog-example/
				int encodeOrDecode=JOptionPane.showOptionDialog(null, "Would you like to Encode or Decode", "Select Option", // Ask user if they want to encode or decode
						JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, option, option[0]);

				if (encodeOrDecode == 0) { //If user wants to encode

					inputPhrase= JOptionPane.showInputDialog(null, "Please enter phrase to encode"); // Prompt user for a phrase

					// Create a panel with a label, a text field, and a button to generate a random key
					JPanel keyPanel = createKeyPanel();
					JTextField keyTextField = (JTextField) keyPanel.getComponent(1);

					// Show the panel in a dialog box and wait for the user to enter a key or generate one
					int keyOption = JOptionPane.showConfirmDialog(null, keyPanel, "Enter encode key or generate one", JOptionPane.OK_CANCEL_OPTION);

					if (keyOption == JOptionPane.OK_OPTION) { // When user enters okay to proceed
						try { // Check if key is an integer
							key = Integer.parseInt(keyTextField.getText());
						} catch (NumberFormatException ex) {
							JOptionPane.showMessageDialog(null, "Invalid input! Please enter a valid key.");
							System.exit(0);
						}
					}
					
					validRange=UserInterface.checkkey(key); // Check if key is in valid range

					if (validRange == false) { // If not display error message
						JOptionPane.showMessageDialog(null, "Please make sure key is in valid range (-2Billion <--> +2Billion");
						System.exit(0);
					}

					else {

						inRange = UserInterface.putkeyInRange(key); // Put key in range from 0-26

						if (inRange == 0 || inRange == 26) { // If key is 0 or 26 error since phrase wont change
							JOptionPane.showMessageDialog(null, "key cannot be 0 or 26.");
							System.exit(0);
						}

						for (int i = 0; i < inputPhrase.length(); i++) { // for loop to encode

							// Check if character is a letter or not
							boolean isLetter = Encryption.isNotLetter(inputPhrase.charAt(i));
							char letter;

							if (isLetter == true) { // If not a letter keep same
								letter = inputPhrase.charAt(i);
								encrypted= encrypted + letter;
							} 

							else { // If letter encode
								char encoded = Encryption.encode(inputPhrase.charAt(i), inRange);
								encrypted = encrypted + Character.toString(encoded);
							}
						}
					}		

					display = "The encoded phrase is:\n" + encrypted; // Save message to display

					JTextArea textArea = createTextArea(display); // Format display

					JOptionPane.showMessageDialog(null, textArea, "Message", JOptionPane.PLAIN_MESSAGE); // Display the text

					String optionsForCopying[] = {"Copy", "No"}; // Create options to ask user

					// Research from https://mkyong.com/swing/java-swing-joptionpane-showoptiondialog-example/
					int copyOrNot=JOptionPane.showOptionDialog(null, "Would you like to Copy the Encoded Phrase and Key", "Select Option", 
							JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, optionsForCopying, optionsForCopying[0]); //Ask user if they want to copy their message

					// Research from https://mkyong.com/swing/java-swing-joptionpane-showoptiondialog-example/
					if (copyOrNot == 0) { // If user wants to copy
						String copy= (encrypted+ "___" + key);
						StringSelection stringSelection = new StringSelection(copy);
						Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
						clipboard.setContents(stringSelection, null);
						JOptionPane.showMessageDialog(null, "The Encoded message has been copied to your clipboard"); // Encoded message has been copied
					}
				}

				if (encodeOrDecode == 1) { // If user wants to decode

					inputPhrase= JOptionPane.showInputDialog(null, "Please enter phrase to decode");// Prompt for phrase

					// Create a panel with a label, a text field, and a button to generate a random key
					JPanel keyPanel = createKeyPanel();
					JTextField keyTextField = (JTextField) keyPanel.getComponent(1);

					// Show the panel in a dialog box and wait for the user to enter a key or generate one
					int keyOption = JOptionPane.showConfirmDialog(null, keyPanel, "Enter decode key or generate one", JOptionPane.OK_CANCEL_OPTION);

					if (keyOption == JOptionPane.OK_OPTION) { // When user enters okay to proceed
						try { // Check if key is an integer
							key = Integer.parseInt(keyTextField.getText());
						} catch (NumberFormatException ex) {
							JOptionPane.showMessageDialog(null, "Invalid input! Please enter a valid key.");
							System.exit(0);
						}
					}

					validRange=UserInterface.checkkey(key); // Check if key is in range

					if (validRange == false) { // If key is not in range display error
						JOptionPane.showMessageDialog(null, "Please make sure key is in valid range (-2Billion <--> +2Billion");
						System.exit(0);
					}

					else {

						inRange = UserInterface.putkeyInRange(key); // Put the user key in range 

						if (inRange == 0 || inRange == 26) { // If key is 0 or 26 error since phrase will stay the same
							JOptionPane.showMessageDialog(null, "key cannot be 0 or 26.");
							System.exit(0);
						}

						for (int i = 0; i < inputPhrase.length(); i++) { // For loop to decode

							//Call isNotLetter method
							boolean isLetter = Encryption.isNotLetter(inputPhrase.charAt(i));
							char letter;

							if (isLetter == true) { // If character is not a letter keep the same
								letter = inputPhrase.charAt(i);
								decrypted= decrypted + letter;
							} 

							else { // If character is a letter decode
								char decoded = Encryption.decode(inputPhrase.charAt(i), inRange);
								decrypted = decrypted + Character.toString(decoded);
							}
						}
					}		

					display = "The encoded phrase is:\n" + decrypted; // Save message to display

					JTextArea textArea = createTextArea(display); // Jtext area to format

					JOptionPane.showMessageDialog(null, textArea, "Message", JOptionPane.PLAIN_MESSAGE); // Display the text

					String optionsForCopying2[] = {"Copy", "No"}; // Create options to ask user

					// Research from https://mkyong.com/swing/java-swing-joptionpane-showoptiondialog-example/
					int copyOrNot=JOptionPane.showOptionDialog(null, "Would you like to Copy the Decoded Phrase and Key", "Select Option", 
							JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, optionsForCopying2, optionsForCopying2[0]); // Ask user to select to Use Live Mode or Normal Mode

					if (copyOrNot == 0) { // If user wants to copy
						String copy= (decrypted + "___" + key);
						StringSelection stringSelection = new StringSelection(copy);
						Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
						clipboard.setContents(stringSelection, null);
						JOptionPane.showMessageDialog(null, "The decode message has been copied to your clipboard"); // Tell user phrase has been copied
					}
				}
			}

		} // Catch any unexpected errors
		catch (FileNotFoundException error) {
			JOptionPane.showMessageDialog(null, fileName + " File Not Found");
		}
		catch (NumberFormatException error) {
			JOptionPane.showMessageDialog(null, fileName + " is corrupted");
		}
		catch (NullPointerException error) {
			JOptionPane.showMessageDialog(null, "Error in the File");
		}
		catch (Exception error) {
			JOptionPane.showMessageDialog(null, "OOPSIE DAISY SOMETHING WENT WRONG!!!");
		}
	}
}
