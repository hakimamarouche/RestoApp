/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.27.0.3728.d139ed893 modeling language!*/

package ca.mcgill.ecse223.resto.model;
import java.sql.Date;
import java.util.*;

// line 3 "../../../../../main.ump"
// line 70 "../../../../../main.ump"
public class Bill
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static int nextBillId = 1;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Bill Attributes
  private int numberOfPeople;
  private double total;
  private Date billingDate;

  //Autounique Attributes
  private int billId;

  //Bill Associations
  private List<Order> orders;
  private List<Table> tables;
  private RestoApp restoApp;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Bill(int aNumberOfPeople, double aTotal, Date aBillingDate, RestoApp aRestoApp, Table... allTables)
  {
    numberOfPeople = aNumberOfPeople;
    total = aTotal;
    billingDate = aBillingDate;
    billId = nextBillId++;
    orders = new ArrayList<Order>();
    tables = new ArrayList<Table>();
    boolean didAddTables = setTables(allTables);
    if (!didAddTables)
    {
      throw new RuntimeException("Unable to create Bill, must have at least 1 tables");
    }
    boolean didAddRestoApp = setRestoApp(aRestoApp);
    if (!didAddRestoApp)
    {
      throw new RuntimeException("Unable to create bill due to restoApp");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setNumberOfPeople(int aNumberOfPeople)
  {
    boolean wasSet = false;
    numberOfPeople = aNumberOfPeople;
    wasSet = true;
    return wasSet;
  }

  public boolean setTotal(double aTotal)
  {
    boolean wasSet = false;
    total = aTotal;
    wasSet = true;
    return wasSet;
  }

  public boolean setBillingDate(Date aBillingDate)
  {
    boolean wasSet = false;
    billingDate = aBillingDate;
    wasSet = true;
    return wasSet;
  }

  public int getNumberOfPeople()
  {
    return numberOfPeople;
  }

  public double getTotal()
  {
    return total;
  }

  public Date getBillingDate()
  {
    return billingDate;
  }

  public int getBillId()
  {
    return billId;
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

  public boolean isNumberOfOrdersValid()
  {
    boolean isValid = numberOfOrders() >= minimumNumberOfOrders();
    return isValid;
  }

  public static int minimumNumberOfOrders()
  {
    return 1;
  }

  public Order addOrder(RestoApp aRestoApp, Table[] allTables, MenuItem[] allMenuItems)
  {
    Order aNewOrder = new Order(this, aRestoApp, allTables, allMenuItems);
    return aNewOrder;
  }

  public boolean addOrder(Order aOrder)
  {
    boolean wasAdded = false;
    if (orders.contains(aOrder)) { return false; }
    Bill existingBill = aOrder.getBill();
    boolean isNewBill = existingBill != null && !this.equals(existingBill);

    if (isNewBill && existingBill.numberOfOrders() <= minimumNumberOfOrders())
    {
      return wasAdded;
    }
    if (isNewBill)
    {
      aOrder.setBill(this);
    }
    else
    {
      orders.add(aOrder);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeOrder(Order aOrder)
  {
    boolean wasRemoved = false;
    //Unable to remove aOrder, as it must always have a bill
    if (this.equals(aOrder.getBill()))
    {
      return wasRemoved;
    }

    //bill already at minimum (1)
    if (numberOfOrders() <= minimumNumberOfOrders())
    {
      return wasRemoved;
    }

    orders.remove(aOrder);
    wasRemoved = true;
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

  public boolean isNumberOfTablesValid()
  {
    boolean isValid = numberOfTables() >= minimumNumberOfTables();
    return isValid;
  }

  public static int minimumNumberOfTables()
  {
    return 1;
  }

  public boolean addTable(Table aTable)
  {
    boolean wasAdded = false;
    if (tables.contains(aTable)) { return false; }
    tables.add(aTable);
    if (aTable.indexOfBill(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aTable.addBill(this);
      if (!wasAdded)
      {
        tables.remove(aTable);
      }
    }
    return wasAdded;
  }

  public boolean removeTable(Table aTable)
  {
    boolean wasRemoved = false;
    if (!tables.contains(aTable))
    {
      return wasRemoved;
    }

    if (numberOfTables() <= minimumNumberOfTables())
    {
      return wasRemoved;
    }

    int oldIndex = tables.indexOf(aTable);
    tables.remove(oldIndex);
    if (aTable.indexOfBill(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aTable.removeBill(this);
      if (!wasRemoved)
      {
        tables.add(oldIndex,aTable);
      }
    }
    return wasRemoved;
  }

  public boolean setTables(Table... newTables)
  {
    boolean wasSet = false;
    ArrayList<Table> verifiedTables = new ArrayList<Table>();
    for (Table aTable : newTables)
    {
      if (verifiedTables.contains(aTable))
      {
        continue;
      }
      verifiedTables.add(aTable);
    }

    if (verifiedTables.size() != newTables.length || verifiedTables.size() < minimumNumberOfTables())
    {
      return wasSet;
    }

    ArrayList<Table> oldTables = new ArrayList<Table>(tables);
    tables.clear();
    for (Table aNewTable : verifiedTables)
    {
      tables.add(aNewTable);
      if (oldTables.contains(aNewTable))
      {
        oldTables.remove(aNewTable);
      }
      else
      {
        aNewTable.addBill(this);
      }
    }

    for (Table anOldTable : oldTables)
    {
      anOldTable.removeBill(this);
    }
    wasSet = true;
    return wasSet;
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
      existingRestoApp.removeBill(this);
    }
    restoApp.addBill(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    while (orders.size() > 0)
    {
      Order aOrder = orders.get(orders.size() - 1);
      aOrder.delete();
      orders.remove(aOrder);
    }
    
    ArrayList<Table> copyOfTables = new ArrayList<Table>(tables);
    tables.clear();
    for(Table aTable : copyOfTables)
    {
      aTable.removeBill(this);
    }
    RestoApp placeholderRestoApp = restoApp;
    this.restoApp = null;
    if(placeholderRestoApp != null)
    {
      placeholderRestoApp.removeBill(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "billId" + ":" + getBillId()+ "," +
            "numberOfPeople" + ":" + getNumberOfPeople()+ "," +
            "total" + ":" + getTotal()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "billingDate" + "=" + (getBillingDate() != null ? !getBillingDate().equals(this)  ? getBillingDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "restoApp = "+(getRestoApp()!=null?Integer.toHexString(System.identityHashCode(getRestoApp())):"null");
  }
}