package restaurantmanager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class saveASeat {

	public static void main(String[] args) throws IOException {

		System.out.println("Welcome to Save a Seat! Are you a restaurant or a customer?");
		
		BufferedReader reader =  new BufferedReader(new InputStreamReader(System.in));
		
		String userType = reader.readLine();
		
		System.out.println("You are a "+userType+".");   
		
		Platform platform = new Platform();		

		if(userType.equals("restaurant")) {
			System.out.println("Please enter the name of your restaurant.");
			String name =  reader.readLine();
			
			//check if name exists in list of restaurants
			boolean found =  false;
			for(Restaurant r : platform.restaurants) {
				if(r.getName()==name) {
					found = true;
					System.out.println("Please enter your restaurant password.");
					String password =  reader.readLine();
					if(platform.restaurantPasswords.get(r) ==password) {
						System.out.println("You are logged in.");
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
			}
		}
	}
}
