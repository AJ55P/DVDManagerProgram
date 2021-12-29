import java.io.*;
import java.util.Scanner;

public class DVDCollection {
	private int numdvds;
	private DVD[] dvdArray;
	private int max = 8; // starting size at 8, double size when inserting
							// into a full array

	public DVDCollection() {
		numdvds = 0;
		dvdArray = new DVD[max];
		for (int i = 0; i < max; i++) {
			dvdArray[i] = new DVD();
		}
	}

	public String toString() {
		String a1 = "";

		for (int i = 0; i < numdvds; i++) {
			if (dvdArray[i].getTitle() != "undefined") {
				a1 = a1 + dvdArray[i].toString() + '\n';
			}
		}
		return "numdvds =  " + numdvds + '\n' + "dvdArray.length = " + dvdArray.length + '\n' + a1;
	}

	public void addOrModifyDVD(String title, String rating, String runningTime) {
		int a = Integer.parseInt(runningTime);
		boolean contain = false;
		if (numdvds == 0) {
			dvdArray[numdvds].setTitle(title);
			dvdArray[numdvds].setRating(rating);
			dvdArray[numdvds].setRunningTime(a);
			numdvds++;
		} else {
			if (numdvds > 0 && numdvds < max) {
				/*
				 * This loop only checks if the title currently exists in the collection.
				 */
				for (int i = 0; i < numdvds; i++) {
					if (dvdArray[i].getTitle().equals(title)) {
						if (rating.equals("") || runningTime.equals("")) {
							System.out.println("Incomplete field were provided!");
							return;
						}

						dvdArray[i].setTitle(title);
						dvdArray[i].setRating(rating);
						dvdArray[i].setRunningTime(a);
						contain = true;// checking if the title exists, if yes, does this
						break;
					}
				}
			}

			if (contain == false) {
				addHelper(title, rating, a);
			}
		}
	}

	public void removeDVD(String title) {
		boolean found = false;
		int d = 0;

		if (numdvds == 0) {
			System.out.println("error, list is empty\n");
			return;
		}

		int i = 0;
		while (i < numdvds && found == false) {
			if (dvdArray[i].getTitle().equals(title)) {
				found = true;
				d = i;

				dvdArray[i].setTitle("undefined");
				dvdArray[i].setRating("undefined");
				dvdArray[i].setRunningTime(0);
				numdvds--;
			}
			i++;
		}
		if (found == true) {
			for (int s = d; s < numdvds; s++) {
				dvdArray[s].setTitle(dvdArray[s + 1].getTitle());
				dvdArray[s].setRating(dvdArray[s + 1].getRating());
				dvdArray[s].setRunningTime(dvdArray[s + 1].getRunningTime());
			}
			System.out.println("Removing: " + title + "\n");
			System.out.println(toString());
			return;
		}
		System.out.println("error, no movie was found with that title");
	}

	public String getDVDsByRating(String rating) {

		String tot1 = "";

		for (int i = 0; i < numdvds; i++) {
			if (dvdArray[i].getRating().equals(rating)) {
				tot1 = tot1 + dvdArray[i].toString() + "\n";

			}
		}

		if (tot1.equals("")) {
			return "error, no dvds feature this rating!\n";
		}
		return tot1;
	}

	public int getTotalRunningTime() {
		int tRun = 0;
		if (numdvds == 0) {
			return 0;
		}

		for (int i = 0; i < numdvds; i++) {
			tRun = tRun + dvdArray[i].getRunningTime();
		}

		return tRun;
	}

	public void loadData(String filename) {
		String a = null;
		String b = null;
		String c = null;
		try {
			File myFile = new File(filename);
			FileReader fileReader = new FileReader(myFile);
			BufferedReader reader = new BufferedReader(fileReader);

			String line = null;

			while ((line = reader.readLine()) != null) {
				String[] input = line.split("/");
				a = input[0];
				b = input[1];
				c = input[2];
				addOrModifyDVD(a, b, c);
			}
			reader.close();

		}

		catch (Exception ex) {
			System.out.println("\nNo file found. Starting now with empty array");
		}
	}

	public void save() {
		Scanner scan = new Scanner(System.in);
		System.out.println("please enter file name that was loaded ");
		String filename1 = scan.nextLine();

		try {
			FileWriter writer1 = new FileWriter(filename1);

			for (int i = 0; i < numdvds; i++) {
				writer1.write(dvdArray[i].getTitle() + "/" + dvdArray[i].getRating() + "/"
						+ dvdArray[i].getRunningTime() + '\n');
			}
			writer1.close();
		}

		catch (IOException one) {
			one.printStackTrace();
		}
		scan.close();
	}

	public void sort() {
		for (int j = 0; j < numdvds - 1; j++) {
			for (int i = 0; i < numdvds - j - 1; i++) {
				DVD a1 = dvdArray[i];
				DVD n2 = dvdArray[i + 1];
				String a1s = a1.getTitle();
				String n2s = n2.getTitle();

				if (a1s.compareTo(n2s) > 0) {
					String j1 = dvdArray[i].getTitle();
					String j2 = dvdArray[i].getRating();
					int j3 = dvdArray[i].getRunningTime();

					dvdArray[i].setTitle(dvdArray[i + 1].getTitle());
					dvdArray[i].setRating(dvdArray[i + 1].getRating());
					dvdArray[i].setRunningTime(dvdArray[i + 1].getRunningTime());

					dvdArray[i + 1].setTitle(j1);
					dvdArray[i + 1].setRating(j2);
					dvdArray[i + 1].setRunningTime(j3);
				}
			}
		}
	}

	public void addHelper(String title, String rating, int runTime) {
		if (numdvds >= max) {
			int oldMax = max;
			max *= 2;

			DVD[] biggerDVDArray = new DVD[max];
			for (int i = 0; i < max; i++) {
				biggerDVDArray[i] = new DVD();
			}

			for (int j = 0; j < oldMax; j++) {
				biggerDVDArray[j].setTitle(dvdArray[j].getTitle());
				biggerDVDArray[j].setRating(dvdArray[j].getRating());
				biggerDVDArray[j].setRunningTime(dvdArray[j].getRunningTime());
			}
			dvdArray = biggerDVDArray;
		}

		dvdArray[numdvds].setTitle(title);
		dvdArray[numdvds].setRating(rating);
		dvdArray[numdvds].setRunningTime(runTime);
		numdvds++;

		sort();
	}
}