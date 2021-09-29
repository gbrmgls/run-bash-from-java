import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException, InterruptedException {
		
		Scanner scanner = new Scanner(System.in);
	    System.out.println("Enter file to run: "); // Default: test.sh
	    String fileName = scanner.nextLine();
	    System.out.println("Enter parameters: ");
	    String fileParams = scanner.nextLine();
	    String commandText = fileName + " " + fileParams;
	    
		ProcessBuilder processBuilder = new ProcessBuilder();
		processBuilder.command("bash", "-c", "./" + commandText);
		
		// Run command
		Process process = processBuilder.start();
		
		// Create string manipulator
		StringBuilder output = new StringBuilder();

		// Inserts standard output in the buffer
		BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		
		// Reads each line from the buffer and appends to output string
		String line;
		while ((line = reader.readLine()) != null) {
			output.append(line + "\n");
		}
		
		// Waits for end of command execution and returns the output
		if (process.waitFor() == 0) {
			System.out.println("From code: Executed command: 'bash -c ./" + commandText + "'");
			System.out.println(output);
		}
	}
}
