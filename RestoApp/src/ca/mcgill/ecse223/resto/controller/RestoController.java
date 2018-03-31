package ca.mcgill.ecse223.resto.controller;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse223.resto.controller.InvalidInputException;
import ca.mcgill.ecse223.resto.application.RestoApplication;
import ca.mcgill.ecse223.resto.model.Menu;
import ca.mcgill.ecse223.resto.model.MenuItem;
import ca.mcgill.ecse223.resto.model.MenuItem.ItemCategory;
import ca.mcgill.ecse223.resto.model.Order;
import ca.mcgill.ecse223.resto.model.Reservation;
import ca.mcgill.ecse223.resto.model.RestoApp;
import ca.mcgill.ecse223.resto.model.Seat;
import ca.mcgill.ecse223.resto.model.Table;

public class RestoController {
	public RestoController() {
		
	}
	
	public static void moveTable(Table table, int x, int y) throws InvalidInputException {

       String error = "";
       int length;
       int width;
       if (table == null) {
            error = "Table not found.";
            throw new InvalidInputException(error);
       }
        
       RestoApp restoApp = RestoApplication.getRestoApp();
       width = table.getWidth();
       length = table.getLength();
       List<Table> currentTable = restoApp.getTables();
       for (Table currentTables : currentTable) {
    	   if ( (x + width) != (currentTables.getX() + currentTables.getWidth()) && (y + length) != (currentTables.getX() + currentTables.getLength()) ) {
                table.setX(x);
                table.setY(y);        
    	   }
    	   else {
    		   error = "There is already a table in this location";
    		   throw new InvalidInputException(error.trim());
         }
        
       }
    RestoApplication.save();
    }
	
