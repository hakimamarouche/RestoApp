package ca.mcgill.ecse223.resto.persistence;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import ca.mcgill.ecse223.resto.model.MenuItem;
import ca.mcgill.ecse223.resto.model.RestoApp;

public class PersistenceObjectStream {
	
	private static String filename = "output.txt";
	
	public static void serialize(Object object) {
		FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream(filename);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(object);
			out.close();
			fileOut.close();
		} catch (Exception e) {
			throw new RuntimeException("Could not save data to file '" + filename + "'.");
		}

	}
	
	public static Object deserialize() {
		Object o = null;
		ObjectInputStream in;
		try {
			FileInputStream fileIn = new FileInputStream(filename);
			in = new ObjectInputStream(fileIn);
			o = in.readObject();
			in.close();
			fileIn.close();
		} catch (Exception e) {
			o = null;
		}
		return o;
	}
	
	public static void setFilename(String newFilename) {
		filename = newFilename;
	}

	/*public static void serialize(RestoApp restoApp) {
		// TODO Auto-generated method stub
		
		RestoApp app = null;
		  try {
		   FileInputStream fileIn = new FileInputStream("menu.resto");
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
		
	}*/

}
