import java.util.*;
import java.io.*;
public class UserAccountSerializedDataStore{


// The known platforms

	public  HashMap<String, User> users = null;
	
// polulate the data for the different platforms into HashMaps




// Polulate the data for Microsoft into HashMap

public void populateUserData(User u){

			//User u = new User(u.getUsername(),u.getPassword(),u.getfirstName(),u.getlastName(),u.getemail(),u.getphone());
			users = readUserAccountDataStore();
			if(users==null){
				users = new HashMap<String,User>();
				users.put(u.getUsername(), u);
				writeUserAccountDataStore(u);
			}
			else if (users.get(u.getUsername())==null){
				users.put(u.getUsername(), u);
				writeUserAccountDataStore(u);
			}
			
}




public void  writeUserAccountDataStore(User u){
	
	 HashMap<String, User> userAccounts  =	readUserAccountDataStore();
    try{
		File file = new File("UserDataStore");
		//String file = "UserDataStore";
		String str = file.getAbsolutePath();
	//OutputStream userDataStore = (OutputStream)UserAccountSerializedDataStore.class.getResource("UserDataStore");
	//BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(str+"/UserDataStore"));
	File userDataStore = new File("C:/apache-tomcat-7.0.34/webapps/BestDeal/WEB-INF/classes/UserDataStore");
    //  int size;
		//File userDataStore =new File(file);
		FileOutputStream uos=new FileOutputStream(userDataStore);
        ObjectOutputStream oos=new ObjectOutputStream(uos);
		userAccounts.put(u.getUsername(),u);
	
        oos.writeObject(userAccounts);
        oos.flush();
        oos.close();
		uos.close();
	//	userDataStore.close();
	
    }catch(Exception e){
		e.printStackTrace();
		System.out.println("Could NOT Write microsoft to GameSpeedDataStore ...");
	}

}


// Read the HashMaps from the File GameSpeedDataStore

public HashMap<String,User> readUserAccountDataStore() {
 HashMap<String,User> usersStored = null ;
 //  User u = new User("bsharma","abc","Bijay","Sharma","bijays.nitdgp@gmail.com","3126787952");
  // usersStored.put(u.username,u);
  usersStored = new HashMap<String,User>();
    try{
		
		InputStream userDataStore = UserAccountSerializedDataStore.class.getResourceAsStream("UserDataStore");
       // File userDataStore=new File("UserDataStore");
       // FileInputStream uis=new FileInputStream(userDataStore);
        ObjectInputStream ois=new ObjectInputStream(userDataStore);

        usersStored=(HashMap<String,User>)ois.readObject();

        ois.close();
     
		
		
        
        for(Map.Entry<String,User> m :usersStored.entrySet()){
            	System.out.println(m.getKey());
		User c = m.getValue();
		
		System.out.println("\t Login Name : "+c.getUsername());
		System.out.println("\t Firs Name : "+c.getfirstName());
		System.out.println("\t Last Name : "+c.getlastName());
		System.out.println("\t User Type : "+c.getUserType());
		
		}
        
    }catch(Exception e){} 
	
	return usersStored;
}



// the testDrive method for SANITY tesing

public static void main(String args[]){

UserAccountSerializedDataStore us = new UserAccountSerializedDataStore();
	
	User u = new User("bsharma","abc","Bijay","Sharma","bijays.nitdgp@gmail.com","3126787952","customer");
	//us.populateUserData(u);
	//us.populateUserData(u);
	//us.writeUserAccountDataStore();
	us.readUserAccountDataStore();

}



}