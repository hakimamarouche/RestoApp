package ca.mcgill.ecse223.resto.controller;

import ca.mcgill.ecse223.resto.application.RestoApplication;
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
}
