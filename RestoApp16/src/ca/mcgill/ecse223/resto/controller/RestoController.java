package ca.mcgill.ecse223.resto.controller;

import ca.mcgill.ecse223.resto.model.Table;

public class RestoController {
	public RestoController() {
		
	}
	public static void deleteTable(Table table) {
		table.delete();
	}
}
