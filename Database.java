// Name: Brian Truong

package movie_database;

import java.util.Random;
import java.util.ArrayList;

public class Database {
	private Movie[] movieArr;
	private int numEntries;
	
	public Database() {
		movieArr = new Movie[50];
		numEntries = 0;
	}
	
	private void resize() {
		// dynamically resize array
		Movie[] newArr = new Movie[numEntries * 2];
		
		// copy entries over
		for (int i = 0; i < numEntries; i++)
			newArr[i] = movieArr[i];
		
		// point from old array to new array
		this.movieArr = newArr;
	}
	
	public void addEntry(Movie movie) {
		if (numEntries >= movieArr.length)
			resize(); // prevent out of range
		
		// append Movie
		this.movieArr[numEntries] = movie;
		this.numEntries++;
	}
	
	public void removeEntry() {
		// extra credit
	}
	
	public int getNumEntries() { return numEntries; }
	
	public Movie getRandomMovie() {
		Movie movie = movieArr[new Random().nextInt(0, numEntries)]; // pull random movie from array of movies
		
		return movie;
	}
	
	public Movie getMovie(int idx) {
		if (idx >= numEntries)
			return movieArr[numEntries - 1]; // return first movie in arr
		if (idx < 0)
			return movieArr[0]; // return last movie in arr
			
		return movieArr[idx];
	}
	
	public Movie[] search(String qualifier, String field) {
		qualifier = qualifier.toLowerCase();
		
		ArrayList<Movie> movieList = new ArrayList<>(); 
		Movie[] movies;
		
		switch (field) {
		case "title":
			for (int i = 0; i < numEntries; i++) {
				if (movieArr[i].getTitle().toLowerCase().contains(qualifier)) 
					movieList.add(movieArr[i]);
			}
			break;
		case "director":
			for (int i = 0; i < numEntries; i++) {
				if (movieArr[i].getDirector().toLowerCase().contains(qualifier))
					movieList.add(movieArr[i]);
			}
			break;
		case "actors":
			for (int i = 0; i < numEntries; i++) {
				String[] actors = movieArr[i].getActors();
				
				if (actors[0].toLowerCase().contains(qualifier) || actors[1].toLowerCase().contains(qualifier))
					movieList.add(movieArr[i]);
			}
			break;
		case "year":
			
			try {
				for (int i = 0; i < numEntries; i++)
					if (Integer.parseInt(qualifier) == movieArr[i].getYear())
						movieList.add(movieArr[i]);
			} catch (NumberFormatException e) {
				System.out.println(qualifier + " is not a valid year.");
			}
			break;
		case "runtime":
			try {
				for (int i = 0; i < numEntries; i++)
					if (Integer.parseInt(qualifier) == movieArr[i].getRuntime())
						movieList.add(movieArr[i]);
			} catch (NumberFormatException e) {
				System.out.println(qualifier + " is not a valid runtime.");
			}
			break;
		}
		
		movies = new Movie[movieList.size()];
		for (int i = 0; i < movieList.size(); i++)
			movies[i] = movieList.get(i);
		
		return movies;
	}
}
