public class DVD {

	// Fields:

	private String title;		// Title of this DVD
	private String rating;		// Rating of this DVD
	private int runningTime;	// Running time of this DVD in minutes
	
	public DVD()
	{
		this.title = "undefined";
		this.rating = "undefined";
		this.runningTime = 0;
	}

	
	public DVD(String dvdTitle, String dvdRating, int dvdRunningTime) 
	{
		this.title = dvdTitle;
		this.rating = dvdRating;
		this.runningTime = dvdRunningTime; 

	}
	
	
	public String getTitle() 
	{
		return title;
	}
	
	public String getRating() 
	{
		return rating;
	}
	
	public int getRunningTime() 
	{
		return runningTime;
	}
	

	public void setTitle(String newTitle) {
		this.title = newTitle;
	}
	

	public void setRating(String newRating) {

		this.rating = newRating;
		
	}

	public void setRunningTime(int newRunningTime) {
		
		this.runningTime = newRunningTime;

	}

	public String toString() {

		return  this.getTitle() + "/" + this.getRating() + "/" + this.getRunningTime() + " minutes";

	}
	
	
}