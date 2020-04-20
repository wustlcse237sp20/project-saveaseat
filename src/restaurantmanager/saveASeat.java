package restaurantmanager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.Scanner;

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

			System.out.println("What would you like to do? Enter see to see reservations, check for check current capacity, or quit to exit.");
			String response = reader.readLine();

			if(response.equals("see")) {
				System.out.println("Current Reservations: ");
				currentRestaurant.seeReservations();
			}
			if(response.equals("check")) {
				System.out.print("Current Capacity: ");
				System.out.println(currentRestaurant.getCurrentCapacity());
			} 
			if(response.equals("quit")) {
				System.out.println("You have been logged out. Thanks for using our platform!");
				return;
			}

			System.out.println("You have been logged out. Thanks for using our platform!");

		}

		// customer side
		if(userType.equals("customer")) {
			System.out.println("Restaurants Available:");
			platform.seeRestaurants();
			System.out.println("What would you like to do? Enter cancel to cancel reservation, enter view to view reservation, enter new to make new reservation.");
			String response = reader.readLine();
			if(response.equals("cancel")) {
				System.out.println("Enter reservation id.");
				int id = Integer.parseInt(reader.readLine());
				System.out.println("Enter location of reservation.");
				String n = reader.readLine();
				for(Restaurant r : platform.restaurants) {
					if(r.getName().equals(n)) {
						for(Reservation res : r.listReservations) {
							if(res.getId()==id) {
								r.listReservations.remove(res);
							}
						}
					}
				}
				System.out.println("Your reservation has been cancelled.");
			} 
			if(response.equals("view")) {
				System.out.println("Enter reservation id.");
				String getLine = reader.readLine();
				int id = Integer.parseInt(getLine);
				System.out.println("Enter location of reservation.");
				String n = reader.readLine();

				for(Restaurant r : platform.restaurants) {
					if(r.getName().equals(n)) {
						for(Reservation res : r.listReservations) {
							if(res.getId()==id) {
								System.out.println("Reservation for a party of "+res.getNumPeople()+" under the name "+res.getName()+
										" at "+res.getTime()+ " on "+res.getDate()+".");
							}
						}
					}
				}

			}  
			if(response.equals("new")) {

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
				System.out.println("Your reservation id is "+uniqueId+". You can use this ID to check your reservation or cancel it.");
			}
		}
	}
}
