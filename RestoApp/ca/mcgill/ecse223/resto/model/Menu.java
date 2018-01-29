/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.27.0.3728.d139ed893 modeling language!*/

package ca.mcgill.ecse223.resto.model;
import java.util.*;

// line 19 "../../../../../main.ump"
// line 83 "../../../../../main.ump"
public class Menu
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Menu Attributes
  private String menuName;

  //Menu Associations
  private List<MenuCategory> menuCategories;
  private RestoApp restoApp;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Menu(String aMenuName, RestoApp aRestoApp)
  {
    menuName = aMenuName;
    menuCategories = new ArrayList<MenuCategory>();
    boolean didAddRestoApp = setRestoApp(aRestoApp);
    if (!didAddRestoApp)
    {
      throw new RuntimeException("Unable to create menus due to restoApp");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setMenuName(String aMenuName)
  {
    boolean wasSet = false;
    menuName = aMenuName;
    wasSet = true;
    return wasSet;
  }

  public String getMenuName()
  {
    return menuName;
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

  public RestoApp getRestoApp()
  {
    return restoApp;
  }

  public static int minimumNumberOfMenuCategories()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public MenuCategory addMenuCategory(String aCategoryName, RestoApp aRestoApp)
  {
    return new MenuCategory(aCategoryName, this, aRestoApp);
  }

  public boolean addMenuCategory(MenuCategory aMenuCategory)
  {
    boolean wasAdded = false;
    if (menuCategories.contains(aMenuCategory)) { return false; }
    Menu existingMenu = aMenuCategory.getMenu();
    boolean isNewMenu = existingMenu != null && !this.equals(existingMenu);
    if (isNewMenu)
    {
      aMenuCategory.setMenu(this);
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
    //Unable to remove aMenuCategory, as it must always have a menu
    if (!this.equals(aMenuCategory.getMenu()))
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
      existingRestoApp.removeMenus(this);
    }
    restoApp.addMenus(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    while (menuCategories.size() > 0)
    {
      MenuCategory aMenuCategory = menuCategories.get(menuCategories.size() - 1);
      aMenuCategory.delete();
      menuCategories.remove(aMenuCategory);
    }
    
    RestoApp placeholderRestoApp = restoApp;
    this.restoApp = null;
    if(placeholderRestoApp != null)
    {
      placeholderRestoApp.removeMenus(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "menuName" + ":" + getMenuName()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "restoApp = "+(getRestoApp()!=null?Integer.toHexString(System.identityHashCode(getRestoApp())):"null");
  }
}