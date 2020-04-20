package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import restaurantmanager.Restaurant;

class RestaurantTest {

	private Restaurant r;
	
	@BeforeEach
	void setupTestingObjects() {
		r = new Restaurant("Test", 1, 1, 1);
	}

	@Test
	void testRestaurant() {
		fail("Not yet implemented");
	}

	@Test
	void testGetName() {
//		fail("Not yet implemented");
		assertEquals("Test", r.getName()); 
	}

	@Test
	void testGetMaxCapacity() {
		fail("Not yet implemented");
	}

	@Test
	void testGetCurrentCapacity() {
		fail("Not yet implemented");
	}

	@Test
	void testGetOpeningTime() {
		fail("Not yet implemented");
	}

	@Test
	void testGetClosingTime() {
		fail("Not yet implemented");
	}

	@Test
	void testGetId() {
		fail("Not yet implemented");
	}

	@Test
	void testAddReservation() {
		fail("Not yet implemented");
	}

	@Test
	void testSeeReservations() {
		fail("Not yet implemented");
	}

}
