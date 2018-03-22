package ca.mcgill.ecse223.resto.persistence;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import ca.mcgill.ecse223.resto.model.MenuItem;
import ca.mcgill.ecse223.resto.model.RestoApp;

public class PersistenceObjectStream {

	public static void serialize(RestoApp restoApp) {
		// TODO Auto-generated method stub
		
		RestoApp app = null;
		  try {
		   FileInputStream fileIn = new FileInputStream("C:\\Users\\farou\\OneDrive\\Documents\\Group16\\RestoApp\\src\\menu.resto");
		   ObjectInputStream in = new ObjectInputStream(fileIn);
		   app = (RestoApp) in.readObject();
		   in.close();
		   fileIn.close();
		  } catch (IOException ie) {
		   ie.printStackTrace();
		  } catch (ClassNotFoundException e) {
		   e.printStackTrace();
		  }
		   for(MenuItem mi : app.getMenu().getMenuItems()) {
			   
			   
			   System.out.println(mi.getName());
			  }
		
	}

	public static void setFilename(String filename) {
		// TODO Auto-generated method stub
		
	}

}
