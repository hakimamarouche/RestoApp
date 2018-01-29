/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.27.0.3728.d139ed893 modeling language!*/

package ca.mcgill.ecse223.resto.model;
import java.util.*;

// line 45 "../../../../../main.ump"
// line 105 "../../../../../main.ump"
public class MenuCategory
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //MenuCategory Attributes
  private String categoryName;

  //MenuCategory Associations
  private List<MenuItem> menuItems;
  private Menu menu;
  private RestoApp restoApp;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public MenuCategory(String aCategoryName, Menu aMenu, RestoApp aRestoApp)
  {
    categoryName = aCategoryName;
    menuItems = new ArrayList<MenuItem>();
    boolean didAddMenu = setMenu(aMenu);
    if (!didAddMenu)
    {
      throw new RuntimeException("Unable to create menuCategory due to menu");
    }
    boolean didAddRestoApp = setRestoApp(aRestoApp);
    if (!didAddRestoApp)
    {
      throw new RuntimeException("Unable to create menuCategory due to restoApp");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setCategoryName(String aCategoryName)
  {
    boolean wasSet = false;
    categoryName = aCategoryName;
    wasSet = true;
    return wasSet;
  }

  public String getCategoryName()
  {
    return categoryName;
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

  public Menu getMenu()
  {
    return menu;
  }

  public RestoApp getRestoApp()
  {
    return restoApp;
  }

  public static int minimumNumberOfMenuItems()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public MenuItem addMenuItem(String aItemName, double aItemPrice, String aItemDescription, RestoApp aRestoApp)
  {
    return new MenuItem(aItemName, aItemPrice, aItemDescription, this, aRestoApp);
  }

  public boolean addMenuItem(MenuItem aMenuItem)
  {
    boolean wasAdded = false;
    if (menuItems.contains(aMenuItem)) { return false; }
    MenuCategory existingMenuCategory = aMenuItem.getMenuCategory();
    boolean isNewMenuCategory = existingMenuCategory != null && !this.equals(existingMenuCategory);
    if (isNewMenuCategory)
    {
      aMenuItem.setMenuCategory(this);
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
    //Unable to remove aMenuItem, as it must always have a menuCategory
    if (!this.equals(aMenuItem.getMenuCategory()))
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

  public boolean setMenu(Menu aMenu)
  {
    boolean wasSet = false;
    if (aMenu == null)
    {
      return wasSet;
    }

    Menu existingMenu = menu;
    menu = aMenu;
    if (existingMenu != null && !existingMenu.equals(aMenu))
    {
      existingMenu.removeMenuCategory(this);
    }
    menu.addMenuCategory(this);
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
      existingRestoApp.removeMenuCategory(this);
    }
    restoApp.addMenuCategory(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    while (menuItems.size() > 0)
    {
      MenuItem aMenuItem = menuItems.get(menuItems.size() - 1);
      aMenuItem.delete();
      menuItems.remove(aMenuItem);
    }
    
    Menu placeholderMenu = menu;
    this.menu = null;
    if(placeholderMenu != null)
    {
      placeholderMenu.removeMenuCategory(this);
    }
    RestoApp placeholderRestoApp = restoApp;
    this.restoApp = null;
    if(placeholderRestoApp != null)
    {
      placeholderRestoApp.removeMenuCategory(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "categoryName" + ":" + getCategoryName()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "menu = "+(getMenu()!=null?Integer.toHexString(System.identityHashCode(getMenu())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "restoApp = "+(getRestoApp()!=null?Integer.toHexString(System.identityHashCode(getRestoApp())):"null");
  }
}