/*
 * OrderStatus.java
 *
 */

import java.util.*;
import java.util.Map;
import javax.servlet.*;
import javax.servlet.http.*;

public class OrderUpdateDetail extends HttpServlet {

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
      Integer order_id = Integer.parseInt(request.getParameter("order_id"));

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
        purchase_orders = MySQLDataStoreUtilities.getOrderDetailOrderId(order_id);

        if(purchase_orders.isEmpty()){
          out.println("There is no such order.");
        }else{
          String updateOrder =
              response.encodeURL("/BestDeal/updateOrder");
          out.println("<div style=\"width: 60%;margin:0 auto\">");
          for(Integer key : purchase_orders.keySet()){
          purchaseDetail purchase = purchase_orders.get(key);
          ArrayList<ItemSold> items = purchase.getItemsSold();


      		out.println("<TABLE BORDER=1 ALIGN=\"CENTER\">\n" +
             "<TR BGCOLOR=\"#FFAD00\">\n");
          out.println("<tr><td><b>Order Id:</b>"+ purchase.getId() +" </td><td><b>Order Date: </b>"+
          purchase.getOrderDate()+" </td> <td><b>Delivery Date: </b>"+
          purchase.getDeliveryDate() +" </td></tr>\n");
          out.println("<tr> <td>Item Id</td><td>Item Desc</td><td>Item Price</td></tr>");
          for(ItemSold it: items){
          out.println("<FORM ACTION=\"" + updateOrder + "\">");
          out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"order_id\"\n" +
                "VALUE=\"" + key + "\">\n");
        
          out.println("<tr> <td><INPUT TYPE=\"TEXT\" NAME=\"itemid\"\n" + "SIZE=3 VALUE=\"" + it.getId() +"\">\n</td>");
          out.println("<td><INPUT TYPE=\"TEXT\" NAME=\"itemdesc\"\n" + "SIZE=30 VALUE=\"" + it.getShort_desc() +"\">\n</td>");
          out.println("<td><INPUT TYPE=\"TEXT\" NAME=\"itemprice\"\n" + "SIZE=3 VALUE=\"" + it.getPrice() +"\">\n</td></tr>");
          out.println("<tr><td colspan='3'>");
      		out.println("<center><input type=\"submit\" value=\"Update Order\" /></center></td></tr>");
          out.println("</form>");

          }


      		out.println("</table>");

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
