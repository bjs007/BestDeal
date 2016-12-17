/*
 * Trending.java
 *
 */


import java.util.*;
import java.util.Map;
import javax.servlet.*;
import javax.servlet.http.*;
import com.mongodb.*;
public class Trending extends HttpServlet {



    /** Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
    * @param request servlet request
    * @param response servlet response
    */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
    AggregationOutput aggregate = MongoDBDataStoreUtilities.topFiveZipCodes();
    AggregationOutput aggregate1 = MongoDBDataStoreUtilities.topFiveSoldProducts();
    AggregationOutput aggregate2 = MongoDBDataStoreUtilities.topFivemostLikedProducts();

    response.setContentType("text/html");
    java.io.PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(true);
		out.println("<html>");
    out.println("<head>");
		out.println("<title>BestDeal</title>");
		out.println("<link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" />");
    out.println("<script type=\"text/javascript\" src=\"javascript.js\"></script>");
    out.println("</head>");
		out.println("<body onload=\"init()\">");

		out.println("<div id=\"container\">");
		out.println("<header>");
		out.println("<h1 style="+ "\"color:red\"" + "<span> BestDeal</span></h1>");
		out.println("<h2 style=\"color:blue\"><I> Better than this is not possible</I></h2>");


    out.println("<div name=\"autofillform\" action=\"autocomplete\">");

    out.println("<table border=\"0\" cellpadding=\"5\">");
    out.println("<tbody>");
    out.println("<tr>");
  //  out.println("<td><strong>Search Here</strong></td>");
    out.println("<td> Search Here");
    out.println("<input type=\"text\" size=\"30\"");
    out.println("id=\"complete-field\" onkeyup=\"doCompletion()\">");
    out.println("</td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("<td id=\"auto-row\" colspan=\"2\">");
    out.println("<table id=\"complete-table\" class=\"popupBox\" style=\"position: absolute; width:315px;\" />");
    out.println("</td>");
    out.println("</tr>");
    out.println("</tbody>");
    out.println("</table>");
    out.println("</div>");



		out.println("</header>\n");
		out.println("<div align=\"right\">\n" );

		String userid = (String)session.getValue("userid");


				//out.println(userid);
		if(userid!=null){
				out.println("Welcome Back "+userid+"!" );
				out.println("<a href= \"http://localhost/BestDeal/logout\">Logout</a>");


				}else{
				out.println("<a href=\"signIn.html\">Sign In</a>\n" +
				"<a href=\"register.html\"> Create account</a>\n");
				}
				out.println("</div>\n");
				out.println(
				"<nav>\n" +
					"<ul>\n" +
						"<li class=\"start selected\"><a href=\"http://localhost/BestDeal/Home\">Home</a></li>\n" +
						"<li><a href= \"http://localhost/BestDeal/productTab?prodType=laptop&user="+userid+"\">Laptop</a></li> \n" +
						"<li><a href= \"http://localhost/BestDeal/productTab?prodType=tablet&user="+userid+"\">Tablet</a></li> \n" +
						"<li><a href= \"http://localhost/BestDeal/productTab?prodType=mobile&user="+userid+"\">Mobile</a></li> \n" +
						"<li><a href= \"http://localhost/BestDeal/productTab?prodType=tv&user="+userid+"\">TV</a></li> \n" +
						"<li class=\"end\"><a href=\"http://localhost/BestDeal/OrderStatus\">Track Order</a></li> \n" +
            "<li class=\"end\"><a href=\"#\">CONTACT</a></li> \n" +
					"</ul>\n" +
				"</nav>\n");
        out.println("<div id=\"body\"> \n" + "<section id=\"content\"> \n" );
        //Write your code here
        out.println("<p style=\"color:red;\">Top 5 Zip-codes where maximum number of products sold </p>" );


        if( aggregate!=null){
        out.println("<table BORDER=1 align =\"CENTER\" style = \"margin: 0px auto;\" cellpadding='2' cellspacing = '1'>\n");
        out.println(" <TR BGCOLOR=\"#FFAD00\">\n");
        out.println("<tr><td><b>Zip-codes</b></td><td><b>Count</b></td></tr>");
        for (DBObject result : aggregate.results()) {
        BasicDBObject bobj = (BasicDBObject) result;
        out.println("<tr><td>"+bobj.getString("Zip-codes")+" </td><td>"+bobj.getString("Review Count")+"</td></tr>");
          }
          out.println("</table>");
        }else{
          out.println("No review has been maintained!!");
        }

        out.println("<p style=\"color:red;\">Top 5 most sold products regardless of the rating </p>" );


        if( aggregate1!=null){
          out.println("<table BORDER=1 align =\"CENTER\" style = \"margin: 0px auto;\" cellpadding='2' cellspacing = '1'>\n");
          out.println(" <TR BGCOLOR=\"#FFAD00\">\n");
        out.println("<tr><td><b>Product</b></td><td><b>Sold</b></td></tr>");
        for (DBObject result : aggregate1.results()) {
        BasicDBObject bobj = (BasicDBObject) result;
        out.println("<tr><td>"+bobj.getString("Product")+" </td><td>"+bobj.getString("Sale Count")+"</td></tr>");
      }
          out.println("</table>");
        }else{
          out.println("No review has been maintained!!");
        }
        out.println("<p style=\"color:red;\">Top 5 most Liked Products as per their average rating (greater than 3)</p>" );



        if( aggregate2!=null){
          out.println("<table BORDER=1 align =\"CENTER\" style = \"margin: 0px auto;\" cellpadding='2' cellspacing = '1'>\n");
          out.println(" <TR BGCOLOR=\"#FFAD00\">\n");
        out.println("<tr><td><b>Product</b></td><td><b>Average Rating</b></td></tr>");
        for (DBObject result : aggregate2.results()) {
        BasicDBObject bobj = (BasicDBObject) result;
        out.println("<tr><td>"+bobj.getString("Product")+" </td><td>"+bobj.getString("Average_rating")+"</td></tr>");
      }
          out.println("</table>");
        }else{
          out.println("No review has been maintained!!");
        }



        out.println("</section> \n" +
          " <aside class=\"sidebar\"> \n" +

					  "  <ul>	\n" +
						  " <li> \n" +
							   " <h4>Laptop</h4> \n" +
							   " <ul> \n" +
								   " <li><a href=\"http://localhost/BestDeal/productTab?company=asus&prodType=laptop\"\">Asus</a></li> \n" +

								"</ul> \n" +

								 " <ul> \n" +
								   " <li><a href=\"http://localhost/BestDeal/productTab?company=dell&prodType=laptop\"\">Dell</a></li> \n" +

								"</ul> \n" +

								 " <ul> \n" +
								   " <li><a href=\"http://localhost/BestDeal/productTab?company=hp&prodType=laptop\"\">HP</a></li> \n" +

								"</ul> \n" +
								" <ul> \n" +
								   " <li><a href=\"http://localhost/BestDeal/productTab?company=samsung&prodType=laptop\"\">Samsung</a></li> \n" +

								"</ul> \n" +
								 "<ul> \n <li><a href=\"http://localhost/BestDeal/productTab?company=lenovo&prodType=laptop\"\">Lenovo</a></li> \n" +

								"</ul> \n" +

						   " </li> \n" +

						   " <li> \n" +
							   " <h4>Smart Phone</h4> \n" +
							   " <ul> \n" +
								   " <li><a href=\"http://localhost/BestDeal/productTab?company=asus&prodType=mobile\"\">Asus</a></li> \n" +

								"</ul> \n" +

								 " <ul> \n" +
								   " <li><a href=\"http://localhost/BestDeal/productTab?company=iphone&prodType=mobile\"\">Iphone</a></li> \n" +

								"</ul> \n" +

								 " <ul> \n" +
								   " <li><a href=\"http://localhost/BestDeal/productTab?company=microsoft&prodType=mobile\"\">Microsoft</a></li> \n" +

								"</ul> \n" +
								" <ul> \n" +
								   " <li><a href=\"http://localhost/BestDeal/productTab?company=nokia&prodType=mobile\"\">Nokia</a></li> \n" +

								"</ul> \n" +
								 "<ul> \n <li><a href=\"http://localhost/BestDeal/productTab?company=samsung&prodType=mobile\"\">Samsung</a></li> \n" +

								"</ul> \n" +

						   " </li> \n" + " <li> \n" +
							   " <h4>Tablet</h4> \n" +
							   " <ul> \n" +
								   " <li><a href=\"http://localhost/BestDeal/productTab?company=apple&prodType=tablet\"\">Apple</a></li> \n" +

								"</ul> \n" +

								 " <ul> \n" +
								   " <li><a href=\"http://localhost/BestDeal/productTab?company=microsoft&prodType=tablet\"\">Microsoft</a></li> \n" +

								"</ul> \n" +

								 " <ul> \n" +
								   " <li><a href=\"http://localhost/BestDeal/productTab?company=nokia&prodType=tablet\"\">Nokia</a></li> \n" +

								"</ul> \n" +
								" <ul> \n" +
								   " <li><a href=\"http://localhost/BestDeal/productTab?company=samsung&prodType=tablet\"\">Samsung</a></li> \n" +

								"</ul> \n" +
								 "<ul> \n <li><a href=\"http://localhost/BestDeal/productTab?company=sony&prodType=tablet\"\">Sony</a></li> \n" +

								"</ul> \n" +

						   " </li> \n" +
						   " <li> \n" +
							   " <h4>TV</h4> \n" +
							   " <ul> \n" +
								   " <li><a href=\"http://localhost/BestDeal/productTab?company=lg&prodType=tv\"\">LG</a></li> \n" +

								"</ul> \n" +

								 " <ul> \n" +
								   " <li><a href=\"http://localhost/BestDeal/productTab?company=onida&prodType=tv\"\">Onida</a></li> \n" +

								"</ul> \n" +

								 " <ul> \n" +
								   " <li><a href=\"http://localhost/BestDeal/productTab?company=philips&prodType=tv\"\">Philips</a></li> \n" +

								"</ul> \n" +
								" <ul> \n" +
								   " <li><a href=\"http://localhost/BestDeal/productTab?company=samsung&prodType=tv\"\">Samsung</a></li> \n" +

								"</ul> \n" +
								 "<ul> \n <li><a href=\"http://localhost/BestDeal/productTab?company=sony&prodType=tv\"\">Sony        </a></li> \n" +

								"</ul> \n" +

						   " </li> \n" +

						   " <li> \n"+

								"<h4>About us</h4> \n" +
								"<ul> \n" +
								   " <li class=\"text\"> \n" +
										"<p style=\"margin: 0;\">Aenean nec massa a tortor auctor sodales sed a dolor. Duis vitae lorem sem. Proin at velit vel arcu pretium luctus. 					<a href=\"#\" class=\"readmore\">Read More &raquo;</a></p> \n" +
									 " </li> \n" +
								"</ul>\n" +
							"</li>\n" +




						"</ul>\n" +

				   " </aside> \n" +
					"<div class=\"clear\"></div> \n" +
				"</div>\n" +


			   "<footer>\n" +
					"<div class=\"footer-content\"> \n" +


					   " <ul> \n" +
							"<li><h6>Orders and Return</h6></li>\n" +
						"</ul> \n" +
						"<ul> \n" +
							"<li><h6>Career</h6></li>\n" +



						"</ul> \n" +
					   " <ul class=\"endfooter\"> \n" +
							"<li><h6>Conditions of Use</h6></li> \n" +


						"</ul> \n" +

						"<div class=\"clear\"></div> \n" +
					"</div>\n" +
					"<div class=\"footer-bottom\"> \n" +
						"<p>&copy; BestDeal 2016. All rights reserved</p>\n" +
					 "</div> \n" +
				"</footer> \n" +
			"</div> \n");









		out.println("</body>");
		out.println("</html>");


//RequestDispatcher rd=request.getRequestDispatcher("index.html");
//rd.forward(request, response);



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
