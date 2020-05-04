package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import restaurantmanager.Restaurant;
import restaurantmanager.Reservation;
import restaurantmanager.Platform;

class RestaurantTest {

	private Restaurant testRestaurant;
	private Reservation testReservation; 
	
	@BeforeEach
	void setupTestingObjects() {
		testRestaurant = new Restaurant("Jeff's Vegan Treats", 100, 1000, 2000);
		testReservation = new Reservation("Jeff's Vegan Treats", "James", 1, 1, 1800, "none", 123); 
	}

	@Test
	void testGetName() {
		assertEquals("Jeff's Vegan Treats", testRestaurant.getName()); 
	}

	@Test
	void testGetMaxCapacity() {
		assertEquals(100, testRestaurant.getMaxCapacity()); 
	}

	@Test
	void testGetCurrentCapacity() {
		assertEquals(0, testRestaurant.getCurrentCapacity());
	}

	@Test
	void testGetOpeningTime() {
		assertEquals(1000, testRestaurant.getOpeningTime());
	}

	@Test
	void testGetClosingTime() {
		assertEquals(2000, testRestaurant.getClosingTime());
	}

	@Test
	void testAddReservation() {
		testRestaurant.addReservation(testReservation); 
		assertEquals(1, testRestaurant.listReservations.size()); 
	}
	
	@Test
	void testGetReservations() {
		testRestaurant.addReservation(testReservation); 
		assertNotNull(testRestaurant.getReservations()); 
	}

}