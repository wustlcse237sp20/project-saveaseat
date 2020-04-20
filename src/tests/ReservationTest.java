package tests;


import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
	void testSeeRestaurants() {
		Restaurant r = new Restaurant("Res", 1, 1, 1);
		Restaurant r1 = new Restaurant("Res1", 1, 1, 1);
		Restaurant r2 = new Restaurant("Res2", 1, 1, 1);
		Restaurant r3 = new Restaurant("Res3", 1, 1, 1);
		p.addRestaurant(r);
		p.addRestaurant(r1);
		p.addRestaurant(r2);
		p.addRestaurant(r3);
		
		ByteArrayOutputStream printed = new ByteArrayOutputStream();
		System.setOut(new PrintStream(printed));
		
		for(int i = 0; i < p.restaurants.size(); i++) {
			System.out.println(p.restaurants.get(i));
		}
		
		String correctOutput = r.getName() +"\n"+r1.getName() +"\n"+r2.getName() +"\n"+r3.getName();
		assertEquals(correctOutput, printed.toString());
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

}