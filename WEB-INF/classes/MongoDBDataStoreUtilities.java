import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Cursor;
import com.mongodb.AggregationOutput;
import java.util.*;
public class MongoDBDataStoreUtilities{
  static DBCollection myReviews;
  public static void getConnection(){
    MongoClient mongo;
    mongo = new MongoClient("localhost",27017);
    DB db = mongo.getDB("CustomerReviews");
    myReviews = db.getCollection("myReviews");
  }


  public static HashMap<String,ArrayList<Review>> selectReviews(){
    getConnection();
    HashMap<String,ArrayList<Review>> reviewHashmap = new HashMap<String,ArrayList<Review>>();

    DBCursor cursor = myReviews.find();
    while(cursor.hasNext()){
      BasicDBObject obj = (BasicDBObject) cursor.next();
      if(!reviewHashmap.containsKey(obj.getString("ProductId"))){
        ArrayList<Review> reviews = new ArrayList<Review>();
        reviewHashmap.put(obj.getString("ProductId"),reviews);
      }
      ArrayList<Review> listReview = reviewHashmap.get(obj.getString("ProductId"));
      Review review = new Review(
      obj.getString("ProductId"),
      obj.getString("ProductModelName"),
      obj.getString("ProductCategory"),
      obj.getString("ProductPrice"),
      obj.getString("RetailerName"),
      obj.getString("RetailerZip"),
      obj.getString("RetailerCity"),
      obj.getString("RetailerState"),
      obj.getString("ProductOnSale"),
      obj.getString("mname"),
      obj.getString("mrebate"),
      obj.getString("uid"),
      obj.getString("age"),
      obj.getString("gender"),
      obj.getString("occupation"),
      obj.getString("date"),
      obj.getInt("rating"),
      obj.getString("reviewText")
      );
      listReview.add(review);
      reviewHashmap.put(obj.getString("ProductId"),listReview);
    }
    return reviewHashmap;
  }
/*
  public static void storeReview(Review review){
    HashMap<String,ArrayList<Review>> reviews = new HashMap<String,ArrayList<Review>>();
    try{
      reviews = MongoDBDataStoreUtilities.selectReview();
    }catch(Exception e){e.printStackTrace();}
    if(!reviews.containsKey(review.getProductModelName())){
      ArrayList<Review> reviewList = new ArrayList<Review>();
      reviews.put(review.getProductModelName(),reviewList);
    }
    ArrayList<Review> reviewList = reviews.get(review.getProductModelName());
    reviewList.add(review);
  }
*/

  public static void insertReview(Review review){
    getConnection();
    BasicDBObject doc = new BasicDBObject("title","myReviews").
                        append("ProductId",review.getProductId()).
                        append("ProductModelName",review.getProductModelName()).
                        append("ProductCategory",review.getProductCategory()).
                        append("ProductPrice",review.getProductPrice()).
                        append("RetailerName",review.getRetailerName()).
                        append("RetailerZip",review.getRetailerZip()).
                        append("RetailerCity",review.getRetailerCity()).
                        append("RetailerState",review.getRetailerState()).
                        append("ProductOnSale",review.getProductOnSale()).
                        append("mname",review.getmname()).
                        append("mrebate",review.getmrebate()).
                        append("uid",review.getuid()).
                        append("age",review.getage()).
                        append("gender",review.getgender()).
                        append("occupation",review.getoccupation()).
                        append("date",review.getdate()).
                        append("rating",review.getrating()).
                        append("reviewText",review.getreviewText());
    myReviews.insert(doc);

  }

public static AggregationOutput topFiveZipCodes(){
  getConnection();
  DBObject groupFields = new BasicDBObject("_id",0);
  groupFields.put("_id","$RetailerZip");
  groupFields.put("count",new BasicDBObject("$sum",1));
  DBObject group = new BasicDBObject("$group",groupFields);
  DBObject projectFields = new BasicDBObject("_id",0);
  projectFields.put("Zip-codes","$_id");
  projectFields.put("Review Count","$count");
  DBObject project = new BasicDBObject("$project",projectFields);
  DBObject sort = new BasicDBObject();
  sort.put("Review Count",-1);
  DBObject limit=new BasicDBObject();
  DBObject orderby=new BasicDBObject();
  orderby=new BasicDBObject("$sort",sort);
  limit=new BasicDBObject("$limit",5);
  AggregationOutput aggregate = myReviews.aggregate(group,project,orderby,limit);
  for (DBObject result : aggregate.results()) {
      BasicDBObject bobj = (BasicDBObject) result;
      System.out.println(bobj.getString("Zip-codes"));
      System.out.println(bobj.getString("Review Count"));
    }

 return aggregate;
}

public static AggregationOutput topFiveSoldProducts(){
  getConnection();
  DBObject groupFields = new BasicDBObject("_id",0);
  groupFields.put("_id","$ProductModelName");
  groupFields.put("count",new BasicDBObject("$sum",1));
  DBObject group = new BasicDBObject("$group",groupFields);
  DBObject projectFields = new BasicDBObject("_id",0);
  projectFields.put("Product","$_id");
  projectFields.put("Sale Count","$count");
  DBObject project = new BasicDBObject("$project",projectFields);
  DBObject sort = new BasicDBObject();
  sort.put("Sale Count",-1);
  DBObject limit=new BasicDBObject();
  DBObject orderby=new BasicDBObject();
  orderby=new BasicDBObject("$sort",sort);
  limit=new BasicDBObject("$limit",5);
  AggregationOutput aggregate = myReviews.aggregate(group,project,orderby,limit);
  for (DBObject result : aggregate.results()) {
      BasicDBObject bobj = (BasicDBObject) result;
      System.out.println(bobj.getString("Product"));
      System.out.println(bobj.getString("Sale Count"));
    }

 return aggregate;
}

public static AggregationOutput topFivemostLikedProducts(){
  getConnection();
  DBObject groupFields = new BasicDBObject("_id",0);
  groupFields.put("_id","$ProductModelName");
  groupFields.put("average_rating", new BasicDBObject("$avg", "$rating"));
  DBObject group = new BasicDBObject("$group",groupFields);
  DBObject projectFields = new BasicDBObject("_id",0);
  projectFields.put("Product","$_id");
  //projectFields.put("Average_rating",new BasicDBObject("$average_rating", 3));
  projectFields.put("Average_rating","$average_rating");
  DBObject match = new BasicDBObject("$match",new BasicDBObject("Average_rating",new BasicDBObject("$gt",3)));
  DBObject project = new BasicDBObject("$project",projectFields);
  DBObject sort = new BasicDBObject();
  sort.put("Average_rating",-1);
  DBObject limit=new BasicDBObject();
  DBObject orderby=new BasicDBObject();
  orderby=new BasicDBObject("$sort",sort);
  limit=new BasicDBObject("$limit",5);
  AggregationOutput aggregate = myReviews.aggregate(group,project,orderby,limit,match);
  for (DBObject result : aggregate.results()) {
      BasicDBObject bobj = (BasicDBObject) result;
      System.out.println(bobj.getString("Product"));
      System.out.println(bobj.getString("Average_rating"));
    }

 return aggregate;
}


public static void main(String args[]){
  //MongoDBDataStoreUtilities.insertReview("samsung","bsharma","Samsung- Pavilion x360","1","10-11-1985","excllent");
MongoDBDataStoreUtilities.topFivemostLikedProducts();
}

}
