package ca.mcgill.ecse223.resto.application;

import ca.mcgill.ecse223.resto.view.RestoPage;
import ca.mcgill.ecse223.resto.model.RestoApp;

public class RestoApplication {
	
	private static RestoApp restoApp;
	
	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new RestoPage().setVisible(true);
			}
		});
	}
	
	public static RestoApp getRestoApp() {
		if (restoApp == null) {
			restoApp = new RestoApp();
		}
		return restoApp;
	}

	public static void save() {
		// TODO Auto-generated method stub
		
	}

}
