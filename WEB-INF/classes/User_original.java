public class User implements java.io.Serializable{
String username;
String password;
String firstName;
String lastname;
String email;
String phone;

User(String username,String password,String firstName,String lastName,String email,String phone){
	this.username = username;
	this.password = password;
	this.firstName = firstName;
	this.lastname = lastName;
	this.email = email;
	this.phone = phone;
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

}