import java.util.*;


public class DVDConsoleUI implements DVDUserInterface {
	
	private DVDCollection dvdlist;
	private Scanner scan;
	
	public DVDConsoleUI(DVDCollection dl) {
		dvdlist = dl;
		scan = new Scanner(System.in);
	}
	
	public void processCommands()
	{
		 String[] commands = {"Add/Modify DVD",
	 			  "Remove DVD",
	 			  "Get DVDs By Rating",
	 			  "Get Total Running Time",
	 			  "Save and Exit"};

		 int choice;
		 
		 do {
			 for (int i = 0; i < commands.length; i++) {
				 System.out.println("Select " + i + ": " + commands[i]);
			 }
			 try {
				 choice = scan.nextInt();
				 scan.nextLine();
				 
				 switch (choice) {
				 
				 	case 0: doAddOrModifyDVD(); break;
				 	case 1: doRemoveDVD(); break;
				 	case 2: doGetDVDsByRating(); break;
				 	case 3: doGetTotalRunningTime(); break;
				 	case 4: doSave(); break;
				 	default:  System.out.println("\nINVALID CHOICE - TRY AGAIN");
				 	
				 }
				 
			 } catch (InputMismatchException e) 
			 
			 
			 {
				 System.out.println("\nINVALID CHOICE - TRY AGAIN");
				 scan.nextLine();
				 choice = -1;
			 }	 
			 
			 
		 } while (choice != commands.length-1);
	}

	private void doAddOrModifyDVD() {

		// Request the title
		System.out.println("Enter title");
		String title = scan.nextLine();
		if (title.equals("")) {
			return;		// input was cancelled
		}
		title = title.toUpperCase();
		
		// Request the rating
		System.out.println("Enter rating");
		String rating = scan.nextLine();
		if (rating.equals("")) {
			return;		// input was cancelled
		}
		rating = rating.toUpperCase();
		
		// Request the running time
		System.out.println("Enter running time");
		String time = scan.nextLine();	// NOTE: time read in as a String!
		if (time.equals("")) {
			return;		// input was cancelled
		}

		// Add or modify the DVD (assuming the rating and time are valid)
		dvdlist.addOrModifyDVD(title, rating, time);
		
		// Display current collection to the console for debugging
		System.out.println("Adding/Modifying: " + title + "," + rating + "," + time);
		System.out.println(dvdlist);
		
	}
	
	private void doRemoveDVD() {

		// Request the title
		System.out.println("Enter title");
		String title = scan.nextLine();
		if (title.equals("")) {
			return;		// dialog was cancelled
		}
		title = title.toUpperCase();
		
		// Remove the matching DVD if found
		dvdlist.removeDVD(title);
		
		// Display current collection to the console for debugging
		/*
		System.out.println("Removing: " + title);
		System.out.println(dvdlist);
		*/
			
	}


	private void doGetDVDsByRating() {

		// Request the rating
		System.out.println("Enter rating");
		String rating = scan.nextLine();
		if (rating.equals("")) {
			return;		// dialog was cancelled
		}
		rating = rating.toUpperCase();
		
		String results = dvdlist.getDVDsByRating(rating);
		System.out.println("DVDs with rating " + rating);
		System.out.println(results);		

	}
	
	private void doGetTotalRunningTime() {
		int total = dvdlist.getTotalRunningTime();
		System.out.println("Total Running Time of DVDs: ");
		System.out.println(total + "\n");
	}
	
	private void doSave() {
		dvdlist.save();
	}	
	
}