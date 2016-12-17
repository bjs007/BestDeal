/*
 * product.java
 * Author: Bijay Sharma
 */

import java.util.*;
import java.util.Map;
import javax.servlet.*;
import javax.servlet.http.*;

public class productTab extends HttpServlet {

 public HashMap<String,Product> laptop = new  HashMap<String,Product>();
 public HashMap<String,Product> mobile = new  HashMap<String,Product>();
 public HashMap<String,Product> tablet = new  HashMap<String,Product>();
 public HashMap<String,Product> tv = new  HashMap<String,Product>();

 public HashMap<String,Product> laptopSession ;
 public HashMap<String,Product> mobileSession ;
 public HashMap<String,Product> tabletSession ;
 public HashMap<String,Product> tvSession ;



    /**
     * Initializes the servlet with some usernames/password
    */
    public void init() {
       try{


      	}
      	catch(Exception e){
      	   e.printStackTrace();
      	}
  }

    /** Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
    * @param request servlet request
    * @param response servlet response
    */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {

    response.setContentType("text/html");
    java.io.PrintWriter out = response.getWriter();
    HttpSession session = request.getSession(true);


    String productXmlFileName;
    SaxParser4BestDealProducts  sx;
    String TOMCAT_HOME = "C:/apache-tomcat-7.0.34";

    laptopSession = (HashMap<String,Product>)session.getValue("laptopSession");
    mobileSession = (HashMap<String,Product>)session.getValue("mobileSession");
    tabletSession = (HashMap<String,Product>)session.getValue("tabletSession");
    tvSession = (HashMap<String,Product>)session.getValue("tvSession");

   if(laptopSession == null){

    productXmlFileName = TOMCAT_HOME+"/webapps/BestDeal/WEB-INF/classes/ProductCatalog.xml";
    sx = new SaxParser4BestDealProducts (productXmlFileName);
    this.laptop = sx.prettyPrint();
    session.setAttribute("laptopSession",this.laptop);
    for(String key:laptop.keySet()){
      MySQLDataStoreUtilities.insertProduct(laptop.get(key),"laptop");
    }
  }else{
    this.laptop = laptopSession;
  }

    if(mobileSession == null){
    productXmlFileName = "C:/apache-tomcat-7.0.34/webapps/BestDeal/WEB-INF/classes/MobileCatalog.xml";
    sx = new SaxParser4BestDealProducts (productXmlFileName);
    this.mobile = sx.prettyPrint();
    session.setAttribute("mobileSession",this.mobile);
    for(String key:mobile.keySet()){
      MySQLDataStoreUtilities.insertProduct(mobile.get(key),"mobile");
    }
  }else{
    this.mobile = mobileSession;
  }

   if(tabletSession == null){
    productXmlFileName = "C:/apache-tomcat-7.0.34/webapps/BestDeal/WEB-INF/classes/TabletCatalog.xml";
    sx = new SaxParser4BestDealProducts (productXmlFileName);
    this.tablet = sx.prettyPrint();
    session.setAttribute("tabletSession",this.tablet);
    for(String key:tablet.keySet()){
      MySQLDataStoreUtilities.insertProduct(tablet.get(key),"tablet");
    }

  }else{
    this.tablet = tabletSession;
  }

  if(tvSession ==  null){
    productXmlFileName = "C:/apache-tomcat-7.0.34/webapps/BestDeal/WEB-INF/classes/TvCatalog.xml";
    sx = new SaxParser4BestDealProducts (productXmlFileName);
    this.tv = sx.prettyPrint();
    session.setAttribute("tvSession",this.tv);
    for(String key:tv.keySet()){
      MySQLDataStoreUtilities.insertProduct(tv.get(key),"tv");
    }
  }else{
     this.tv = tvSession;
  }





    String userid = (String)session.getValue("userid");
    HashMap<String,Product> products = new HashMap<String,Product>();
		String prodType = request.getParameter("prodType");
    String company  = null;
    company = request.getParameter("company");
    String imageFolder;

		out.println("<html>");
    out.println("<head>");
		out.println("<title>Products</title>");
		out.println("<link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" />");
		out.println("</head>");
		out.println("<body>");
		out.println("<div id=\"container\">");
		out.println("<header>");
		out.println("<h1 style=\"color:red\">Best<span>Deal</span></h1>");
		out.println("<h2 style=\"color:blue\"><I> Better than it is not possible</I></h2>");
		out.println("</header>");
		out.println("<div align=\"right\">\n" );

	  if(userid!=null){
				out.println("Welcome Back "+userid+"!" );
				out.println("<a href= \"http://localhost/BestDeal/logout\">Logout</a>");
				}else{
				out.println("<a href=\"signIn.html\">Sign In</a>\n" +
				"<a href=\"createAccount.htm\"> Create account</a>\n");
				}

    out.println("</div>\n");

   /*
          Code to handle particular product type
   */

		if(prodType.equals("laptop")){
			imageFolder = "/images/laptop/";
			products = laptop;
		}
		else if(prodType.equals("tablet")){
			imageFolder = "/images/tablet/";
			products = tablet;
		}
		else if (prodType.equals("mobile")){
			products = mobile;
			imageFolder = "/images/mobile/";
		}
		else {
			products = tv;
			imageFolder = "/images/tv/";
		}



			String formURL =  "/BestDeal/OrderPage";
		  formURL = response.encodeURL(formURL);
      String reviewURL =  "/BestDeal/reviewForm";
      reviewURL = response.encodeURL(reviewURL);
      String viewReviewURL =  "/BestDeal/viewReview";
      viewReviewURL = response.encodeURL(viewReviewURL);

		  if(company==null){

			for(String key: products.keySet()){
			    Product prd = products.get(key);

          out.println("<table>");

					out.println("<tr>");
					out.println("<td>");
					out.println("<img src=" +request.getContextPath()+imageFolder+prd.getImage());
					out.println("alt=\"Product\" width=\"250\" height=\"200\">");
					out.println("</td>");

					out.println("<td>");
					out.println("<B>Short Description:</B>"+prd.getShortDescription());
					out.println("<BR><B>Price: $</B>"+prd.getPrice());
					out.println("</td>");
					out.println("<td>");

			//out.println(userid);
      			    if(userid!=null){
              //  out.println(prd.getId());
                out.println("<FORM ACTION=\""+formURL +  "\">\n"+
        			    "<INPUT TYPE=\"HIDDEN\" NAME=\"itemID\" " +
                  "VALUE=\"" + prd.getId()+"\">"+
        			    "<INPUT TYPE=\"HIDDEN\" NAME=\"prodname\" " +
                  "VALUE=\"" + prd.getName()+"\">" +
          			  "<INPUT TYPE=\"HIDDEN\" NAME=\"Short Description\" " +
                  "VALUE=\"" + prd.getShortDescription()+"\">");
                out.println("<INPUT TYPE=\"SUBMIT\" " + "VALUE=\"Add to Shopping Cart\">\n");
                out.println("</FORM>");


                out.println("<FORM ACTION=\""+reviewURL +  "\">\n"+
                "<INPUT TYPE=\"HIDDEN\" NAME=\"itemID\"VALUE=\"" + prd.getId()+"\">");
      			    out.println("<INPUT TYPE=\"SUBMIT\" " + "VALUE=\"Write Review\">\n");
                out.println("</FORM>");

                out.println("<FORM ACTION=\""+viewReviewURL +  "\">\n"+
        			    "<INPUT TYPE=\"HIDDEN\" NAME=\"prodname\" " +
                  "VALUE=\"" + prd.getName()+"\">" +
          			  "<INPUT TYPE=\"HIDDEN\" NAME=\"Short Description\" " +
                  "VALUE=\"" + prd.getShortDescription()+"\">" );
                out.println(  "<INPUT TYPE=\"HIDDEN\" NAME=\"itemID\"VALUE=\"" + prd.getId()+"\">");
      			    out.println("<INPUT TYPE=\"SUBMIT\" " + "VALUE=\"View Review\">\n");
                out.println("</FORM>");

                out.println("</td>");
          			out.println("</tr>");
          			out.println("</table>");

      			}
      				else{
          			out.println("<a href= \"http://localhost/BestDeal/signIn.html\">Login to buy</a>");
          			out.println("</td>");
      		    	out.println("</tr>");
      					out.println("</table>");
      		  		out.println("</FORM>");
				}
			}
			}else{



				for(String key: products.keySet())

			{
			Product prd = products.get(key);
			//ArrayList<String> detail = products.get(key);
			if(prd.getName().equals(company)){


			  	out.println("<table>");
					out.println("<tr>");
					out.println("<td>");
					out.println("<img src=" +request.getContextPath()+imageFolder+prd.getImage());
					out.println("alt=\"Product\" width=\"250\" height=\"200\">");
					out.println("</td>");

					out.println("<td>");
					out.println("<B>short Description:</B>"+prd.getShortDescription());
					out.println("<BR><B>Price: $</B>"+prd.getPrice());
					out.println("</td>");
					out.println("<td>");

			//out.println(userid);
			if(userid!=null){
        out.println(prd.getId());
        out.println("<FORM ACTION=\""+formURL +  "\">\n"+
          "<INPUT TYPE=\"HIDDEN\" NAME=\"itemID\" " +
          "VALUE=\"" + prd.getId()+"\">"+
          "<INPUT TYPE=\"HIDDEN\" NAME=\"prodname\" " +
          "VALUE=\"" + prd.getName()+"\">" +
          "<INPUT TYPE=\"HIDDEN\" NAME=\"Short Description\" " +
          "VALUE=\"" + prd.getShortDescription()+"\">");
        out.println("<INPUT TYPE=\"SUBMIT\" " + "VALUE=\"Add to Shopping Cart\">\n");
        out.println("</FORM>");


        out.println("<FORM ACTION=\""+reviewURL +  "\">\n"+
        "<INPUT TYPE=\"HIDDEN\" NAME=\"itemID\"VALUE=\"" + prd.getId()+"\">");
        out.println("<INPUT TYPE=\"SUBMIT\" " + "VALUE=\"Write Review\">\n");
        out.println("</FORM>");

        out.println("<FORM ACTION=\""+viewReviewURL +  "\">\n"+
          "<INPUT TYPE=\"HIDDEN\" NAME=\"prodname\" " +
          "VALUE=\"" + prd.getName()+"\">" +
          "<INPUT TYPE=\"HIDDEN\" NAME=\"Short Description\" " +
          "VALUE=\"" + prd.getShortDescription()+"\">" );
        out.println(  "<INPUT TYPE=\"HIDDEN\" NAME=\"itemID\"VALUE=\"" + prd.getId()+"\">");
        out.println("<INPUT TYPE=\"SUBMIT\" " + "VALUE=\"View Review\">\n");
        out.println("</FORM>");

        out.println("</td>");
        out.println("</tr>");
        out.println("</table>");




     }
				else
			out.println("<a href= \"http://localhost/BestDeal/signIn.html\">Login to buy</a>");

      out.println("<td>");
    	out.println("</td>");
			out.println("</tr>");
			out.println("</table>");
			out.println("</FORM>");

			}


			}




			}



			out.println("</div>");
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
