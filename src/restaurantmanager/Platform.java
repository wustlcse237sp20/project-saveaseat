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
	
	public String getPassword(Restaurant r) {
		return this.restaurantPasswords.get(r);
	}
	
	public String getPassword(String name) {
		for (Restaurant r : this.restaurants) {
			if (name.equals(r.getName())) {
				return this.getPassword(r);
			}
		}
		return "0";
	}
	
	public List<Restaurant> getRestaurants() {
		return this.restaurants;
	}
	
	public boolean restaurantInSystem(String name) {
		for (Restaurant r: this.restaurants) {
			if (name.equals(r.getName())) {
				System.out.println(r.getName());
				return true;
			}
		}
		return false;
	}
	
	public void seeRestaurants() {
		for(Restaurant r: this.restaurants) {
			System.out.println(r.getName());
		}
	}
	
	public void addRestaurant(Restaurant r) {
		this.restaurants.add(r);
	}
	
	public void addRestaurantPassword(Restaurant r, String password) {
		this.restaurantPasswords.put(r, password);
	}
	
	 
	public Restaurant findRestaurant(String name) {
		for(Restaurant r : this.restaurants) {
			if(r.getName().equals(name)) {
				return r;
			}
		}
		return null;
	}
}
