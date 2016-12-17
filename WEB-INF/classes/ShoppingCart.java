

import java.util.*;

/** A shopping cart data structure used to track orders.
 *  The OrderPage servlet associates one of these carts
 *  with each user session.
 *  <P>
 *  Taken from Core Servlets and JavaServer Pages 2nd Edition
 *  from Prentice Hall and Sun Microsystems Press,
 *  http://www.coreservlets.com/.
 *  &copy; 2003 Marty Hall; may be freely used or adapted.
 */

public class ShoppingCart implements java.io.Serializable{
  private ArrayList<ItemOrder> itemsOrdered;
  SaxParser4BestDealProducts sx;
  HashMap<String,Product> mapProduct;
  HashMap<String,Product> mobProduct;
  HashMap<String,Product> tabProduct;
  HashMap<String,Product> tvProduct;
  /** Builds an empty shopping cart. */
  Product item;
  public ShoppingCart() {
 	String productXmlFileName = "C:/apache-tomcat-7.0.34/webapps/BestDeal/WEB-INF/classes/ProductCatalog.xml";
  String MobileXmlFileName = "C:/apache-tomcat-7.0.34/webapps/BestDeal/WEB-INF/classes/MobileCatalog.xml";
  String TabletXmlFileName = "C:/apache-tomcat-7.0.34/webapps/BestDeal/WEB-INF/classes/TabletCatalog.xml";
  String TvXmlFileName = "C:/apache-tomcat-7.0.34/webapps/BestDeal/WEB-INF/classes/TvCatalog.xml";

	sx = new SaxParser4BestDealProducts (productXmlFileName);
   itemsOrdered = new ArrayList<ItemOrder>();

	mapProduct  = sx.prettyPrint();
  sx = new SaxParser4BestDealProducts (MobileXmlFileName);
  mobProduct = sx.prettyPrint();
  sx = new SaxParser4BestDealProducts (TabletXmlFileName);
  tabProduct = sx.prettyPrint();
  sx = new SaxParser4BestDealProducts (TvXmlFileName);
  tvProduct = sx.prettyPrint();

 for(String prdid: mobProduct.keySet()){
   mapProduct.put(prdid,mobProduct.get(prdid));
 }

 for(String prdid: tabProduct.keySet()){
   mapProduct.put(prdid,tabProduct.get(prdid));
 }

 for(String prdid: tvProduct.keySet()){
   mapProduct.put(prdid,tvProduct.get(prdid));
 }





}

  /** Returns List of ItemOrder entries giving
   *  Item and number ordered. Declared as List instead
   *  of ArrayList so that underlying implementation
   *  can be changed later.
   */

  public List getItemsOrdered() {
    return(itemsOrdered);
  }

  /** Looks through cart to see if it already contains
   *  an order entry corresponding to item ID. If it does,
   *  increments the number ordered. If not, looks up
   *  Item in catalog and adds an order entry for it.
   */

  public synchronized void addItem(String itemID) {
    ItemOrder order;
    for(int i=0; i<itemsOrdered.size(); i++) {
      order = (ItemOrder)itemsOrdered.get(i);
      if (order.getItemID().equals(itemID)) {
        order.incrementNumItems();
        return;
      }
    }

    item = mapProduct.get(itemID);
    ItemOrder newOrder = new ItemOrder(item);
    itemsOrdered.add(newOrder);
  }

  /** Looks through cart to find order entry corresponding
   *  to item ID listed. If the designated number
   *  is positive, sets it. If designated number is 0
   *  (or, negative due to a user input error), deletes
   *  item from cart.
   */

  public synchronized void setNumOrdered(String itemID,
                                         int numOrdered) {
    ItemOrder order;
    for(int i=0; i<itemsOrdered.size(); i++) {
      order = (ItemOrder)itemsOrdered.get(i);
      if (order.getItemID().equals(itemID)) {
        if (numOrdered <= 0) {
          itemsOrdered.remove(i);
        } else {
          order.setNumItems(numOrdered);
        }
        return;
      }
    }
	item = mapProduct.get(itemID);
    ItemOrder newOrder =
      new ItemOrder(item);
    itemsOrdered.add(newOrder);
  }
}
