

import java.util.ArrayList;
import java.util.List;


public class Product implements java.io.Serializable {
    String retailer;
    String name;
    String id;
    String shortDescription;
    String image;
    int price;
    List<String> accessories;
    public Product(){
        accessories=new ArrayList<String>();
    }



public void setId(String id) {
	this.id = id;
}
public String getId(){ return id;}
public void setName(String name) {
	this.name = name;
}
public String getName(){return name;}
public void setRetailer(String retailer) {
	this.retailer = retailer;
}
public String getRetailer(){return retailer;}


public void setImage(String image) {
	this.image = image;
}
public String getImage(){return image;}

public void setShortDescription(String shortDescription) {
	this.shortDescription = shortDescription;
}

public String getShortDescription(){return shortDescription;}
public void setPrice(int price) {
	this.price = price;
}
public int getPrice(){return this.price;}

public List getAccessories() {
	return accessories;
}





}
