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
		Restaurant r = new Restaurant("Res", 1, 1, 1);
		p.addRestaurantPassword(r, "12345");
		assertEquals("12345", p.getPassword(r));
	}
	
	
	@Test
	void testAddRestaurant() {
		Restaurant r = new Restaurant("Res", 1, 1, 1);
		p.addRestaurant(r);
		assertTrue(p.restaurants.contains(r));
	}
	
	@Test
	void testAddRestaurantPassword() {
		Restaurant r = new Restaurant("Res", 1, 1, 1);
		p.addRestaurant(r);
		p.addRestaurantPassword(r, "12345");
		assertEquals("12345", p.restaurantPasswords.get(r));
	}
	
	@Test
	void getRestaurants() {
		Restaurant r = new Restaurant("Res", 1, 1, 1);
		p.addRestaurant(r);
		p.addRestaurantPassword(r, "12345");
		Restaurant r2 = new Restaurant("Res2", 1, 1, 2);
		p.addRestaurant(r2);
		p.addRestaurantPassword(r2, "12345");
		assertEquals(2, p.getRestaurants().size());
	}

}