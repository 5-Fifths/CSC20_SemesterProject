// Name: Brian Truong

package movie_database;

public class Movie {
	private String 		title;
	private String[]	actorNames;
	private String 		director;
	private int 		year;
	private int 		runtime;
	
	public Movie() {
		title = "";
		actorNames = new String[] { "", "" };
		director = "";
		year = -1;
		runtime = -1;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String[] getActors() {
		return actorNames;
	}
	
	public String getDirector() {
		return director;
	}
	
	public int getYear() {
		return year;
	}
	
	public int getRuntime() {
		return runtime;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setActor(int idx, String name) {
		this.actorNames[idx] = name;
	}
	
	public void setDirector(String name) {
		this.director = name;
	}
	
	public void setYear(int year) {
		this.year = year;
	}
	
	public void setRuntime(int runtime) {
		this.runtime = runtime;
	}
	
	public String toString() {
		String temp;
		temp = "Title:\t\t" 	+  getTitle() + 
			   "\nDirector:\t" 	+  getDirector() +
			   "\nActors:\t\t" 	+  getActors()[0] + ", " +  getActors()[1] +
			   "\nYear:\t\t"   	+  getYear() +
			   "\nRuntime:\t"	+  getRuntime() + '\n';
		
		return temp;
	}
}
