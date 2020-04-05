package restaurantmanager;

import java.util.List;
import java.util.Map;

public class Platform {
	List<Restaurant> restaurants;
	Map<Restaurant, String> restaurantPasswords;
	
	
	public String getPassword(Restaurant r) {
		return this.restaurantPasswords.get(r);
	}
	
	public void seeRestaurants() {
		for(Restaurant r: this.restaurants) {
			System.out.println(r);
		}
	}
	
	public void addRestaurant(Restaurant r) {
		this.restaurants.add(r);
		restaurants.add(r);
	}
	
	public void addRestaurantPassword(Restaurant r, String password) {
		this.restaurantPasswords.put(r, password);
	}
}
