/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.27.0.3728.d139ed893 modeling language!*/

package ca.mcgill.ecse223.resto.model;
import java.util.*;

// line 37 "../../../../../main.ump"
// line 97 "../../../../../main.ump"
public class Table
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Table Attributes
  private int numberOfSeats;
  private List<int> tableLocation;
  private int tableNumber;
  private boolean isAvailable;

  //Table Associations
  private List<Bill> bills;
  private List<Order> orders;
  private Reservation reservation;
  private RestoApp restoApp;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Table(int aNumberOfSeats, int aTableNumber, boolean aIsAvailable, RestoApp aRestoApp)
  {
    numberOfSeats = aNumberOfSeats;
    tableLocation = new ArrayList<int>();
    tableNumber = aTableNumber;
    isAvailable = aIsAvailable;
    bills = new ArrayList<Bill>();
    orders = new ArrayList<Order>();
    boolean didAddRestoApp = setRestoApp(aRestoApp);
    if (!didAddRestoApp)
    {
      throw new RuntimeException("Unable to create table due to restoApp");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setNumberOfSeats(int aNumberOfSeats)
  {
    boolean wasSet = false;
    numberOfSeats = aNumberOfSeats;
    wasSet = true;
    return wasSet;
  }

  public boolean addTableLocation(int aTableLocation)
  {
    boolean wasAdded = false;
    wasAdded = tableLocation.add(aTableLocation);
    return wasAdded;
  }

  public boolean removeTableLocation(int aTableLocation)
  {
    boolean wasRemoved = false;
    wasRemoved = tableLocation.remove(aTableLocation);
    return wasRemoved;
  }

  public boolean setTableNumber(int aTableNumber)
  {
    boolean wasSet = false;
    tableNumber = aTableNumber;
    wasSet = true;
    return wasSet;
  }

  public boolean setIsAvailable(boolean aIsAvailable)
  {
    boolean wasSet = false;
    isAvailable = aIsAvailable;
    wasSet = true;
    return wasSet;
  }

  public int getNumberOfSeats()
  {
    return numberOfSeats;
  }

  public int getTableLocation(int index)
  {
    int aTableLocation = tableLocation.get(index);
    return aTableLocation;
  }

  public int[] getTableLocation()
  {
    int[] newTableLocation = tableLocation.toArray(new int[tableLocation.size()]);
    return newTableLocation;
  }

  public int numberOfTableLocation()
  {
    int number = tableLocation.size();
    return number;
  }

  public boolean hasTableLocation()
  {
    boolean has = tableLocation.size() > 0;
    return has;
  }

  public int indexOfTableLocation(int aTableLocation)
  {
    int index = tableLocation.indexOf(aTableLocation);
    return index;
  }

  public int getTableNumber()
  {
    return tableNumber;
  }

  public boolean getIsAvailable()
  {
    return isAvailable;
  }

  public Bill getBill(int index)
  {
    Bill aBill = bills.get(index);
    return aBill;
  }

  public List<Bill> getBills()
  {
    List<Bill> newBills = Collections.unmodifiableList(bills);
    return newBills;
  }

  public int numberOfBills()
  {
    int number = bills.size();
    return number;
  }

  public boolean hasBills()
  {
    boolean has = bills.size() > 0;
    return has;
  }

  public int indexOfBill(Bill aBill)
  {
    int index = bills.indexOf(aBill);
    return index;
  }

  public Order getOrder(int index)
  {
    Order aOrder = orders.get(index);
    return aOrder;
  }

  public List<Order> getOrders()
  {
    List<Order> newOrders = Collections.unmodifiableList(orders);
    return newOrders;
  }

  public int numberOfOrders()
  {
    int number = orders.size();
    return number;
  }

  public boolean hasOrders()
  {
    boolean has = orders.size() > 0;
    return has;
  }

  public int indexOfOrder(Order aOrder)
  {
    int index = orders.indexOf(aOrder);
    return index;
  }

  public Reservation getReservation()
  {
    return reservation;
  }

  public boolean hasReservation()
  {
    boolean has = reservation != null;
    return has;
  }

  public RestoApp getRestoApp()
  {
    return restoApp;
  }

  public static int minimumNumberOfBills()
  {
    return 0;
  }

  public boolean addBill(Bill aBill)
  {
    boolean wasAdded = false;
    if (bills.contains(aBill)) { return false; }
    bills.add(aBill);
    if (aBill.indexOfTable(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aBill.addTable(this);
      if (!wasAdded)
      {
        bills.remove(aBill);
      }
    }
    return wasAdded;
  }

  public boolean removeBill(Bill aBill)
  {
    boolean wasRemoved = false;
    if (!bills.contains(aBill))
    {
      return wasRemoved;
    }

    int oldIndex = bills.indexOf(aBill);
    bills.remove(oldIndex);
    if (aBill.indexOfTable(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aBill.removeTable(this);
      if (!wasRemoved)
      {
        bills.add(oldIndex,aBill);
      }
    }
    return wasRemoved;
  }

  public boolean addBillAt(Bill aBill, int index)
  {  
    boolean wasAdded = false;
    if(addBill(aBill))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBills()) { index = numberOfBills() - 1; }
      bills.remove(aBill);
      bills.add(index, aBill);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveBillAt(Bill aBill, int index)
  {
    boolean wasAdded = false;
    if(bills.contains(aBill))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBills()) { index = numberOfBills() - 1; }
      bills.remove(aBill);
      bills.add(index, aBill);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addBillAt(aBill, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfOrders()
  {
    return 0;
  }

  public boolean addOrder(Order aOrder)
  {
    boolean wasAdded = false;
    if (orders.contains(aOrder)) { return false; }
    orders.add(aOrder);
    if (aOrder.indexOfTable(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aOrder.addTable(this);
      if (!wasAdded)
      {
        orders.remove(aOrder);
      }
    }
    return wasAdded;
  }

  public boolean removeOrder(Order aOrder)
  {
    boolean wasRemoved = false;
    if (!orders.contains(aOrder))
    {
      return wasRemoved;
    }

    int oldIndex = orders.indexOf(aOrder);
    orders.remove(oldIndex);
    if (aOrder.indexOfTable(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aOrder.removeTable(this);
      if (!wasRemoved)
      {
        orders.add(oldIndex,aOrder);
      }
    }
    return wasRemoved;
  }

  public boolean addOrderAt(Order aOrder, int index)
  {  
    boolean wasAdded = false;
    if(addOrder(aOrder))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfOrders()) { index = numberOfOrders() - 1; }
      orders.remove(aOrder);
      orders.add(index, aOrder);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveOrderAt(Order aOrder, int index)
  {
    boolean wasAdded = false;
    if(orders.contains(aOrder))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfOrders()) { index = numberOfOrders() - 1; }
      orders.remove(aOrder);
      orders.add(index, aOrder);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addOrderAt(aOrder, index);
    }
    return wasAdded;
  }

  public boolean setReservation(Reservation aReservation)
  {
    //
    // This source of this source generation is association_SetOptionalOneToMandatoryMany.jet
    // This set file assumes the generation of a maximumNumberOfXXX method does not exist because 
    // it's not required (No upper bound)
    //   
    boolean wasSet = false;
    Reservation existingReservation = reservation;

    if (existingReservation == null)
    {
      if (aReservation != null)
      {
        if (aReservation.addTable(this))
        {
          existingReservation = aReservation;
          wasSet = true;
        }
      }
    } 
    else if (existingReservation != null)
    {
      if (aReservation == null)
      {
        if (existingReservation.minimumNumberOfTables() < existingReservation.numberOfTables())
        {
          existingReservation.removeTable(this);
          existingReservation = aReservation;  // aReservation == null
          wasSet = true;
        }
      } 
      else
      {
        if (existingReservation.minimumNumberOfTables() < existingReservation.numberOfTables())
        {
          existingReservation.removeTable(this);
          aReservation.addTable(this);
          existingReservation = aReservation;
          wasSet = true;
        }
      }
    }
    if (wasSet)
    {
      reservation = existingReservation;
    }
    return wasSet;
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
      existingRestoApp.removeTable(this);
    }
    restoApp.addTable(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    ArrayList<Bill> copyOfBills = new ArrayList<Bill>(bills);
    bills.clear();
    for(Bill aBill : copyOfBills)
    {
      if (aBill.numberOfTables() <= Bill.minimumNumberOfTables())
      {
        aBill.delete();
      }
      else
      {
        aBill.removeTable(this);
      }
    }
    ArrayList<Order> copyOfOrders = new ArrayList<Order>(orders);
    orders.clear();
    for(Order aOrder : copyOfOrders)
    {
      if (aOrder.numberOfTables() <= Order.minimumNumberOfTables())
      {
        aOrder.delete();
      }
      else
      {
        aOrder.removeTable(this);
      }
    }
    if (reservation != null)
    {
      if (reservation.numberOfTables() <= 1)
      {
        reservation.delete();
      }
      else
      {
        Reservation placeholderReservation = reservation;
        this.reservation = null;
        placeholderReservation.removeTable(this);
      }
    }
    RestoApp placeholderRestoApp = restoApp;
    this.restoApp = null;
    if(placeholderRestoApp != null)
    {
      placeholderRestoApp.removeTable(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "numberOfSeats" + ":" + getNumberOfSeats()+ "," +
            "tableNumber" + ":" + getTableNumber()+ "," +
            "isAvailable" + ":" + getIsAvailable()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "reservation = "+(getReservation()!=null?Integer.toHexString(System.identityHashCode(getReservation())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "restoApp = "+(getRestoApp()!=null?Integer.toHexString(System.identityHashCode(getRestoApp())):"null");
  }
}