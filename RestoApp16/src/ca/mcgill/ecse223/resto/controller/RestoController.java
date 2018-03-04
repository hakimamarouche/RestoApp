package ca.mcgill.ecse223.resto.controller;

import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse223.resto.application.RestoApplication;
import ca.mcgill.ecse223.resto.model.Menu;
import ca.mcgill.ecse223.resto.model.MenuItem;
import ca.mcgill.ecse223.resto.model.MenuItem.ItemCategory;
import ca.mcgill.ecse223.resto.model.RestoApp;
import ca.mcgill.ecse223.resto.model.Table;

public class RestoController {
	public RestoController() {
		
	}
	public static void deleteTable(int number) throws InvalidInputException {
		RestoApp restoApp = RestoApplication.getRestoApp();
		Table foundTable = null;
		for(Table table : restoApp.getTables()) {
			if (table.getNumber() == number) {
				foundTable = table;
			}
		}
		if (foundTable != null) {
			foundTable.delete();
			/*
			try {
				RestoApplication.save();
			} catch (RuntimeException e) {
				throw new InvalidInputException(e.getMessage());
			}
			*/
		}
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
