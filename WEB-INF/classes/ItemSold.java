public class ItemSold{
  Integer id;
  Integer price;
  String short_desc;
  ItemSold(Integer id,String short_desc,Integer price){
    this.id = id;
    this.short_desc = short_desc;
    this.price = price;
  }
 Integer getId(){
   return id;
 }
 String getShort_desc(){
   return short_desc;
 }
  Integer getPrice(){
    return price;
  }
}
