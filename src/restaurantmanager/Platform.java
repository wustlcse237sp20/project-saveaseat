package restaurantmanager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Platform {
	public List<Restaurant> restaurants;
	public Map<Restaurant, String> restaurantPasswords;

	public Platform() {
		restaurants =  new LinkedList<Restaurant>();
		restaurantPasswords = new HashMap<Restaurant, String>();
	}

	public List<Restaurant> getRestaurants () { 
		return this.restaurants; 
	}

	public String getPassword(Restaurant r) {
		return this.restaurantPasswords.get(r);
	}

	public void addRestaurant(Restaurant r) {
		this.restaurants.add(r);
	}

	public void addRestaurantPassword(Restaurant r, String password) {
		this.restaurantPasswords.put(r, password);
	}

	public Restaurant userAddedRestaurant (Platform platform) throws IOException {

		BufferedReader reader =  new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Please enter the name of your restaurant: ");
		String restaurant =  reader.readLine();

		System.out.println("Please enter your restaurant's max capacity: ");
		int maxCapacity =  Integer.parseInt(reader.readLine());

		System.out.println("Please enter your restaurant's opening time (24 hour format i.e. 1800 for 6pm): ");
		int openingTime  = Integer.parseInt(reader.readLine());

		System.out.println("Please enter your restaurant's closing time (24 hour format i.e. 1800 for 6pm): ");
		int closingTime  = Integer.parseInt(reader.readLine()); 

		Restaurant r = new Restaurant(restaurant, maxCapacity, openingTime, closingTime);

		System.out.println("Please enter your new platform password.");
		String password = reader.readLine();

		platform.addRestaurant(r);
		platform.addRestaurantPassword(r, password);

		System.out.println("Your restaurant has been added to our system.");
		return r; 

	}
	public boolean checkAvailability(int date, int numPeople, int time, String place) {
		int sumPeopleAtTime = 0;
		Restaurant targetRes = null;
		for(Restaurant r : this.restaurants) {
			if(r.getName().equals(place)) {
				targetRes = r;
				for(Reservation res : r.getReservations()) {
					if(res.getDate()== date) {
						if(res.getTime()==time) {
							sumPeopleAtTime=sumPeopleAtTime+res.numPeople;
						}
					}
				}
			}
		}
		if((sumPeopleAtTime + numPeople) > targetRes.maxCapacity) {
			return false;
		}
		return true;

	}

	public void seeAvailableTimes(int date, int numPeople, String place) {
		Restaurant currentRes = null;
		List<Integer> availableTimes = new LinkedList<Integer>();
		Map<Integer, Boolean> timeMap = new HashMap<Integer, Boolean>();

		for(Restaurant r : this.restaurants) {
			if(r.getName().equals(place)) {
				currentRes = r;
				int openingTime = r.getOpeningTime();
				int closingTime = r.getClosingTime();
				for(int i = openingTime; i < closingTime; i = i + 100) {
					timeMap.put(i, true);
					availableTimes.add(i);
				}
				for(Integer i : availableTimes) {
					if(!checkAvailability(date, numPeople, i, place)) {
						timeMap.replace(i, false);
					}
				}
				for(int i = openingTime; i < closingTime; i = i + 100) {
					if(timeMap.get(i)==true) {
						System.out.println(i);
					}
				}
			}
		}

	}
	public Reservation newReservation () throws IOException { 
		System.out.println("Here is a list of available restaurants:");
		seeRestaurants();

		BufferedReader reader =  new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Enter the name of the restaurant you'd like to make a reservation at.");
		String place = reader.readLine();
		while(findRestaurant(place) == null) { 
			System.out.println("Invalid entry, try again!"); 
			place = reader.readLine(); 
		}
		Restaurant rest = findRestaurant(place);
		System.out.println("Reservation for how many?");
		int numPeople = Integer.parseInt(reader.readLine());
		while(numPeople > rest.getMaxCapacity()) {
			System.out.println("Sorry, your reservation number request exceeds the max capacity of the restaurant, which is " + rest.maxCapacity+".");
			System.out.println("Reservation for how many?");
			numPeople = Integer.parseInt(reader.readLine());
		}
		System.out.println("Enter the date you'd like to have a reservation in the format mmdd.");
		int date = Integer.parseInt(reader.readLine());

		System.out.println("Enter the time you'd like to have a reservation in the format hhmm. Reservations only available in hourly increments.");
		int time = Integer.parseInt(reader.readLine());

		while(!checkAvailability(date, numPeople, time, place)) {
			System.out.println("Sorry, that time is not available. Available times for this date are: ");
			seeAvailableTimes(date, numPeople, place);
			System.out.println("Enter the time you'd like to have a reservation in the format hhmm. Reservations only available in hourly increments.");
			time = Integer.parseInt(reader.readLine());
		}
		
		System.out.println("What name do you want the reservation under?");
		String name = reader.readLine();

		System.out.println("Do you have any requests?");
		String requests = reader.readLine();

		Random rand = new Random();
		int uniqueId = rand.nextInt(1000000);

		Reservation reservation = new Reservation(place, name, numPeople, date, time, requests, uniqueId);
		findRestaurant(place).addReservation(reservation);

		System.out.println("Your reservation id is " +  uniqueId + ". You can use this ID to check your reservation or cancel it.");

		return reservation;
	}

	public Restaurant findRestaurant(String name) {
		for(Restaurant r : this.restaurants) {
			if(r.getName().equals(name)) {
				return r;
			}
		}
		return null;
	}

	public void seeRestaurants() {
		for(Restaurant r: this.restaurants) {
			System.out.println(r.getName());
		}
	}

	public void manageUserReservations(String restaurantName, int reservationId) throws IOException { 
		System.out.println("Here are your reservation details: ");
		Restaurant current;
		Reservation currentRes; 

		for(Restaurant r : getRestaurants()) {
			if(r.getName().equals(restaurantName)) {
				current = r;
				for(Reservation res : r.listReservations) {
					if(res.getId() == reservationId) {
						currentRes = res; 
						System.out.println("Reservation for a party of "+res.getNumPeople()+" under the name "+res.getName()+
								" at "+res.getTime()+ " on "+res.getDate()+".");
					}
				}
			}
		}

		BufferedReader reader =  new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Would you like to cancel this reservation [y/n]?");
		String ans = reader.readLine(); 
		while (!(ans.equals("y")) && !(ans.equals("n"))) {
			System.out.println("Invalid entry, try again!"); 
			ans = reader.readLine(); 
		}
		if (ans.equals("y")) {
			List<Reservation> reservations = findRestaurant(restaurantName).getReservations();
			for (Reservation r : reservations) { 
				if (r.getId() == reservationId) { 
					reservations.remove(r); 
				}
			}
		} 
	}

}
