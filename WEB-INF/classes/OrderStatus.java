/*
 * OrderStatus.java
 *
 */

import java.util.*;
import java.util.Map;
import javax.servlet.*;
import javax.servlet.http.*;

public class OrderStatus extends HttpServlet {
   HashMap<String,ArrayList<ItemOrder>> existingcart = null;
   UserCarts us = null;
   OrderTrack ot = null;
   //MySQLDataStoreUtilities mysqlUtility = null;
    /**
     * Initializes the servlet with some usernames/password
    */
    public void init() {

    //    mysqlUtility = new MySQLDataStoreUtilities();
    }

    /** Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
    * @param request servlet request
    * @param response servlet response
    */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {

        showPage(response,request);


}
    /**
     * Actually shows the <code>HTML</code> result page
     */
    protected void showPage(HttpServletResponse response,HttpServletRequest request)
    throws ServletException, java.io.IOException {
      HttpSession session = request.getSession(true);
	    String userid = (String)session.getValue("userid");

      response.setContentType("text/html");
      java.io.PrintWriter out = response.getWriter();
      HashMap<Integer,purchaseDetail> purchase_orders = null;
		// out.println("In order");
      out.println("<html>");
      out.println("<head>");
		  out.println("<title>BestDeal</title>");
		//  out.println("<title>spigot - Free CSS Template by ZyPOP</title>");
		  out.println("<link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" />");
		  out.println("</head>");
		  out.println("<body>");
		  out.println("<div id=\"container\">");
		  out.println("<header>");
		  out.println("<h1 style="+ "\"color:red\"" + "<span>Best Deal</span></h1>");
		  out.println("<h2 style=\"color:blue\"><I> Better than this is not possible</I></h2>");
		  out.println("</header>\n");
		  out.println("<div id=\"body\"> ");

      if(userid==null){
        out.print("Please sign in to check status");
  		  out.println("<a href=\"signIn.html\">Click here to signIn</a>\n");
      }

        else{
        purchase_orders = MySQLDataStoreUtilities.getOrderDetail(userid);

        if(purchase_orders.isEmpty()){
          out.println("Hi "+userid+"!. You have not placed any order.");
        }else{
          String OrderCancel =
              response.encodeURL("/BestDeal/OrderCancel");
          out.println("<div style=\"width: 60%;margin:0 auto\">");
          for(Integer key : purchase_orders.keySet()){
          purchaseDetail purchase = purchase_orders.get(key);
          ArrayList<ItemSold> items = purchase.getItemsSold();

          out.println("<FORM ACTION=\"" + OrderCancel + "\">");
          out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"order_id\"\n" +
              "VALUE=\"" + key + "\">\n");
      		out.println("<TABLE BORDER=1 ALIGN=\"CENTER\">\n" +
             "<TR BGCOLOR=\"#FFAD00\">\n");
          out.println("<tr><td><b>Order Id:</b>"+ purchase.getId() +" </td><td><b>Order Date: </b>"+
          purchase.getOrderDate()+" </td> <td><b>Delivery Date: </b>"+
          purchase.getDeliveryDate() +" </td></tr>\n");
          out.println("<tr> <td>Item Id</td><td>Item Desc</td><td>Item Price</td></tr>");
          for(ItemSold it: items){
          out.println("<tr> <td>"+it.getId() +" </td>");
          out.println("<td>"+it.getShort_desc() +" </td>");
          out.println("<td>"+it.getPrice()+" </td><tr>\n");
          }

          out.println("<tr><td colspan='3'>");
      		out.println("<center><input type=\"submit\" value=\"Cancel Order\" /></center>");
      		out.println("</td></tr></table></form>");

        }





          out.println("</div> \n");


        }
      }




  		out.println("</div> \n");
  		out.println("</div> \n");
  		out.println("</body>");
  		out.println("</html>");

	}
    /** Handles the HTTP <code>GET</code> method.
    * @param request servlet request
    * @param response servlet response
    */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
        processRequest(request, response);
    }

    /** Handles the HTTP <code>POST</code> method.
    * @param request servlet request
    * @param response servlet response
    */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
        processRequest(request, response);
    }
}
