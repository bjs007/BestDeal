public class User implements java.io.Serializable{
String username;
String password;
String firstName;
String lastname;
String email;
String phone;
String userType;

User(String username,String password,String firstName,String lastName,String email,String phone,String userType){
	this.username = username;
	this.password = password;
	this.firstName = firstName;
	this.lastname = lastName;
	this.email = email;
	this.phone = phone;
	this.userType = userType;
}

public String getUsername(){
	return this.username;
}

public String getPassword(){
	return this.password;
}
public String getfirstName(){
	return this.firstName;
}
public String getlastName(){
	return this.lastname;
}
public String getemail(){
	return this.email;
}
public String getphone(){
	return this.phone;
}

public String getUserType(){
 return this.userType;
}

}