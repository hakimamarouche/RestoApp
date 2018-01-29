/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.27.0.3728.d139ed893 modeling language!*/

package ca.mcgill.ecse223.resto.model;
import java.util.*;

// line 13 "../../../../../main.ump"
// line 77 "../../../../../main.ump"
public class Order
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static int nextOrderId = 1;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Autounique Attributes
  private int orderId;

  //Order Associations
  private List<Table> tables;
  private Bill bill;
  private List<MenuItem> menuItems;
  private RestoApp restoApp;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Order(Bill aBill, RestoApp aRestoApp, Table[] allTables, MenuItem[] allMenuItems)
  {
    orderId = nextOrderId++;
    tables = new ArrayList<Table>();
    boolean didAddTables = setTables(allTables);
    if (!didAddTables)
    {
      throw new RuntimeException("Unable to create Order, must have at least 1 tables");
    }
    boolean didAddBill = setBill(aBill);
    if (!didAddBill)
    {
      throw new RuntimeException("Unable to create order due to bill");
    }
    menuItems = new ArrayList<MenuItem>();
    boolean didAddMenuItems = setMenuItems(allMenuItems);
    if (!didAddMenuItems)
    {
      throw new RuntimeException("Unable to create Order, must have at least 1 menuItems");
    }
    boolean didAddRestoApp = setRestoApp(aRestoApp);
    if (!didAddRestoApp)
    {
      throw new RuntimeException("Unable to create order due to restoApp");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public int getOrderId()
  {
    return orderId;
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

  public Bill getBill()
  {
    return bill;
  }

  public MenuItem getMenuItem(int index)
  {
    MenuItem aMenuItem = menuItems.get(index);
    return aMenuItem;
  }

  public List<MenuItem> getMenuItems()
  {
    List<MenuItem> newMenuItems = Collections.unmodifiableList(menuItems);
    return newMenuItems;
  }

  public int numberOfMenuItems()
  {
    int number = menuItems.size();
    return number;
  }

  public boolean hasMenuItems()
  {
    boolean has = menuItems.size() > 0;
    return has;
  }

  public int indexOfMenuItem(MenuItem aMenuItem)
  {
    int index = menuItems.indexOf(aMenuItem);
    return index;
  }

  public RestoApp getRestoApp()
  {
    return restoApp;
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
    if (aTable.indexOfOrder(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aTable.addOrder(this);
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
    if (aTable.indexOfOrder(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aTable.removeOrder(this);
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
        aNewTable.addOrder(this);
      }
    }

    for (Table anOldTable : oldTables)
    {
      anOldTable.removeOrder(this);
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

  public boolean setBill(Bill aBill)
  {
    boolean wasSet = false;
    //Must provide bill to order
    if (aBill == null)
    {
      return wasSet;
    }

    if (bill != null && bill.numberOfOrders() <= Bill.minimumNumberOfOrders())
    {
      return wasSet;
    }

    Bill existingBill = bill;
    bill = aBill;
    if (existingBill != null && !existingBill.equals(aBill))
    {
      boolean didRemove = existingBill.removeOrder(this);
      if (!didRemove)
      {
        bill = existingBill;
        return wasSet;
      }
    }
    bill.addOrder(this);
    wasSet = true;
    return wasSet;
  }

  public boolean isNumberOfMenuItemsValid()
  {
    boolean isValid = numberOfMenuItems() >= minimumNumberOfMenuItems();
    return isValid;
  }

  public static int minimumNumberOfMenuItems()
  {
    return 1;
  }

  public boolean addMenuItem(MenuItem aMenuItem)
  {
    boolean wasAdded = false;
    if (menuItems.contains(aMenuItem)) { return false; }
    menuItems.add(aMenuItem);
    if (aMenuItem.indexOfOrder(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aMenuItem.addOrder(this);
      if (!wasAdded)
      {
        menuItems.remove(aMenuItem);
      }
    }
    return wasAdded;
  }

  public boolean removeMenuItem(MenuItem aMenuItem)
  {
    boolean wasRemoved = false;
    if (!menuItems.contains(aMenuItem))
    {
      return wasRemoved;
    }

    if (numberOfMenuItems() <= minimumNumberOfMenuItems())
    {
      return wasRemoved;
    }

    int oldIndex = menuItems.indexOf(aMenuItem);
    menuItems.remove(oldIndex);
    if (aMenuItem.indexOfOrder(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aMenuItem.removeOrder(this);
      if (!wasRemoved)
      {
        menuItems.add(oldIndex,aMenuItem);
      }
    }
    return wasRemoved;
  }

  public boolean setMenuItems(MenuItem... newMenuItems)
  {
    boolean wasSet = false;
    ArrayList<MenuItem> verifiedMenuItems = new ArrayList<MenuItem>();
    for (MenuItem aMenuItem : newMenuItems)
    {
      if (verifiedMenuItems.contains(aMenuItem))
      {
        continue;
      }
      verifiedMenuItems.add(aMenuItem);
    }

    if (verifiedMenuItems.size() != newMenuItems.length || verifiedMenuItems.size() < minimumNumberOfMenuItems())
    {
      return wasSet;
    }

    ArrayList<MenuItem> oldMenuItems = new ArrayList<MenuItem>(menuItems);
    menuItems.clear();
    for (MenuItem aNewMenuItem : verifiedMenuItems)
    {
      menuItems.add(aNewMenuItem);
      if (oldMenuItems.contains(aNewMenuItem))
      {
        oldMenuItems.remove(aNewMenuItem);
      }
      else
      {
        aNewMenuItem.addOrder(this);
      }
    }

    for (MenuItem anOldMenuItem : oldMenuItems)
    {
      anOldMenuItem.removeOrder(this);
    }
    wasSet = true;
    return wasSet;
  }

  public boolean addMenuItemAt(MenuItem aMenuItem, int index)
  {  
    boolean wasAdded = false;
    if(addMenuItem(aMenuItem))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfMenuItems()) { index = numberOfMenuItems() - 1; }
      menuItems.remove(aMenuItem);
      menuItems.add(index, aMenuItem);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveMenuItemAt(MenuItem aMenuItem, int index)
  {
    boolean wasAdded = false;
    if(menuItems.contains(aMenuItem))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfMenuItems()) { index = numberOfMenuItems() - 1; }
      menuItems.remove(aMenuItem);
      menuItems.add(index, aMenuItem);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addMenuItemAt(aMenuItem, index);
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
      existingRestoApp.removeOrder(this);
    }
    restoApp.addOrder(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    ArrayList<Table> copyOfTables = new ArrayList<Table>(tables);
    tables.clear();
    for(Table aTable : copyOfTables)
    {
      aTable.removeOrder(this);
    }
    Bill existingBill = bill;
    bill = null;
    if (existingBill != null)
    {
      existingBill.delete();
    }
    ArrayList<MenuItem> copyOfMenuItems = new ArrayList<MenuItem>(menuItems);
    menuItems.clear();
    for(MenuItem aMenuItem : copyOfMenuItems)
    {
      aMenuItem.removeOrder(this);
    }
    RestoApp placeholderRestoApp = restoApp;
    this.restoApp = null;
    if(placeholderRestoApp != null)
    {
      placeholderRestoApp.removeOrder(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "orderId" + ":" + getOrderId()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "bill = "+(getBill()!=null?Integer.toHexString(System.identityHashCode(getBill())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "restoApp = "+(getRestoApp()!=null?Integer.toHexString(System.identityHashCode(getRestoApp())):"null");
  }
}