// Name: Brian Truong

package movie_database;

import java.util.StringTokenizer;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class DB_Load {
	private Database db;
	
	public DB_Load(Database db) {
		this.db = db;
	}
	
	// writes entries into database object to be accessed in the program
	public void load() throws FileNotFoundException {
		File dbtxt = new File(".\\db.txt");
		Scanner reader = new Scanner(dbtxt);
		
		while (reader.hasNextLine()) {
			// title | director | actor1, actor2 | year | runtime
			StringTokenizer st = new StringTokenizer(reader.nextLine(), "|");
			Movie movie = new Movie(); 
			String fields[] = new String[5];
			
			int n = st.countTokens(); // saves initial tokens
			for (int i = 0; i < n; i++) {
				String temp = st.nextToken().trim();
				fields[i] = temp;
			}
				
			// stores "actor1, actor2" string into [actor1, actor2] field in Movie
			StringTokenizer temp = new StringTokenizer(fields[2], ",");
			String[] actors = {temp.nextToken().trim(), temp.nextToken().trim()};
			
			movie.setActor(0, actors[0]);
			movie.setActor(1, actors[1]);	
			movie.setTitle(fields[0]);
			movie.setDirector(fields[1]);
			movie.setYear(Integer.parseInt(fields[3]));
			movie.setRuntime(Integer.parseInt(fields[4]));
			
			db.addEntry(movie);
		}
		
		reader.close();
	}

	public Database getDB() {
		return db;
	}
}
