/*
 * Home.java
 *
 */


import java.util.*;
import java.util.Map;
import javax.servlet.*;
import javax.servlet.http.*;

public class Home extends HttpServlet {

 public Map<String,ArrayList<String>> products = new  HashMap<String,ArrayList<String>>();
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
	//products = new  HashMap<String,ArrayList<String>>();

	//String productXmlFileName = "C:/apache-tomcat-7.0.34/webapps/BestDeal/WEB-INF/classes/ProductCatalog.xml";
	//SaxParser4BestDealProducts sx = new SaxParser4BestDealProducts(productXmlFileName);

	   }
	catch(Exception e){ e.printStackTrace();}

    }

    /** Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
    * @param request servlet request
    * @param response servlet response
    */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {

	ArrayList<String> str = new ArrayList<String>();
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






		out.println("<html>");
    out.println("<head>");
		out.println("<title>BestDeal</title>");
		out.println("<title>spigot - Free CSS Template by ZyPOP</title>");
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

/*
    out.println("<div  name=\"autofillform\">");
    out.println("<input type=\"text\" name=\"complete-field\" value=\"\" class=\"input\" id=\"complete-field\" onkeyup=\"doCompletion()\" placeholder=\"search here..\" style=\"padding: 5px; font-size: 16px;\" />");
    out.println("<div id=\"auto-row\">");
    out.println("<table id=\"complete-table\" class=\"gridtable\" style=\"position: absolute; width:315px;\"></table>");
    out.println("</div>");
    out.println("</div>");
*/



















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

						"<li class=\"end\"><a href=\"http://localhost/BestDeal/Trending\">Trending</a></li> \n" +
            "<li class=\"end\"><a href=\"#\">CONTACT</a></li> \n" +
					"</ul>\n" +
				"</nav>\n" +



				"<div id=\"body\"> \n" +



					"<section id=\"content\"> \n" +


						"<h2>Welcome to BestDeal</h2> \n" +

"<img class=\"header-image\" src=\"images/homePage.jpg\" style = \"width:100%;height:50%;\" />"
						+"<p></p>\n" );


            String formURL =  "/BestDeal/OrderPage";
            formURL = response.encodeURL(formURL);
            String reviewURL =  "/BestDeal/reviewForm";
            reviewURL = response.encodeURL(reviewURL);
            String viewReviewURL =  "/BestDeal/viewReview";
            viewReviewURL = response.encodeURL(viewReviewURL);
            String imageFolder;
DealMatch dm = new DealMatch();
HashMap<String,Product> map = new HashMap<String,Product>();
HashMap<String,String> deals = new HashMap<String,String>();
map = dm.getMatchedProducts();
map =  dm.getMatchedProducts();
deals = dm.getDeals();
out.println("<H1> We beat our comptitors in all aspects price-match Guaranteed</H1>");
if(deals.isEmpty()){
out.println("<H6> No match found</H6>");
}else{
//  int size = deals.size();
//out.println("size of linkedlist is" +size);
out.println();
    out.println("<table>");
      for(String string: deals.keySet()){
           out.println("<tr>"+deals.get(string)+"</tr>");
           out.println("<BR>");
        }
out.println("</table>");


for(String key: map.keySet()){
  Product prd = map.get(key);


  String prodType = MySQLDataStoreUtilities.ProductType(prd.getId());

    if(prodType.equals("laptop")){
      imageFolder = "/images/laptop/";
  //    products = laptop;
    }
    else if(prodType.equals("tablet")){
      imageFolder = "/images/tablet/";
  //    products = tablet;
    }
    else if (prodType.equals("mobile")){
  //    products = mobile;
      imageFolder = "/images/mobile/";
    }
    else {
  //    products = tv;
      imageFolder = "/images/tv/";
    }


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

}
				   out.println(" </section> \n" +

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
