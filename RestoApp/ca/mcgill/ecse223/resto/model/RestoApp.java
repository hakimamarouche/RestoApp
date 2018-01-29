/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.27.0.3728.d139ed893 modeling language!*/

package ca.mcgill.ecse223.resto.model;
import java.util.*;
import java.sql.Date;
import java.sql.Time;

// line 59 "../../../../../main.ump"
// line 117 "../../../../../main.ump"
public class RestoApp
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //RestoApp Associations
  private List<Bill> bills;
  private List<Order> orders;
  private List<Menu> menus;
  private List<Reservation> reservations;
  private List<Table> tables;
  private List<MenuCategory> menuCategories;
  private List<MenuItem> menuItems;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public RestoApp()
  {
    bills = new ArrayList<Bill>();
    orders = new ArrayList<Order>();
    menus = new ArrayList<Menu>();
    reservations = new ArrayList<Reservation>();
    tables = new ArrayList<Table>();
    menuCategories = new ArrayList<MenuCategory>();
    menuItems = new ArrayList<MenuItem>();
  }

  //------------------------
  // INTERFACE
  //------------------------

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

  public Menu getMenus(int index)
  {
    Menu aMenus = menus.get(index);
    return aMenus;
  }

  public List<Menu> getMenus()
  {
    List<Menu> newMenus = Collections.unmodifiableList(menus);
    return newMenus;
  }

  public int numberOfMenus()
  {
    int number = menus.size();
    return number;
  }

  public boolean hasMenus()
  {
    boolean has = menus.size() > 0;
    return has;
  }

  public int indexOfMenus(Menu aMenus)
  {
    int index = menus.indexOf(aMenus);
    return index;
  }

  public Reservation getReservation(int index)
  {
    Reservation aReservation = reservations.get(index);
    return aReservation;
  }

  public List<Reservation> getReservations()
  {
    List<Reservation> newReservations = Collections.unmodifiableList(reservations);
    return newReservations;
  }

  public int numberOfReservations()
  {
    int number = reservations.size();
    return number;
  }

  public boolean hasReservations()
  {
    boolean has = reservations.size() > 0;
    return has;
  }

  public int indexOfReservation(Reservation aReservation)
  {
    int index = reservations.indexOf(aReservation);
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

  public MenuCategory getMenuCategory(int index)
  {
    MenuCategory aMenuCategory = menuCategories.get(index);
    return aMenuCategory;
  }

  public List<MenuCategory> getMenuCategories()
  {
    List<MenuCategory> newMenuCategories = Collections.unmodifiableList(menuCategories);
    return newMenuCategories;
  }

  public int numberOfMenuCategories()
  {
    int number = menuCategories.size();
    return number;
  }

  public boolean hasMenuCategories()
  {
    boolean has = menuCategories.size() > 0;
    return has;
  }

  public int indexOfMenuCategory(MenuCategory aMenuCategory)
  {
    int index = menuCategories.indexOf(aMenuCategory);
    return index;
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

  public static int minimumNumberOfBills()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Bill addBill(int aNumberOfPeople, double aTotal, Date aBillingDate, Table... allTables)
  {
    return new Bill(aNumberOfPeople, aTotal, aBillingDate, this, allTables);
  }

  public boolean addBill(Bill aBill)
  {
    boolean wasAdded = false;
    if (bills.contains(aBill)) { return false; }
    RestoApp existingRestoApp = aBill.getRestoApp();
    boolean isNewRestoApp = existingRestoApp != null && !this.equals(existingRestoApp);
    if (isNewRestoApp)
    {
      aBill.setRestoApp(this);
    }
    else
    {
      bills.add(aBill);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeBill(Bill aBill)
  {
    boolean wasRemoved = false;
    //Unable to remove aBill, as it must always have a restoApp
    if (!this.equals(aBill.getRestoApp()))
    {
      bills.remove(aBill);
      wasRemoved = true;
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
  /* Code from template association_AddManyToOne */
  public Order addOrder(Bill aBill, Table[] allTables, MenuItem[] allMenuItems)
  {
    return new Order(aBill, this, allTables, allMenuItems);
  }

  public boolean addOrder(Order aOrder)
  {
    boolean wasAdded = false;
    if (orders.contains(aOrder)) { return false; }
    RestoApp existingRestoApp = aOrder.getRestoApp();
    boolean isNewRestoApp = existingRestoApp != null && !this.equals(existingRestoApp);
    if (isNewRestoApp)
    {
      aOrder.setRestoApp(this);
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
    //Unable to remove aOrder, as it must always have a restoApp
    if (!this.equals(aOrder.getRestoApp()))
    {
      orders.remove(aOrder);
      wasRemoved = true;
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

  public static int minimumNumberOfMenus()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Menu addMenus(String aMenuName)
  {
    return new Menu(aMenuName, this);
  }

  public boolean addMenus(Menu aMenus)
  {
    boolean wasAdded = false;
    if (menus.contains(aMenus)) { return false; }
    RestoApp existingRestoApp = aMenus.getRestoApp();
    boolean isNewRestoApp = existingRestoApp != null && !this.equals(existingRestoApp);
    if (isNewRestoApp)
    {
      aMenus.setRestoApp(this);
    }
    else
    {
      menus.add(aMenus);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeMenus(Menu aMenus)
  {
    boolean wasRemoved = false;
    //Unable to remove aMenus, as it must always have a restoApp
    if (!this.equals(aMenus.getRestoApp()))
    {
      menus.remove(aMenus);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addMenusAt(Menu aMenus, int index)
  {  
    boolean wasAdded = false;
    if(addMenus(aMenus))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfMenus()) { index = numberOfMenus() - 1; }
      menus.remove(aMenus);
      menus.add(index, aMenus);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveMenusAt(Menu aMenus, int index)
  {
    boolean wasAdded = false;
    if(menus.contains(aMenus))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfMenus()) { index = numberOfMenus() - 1; }
      menus.remove(aMenus);
      menus.add(index, aMenus);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addMenusAt(aMenus, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfReservations()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Reservation addReservation(Date aReservationDate, Time aReservedTime, int aNumberOfPersons, String aCustomerName, Time aReservationDuration, String aContactInfo, Table... allTables)
  {
    return new Reservation(aReservationDate, aReservedTime, aNumberOfPersons, aCustomerName, aReservationDuration, aContactInfo, this, allTables);
  }

  public boolean addReservation(Reservation aReservation)
  {
    boolean wasAdded = false;
    if (reservations.contains(aReservation)) { return false; }
    RestoApp existingRestoApp = aReservation.getRestoApp();
    boolean isNewRestoApp = existingRestoApp != null && !this.equals(existingRestoApp);
    if (isNewRestoApp)
    {
      aReservation.setRestoApp(this);
    }
    else
    {
      reservations.add(aReservation);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeReservation(Reservation aReservation)
  {
    boolean wasRemoved = false;
    //Unable to remove aReservation, as it must always have a restoApp
    if (!this.equals(aReservation.getRestoApp()))
    {
      reservations.remove(aReservation);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addReservationAt(Reservation aReservation, int index)
  {  
    boolean wasAdded = false;
    if(addReservation(aReservation))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfReservations()) { index = numberOfReservations() - 1; }
      reservations.remove(aReservation);
      reservations.add(index, aReservation);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveReservationAt(Reservation aReservation, int index)
  {
    boolean wasAdded = false;
    if(reservations.contains(aReservation))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfReservations()) { index = numberOfReservations() - 1; }
      reservations.remove(aReservation);
      reservations.add(index, aReservation);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addReservationAt(aReservation, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfTables()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Table addTable(int aNumberOfSeats, int aTableNumber, boolean aIsAvailable)
  {
    return new Table(aNumberOfSeats, aTableNumber, aIsAvailable, this);
  }

  public boolean addTable(Table aTable)
  {
    boolean wasAdded = false;
    if (tables.contains(aTable)) { return false; }
    RestoApp existingRestoApp = aTable.getRestoApp();
    boolean isNewRestoApp = existingRestoApp != null && !this.equals(existingRestoApp);
    if (isNewRestoApp)
    {
      aTable.setRestoApp(this);
    }
    else
    {
      tables.add(aTable);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeTable(Table aTable)
  {
    boolean wasRemoved = false;
    //Unable to remove aTable, as it must always have a restoApp
    if (!this.equals(aTable.getRestoApp()))
    {
      tables.remove(aTable);
      wasRemoved = true;
    }
    return wasRemoved;
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

  public static int minimumNumberOfMenuCategories()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public MenuCategory addMenuCategory(String aCategoryName, Menu aMenu)
  {
    return new MenuCategory(aCategoryName, aMenu, this);
  }

  public boolean addMenuCategory(MenuCategory aMenuCategory)
  {
    boolean wasAdded = false;
    if (menuCategories.contains(aMenuCategory)) { return false; }
    RestoApp existingRestoApp = aMenuCategory.getRestoApp();
    boolean isNewRestoApp = existingRestoApp != null && !this.equals(existingRestoApp);
    if (isNewRestoApp)
    {
      aMenuCategory.setRestoApp(this);
    }
    else
    {
      menuCategories.add(aMenuCategory);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeMenuCategory(MenuCategory aMenuCategory)
  {
    boolean wasRemoved = false;
    //Unable to remove aMenuCategory, as it must always have a restoApp
    if (!this.equals(aMenuCategory.getRestoApp()))
    {
      menuCategories.remove(aMenuCategory);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addMenuCategoryAt(MenuCategory aMenuCategory, int index)
  {  
    boolean wasAdded = false;
    if(addMenuCategory(aMenuCategory))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfMenuCategories()) { index = numberOfMenuCategories() - 1; }
      menuCategories.remove(aMenuCategory);
      menuCategories.add(index, aMenuCategory);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveMenuCategoryAt(MenuCategory aMenuCategory, int index)
  {
    boolean wasAdded = false;
    if(menuCategories.contains(aMenuCategory))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfMenuCategories()) { index = numberOfMenuCategories() - 1; }
      menuCategories.remove(aMenuCategory);
      menuCategories.add(index, aMenuCategory);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addMenuCategoryAt(aMenuCategory, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfMenuItems()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public MenuItem addMenuItem(String aItemName, double aItemPrice, String aItemDescription, MenuCategory aMenuCategory)
  {
    return new MenuItem(aItemName, aItemPrice, aItemDescription, aMenuCategory, this);
  }

  public boolean addMenuItem(MenuItem aMenuItem)
  {
    boolean wasAdded = false;
    if (menuItems.contains(aMenuItem)) { return false; }
    RestoApp existingRestoApp = aMenuItem.getRestoApp();
    boolean isNewRestoApp = existingRestoApp != null && !this.equals(existingRestoApp);
    if (isNewRestoApp)
    {
      aMenuItem.setRestoApp(this);
    }
    else
    {
      menuItems.add(aMenuItem);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeMenuItem(MenuItem aMenuItem)
  {
    boolean wasRemoved = false;
    //Unable to remove aMenuItem, as it must always have a restoApp
    if (!this.equals(aMenuItem.getRestoApp()))
    {
      menuItems.remove(aMenuItem);
      wasRemoved = true;
    }
    return wasRemoved;
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

  public void delete()
  {
    while (bills.size() > 0)
    {
      Bill aBill = bills.get(bills.size() - 1);
      aBill.delete();
      bills.remove(aBill);
    }
    
    while (orders.size() > 0)
    {
      Order aOrder = orders.get(orders.size() - 1);
      aOrder.delete();
      orders.remove(aOrder);
    }
    
    while (menus.size() > 0)
    {
      Menu aMenus = menus.get(menus.size() - 1);
      aMenus.delete();
      menus.remove(aMenus);
    }
    
    while (reservations.size() > 0)
    {
      Reservation aReservation = reservations.get(reservations.size() - 1);
      aReservation.delete();
      reservations.remove(aReservation);
    }
    
    while (tables.size() > 0)
    {
      Table aTable = tables.get(tables.size() - 1);
      aTable.delete();
      tables.remove(aTable);
    }
    
    while (menuCategories.size() > 0)
    {
      MenuCategory aMenuCategory = menuCategories.get(menuCategories.size() - 1);
      aMenuCategory.delete();
      menuCategories.remove(aMenuCategory);
    }
    
    while (menuItems.size() > 0)
    {
      MenuItem aMenuItem = menuItems.get(menuItems.size() - 1);
      aMenuItem.delete();
      menuItems.remove(aMenuItem);
    }
    
  }

}