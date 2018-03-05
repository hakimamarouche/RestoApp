package ca.mcgill.ecse223.resto.controller;

import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse223.resto.controller.InvalidInputException;
import ca.mcgill.ecse223.resto.application.RestoApplication;
import ca.mcgill.ecse223.resto.model.Menu;
import ca.mcgill.ecse223.resto.model.MenuItem;
import ca.mcgill.ecse223.resto.model.MenuItem.ItemCategory;
import ca.mcgill.ecse223.resto.model.Order;
import ca.mcgill.ecse223.resto.model.RestoApp;
import ca.mcgill.ecse223.resto.model.Seat;
import ca.mcgill.ecse223.resto.model.Table;

public class RestoController {
	public RestoController() {
		
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
		if (error.length() > 0) {
			throw new InvalidInputException(error.trim());
		}
		
		RestoApp r = RestoApplication.getRestoApp();
		List<Table> currentTables = r.getCurrentTables();
		
//		for (Table currentTable : currentTables) {
//			try {
//			}
//			catch (RuntimeException e) {
//				if (currentTable.doesOverlap()) {
//					error = "Tables overlaps";
//				}
//			}
//		}
		
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
		
		for (int i = 1 ; i <= numberOfSeats; i++) {
			Seat seat = table.addSeat();
			table.addCurrentSeat(seat);
		}
		RestoApplication.save();
	}
	
	public static List<Table> getTables() {
		return RestoApplication.getRestoApp().getTables();
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
		// TODO restoApp.save();
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
	
}