	public static void createTable (int number, int x, int y, int width, int length, int numberOfSeats) throws InvalidInputException {
		String error = "";
		if (x < 0 || y < 0) {
			error = "The position of te table cannot be chararterised by negative x and y variables.";
		}
		if (number <= 0) {
			error = "The number of a table has to be positive.";
		}
		if (width <= 0 || length <= 0) {
			error = "The width and the length has to be positive.";
		}
		if (numberOfSeats <= 0) {
			error = "The number of seats needs to be positive.";
		}
		
		RestoApp r = RestoApplication.getRestoApp();
		List<Table> currentTables = r.getCurrentTables();
		
		for (Table currentTable : currentTables) {
			if (currentTable.doesOverlaps(x, y, width, length)) {
				error = "Cannot create table since it overlaps with other, please change the location or the dimensions";
			}
		}
		
		if (error.length() > 0) {
			throw new InvalidInputException(error.trim());
		}
		
		
		Table table;
		try {
		table = new Table(number, x, y, width, length, r);
		}
		catch (RuntimeException e) {
			error = e.getMessage();
			if (error.equals("Cannot create due to duplicate number")) {
				error = "A table with this number already exists. Please use a different number.";
			}
			throw new InvalidInputException(error);
		}
		
		r.addCurrentTable(table);
		r.addTable(table);
		
		for (int i = 1 ; i <= numberOfSeats; i++) {
			Seat seat = table.addSeat();
			table.addCurrentSeat(seat);
		}
		
		try {
			RestoApplication.save();
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
		
		
	}
	
	public static List<Table> getTables() {
		return RestoApplication.getRestoApp().getCurrentTables();
	}
	
	public static void removeTable(int number) throws InvalidInputException {
		Table foundTable = Table.getWithNumber(number);
		removeTable(foundTable);
	}
	public static void removeTable(Table table) throws InvalidInputException {
		String error = "";
		if (table == null) {
			error = "Table not found.";
			throw new InvalidInputException(error.trim());
		}
		RestoApp restoApp = RestoApplication.getRestoApp();
		List<Order> currentOrders = restoApp.getCurrentOrders();
		for (Order order : currentOrders) {
			List<Table> tables = order.getTables();
			boolean inUse = tables.contains(table);
			if (inUse == true) {
				error = "Table in use.";
				throw new InvalidInputException(error.trim());
			}
		}
		restoApp.removeCurrentTable(table);
		RestoApplication.save();
	}
	
		public static void updateTable(int number, int newNumber, int numberOfSeats) throws InvalidInputException {
		Table foundTable = Table.getWithNumber(number);
		updateTable(foundTable, newNumber, numberOfSeats);
	}
	
	
	public static void updateTable(Table table, int newNumber, int numberOfSeats) throws InvalidInputException {
		String error = "";
		if (table == null) {
			error = "Table not found.";
			throw new InvalidInputException(error.trim()); 
		}
		else if (numberOfSeats < 0) {
			error = "Invalid number of seats.";
			throw new InvalidInputException(error.trim());
		}
		else if (newNumber < 0) {
			error = "Invalid table number.";
			throw new InvalidInputException(error.trim());
		}
		
		RestoApp restoApp = RestoApplication.getRestoApp();
		List<Order> currentOrders = restoApp.getCurrentOrders();
		for (Order order : currentOrders) {
			List<Table> tables = order.getTables();
			boolean inUse = tables.contains(table);
			if (inUse == true) {
				error = "Table in use.";
				throw new InvalidInputException(error.trim());
			}
		}
		
		try {
			table.setNumber(newNumber);
		}
		catch (RuntimeException e) {
			error = e.getMessage();
			if (error.equals("Cannot create due to duplicate number")) {
				error = "A table with this number already exists. Please use a different number.";
			}
			throw new InvalidInputException(error);
		}	
		
		
		int n = table.numberOfCurrentSeats();
		for (int i = 1; i < (numberOfSeats - n); i++) {
			 Seat seat = table.addSeat();
			 table.addCurrentSeat(seat);
		}
		
		
		for (int j = 1; j < (n - numberOfSeats); j++) {
			 Seat seat = table.getCurrentSeat(0);
			 table.removeCurrentSeat(seat);
		}

		RestoApplication.save();
	
	}
	
	public static List<ItemCategory> getItemCategories(){
		
		List <ItemCategory> categorieList = new ArrayList<ItemCategory>();
		
		
		categorieList.add(ItemCategory.Appetizer);
		categorieList.add(ItemCategory.Main);
		categorieList.add(ItemCategory.Dessert);
		categorieList.add(ItemCategory.AlcoholicBeverage);		
		categorieList.add(ItemCategory.NonAlcoholicBeverage);
		
		
		return categorieList;
		
	}
	
	public static List<MenuItem> getMenuItems(ItemCategory itemCategory){
		
		List<MenuItem> itemsElements = new ArrayList<MenuItem>();//create my list of item desired
		for(MenuItem menu_item: RestoApplication.getRestoApp().getMenu().getMenuItems()) //getting each element in our menu item List
			if(menu_item.getItemCategory().equals(itemCategory) && menu_item.hasCurrentPricedMenuItem())//if its the same element type and its price exist then we add it to the list
				itemsElements.add(menu_item);
			
		return itemsElements;
	}
	
	public static void reserve(List <String> tables, Date date, Time time, int numberInParty, String contactName, 
			String contactEmailAddress, String contactPhoneNumber) throws InvalidInputException{
		List<Table> tableList = new ArrayList<Table>();
		for(String tableNumberStr : tables) {
			int tableNumberInt = Integer.parseInt(tableNumberStr);
			tableList.add(Table.getWithNumber(tableNumberInt));
		}
		reserve(date, time, numberInParty, contactName, contactEmailAddress, contactPhoneNumber, tableList);
	}
	
	public static void reserve(Date date, Time time, int numberInParty, String contactName, 
			String contactEmailAddress, String contactPhoneNumber, List <Table> tables) throws InvalidInputException{
			
			String error = "";
			for(Table table : tables) {
				if(table == null) {
					error = "One of these tables does not exist";
					throw new InvalidInputException(error.trim());
				}
			}
			if (date == null || time == null || contactName == null || contactEmailAddress == null || contactPhoneNumber == null) {
				error = "Missing reservation input.";
				throw new InvalidInputException(error.trim());
			}
			if (numberInParty <= 0) {
				error = "Number of people in party must be positive.";
				throw new InvalidInputException(error.trim());
			}
			RestoApp r = RestoApplication.getRestoApp();
			List <Table> currentTables = r.getCurrentTables();
			int seatCapacity = 0;
			for (Table table : tables) {
				boolean current = currentTables.contains(table);
				if (current == false) {
					error = "Table: " + table.getNumber() + " does not exist";
					throw new InvalidInputException(error.trim());
				}
				seatCapacity += table.numberOfCurrentSeats();
				List <Reservation> reservations = table.getReservations();
				for (Reservation reservation : reservations) {
					boolean overlaps = reservation.doesOverlap(date, time);
					if (overlaps) {
						error = "Reservation overlaps with table: " + table.getNumber();
						throw new InvalidInputException(error.trim());
					}
				}
			}
			if (seatCapacity < numberInParty) {
				error = "Not enough seats for party.";
				throw new InvalidInputException(error.trim());
			}
			Table[] currentTablesArray = new Table[currentTables.size()];
			currentTables.toArray(currentTablesArray);
			Reservation res = new Reservation(date, time, numberInParty, contactName, contactEmailAddress, contactPhoneNumber, r,currentTablesArray);
			System.out.println("Added reservation for tables: ");
			for (Table table : currentTablesArray) {
				System.out.print(table.getNumber());
			}
			RestoApplication.save();
		}
	
	public static void startOrder(List<Table> tables) throws InvalidInputException {
		String error = "";
		if(tables == null) {
			error = "Need to select tables";
			throw new InvalidInputException(error.trim());
		}
		RestoApp r = RestoApplication.getRestoApp();
		List<Table> currentTables = r.getCurrentTables();
		for (Table table : tables) {
			boolean current = currentTables.contains(table);
			if (current == false) {
				error = "Table: "+table.getNumber()+" does not exist.";
				throw new InvalidInputException(error.trim());
			}
		}
		boolean orderCreated = false;
		Order newOrder = null;
		for(Table table : tables) {
			if(orderCreated) {
				table.addToOrder(newOrder);
			}
			else {
				Order lastOrder = null;
				if(table.numberOfOrders() > 0) {
					lastOrder = table.getOrder(table.numberOfOrders()-1);
				}
				table.startOrder();
				if (table.numberOfOrders() > 0 && !table.getOrder(table.numberOfOrders()-1).equals(lastOrder)) {
					orderCreated = true;
					newOrder = table.getOrder(table.numberOfOrders()-1);
				}
			}
		}
		r.addCurrentOrder(newOrder);
		RestoApplication.save();
	}
	
	public static void endOrder(Order order) throws InvalidInputException {
		String error = "";
		if (order == null) {
			error = "Must specify order to end.";
			throw new InvalidInputException(error.trim());
		}
		RestoApp r = RestoApplication.getRestoApp();
		List<Order> currentOrders = r.getCurrentOrders();
		boolean current = currentOrders.contains(order);
		if(current == false) {
			error = "Order does not exist.";
			throw new InvalidInputException(error.trim());
		}
		List<Table> tables = order.getTables();
		for(Table table : tables) {
			if (table.numberOfOrders() > 0 && table.getOrder(table.numberOfOrders()-1).equals(order)) {
				table.endOrder(order);
			}
		}
		if(allTablesAvailableOrDifferentCurrentOrder(tables,order)) {
			r.removeCurrentOrder(order);
		}
		RestoApplication.save();
	}

	private static boolean allTablesAvailableOrDifferentCurrentOrder(List<Table> tables, Order order) {
		boolean result = true;
		for (Table table : tables) {
			if (table.getStatus() != Table.Status.Available || table.getOrder(table.numberOfOrders()-1).equals(order)) {
				result = false;
				break;
			}
		}
		return result;
	}

}
