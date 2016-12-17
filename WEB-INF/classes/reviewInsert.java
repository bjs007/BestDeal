/*
*
*
*
*/

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.text.*;


public class reviewInsert extends HttpServlet {
  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
      throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
    		HttpSession session = request.getSession(true);
        String userid =(String) session.getValue("userid");
        String itemID = request.getParameter("itemID");
        String ProductModelName = request.getParameter("ProductModelName");
        String ProductCategory = request.getParameter("ProductCategory");
        String ProductPrice = request.getParameter("ProductPrice");
        String RetailerName = request.getParameter("RetailerName");
        String RetailerZip = request.getParameter("RetailerZip");
        String RetailerCity = request.getParameter("RetailerCity");
        String RetailerState = request.getParameter("RetailerState");
        String ProductOnSale = request.getParameter("ProductOnSale");
        String mname = request.getParameter("mname");
        String mrebate = request.getParameter("mrebate");
        String uid = request.getParameter("uid");
        String age = request.getParameter("age");
        String gender = request.getParameter("gender");
        String occupation = request.getParameter("occupation");
        String date = request.getParameter("rdate");
        Integer rating = Integer.parseInt(request.getParameter("rating"));
        String reviewText = request.getParameter("reviewText");

        Review review = new Review(itemID,ProductModelName,ProductCategory,ProductPrice,RetailerName,RetailerZip,
        RetailerCity,RetailerState,ProductOnSale,mname,mrebate,uid,age,gender,occupation,date,rating,reviewText);

        MongoDBDataStoreUtilities.insertReview(review);
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
        out.println("<div style=\"width: 30%;height:100%;margin:0 auto\">");
        out.println("Review submitted");

    //		out.println("<div style=\"width: 30%;height:100%;margin:0 auto\">");
    	//	out.println("<CENTER> <h3> Please provide below detail to buy</h3> </CENTER>");

    		out.println("</div> \n");
    	//	out.println("</div> \n");
    		out.println("</div> \n");
    		out.println("</body>");
    		out.println("</html>");


  }
}
