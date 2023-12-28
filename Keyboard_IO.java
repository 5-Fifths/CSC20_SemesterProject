// Name: Brian Truong

package movie_database;

import java.util.Scanner;

public class Keyboard_IO {
	private Scanner kb;
	
	public Keyboard_IO() {
		this.kb = new Scanner(System.in);
	} 
	
	public String readLine() {
		return kb.nextLine();
	}
	
	public String readToken() {
		return kb.next();
	}
	
	public char readChar() {
		return kb.next().charAt(0);
	}
	
	public int readInt() {
		return kb.nextInt();
	}
	
	public void flush() {
		this.readLine();
	}
	
	public void close() {
		kb.close();
	}
}
