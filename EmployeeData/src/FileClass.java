import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

/**
 * 
 */

/**
 * @author Nawaf
 * Date: May 12 2023
 * Description: Class to save and read from file
 *
 */
public class FileClass {

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

	public static String[] fileInfo(String fileName) throws IOException { // Method to read information from the pre existing file

		FileReader file = new FileReader (fileName); // Open file to read from
		BufferedReader input= new BufferedReader (file);

		// Read the size of file
		int size= sizeOfFile(fileName);

		// Create a string for file information
		String fileInfo[]= new String [size];

		for (int i=0; i<size; i ++) { // Loop through and save info
			fileInfo[i]=input.readLine();
		}

		// Close file stream
		input.close();

		return fileInfo; // Return the information from file as string
	}

	public static FileWriter saveInfo(String nameOfFile, String info, boolean append) throws IOException{ // Method to save user key and file info to new fixed textfile

		// Open file to write
		FileWriter fileK = new FileWriter(nameOfFile, append);
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

	public static void main(String[] args) throws IOException {

		String fileInfo[];

		fileInfo=fileInfo("PasswordFile.txt");
		
		for (int i=0; i<fileInfo.length; i++) { // Loop through

			System.out.println(fileInfo[i]);
		}

		saveInfo("PasswordFile.txt", "Campos/123", true);

		int size = sizeOfFile("PasswordFile.txt");

		System.out.println(size);
	}
}
