public class Review {
String ProductId;
String ProductModelName;
String ProductCategory;
String ProductPrice;
String RetailerName;
String RetailerZip;
String RetailerCity;
String RetailerState;
String ProductOnSale;
String mname;
String mrebate;
String uid;
String age;
String gender;
String occupation;
String date;
Integer rating;
String reviewText;
Review(String ProductId,String ProductModelName,String ProductCategory,
    String ProductPrice,String RetailerName,
    String RetailerZip,String RetailerCity,String RetailerState,
    String ProductOnSale,String mname,String mrebate,String uid,String age,
    String gender,String occupation,String date,
    Integer rating,String reviewText){
    this.ProductId = ProductId;
    this.ProductModelName = ProductModelName;
    this.ProductCategory = ProductCategory;
    this.ProductPrice = ProductPrice;
    this.RetailerName = RetailerName;
    this.RetailerZip = RetailerZip;
    this.RetailerCity = RetailerCity;
    this.RetailerState = RetailerState;
    this.ProductOnSale = ProductOnSale;
    this.mname = mname;
    this.mrebate = mrebate;
    this.uid = uid;
    this.age = age;
    this.gender = gender;
    this.occupation = occupation;
    this.date = date;
    this.rating = rating;
    this.reviewText = reviewText;
   }
String getProductId(){return ProductId;}
String getProductModelName(){return ProductModelName;}
String getProductCategory(){return ProductCategory;}
String getProductPrice(){return ProductPrice;}
String getRetailerName(){return RetailerName;}
String getRetailerZip(){return RetailerZip;}
String getRetailerCity(){return RetailerCity;}
String getRetailerState(){return RetailerState;}
String getProductOnSale(){return ProductOnSale;}
String getmname(){return mname;}
String getmrebate(){return mrebate;}
String getuid(){return uid;}
String getage(){return age;}
String getgender(){return gender;}
String getoccupation(){return occupation;}
String getdate(){return date;}
Integer getrating(){return rating;}
String getreviewText(){return reviewText;}
}
