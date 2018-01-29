/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.27.0.3728.d139ed893 modeling language!*/

package ca.mcgill.ecse223.resto.model;
import java.util.*;

// line 51 "../../../../../main.ump"
// line 111 "../../../../../main.ump"
public class MenuItem
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //MenuItem Attributes
  private String itemName;
  private double itemPrice;
  private String itemDescription;

  //MenuItem Associations
  private List<Order> orders;
  private MenuCategory menuCategory;
  private RestoApp restoApp;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public MenuItem(String aItemName, double aItemPrice, String aItemDescription, MenuCategory aMenuCategory, RestoApp aRestoApp)
  {
    itemName = aItemName;
    itemPrice = aItemPrice;
    itemDescription = aItemDescription;
    orders = new ArrayList<Order>();
    boolean didAddMenuCategory = setMenuCategory(aMenuCategory);
    if (!didAddMenuCategory)
    {
      throw new RuntimeException("Unable to create menuItem due to menuCategory");
    }
    boolean didAddRestoApp = setRestoApp(aRestoApp);
    if (!didAddRestoApp)
    {
      throw new RuntimeException("Unable to create menuItem due to restoApp");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setItemName(String aItemName)
  {
    boolean wasSet = false;
    itemName = aItemName;
    wasSet = true;
    return wasSet;
  }

  public boolean setItemPrice(double aItemPrice)
  {
    boolean wasSet = false;
    itemPrice = aItemPrice;
    wasSet = true;
    return wasSet;
  }

  public boolean setItemDescription(String aItemDescription)
  {
    boolean wasSet = false;
    itemDescription = aItemDescription;
    wasSet = true;
    return wasSet;
  }

  public String getItemName()
  {
    return itemName;
  }

  public double getItemPrice()
  {
    return itemPrice;
  }

  public String getItemDescription()
  {
    return itemDescription;
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

  public MenuCategory getMenuCategory()
  {
    return menuCategory;
  }

  public RestoApp getRestoApp()
  {
    return restoApp;
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
    if (aOrder.indexOfMenuItem(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aOrder.addMenuItem(this);
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
    if (aOrder.indexOfMenuItem(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aOrder.removeMenuItem(this);
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

  public boolean setMenuCategory(MenuCategory aMenuCategory)
  {
    boolean wasSet = false;
    if (aMenuCategory == null)
    {
      return wasSet;
    }

    MenuCategory existingMenuCategory = menuCategory;
    menuCategory = aMenuCategory;
    if (existingMenuCategory != null && !existingMenuCategory.equals(aMenuCategory))
    {
      existingMenuCategory.removeMenuItem(this);
    }
    menuCategory.addMenuItem(this);
    wasSet = true;
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
      existingRestoApp.removeMenuItem(this);
    }
    restoApp.addMenuItem(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    ArrayList<Order> copyOfOrders = new ArrayList<Order>(orders);
    orders.clear();
    for(Order aOrder : copyOfOrders)
    {
      if (aOrder.numberOfMenuItems() <= Order.minimumNumberOfMenuItems())
      {
        aOrder.delete();
      }
      else
      {
        aOrder.removeMenuItem(this);
      }
    }
    MenuCategory placeholderMenuCategory = menuCategory;
    this.menuCategory = null;
    if(placeholderMenuCategory != null)
    {
      placeholderMenuCategory.removeMenuItem(this);
    }
    RestoApp placeholderRestoApp = restoApp;
    this.restoApp = null;
    if(placeholderRestoApp != null)
    {
      placeholderRestoApp.removeMenuItem(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "itemName" + ":" + getItemName()+ "," +
            "itemPrice" + ":" + getItemPrice()+ "," +
            "itemDescription" + ":" + getItemDescription()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "menuCategory = "+(getMenuCategory()!=null?Integer.toHexString(System.identityHashCode(getMenuCategory())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "restoApp = "+(getRestoApp()!=null?Integer.toHexString(System.identityHashCode(getRestoApp())):"null");
  }
}