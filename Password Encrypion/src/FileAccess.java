import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
/**
 * 
 */

/**
 * @author Nawaf
 * Minhaaj Helped
 * (Angad didn't do his part)
 * Date: 3/01/2023
 * Description: A Loader class that loads information from the file and also saves user key and phrase to a text file
 * Method List:	public static String fileInfo(String fileName, int wPhrase) throws IOException 
 * 	            public static FileWriter fileSaver(String nameOfFile, String fileInfo, String key) throws IOException 


 *
 */
public class FileAccess {


	public static String[] fileInfo(String fileName) throws IOException { // Method to read information from the pre existing file

		FileReader file = new FileReader (fileName); // Open file to read from
		BufferedReader input= new BufferedReader (file);

		// Read the size of file
		int size= Integer.parseInt(input.readLine());

		// Create a string for file information
		String fileInfo[]= new String [size];

		for (int i=0; i<size; i ++) { // Loop through and save info
			fileInfo[i]=input.readLine();
		}

		// Close file stream
		input.close();

		return fileInfo; // Return the information from file as string
	}

	public static FileWriter fileSaver(String nameOfFile, String info, int length) throws IOException{ // Method to save user key and file info to new fixed textfile

		File file = new File(nameOfFile); // Create a new file with fixed name

		// Open file to write
		FileWriter fileK = new FileWriter(file);
		PrintWriter inputSave = new PrintWriter(fileK);

		inputSave.println(length); // Save 1 on first line since the user is only adding 1 phrase
		inputSave.print(info); // Save the key and phrase to 2nd line

		// Close the file
		fileK.close();

		return fileK; // Return the create file
	}

	public static void main(String[] args) throws IOException {

		// Declare String to display information from file
		String  info="";
		String fileInfo[];

		// Call loader method
		fileInfo=fileInfo("Phrases.txt");

		for (int i=0; i< fileInfo.length; i++) { // Loop through

			info= info + fileInfo[i].concat("\n");
		}
		
		System.out.println(info); // Print the information from file

		FileWriter fileSave = fileSaver("TestFileMaker.txt", "+0000000004The cook worked 12 hours in the darkened kitchen!", 1); //Test saving file

	}

}
