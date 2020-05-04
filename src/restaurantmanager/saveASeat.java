package restaurantmanager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.Scanner;

public class saveASeat {

	public static void main(String[] args) throws IOException {

		Platform platform = new Platform();
		System.out.println("Welcome to Save a Seat! Are you a restaurant or a customer [r/c] ?");

		BufferedReader reader =  new BufferedReader(new InputStreamReader(System.in));
		String userType = reader.readLine();

		Restaurant res1 = new Restaurant("Hammy's", 10, 1100, 2300);
		Restaurant res2 = new Restaurant("Lucia's", 10, 1100, 2300);
		Restaurant res3 = new Restaurant("G Hao's", 10, 1100, 2300);

		platform.addRestaurant(res1);
		platform.addRestaurant(res2);
		platform.addRestaurant(res3);

		platform.addRestaurantPassword(res1, "12345");
		platform.addRestaurantPassword(res2, "12345");
		platform.addRestaurantPassword(res3, "12345");

		Reservation rv1 = new Reservation("Hammy's", "Payden Webb", 10, 1122, 1145, "none", 12345678);
		Reservation rv2 = new Reservation("Lucia's", "Emma Goldberg", 10, 1215, 1145, "none", 83827429);
		Reservation rv3 = new Reservation("G Hao's", "Marcela Interiano", 10, 1010, 1215, "none", 59938138);

		res1.addReservation(rv1);
		res2.addReservation(rv2);
		res3.addReservation(rv3);

		while (!(userType.equals("r")) && !(userType.equals("c"))) {
			System.out.println("Invalid entry, try again!"); 
			userType = reader.readLine(); 
		}

		if (userType.equals("r")) { 
			System.out.println("Welcome owner, enter:");
			System.out.println("1 - to manage YOUR restaurant");
			System.out.println("2 - to add YOUR restaurant to our platform");
			System.out.println("q - to quit");
			String ans = reader.readLine(); 
			while (!(ans.equals("q"))) {
				if (ans.equals("1")) { 
					System.out.println("Please enter the name of your restaurant:");
					String restaurantName =  reader.readLine();
					if(platform.findRestaurant(restaurantName) != null) {
						Restaurant currentRestaurant = platform.findRestaurant(restaurantName); 
						System.out.println("Enter the password to your restaurant: ");
						String password =  reader.readLine();
						while(!platform.restaurantPasswords.get(currentRestaurant).equals(password)) {
							password = reader.readLine(); 
						}
						System.out.println("Successfully logged in."); 
						currentRestaurant.run();
					} else { 
						System.out.println("Restaurant: "  + restaurantName + " does not exist in our system, terminating.");
						System.exit(0);
					}
				}
				if (ans.equals("2")) { 
					Restaurant newRestaurant = platform.userAddedRestaurant(platform);
					newRestaurant.run();
				}
				ans = reader.readLine(); 
			}	
			System.exit(0);
		} else { 
			System.out.println("Welcome customer, enter:");
			System.out.println("1 - to create a new reservation");
			System.out.println("2 - to manage your current reservations");
			System.out.println("q - to quit");
			String ans = reader.readLine(); 
			while (!(ans.equals("q"))) {
				if (ans.equals("1")) { 
					platform.newReservation(); 
				}
				if (ans.equals("2")) { 
					System.out.println("What is the name of the restaurant you made the reservation at?");
					String restaurantName = reader.readLine();
					while(platform.findRestaurant(restaurantName) == null) {
						System.out.println("Invalid entry, try again!"); 
						restaurantName = reader.readLine();
					}
					System.out.println("What is your unique reservations ID?");
					int id = Integer.parseInt(reader.readLine()); 
					platform.manageUserReservations(restaurantName, id);
				}
				System.out.println("Welcome customer, enter:");
				System.out.println("1 - to create a new reservation");
				System.out.println("2 - to manage your current reservations");
				System.out.println("q - to quit");
				ans = reader.readLine(); 
			}			
			System.exit(0);
		}
	
	}
}
