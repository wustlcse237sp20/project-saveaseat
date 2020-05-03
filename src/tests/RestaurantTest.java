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
		testRestaurant = new Restaurant("Test", 1, 1, 1);
		testReservation = new Reservation("Test", "Test", 1, 1, 1800, "none", 123); 
	}

	@Test
	void testGetName() {
		assertEquals("Test", testRestaurant.getName()); 
	}

	@Test
	void testGetMaxCapacity() {
		assertEquals(1, testRestaurant.getMaxCapacity()); 
	}

	@Test
	void testGetCurrentCapacity() {
		assertEquals(0, testRestaurant.getCurrentCapacity());
	}

	@Test
	void testGetOpeningTime() {
		assertEquals(1, testRestaurant.getOpeningTime());
	}

	@Test
	void testGetClosingTime() {
		assertEquals(1, testRestaurant.getClosingTime());
	}

	@Test
	void testAddReservation() {
		testRestaurant.addReservation(testReservation); 
		assertEquals(1, testRestaurant.listReservations.size()); 
	}

}