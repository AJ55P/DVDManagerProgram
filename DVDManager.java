import java.util.*;

/**
 * 	Program to display and modify a simple DVD collection
 */

public class DVDManager {
	public static void main(String[] args) {
		DVDUserInterface dlInterface;
		DVDCollection dl = new DVDCollection();

		Scanner scan = new Scanner(System.in);

		System.out.println("Enter name of data file to load:");
		String filename = scan.nextLine();			
		dl.loadData(filename);

		System.out.println("\nInput interface type: C = Console");
		
		String interfaceType = scan.nextLine();
		
		if (interfaceType.equals("C")) {
			dlInterface = new DVDConsoleUI(dl);
			dlInterface.processCommands();
		} 
		
		else {
			System.out.println("Unrecognized interface type. Program exiting.");
			System.exit(0);
		}
		scan.close();
	}

}