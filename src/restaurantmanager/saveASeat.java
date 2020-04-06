package restaurantmanager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class saveASeat {

	public static void main(String[] args) throws IOException {
		Platform platform = new Platform();
		System.out.println("Welcome to Save a Seat! Are you a restaurant or a customer?");
		
		BufferedReader reader =  new BufferedReader(new InputStreamReader(System.in));
		
		String userType = reader.readLine();
		
		Restaurant res1 = new Restaurant("Hammy's", 10, 11, 11);
		Restaurant res2 = new Restaurant("Lucia's", 10, 11, 11);
		Restaurant res3 = new Restaurant("G Hao's", 10, 11, 11);
		platform.addRestaurant(res1);
		platform.addRestaurant(res2);
		platform.addRestaurant(res3);
		
		platform.addRestaurantPassword(res1, "12345");
		platform.addRestaurantPassword(res2, "12345");
		platform.addRestaurantPassword(res3, "12345");
		
		//Reservation(name, numPeople, date, time, requests, uniqueId);
		Reservation rv1 = new Reservation("Payden Webb", 10, 1122, 1145, "none", 12345678);
		Reservation rv2 = new Reservation("Emma Goldberg", 10, 1215, 1145, "none", 83827429);
		Reservation rv3 = new Reservation("Marcela Interiano", 10, 1010, 1215, "none", 59938138);

		res1.addReservation(rv1);
		res2.addReservation(rv2);
		res3.addReservation(rv3);
		
		// restaurant side
		if(userType.equals("restaurant")) {
			System.out.println("Please enter the name of your restaurant.");
			String name =  reader.readLine();
			
			//check if name exists in list of restaurants
			boolean found =  false;
			for(Restaurant r : platform.restaurants) {
				if(r.getName().equals(name)) {
					found = true;
					System.out.println("Please enter your restaurant password.");
					String password =  reader.readLine();
					if(platform.restaurantPasswords.get(r).equals(password)) {
						System.out.println("You are logged in.");
						break;
					}
				} 
			}
			if(!found) {
				System.out.println("Your restaurant is not in our system. Let's add you!");
				
				System.out.println("Please enter your restaurant's max capacity.");
				int maxCapacity =  Integer.parseInt(reader.readLine());
				
				System.out.println("Please enter your restaurant's opening time.");
				int openingTime  = Integer.parseInt(reader.readLine());
				
				System.out.println("Please enter your restaurant's closing time.");
				int closingTime  = Integer.parseInt(reader.readLine());
						
				Restaurant r = new Restaurant(name, maxCapacity, openingTime, closingTime);
				platform.addRestaurant(r);
				
				System.out.println("Please enter your new platform password.");
				String password = reader.readLine();
				
				platform.addRestaurantPassword(r, password);
				System.out.println("Your restaurant has been added to our system.");
			}
			
			System.out.println("Here are your current reservations: ");
			Restaurant currentRestaurant = null;
			for(Restaurant r : platform.restaurants) {
				if(r.getName().equals(name)) {
					currentRestaurant = r;
				}
			}
			currentRestaurant.seeReservations();
			System.out.print("Here is your current capacity: ");
			System.out.println(currentRestaurant.getCurrentCapacity());
			
		}
		
		// customer side
		if(userType.equals("customer")) {
			platform.seeRestaurants();
			System.out.println("Enter the name of the restaurant you'd like to make a reservation at.");
			String place = reader.readLine();
			
			System.out.println("Enter the date you'd like to have a reservation in the format mmdd.");
			int date = Integer.parseInt(reader.readLine());
			
			System.out.println("Enter the time you'd like to have a reservation in the format hhmm.");
			int time = Integer.parseInt(reader.readLine());
			
			System.out.println("Reservation for how many?");
			int numPeople = Integer.parseInt(reader.readLine());
			
			System.out.println("What name do you want the reservation under?");
			String name = reader.readLine();
			
			System.out.println("Do you have any requests?");
			String requests = reader.readLine();
			
			Random rand = new Random();
			int uniqueId = rand.nextInt(1000000);
		
			Reservation reservation = new Reservation(name, numPeople, date, time, requests, uniqueId);
			
			for(Restaurant r : platform.restaurants) {
				if(r.getName().equals(place)) {
					r.addReservation(reservation);
				}
			}
		}
		
	}
}
