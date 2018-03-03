package ca.mcgill.ecse223.resto.application;

import ca.mcgill.ecse223.resto.view.RestoPage;
import ca.mcgill.ecse223.resto.model.RestoApp;
import ca.mcgill.ecse223.resto.persistence.PersistenceObjectStream;

public class RestoApplication {
	
	private static RestoApp restoApp;
	private static String filename = "menu.resto";
	
	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new RestoPage().setVisible(true);
			}
		});
	}
	
	public static RestoApp getRestoApp() {
		if (restoApp == null) {
			//load model
			restoApp = new RestoApp();
		}
		return restoApp;
	}
	/**
	 * Persistence
	 * 
	public static void save() {
		PersistenceObjectStream.serialize(restoApp);
	}
	
	public static RestoApp load() {
		PersistenceObjectStream.setFilename(filename);
		// model cannot be loaded - create empty RestoApp
		if (restoApp == null) {
			restoApp = new RestoApp();
		}
		else {
			restoApp.reinitialize();
		}
		return restoApp;
	}
	*/
	
	public static void setFilename(String newFilename) {
		filename = newFilename;
	}

}
