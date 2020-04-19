package restaurantmanager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

public class LocalStorage {
	
	private static String FILE_PATH = "src/restaurantmanager/LocalStorage.txt";
	
	private static Gson gson = new Gson();
	
	public static void main(String[] args) {
		Platform platform = new Platform();
		Restaurant res1 = new Restaurant("Hammy's", 10, 11, 11);
		Restaurant res2 = new Restaurant("Lucia's", 10, 11, 11);
		Restaurant res3 = new Restaurant("G Hao's", 10, 11, 11);
		platform.addRestaurant(res1);
		platform.addRestaurant(res2);
		platform.addRestaurant(res3);
		
		writeToFile(gson.toJson(platform).toString());	
		readFromFile();
	}
	
	private static void writeToFile(String data) {
		File f = new File(FILE_PATH);
		if (!f.exists()) { // if file not there, make a file
			try {
				f.createNewFile();
			} catch (IOException e) {
				System.out.println("Excepton Occured: " + e.toString());
			}
		}
		else { // if file is there, delete all contents
			PrintWriter pw = null;
			try {
				pw = new PrintWriter(FILE_PATH);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			pw.close();
		}
 
		try {
			// Convenience class for writing character files
			FileWriter writer = new FileWriter(f.getAbsoluteFile(), true);
 
			// Writes text to a character-output stream
			BufferedWriter bufferWriter = new BufferedWriter(writer);
			bufferWriter.write(data.toString());
			bufferWriter.close();
 
			System.out.println(("Company data saved at file location: " + FILE_PATH + " Data: " + data + "\n"));
		} catch (IOException e) {
			System.out.println(("Hmm.. Got an error while saving Company data to file " + e.toString()));
		}
	}
	
	public static void readFromFile() {
		File f = new File(FILE_PATH);
		if (!f.exists())
			System.out.println("File doesn't exist");
 
		InputStreamReader isReader;
		try {
			isReader = new InputStreamReader(new FileInputStream(f), "UTF-8");
 
			JsonReader reader = new JsonReader(isReader);
			
			Platform plat = gson.fromJson(reader, Platform.class);
			for (Restaurant r : plat.restaurants) {
				System.out.println(r.name);
			}

 
		} catch (Exception e) {
			System.out.println("error load cache from file " + e.toString());
		}
 
		System.out.println("\nComapny Data loaded successfully from file " + FILE_PATH);
 
	}
	
	
	
}