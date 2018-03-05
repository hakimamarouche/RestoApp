package ca.mcgill.ecse223.resto.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ca.mcgill.ecse223.resto.application.RestoApplication;
import ca.mcgill.ecse223.resto.model.RestoApp;

public class RestoControllerTest {

	@Before
	public void setUp() {
		// clear all data
		RestoApp r = RestoApplication.getRestoApp();
		r.delete();
	}

	@Test
	public void testCreateTableSuccess() {
		RestoApp r = RestoApplication.getRestoApp();
		int number = 1;
		int x = 200;
		int y = 200;
		int width = 20;
		int length = 20;
		int numberOfSeats = 1;

		try {
			RestoController.createTable(number, x, y, width, length, numberOfSeats);
		} catch (InvalidInputException e) {
			// Check that no error occurred
			fail();	
		}
		
		// Check model in memory
		checkResultTable(number, x, y, width, length, numberOfSeats, r, 1);

	}
	
	private void checkResultTable(int number, int x, int y, int width, int length, int numberOfSeats, RestoApp r, int numberTables) {
		assertEquals(numberTables, r.getTables().size());
		if (numberTables > 0) {
			assertEquals(number, r.getTable(0).getNumber());
			assertEquals(x, r.getTable(0).getX());
			assertEquals(y, r.getTable(0).getY());
			assertEquals(width, r.getTable(0).getWidth());
			assertEquals(length, r.getTable(0).getLength());
			assertEquals(numberOfSeats, r.getTable(0).getSeats().size());
			assertEquals(r, r.getTable(0).getRestoApp());
			assertEquals(0, r.getTable(0).getReservations().size());
			assertEquals(0, r.getTable(0).getOrders().size());
		}
		assertEquals(null, r.getMenu());
		assertEquals(0, r.getPricedMenuItems().size());
		assertEquals(0, r.getBills().size());
	}

}
