/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.27.0.3728.d139ed893 modeling language!*/

package ca.mcgill.ecse223.resto.model;
import java.sql.Date;
import java.sql.Time;
import java.util.*;

// line 25 "../../../../../main.ump"
// line 90 "../../../../../main.ump"
public class Reservation
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static int nextReservationId = 1;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Reservation Attributes
  private Date reservationDate;
  private Time reservedTime;
  private int numberOfPersons;
  private String customerName;
  private Time reservationDuration;
  private String contactInfo;

  //Autounique Attributes
  private int reservationId;

  //Reservation Associations
  private List<Table> tables;
  private RestoApp restoApp;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Reservation(Date aReservationDate, Time aReservedTime, int aNumberOfPersons, String aCustomerName, Time aReservationDuration, String aContactInfo, RestoApp aRestoApp, Table... allTables)
  {
    reservationDate = aReservationDate;
    reservedTime = aReservedTime;
    numberOfPersons = aNumberOfPersons;
    customerName = aCustomerName;
    reservationDuration = aReservationDuration;
    contactInfo = aContactInfo;
    reservationId = nextReservationId++;
    tables = new ArrayList<Table>();
    boolean didAddTables = setTables(allTables);
    if (!didAddTables)
    {
      throw new RuntimeException("Unable to create Reservation, must have at least 1 tables");
    }
    boolean didAddRestoApp = setRestoApp(aRestoApp);
    if (!didAddRestoApp)
    {
      throw new RuntimeException("Unable to create reservation due to restoApp");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setReservationDate(Date aReservationDate)
  {
    boolean wasSet = false;
    reservationDate = aReservationDate;
    wasSet = true;
    return wasSet;
  }

  public boolean setReservedTime(Time aReservedTime)
  {
    boolean wasSet = false;
    reservedTime = aReservedTime;
    wasSet = true;
    return wasSet;
  }

  public boolean setNumberOfPersons(int aNumberOfPersons)
  {
    boolean wasSet = false;
    numberOfPersons = aNumberOfPersons;
    wasSet = true;
    return wasSet;
  }

  public boolean setCustomerName(String aCustomerName)
  {
    boolean wasSet = false;
    customerName = aCustomerName;
    wasSet = true;
    return wasSet;
  }

  public boolean setReservationDuration(Time aReservationDuration)
  {
    boolean wasSet = false;
    reservationDuration = aReservationDuration;
    wasSet = true;
    return wasSet;
  }

  public boolean setContactInfo(String aContactInfo)
  {
    boolean wasSet = false;
    contactInfo = aContactInfo;
    wasSet = true;
    return wasSet;
  }

  public Date getReservationDate()
  {
    return reservationDate;
  }

  public Time getReservedTime()
  {
    return reservedTime;
  }

  public int getNumberOfPersons()
  {
    return numberOfPersons;
  }

  public String getCustomerName()
  {
    return customerName;
  }

  public Time getReservationDuration()
  {
    return reservationDuration;
  }

  public String getContactInfo()
  {
    return contactInfo;
  }

  public int getReservationId()
  {
    return reservationId;
  }

  public Table getTable(int index)
  {
    Table aTable = tables.get(index);
    return aTable;
  }

  public List<Table> getTables()
  {
    List<Table> newTables = Collections.unmodifiableList(tables);
    return newTables;
  }

  public int numberOfTables()
  {
    int number = tables.size();
    return number;
  }

  public boolean hasTables()
  {
    boolean has = tables.size() > 0;
    return has;
  }

  public int indexOfTable(Table aTable)
  {
    int index = tables.indexOf(aTable);
    return index;
  }

  public RestoApp getRestoApp()
  {
    return restoApp;
  }

  public static int minimumNumberOfTables()
  {
    return 1;
  }

  public boolean addTable(Table aTable)
  {
    boolean wasAdded = false;
    if (tables.contains(aTable)) { return false; }
    Reservation existingReservation = aTable.getReservation();
    if (existingReservation != null && existingReservation.numberOfTables() <= minimumNumberOfTables())
    {
      return wasAdded;
    }
    else if (existingReservation != null)
    {
      existingReservation.tables.remove(aTable);
    }
    tables.add(aTable);
    setReservation(aTable,this);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeTable(Table aTable)
  {
    boolean wasRemoved = false;
    if (tables.contains(aTable) && numberOfTables() > minimumNumberOfTables())
    {
      tables.remove(aTable);
      setReservation(aTable,null);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean setTables(Table... newTables)
  {
    boolean wasSet = false;
    if (newTables.length < minimumNumberOfTables())
    {
      return wasSet;
    }

    ArrayList<Table> checkNewTables = new ArrayList<Table>();
    HashMap<Reservation,Integer> reservationToNewTables = new HashMap<Reservation,Integer>();
    for (Table aTable : newTables)
    {
      if (checkNewTables.contains(aTable))
      {
        return wasSet;
      }
      else if (aTable.getReservation() != null && !this.equals(aTable.getReservation()))
      {
        Reservation existingReservation = aTable.getReservation();
        if (!reservationToNewTables.containsKey(existingReservation))
        {
          reservationToNewTables.put(existingReservation, new Integer(existingReservation.numberOfTables()));
        }
        Integer currentCount = reservationToNewTables.get(existingReservation);
        int nextCount = currentCount - 1;
        if (nextCount < 1)
        {
          return wasSet;
        }
        reservationToNewTables.put(existingReservation, new Integer(nextCount));
      }
      checkNewTables.add(aTable);
    }

    tables.removeAll(checkNewTables);

    for (Table orphan : tables)
    {
      setReservation(orphan, null);
    }
    tables.clear();
    for (Table aTable : newTables)
    {
      if (aTable.getReservation() != null)
      {
        aTable.getReservation().tables.remove(aTable);
      }
      setReservation(aTable, this);
      tables.add(aTable);
    }
    wasSet = true;
    return wasSet;
  }

  private void setReservation(Table aTable, Reservation aReservation)
  {
    try
    {
      java.lang.reflect.Field mentorField = aTable.getClass().getDeclaredField("reservation");
      mentorField.setAccessible(true);
      mentorField.set(aTable, aReservation);
    }
    catch (Exception e)
    {
      throw new RuntimeException("Issue internally setting aReservation to aTable", e);
    }
  }

  public boolean addTableAt(Table aTable, int index)
  {  
    boolean wasAdded = false;
    if(addTable(aTable))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTables()) { index = numberOfTables() - 1; }
      tables.remove(aTable);
      tables.add(index, aTable);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveTableAt(Table aTable, int index)
  {
    boolean wasAdded = false;
    if(tables.contains(aTable))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTables()) { index = numberOfTables() - 1; }
      tables.remove(aTable);
      tables.add(index, aTable);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addTableAt(aTable, index);
    }
    return wasAdded;
  }

  public boolean setRestoApp(RestoApp aRestoApp)
  {
    boolean wasSet = false;
    if (aRestoApp == null)
    {
      return wasSet;
    }

    RestoApp existingRestoApp = restoApp;
    restoApp = aRestoApp;
    if (existingRestoApp != null && !existingRestoApp.equals(aRestoApp))
    {
      existingRestoApp.removeReservation(this);
    }
    restoApp.addReservation(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    for(Table aTable : tables)
    {
      setReservation(aTable,null);
    }
    tables.clear();
    RestoApp placeholderRestoApp = restoApp;
    this.restoApp = null;
    if(placeholderRestoApp != null)
    {
      placeholderRestoApp.removeReservation(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "reservationId" + ":" + getReservationId()+ "," +
            "numberOfPersons" + ":" + getNumberOfPersons()+ "," +
            "customerName" + ":" + getCustomerName()+ "," +
            "contactInfo" + ":" + getContactInfo()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "reservationDate" + "=" + (getReservationDate() != null ? !getReservationDate().equals(this)  ? getReservationDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "reservedTime" + "=" + (getReservedTime() != null ? !getReservedTime().equals(this)  ? getReservedTime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "reservationDuration" + "=" + (getReservationDuration() != null ? !getReservationDuration().equals(this)  ? getReservationDuration().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "restoApp = "+(getRestoApp()!=null?Integer.toHexString(System.identityHashCode(getRestoApp())):"null");
  }
}