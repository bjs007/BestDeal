/*
 * LoginServlet.java
 *
 */
 

import java.util.HashMap;
import java.util.Map;
import javax.servlet.*;
import javax.servlet.http.*;

public class LoginServlet extends HttpServlet {
   HashMap<String,User> users;
   UserAccountSerializedDataStore us;
    /** 
     * Initializes the servlet with some usernames/password
    */  
    public void init() {
           //     us = new UserAccountSerializedDataStore();
			//	users = us.readUserAccountDataStore();
				
				userUtility = new userUpdateUtility();
    }

    /** Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
    * @param request servlet request
    * @param response servlet response
    */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
	HttpSession session = request.getSession(true);
        String userid = request.getParameter("userid");
        String password = request.getParameter("password");
		String userType = request.getParameter("userType");
		session.putValue("userType",userType);
		java.io.PrintWriter out1 = response.getWriter();
		
		
        if(userid != null && userid.length() != 0) {
            userid = userid.trim();
        }
        if(password != null && password.length() != 0) {
            password = password.trim();
        }
		String realpassword = null;
        if(userid != null &&
            password != null) {
                User u = users.get(userid);
				if(u==null){
					showPage(response,request, "User does not exists!",false);
				}else{
				realpassword = u.getPassword();
				}
				
                if(realpassword != null &&
                    realpassword.equals(password)) {
						session.putValue("userid", userid);
						
						String userCat = u.getUserType();
						if(userCat!=null){
						if(userCat.equals("customer")){
							
							RequestDispatcher rd=request.getRequestDispatcher("/Home");
							rd.forward(request, response);
							
							//showPage(response, request,userCat,true);
							
						}
						else if(userCat.equals("salesman")){
						  //	showPage(response,request, "User does not exists!",false);	
							RequestDispatcher rd=request.getRequestDispatcher("/salesman.html");
							rd.forward(request, response);					
						}
						else{
							RequestDispatcher rd=request.getRequestDispatcher("/salesmanager.html");
							rd.forward(request, response);
						}
						
						
						
					}else{
						showPage(response,request, "User has no type!",false);
					}
					
					
					
                } else {
                    showPage(response,request, "Login Failure! Username or password is incorrect",false);
                }
        }  else {
            showPage(response, request,"Login Failure!  You must supply a username and password",false);
        }
    } 
    
    /**
     * Actually shows the <code>HTML</code> result page
     */
    protected void showPage(HttpServletResponse response,HttpServletRequest request, String message,boolean login)
    throws ServletException, java.io.IOException {

	if (login== true)
	{
		RequestDispatcher rd=request.getRequestDispatcher("/Home");
		rd.forward(request, response);
	} 
        response.setContentType("text/html");
        java.io.PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Login Servlet Result</title>");  
        out.println("</head>");
        out.println("<body>");
        out.println("<h2>" + message + "</h2>");
        out.println("</body>");
        out.println("</html>");
        out.close();
	
 
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
