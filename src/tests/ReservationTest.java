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


class ReservationTest {
	
	Reservation r;
	
	@BeforeEach
	void setupTest() {
		r = new Reservation("Hammy's", "Doug", 2, 1214, 1215, "none",3728389);
	}
	
	@Test
	void testGetName() {
		assertEquals("Doug", r.getName());
	}
	
	@Test
	void testGetNumPeople() {
		assertEquals(2, r.getNumPeople());
	}
	
	@Test
	void testGetDate() {
		assertEquals(1214, r.getDate());
	}
	
	@Test
	void testGetTime() {
		assertEquals(1215, r.getTime());
	}
	
	@Test
	void testGetId() {
		assertEquals(3728389, r.getId());
	}

}
