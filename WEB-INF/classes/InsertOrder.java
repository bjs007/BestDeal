

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

/** Shows all the parameters sent to the servlet via either
 *  GET or POST. Specially marks parameters that have
 *  no values or multiple values.
 *  <P>
 *  Taken from Core Servlets and JavaServer Pages 2nd Edition
 *  from Prentice Hall and Sun Microsystems Press,
 *  http://www.coreservlets.com/.
 *  &copy; 2003 Marty Hall; may be freely used or adapted.
*/

public class InsertOrder extends HttpServlet {
//MySQLDataStoreUtilities userUtility = null;
public void init(){

//	userUtility = new MySQLDataStoreUtilities();
	/*us = new UserAccountSerializedDataStore();
	users = us.readUserAccountDataStore();*/

}





  public void doGet(HttpServletRequest request,HttpServletResponse response)
      throws ServletException, IOException {
  response.setContentType("text/html");
  PrintWriter out = response.getWriter();
  String docType =
      "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " +
      "Transitional//EN\">\n";
  String title = "Reading All Request Parameters";
  ArrayList<ItemSold> items = new ArrayList<ItemSold>();

	Integer item_id = Integer.parseInt(request.getParameter("item_id"));
	Integer item_price = Integer.parseInt (request.getParameter("item_price"));
	String item_desc = request.getParameter("item_desc");

  HttpSession session = request.getSession(true);
	String user_id = (String)session.getValue("customer");

  int order_id = MySQLDataStoreUtilities.OrderCount() + 1;
	java.util.Date order_date = new java.util.Date();
	java.util.Date delivery_date = new java.util.Date();
	Calendar c = Calendar.getInstance();
	c.setTime(delivery_date);
	c.add(Calendar.DATE, 15);
	delivery_date = c.getTime();

  ItemSold item = new ItemSold(item_id,item_desc,item_price);
	items.add(item);
	purchaseDetail pd = new purchaseDetail((Integer)order_id,user_id,items,order_date,delivery_date);
  MySQLDataStoreUtilities.insertPurchaserDetail(pd);
	showMessage((Integer)order_id,response,request);

  }

  public void showMessage(Integer order_id,HttpServletResponse response,HttpServletRequest request) throws ServletException, IOException{
	  String title = "Sing up Screen";
	  PrintWriter out = response.getWriter();
	  out.println(
                "<HTML>\n" +
                "<HEAD><TITLE>" + "Confirmation Page" + "</TITLE></HEAD>\n" +
                "<BODY>");

		  out.println("<h2>Order placed!!"+order_id+"<h2>");

		  out.println("</BODY>");


  }

  public void doPost(HttpServletRequest request,
                     HttpServletResponse response)
      throws ServletException, IOException {
    doGet(request, response);
  }
}
