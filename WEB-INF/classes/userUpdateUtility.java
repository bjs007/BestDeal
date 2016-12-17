import java.util.*;
import java.sql.*;
public class userUpdateUtility{
	
	Connection conn = null;
	public void insertUser(User user){
		String userid = user.getUsername();
		String password = user.getPassword();
		String firstName = user.getfirstName();
		String lastName = user.getlastName();
		String email = user.getemail();
		String phone = user.getphone();
		String userType = user.getUserType();
		try{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdealdatabase","root","mysql");
		String queryString = "INSERT INTO USER(userid,password,firstName,lastName,email,phone,userType)" 
		+ "VALUES(?,?,?,?,?,?,?);";
		PreparedStatement pst = conn.prepareStatement(queryString);
		pst.setString(1,userid);
		pst.setString(2,password);
		pst.setString(3,firstName);
		pst.setString(4,lastName);
		pst.setString(5,email);
		pst.setString(6,phone);
		pst.setString(7,userType);
		pst.execute();
		
			
		}catch(Exception e){
			
			e.printStackTrace();
		}
	}
	
		public boolean doesExist(String userid){
		boolean found = false;
		
		try{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdealdatabase","root","mysql");
		String queryString = "SELECT userid FROM USER WHERE USERID = ?"; 
		PreparedStatement pst = conn.prepareStatement(queryString);
		pst.setString(1,userid);
	
	
		ResultSet rs = pst.executeQuery();
		if (rs.next()){
			found = true;
			System.out.println("I'm here in Utility");
		}
		else {
			System.out.println("I'm not here");
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return found;
	}
	
	
	public boolean doesExist(String userid,String password,String userType){
		boolean found = false;
		
		try{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdealdatabase","root","mysql");
		String queryString = "SELECT userid FROM USER WHERE USERID = ? and USERTYPE = ? and password = ?"; 
		PreparedStatement pst = conn.prepareStatement(queryString);
		pst.setString(1,userid);
		pst.setString(2,userType);
		pst.setString(3,password);
	
	
		ResultSet rs = pst.executeQuery();
		if (rs.next()){
			found = true;
			//System.out.println("I'm here in Utility");
		}
		
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return found;
	}
	
	
	public static void main(String args[]){
	/*	User u = new User("bsharma","mysql","Bijay","Sharma","bijays.nitdpg@gmail.com","3126787952","customer");
		
		
		boolean found = false;
userUpdateUtility up = new userUpdateUtility();	
up.insertUser(u);	
		found = up.doesExist("bsharma");
		System.out.println(found);
	*/
	}
	
}