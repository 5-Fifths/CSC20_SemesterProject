// Name: Brian Truong

package movie_database;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DB_Save {
	private Database db;
	
	public DB_Save(Database db) {
		this.db = db;
	}
	
	// saves entries into db.txt to be accessed on program opening
	public void save(String saveString) throws IOException {
		File dbtxt = new File(".\\db.txt");
		FileWriter writer = new FileWriter(dbtxt, true);
		
		writer.write(saveString + '\n');		
		writer.close();
	}
	
	public Database getDB() {
		return db;
	}
}
