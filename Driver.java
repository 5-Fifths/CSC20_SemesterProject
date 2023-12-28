// Name: Brian Truong

package movie_database;

import java.io.IOException;
import java.io.FileNotFoundException;

public class Driver {
	public static Keyboard_IO KB = new Keyboard_IO();
	public static Database DB;
	
	public static void main(String[] args) throws IOException, FileNotFoundException {
		boolean repeat = true;
		DB = uploadDB();
		
		printIntro();
		
		while (repeat) {
			char mainOption = mainMenu();
			System.out.println();
			
			if (mainOption == 'i') { // exit loop
				repeat = false;
				KB.close();
				break;
			}
			
			switch (mainOption) {
				case 'a':
					System.out.println("Please enter the movie information below. Leave the prompt blank if you don't have the information.");
					constructMovie();
					break;
				case 'b':
					// search by title
					System.out.println("Title: ");
					outputSearch(DB, "title");
					break;
				case 'c':
					// search by director
					System.out.print("Director: ");
					outputSearch(DB, "director");
					break;
				case 'd':
					// search by actor
					System.out.print("Actor: ");
					outputSearch(DB, "actors");
					break;
				case 'e':
					// search by year
					System.out.print("Year: ");
					outputSearch(DB, "year");
					break;
				case 'f':
					// search by runtime
					System.out.print("Runtime: ");
					outputSearch(DB, "runtime");
					break;
				case 'g':
					// lookup by index
					System.out.println("Number of movies in database: " + DB.getNumEntries() + '\n');
					System.out.print("Entry Number (1-" + DB.getNumEntries() + "): ");
					
					String input = KB.readLine();
					try {
						int idx = Integer.parseInt(input) - 1;
						if (idx >= DB.getNumEntries() || idx < 0) { // idx out of bounds
							System.out.println(input + " is invalid.");
							break;
						}
						
						System.out.println(DB.getMovie(idx).toString());
					} catch (NumberFormatException e) {
						System.out.println(input + " is not a valid input.");
					}
					
					break;
				case 'h':
					// generate random movie
					System.out.println(DB.getRandomMovie().toString());
					break;
				default:
					System.out.println("Invalid option. Please try again.");
					continue;
			}
			KB.readLine(); // pause after output generates
		}
		printOutro();
	}
	
	private static Database uploadDB() throws FileNotFoundException {
		Database DB = new Database();
		DB_Load load = new DB_Load(DB);
		load.load();
		DB = load.getDB();
		
		return DB;
	}
	
	private static void printIntro() {
		System.out.println(
			"    _     _                       _         \n"	+
			"   | |   | |                     (_)		 \n"	+        
			"   | |__ | |__   ___    _ __ ___  _ _ __	 \n"	+    
			"   | '_ \\| '_ \\ / _ \\  | '_ ` _ \\| | '_ \\	 \n"   	+
			"   | | | | |_) | (_) | | | | | | | | | | |  \n"		+  
			"   |_| |_|_.__/ \\___/  |_| |_| |_|_|_| |_|	 \n"	+  
			"   ______ ______ ______ ______ ______ ______   \n"		+ 
			"  |______|______|______|______|______|______| "
				);
		
		System.out.println("\nEnter any key to start. ");
		KB.readLine();
	}
	
	private static void printOutro() {
		System.out.println("See you next time.");
	}
	
	private static char mainMenu() {
		System.out.println("Please select one of the letter options below.");
		System.out.println("Options: ");
		System.out.println(
			" a) New Entry\n" 				+
			" b) Search by Title\n" 		+
			" c) Search by Director\n" 		+
			" d) Search by Actor\n" 		+
			" e) Search by Year\n" 			+
			" f) Search by Runtime\n" 		+
			" g) Search by index\n"		 	+
			" h) Search randomly\n"			+
			" i) Quit"
				);
		
		System.out.print("Selection: ");
		
		return KB.readLine().toLowerCase().charAt(0);
	}
	
	private static void constructMovie() throws IOException, FileNotFoundException {
		DB_Save save = new DB_Save(DB);
		Movie movie = new Movie();
		String entryData = "";
		
		System.out.println("Title: ");
			while (movie.getTitle().isEmpty())
				populateMovie(KB.readLine(), "title", movie);
			entryData += movie.getTitle() + " | "; 
	
		System.out.println("Director: ");
			populateMovie(KB.readLine(), "director", movie);
			entryData += movie.getDirector() + " | ";
			
		System.out.println("Lead Actor: ");
			populateMovie(KB.readLine(), "actors", movie);
			entryData += movie.getActors()[0] + ", ";
			
		System.out.println("2nd Lead Actor: ");
			populateMovie(KB.readLine(), "actors", movie);
			entryData += movie.getActors()[1] + " | ";
			
		System.out.println("Year Released: ");
			populateMovie(KB.readLine(), "year", movie);
			entryData += movie.getYear() + " | ";
			
		System.out.println("Runtime: ");
			populateMovie(KB.readLine(), "runtime", movie);
			entryData += movie.getRuntime();
			
		store(DB, save, entryData, movie);
	} 
	
	private static void populateMovie(String input, String field, Movie movie) {
		if (input.isEmpty()) {
			input = "-1"; // empty strings cause parsing errors while loading DB
		}
		
		switch (field) {
			case "title":
				if (input.length() < 3) {
					System.out.println("Movie title must be at least 3 characters. Please try again.");
					System.out.print("Title: ");
					break;
				}
				movie.setTitle(input);
					
				break;
			case "director":
				movie.setDirector(input);
				break;
			case "actors":
				if (movie.getActors()[0].equals("")) // the first actor field in array is empty
					movie.setActor(0, input);
				else 
					movie.setActor(1, input);
				
				break;
			case "year":
				System.out.println(Integer.parseInt(input));
				movie.setYear(Integer.parseInt(input));
				break;
			case "runtime":
				System.out.println(Integer.parseInt(input));
				movie.setRuntime(Integer.parseInt(input));
				break;
			default:
				System.out.println(field + " is not a valid input");
				break;
		}
		
		return;
	}
	
	private static void store(Database DB, DB_Save save, String entryData, Movie movie) throws IOException {
		// saves new entry to DB.txt & current array
		DB.addEntry(movie); 	// stores new movie object to DB array
		System.out.println("The following movie has been added to the database.\n" + movie.toString());
		
		save.save(entryData); 	// stores new string entry to DB.txt
		System.out.println("The movie \"" + movie.getTitle() + "\" has been saved to file.\n");
	}
	
	private static void outputSearch(Database DB, String field) {
		String qualifier = KB.readLine();
		Movie[] movies = DB.search(qualifier, field);
		
		if (movies.length <= 0) {
			System.out.println("No " + field + " found containing \"" + qualifier + "\".\n");
			return;
		}
		
		for (int i = 0; i < movies.length; i++)
			System.out.println(movies[i].toString());
	}
}
