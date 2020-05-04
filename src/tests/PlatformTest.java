package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import restaurantmanager.Restaurant;
import restaurantmanager.Reservation;
import restaurantmanager.Platform;

class PlatformTest {

	Platform p;
	
	@BeforeEach
	void testingSetup() {
		p = new Platform();
	}
	
	@Test
	void testGetPassword() {
		Restaurant r = new Restaurant("Res", 10, 1100, 2300);
		p.addRestaurantPassword(r, "12345");
		assertEquals("12345", p.getPassword(r));
	}
	
	
	@Test
	void testAddRestaurant() {
		Restaurant r = new Restaurant("Res", 10, 1100, 2300);
		p.addRestaurant(r);
		assertTrue(p.restaurants.contains(r));
	}
	
	@Test
	void testAddRestaurantPassword() {
		Restaurant r = new Restaurant("Res", 10, 1100, 2300);
		p.addRestaurant(r);
		p.addRestaurantPassword(r, "12345");
		assertEquals("12345", p.restaurantPasswords.get(r));
	}
	

}