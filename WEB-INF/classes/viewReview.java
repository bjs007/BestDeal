

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.text.*;


public class viewReview extends HttpServlet {
 HashMap<String,ArrayList<Review>> reviewHashmap = null;



  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
      throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
    		HttpSession session = request.getSession(true);
        String itemID = request.getParameter("itemID");
    	  reviewHashmap =  MongoDBDataStoreUtilities.selectReviews();

    		out.println("<html>");
        out.println("<head>");
    		out.println("<title>spigot - Free CSS Template by ZyPOP</title>");
    		out.println("<title>spigot - Free CSS Template by ZyPOP</title>");
    		out.println("<link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" />");
    		out.println("</head>");
    		out.println("<body>");

    		out.println("<div id=\"container\">");
    		out.println("<header>");
    		out.println("<h1 style="+ "\"color:red\"" + "<span>BestDeal</span></h1>");
    		out.println("<h2 style=\"color:blue\"><I> Better than this is not possible</I></h2>");
    		out.println("</header>\n");
        out.println("<div id=\"body\"> ");
        out.println("<div style=\"width: 50%;margin:0 auto\">");

    //		out.println("<div style=\"width: 30%;height:100%;margin:0 auto\">");
    	//	out.println("<CENTER> <h3> Please provide below detail to buy</h3> </CENTER>");

        if(reviewHashmap !=null ) {
           ArrayList<Review> reviews = reviewHashmap.get(itemID);
                if(reviews !=null) {
                int count = 0;
                for(Review review : reviews){
                  count++;
                  out.println("<h2 style=\"color:red;\"> Review# "+count+" </h2>");
                  out.println("<table BORDER=1 align =\"CENTER\" style = \"margin: 0px auto;\" cellpadding='2' cellspacing = '1'>\n");
                  out.println(" <TR BGCOLOR=\"#FFAD00\">\n");
            		out.println("<tr><td>ProductModelName: </td><td>"+review.getProductModelName()+"</td></tr>");
                out.println("<tr><td>ProductCategory: </td><td>"+review.getProductCategory()+"</td></tr>");
                out.println("<tr><td>ProductPrice: </td><td>"+review.getProductPrice()+"</td></tr>");
                out.println("<tr><td>RetailerName: </td><td>"+review.getRetailerName()+"</td></tr>");
                out.println("<tr><td>RetailerZip: </td><td>"+review.getRetailerZip()+"</td></tr>");
                out.println("<tr><td>RetailerCity: </td><td>"+review.getRetailerCity()+"</td></tr>");
                out.println("<tr><td>RetailerState: </td><td>"+review.getRetailerState()+"</td></tr>");
                out.println("<tr><td>ProductOnSale: </td><td>"+review.getProductOnSale()+"</td></tr>");
                out.println("<tr><td>ManufacturerName: </td><td>"+review.getmname()+"</td></tr>");
                out.println("<tr><td>ManufacturerRebate: </td><td>"+review.getmrebate()+"</td></tr>");
                out.println("<tr><td>Userid: </td><td>"+review.getuid()+"</td></tr>");
                out.println("<tr><td>UserAge: </td><td>"+review.getage()+"</td></tr>");
                out.println("<tr><td>UserGender: </td><td>"+review.getgender()+"</td></tr>");
                out.println("<tr><td>UserOccupation: </td><td>"+review.getoccupation()+"</td></tr>");
                out.println("<tr><td>ReviewDate: </td><td>"+review.getdate()+"</td></tr>");
                out.println("<tr><td>ReviewRating: </td><td>"+review.getrating()+"</td></tr>");
                out.println("<tr><td>RevieText: </td><td>"+review.getreviewText()+"</td></tr>\n");

                out.println("</table>");

               }
              }else{
                out.println("No review available");
              }
            }else{
              out.println("No review available");
            }



    		out.println("</div> \n");
    	//	out.println("</div> \n");
    		out.println("</div> \n");
    		out.println("</body>");
    		out.println("</html>");


  }
}
